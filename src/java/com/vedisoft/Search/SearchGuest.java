package com.vedisoft.Search;

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
public class SearchGuest extends HttpServlet {

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
            out.println("<title>SearchGuest</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div id=\"wrap\">");
            RequestDispatcher dispatcher;
            dispatcher = request.getRequestDispatcher("/design/header.html");
            dispatcher.include(request, response);
            out.println("<div id=\"content-wrap\">");
            dispatcher = request.getRequestDispatcher("/design/searchsidebar.html");
            dispatcher.include(request, response);
            out.println("<div id=\"main\">");
            out.println("<form name=\"thisForm1\" action=\"SearchGuest\"></br>");
            out.println("Search Guests&nbsp;");
            out.println("<input type=\"text\" name=\"guestname\" ><br/>");
            out.println("<input type=\"submit\" value=\"submit\" >");
            out.println("</form>");
            if (request.getParameter("guestname") != null) {
                GuestDAO gd = new GuestDAO();
                ArrayList<Guest> gl = gd.findAll1(request.getParameter("guestname"));
                out.println("<table><tr><th>Guest Name</th><th>Age</th><th>Gender</th></tr>");
                for (Guest g : gl) {
                    out.println("<tr><td>" + g.getGuestName() + "</td><td>" + g.getAge() + "</td><td>" + g.getGender() + "</td><td><" + g.getPrimary() + "/td></tr>");
                }
                out.println("</table>");
            }
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