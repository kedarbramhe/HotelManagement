/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vedisoft.Rates;

import com.vedisoft.Bookings.BookingsDAO;
import com.vedisoft.RoomCategories.RoomCategories;
import com.vedisoft.RoomCategories.RoomCategoriesDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

/**
 *
 * @author vedisoft
 */
public class RatesView extends HttpServlet {

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
            dispatcher = request.getRequestDispatcher("/design/mastersidebar.html");
            dispatcher.include(request, response);
            out.println("<div id=\"main\">");
            out.println(" <h3>Rates</h3>");
            out.println("<a href=\"RatesAdd\">");
            out.println("<font size=\"3\">ADD Rates</font>");
            out.println("</a>");
            out.println("<form name=\"thisform\" action=\"RatesView\">");
            out.println("Select Category:<br>");
            out.println("<select name=\"rcategoryid\" size=1>");
            RoomCategoriesDAO rc = new RoomCategoriesDAO();
            ArrayList<RoomCategories> ar = rc.findAll();
            for (RoomCategories r : ar) {
                out.println("<option name=" + r.getRcategoryId() + " value=" + r.getRcategoryId() + ">");
                out.println(r.getRcategoryName());
                out.println("</option>");
            }
            out.println("</select>");
            out.println("<input type=\"submit\" value=\"Show\">");
            out.println("</form>");
            String rcat = request.getParameter("rcategoryid");
            if (rcat != null) {
                int rcategoryid = Integer.parseInt(rcat);
                out.println("<table>");
                out.println("<tr><th>From Date</th> <th>To Date</th> <th>Tax </th><th>Rate</th><th colspan=\"2\"></th></tr>");
                RatesDAO rd = new RatesDAO();
                ArrayList<Rates> list = rd.findRatesForCategory(rcategoryid);
                for (Rates bean : list) {
                    out.println("<tr>");
                    out.println("<td>" + BookingsDAO.convertDate1(bean.getFromDate()) + "</td>");
                    out.println("<td>" + BookingsDAO.convertDate1(bean.getToDate()) + "</td>");
                    out.println("<td>" + bean.getTax() + "</td>");
                    out.println("<td>" + bean.getRate() + "</td>");
                    out.println("<td><a href=\"RatesUpdate?rateid=" + bean.getRateId() + "\">Edit</a></td>");
                    out.println("<td><a href=\"RatesForm?opn=delete&rateid=" + bean.getRateId() + "\">Delete</a></td>");
                    out.println("</tr>");
                }
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