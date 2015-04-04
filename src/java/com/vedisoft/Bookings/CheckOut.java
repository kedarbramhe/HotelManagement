/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vedisoft.Bookings;

import com.vedisoft.Guest.Guest;
import com.vedisoft.Guest.GuestDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
public class CheckOut extends HttpServlet {

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
            DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            out.println("<html>");
            out.println(" <head>");
            out.println("<script language=\"JavaScript\">");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/validate.js");
            dispatcher.include(request, response);
            out.println("</script>");
            out.println("<link rel=\"stylesheet\" href=\"images/HarvestField.css\" type=\"text/css\" />");
            out.println("</head>");
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
            out.println("<form name=\"thisform\" action=\"CheckOut1\">");
            out.println("Select Guest Name:");
            out.println("<select name=\"guestid\">");
            BookingsDAO bd = new BookingsDAO();
            ArrayList<Bookings> ab = bd.findBookingsBetweenDates();
            GuestDAO gd = new GuestDAO();
            for (Bookings b : ab) {
                ArrayList<Integer> ag = gd.findPrimaryGuestsForBooking(b.getBookingId());
                if (!b.getStatus().equalsIgnoreCase("paid")) {
                    for (Integer g1 : ag) {
                        Guest g = gd.find(g1.intValue());
                        out.println("<option name=\"" + g.getGuestName() + "\" value=\"" + g.getGuestId() + "\">" + g.getGuestName() + "</option>");
                    }
                }
            }
            out.println("</select>");
            out.println("<input type=\"submit\" value=\"Checkout\">");
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
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
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
