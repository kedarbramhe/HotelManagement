/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vedisoft.Search;

import com.vedisoft.Bookings.Bookings;
import com.vedisoft.Bookings.BookingsDAO;
import com.vedisoft.Guest.Guest;
import com.vedisoft.Guest.GuestDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
public class SearchBooking extends HttpServlet {

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
            out.println("<head>");
            out.println("<link rel=\"stylesheet\" href=\"images/HarvestField.css\" type=\"text/css\" />");
            out.println("<link rel=\"stylesheet\" href=\"images/calender.css\" type=\"text/css\" />");
            out.println("<link rel=\"stylesheet\" href=\"images/epoch_styles.css\" type=\"text/css\" />");
            out.println("<script language=\"JavaScript\">");
            out.println("var dp_cal1");
            out.println("var dp_cal2");
            out.println("window.onload = function () {");
            out.println(" dp_cal2  = new Epoch('epoch_popup','popup',document.getElementById('popup_container1'));};");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/validate.js");
            dispatcher.include(request, response);
            dispatcher = request.getRequestDispatcher("/images/epoch_classes.js");
            dispatcher.include(request, response);
            out.println("</script>");
            out.println("<title>SearchGuest</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div id=\"wrap\">");
            dispatcher = request.getRequestDispatcher("/design/header.html");
            dispatcher.include(request, response);
            out.println("<div id=\"content-wrap\">");
            dispatcher = request.getRequestDispatcher("/design/searchsidebar.html");
            dispatcher.include(request, response);
            out.println("<div id=\"main\">");
            out.println("<form name=\"thisform\" action=\"SearchBooking\">");
            out.println("<b>Enter Booking Date:</b></br>");
            out.println("<input type=\"text\" name=\"bookingdate\" id=\"popup_container1\" >");
            out.println("<input type=\"submit\" value=\"Find\">");
            out.println("</form>");
            String bdate = request.getParameter("bookingdate");
            if (bdate != null) {
                String bookingdate = BookingsDAO.convertDate(bdate);
                out.println("<table <tr><th>CheckIn Date</th> <th>Checkout Date</th> <th>Guest Name</th> <th>Number Of Rooms</th></tr>");
                BookingsDAO bd = new BookingsDAO();
                GuestDAO gd = new GuestDAO();
                ArrayList<Integer> ai = bd.findAllByDate(bookingdate);
                for (Integer i : ai) {
                    int id = i.intValue();
                    Bookings b = new Bookings();
                    b = bd.find(id);
                    Guest g = gd.find(b.getGuestId());
                    out.println("<tr><td>" + BookingsDAO.convertDate1(b.getCheckInDate()) + "</td><td>" + BookingsDAO.convertDate1(b.getCheckOutDate()) + "</td><td>" + g.getGuestName() + "</td><td>" + b.getRoomNo() + "</td></tr>");
                }

            }
            out.println("</table></div></div>");
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