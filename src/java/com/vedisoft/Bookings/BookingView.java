/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vedisoft.Bookings;

import com.vedisoft.Guest.Guest;
import com.vedisoft.Guest.GuestDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 *
 * @author vedisoft
 */
public class BookingView extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        String un = (String) session.getAttribute("user");
        String pwd = (String) session.getAttribute("pass");
        if (un == null && pwd == null) {
            response.sendRedirect("Login");
        }
        try {
            out.println("<html>");
            out.println(" <head>");
            out.println("<link rel=\"stylesheet\" href=\"images/HarvestField.css\" type=\"text/css\" />");
            out.println("</head>");
            out.println("<title>Vedisoft: Java Web and Enterprise Technologies</title>");
            out.println(" </head>");
            out.println(" <body>");
            out.println("<div id=\"wrap\">");
            RequestDispatcher dispatcher;
            dispatcher = request.getRequestDispatcher("/design/header.html");
            dispatcher.include(request, response);
            out.println("<div id=\"content-wrap\">");
            dispatcher = request.getRequestDispatcher("/design/operationsidebar.html");
            dispatcher.include(request, response);
            out.println("<div id=\"main\">");
            out.println("<h3> Booking  Details</h3>");
            out.println("<a href=\"BookingAdd\">");
            out.println("<font size=\"3\">Add Booking</font>");
            out.println("</a>");
            out.println("<table>");
            out.println("<tr><th class=\"first\"> Number of Rooms</th> <th>Guest Name</th><th>Amount</th><th>Status</th><th colspan=\"2\"></th></tr>");
            BookingsDAO bd = new BookingsDAO();
            Bookings bean = new Bookings();
            GuestDAO gd = new GuestDAO();
            Calendar cal = Calendar.getInstance();
            Date current = cal.getTime();
            SimpleDateFormat adf = new SimpleDateFormat("yyyy-MM-dd");
            String curr = adf.format(current);
            ArrayList<Integer> list = bd.findAllByDate(curr);
            for (Integer i : list) {
                bean = bd.find(i.intValue());
                out.println("<tr class=\"row-a\">");
                out.println("<td>" + bean.getRoomNo() + "</td>");
                Guest g = gd.find(bean.getGuestId());
                out.println("<td>" + g.getGuestName() + "</td>");
                out.println("<td>" + bean.getAmount() + "</td>");
                out.println("<td>" + bean.getStatus() + "</td>");
                out.println("</tr>");
            }
            out.println("</table>");
            out.println("</div></div>");
            dispatcher = request.getRequestDispatcher("/design/footer.html");
            dispatcher.include(request, response);
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");

        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
/*
Vedisoft Software and Education Services Pvt. Ltd.<br/>
275, Zone II, M.P.Nagar,
Bhopal.
Ph: 0755-4076207,208<br/>
Email: contact@vedisoft.com<br/>
Web: www.vedisoft.com<br/>
Courses : Java, .NET, PHP, C/C++, Web Designing
Certifications : OCJP, OCP, CCNA
Major and Minor Training and Projects
 */
