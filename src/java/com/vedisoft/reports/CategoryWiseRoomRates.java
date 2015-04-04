package com.vedisoft.reports;

import com.vedisoft.Bookings.BookingsDAO;
import com.vedisoft.Rates.Rates;
import com.vedisoft.Rates.RatesDAO;
import com.vedisoft.RoomCategories.RoomCategories;
import com.vedisoft.RoomCategories.RoomCategoriesDAO;
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
public class CategoryWiseRoomRates extends HttpServlet {

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
            dispatcher = request.getRequestDispatcher("/design/reportssidebar.html");
            dispatcher.include(request, response);
            out.println("<div id=\"main\">");
            RatesDAO rad = new RatesDAO();
            RoomCategoriesDAO rcd = new RoomCategoriesDAO();
            ArrayList<RoomCategories> al = rcd.findAll();
            int category = 0;
            out.println("<table border=\"3\" width=\"30\" cellspacing=\"5\" cellpadding=\"2\"><tr> <th>Category Name</th><th>From Date</th><th>To Date</th><th>Tax </th> <th>Rate</th></tr>");
            for (RoomCategories rc : al) {
                category = rc.getRcategoryId();
                out.println("<tr><td><font color=\"red\">" + rc.getRcategoryName() + "</font></td><td></td><td></td><td></td><td></td>");
                out.println("</tr>");
                ArrayList<Rates> ar = rad.findRatesForCategory(category);
                for (Rates r : ar) {
                    out.println("<tr><td>   </td><td>" + BookingsDAO.convertDate2(r.getFromDate()) + "</td><td>" + BookingsDAO.convertDate2(r.getToDate()) + "</td><td>" + r.getTax() + "</td><td>" + r.getRate() + "</td></tr>");
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
