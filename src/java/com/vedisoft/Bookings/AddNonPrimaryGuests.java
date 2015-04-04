/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vedisoft.Bookings;

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
public class AddNonPrimaryGuests extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            HttpSession session = request.getSession();
            String un = (String) session.getAttribute("user");
            String pwd = (String) session.getAttribute("pass");
            if (un == null && pwd == null) {
                response.sendRedirect("Login");
            }
            out.println("<html>");
            out.println(" <head>");
            out.println("<script language=\"JavaScript\">");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/validate.js");
            dispatcher.include(request, response);
            out.println("</script>");
            out.println("<link rel=\"stylesheet\" href=\"images/HarvestField.css\" type=\"text/css\" />");
            out.println("</head>");
            out.println("<script language=\"JavaScript\">");
            RequestDispatcher dispatcher1 = request.getRequestDispatcher("/WEB-INF/validate.js");
            dispatcher1.include(request, response);
            out.println("</script>");
            out.println("<title>Vedisoft: Java Web and Enterprise Technologies</title>");
            out.println(" </head>");
            out.println(" <body>");
            out.println("<div id=\"wrap\">");
            dispatcher = request.getRequestDispatcher("/design/header.html");
            dispatcher.include(request, response);
            out.println("<div id=\"content-wrap\">");
            dispatcher = request.getRequestDispatcher("/design/operationsidebar.html");
            dispatcher.include(request, response);
            out.println("<div id=\"main\">");
            String bookingid = request.getParameter("bookingid");
            String name = request.getParameter("guestname");
            String age = request.getParameter("age");
            String mobile = request.getParameter("mobile");
            GuestDAO gd = new GuestDAO();
            if (name != null && age != null && mobile != null && bookingid != null) {
                Guest g = new Guest();
                g.setGuestName(name);
                g.setAge(Integer.parseInt(age));
                g.setMobile(mobile);
                g.setPrimary("NO");
                int id = Integer.parseInt(bookingid);
                g.setBookingId(id);
                g.setGender(request.getParameter("gender"));
                gd.create(g);
                out.println("<h1> Guest Details </h1>");
                out.println("<a href=\"BookingsAddMorGuests?bookingid=" + id + "\">");
                out.println("<font size=\"4\">Add More Guests</font>");
                out.println("</a>");
                out.println("<table>");
                out.println("<tr><th> Guest name</th><th> Age</th><th> Gender</th></tr>");
                GuestDAO rd = new GuestDAO();
                Guest g1 = new Guest();
                ArrayList<Integer> list = rd.findGuestsForBooking(Integer.parseInt(bookingid));
                for (Integer i : list) {
                    g1 = rd.find(i.intValue());
                    out.println("<tr>");
                    out.println("<td>" + g1.getGuestName() + "</td>");
                    out.println("<td>" + g1.getAge() + "</td>");
                    out.println("<td>" + g1.getGender() + "</td>");
                    out.println("</tr>");
                }
                out.println("</table>");
                out.println("</div></div>");
                dispatcher = request.getRequestDispatcher("/design/footer.html");
                dispatcher.include(request, response);
                out.println("</div>");
                out.println("</body>");
                out.println("</html>");
            }
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
