/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vedisoft.Bookings;

import java.io.IOException;
import java.io.PrintWriter;
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
public class CheckoutConfirm extends HttpServlet {

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
            RequestDispatcher dispatcher;
            dispatcher = request.getRequestDispatcher("/WEB-INF/validate.js");
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
            out.println("<h1>Successfully checked out<h1>");
            String bookingid = request.getParameter("bookingid");
            if (bookingid != null) {
                int bid = Integer.parseInt(bookingid);
                BookingsDAO bd = new BookingsDAO();
                Bookings b = bd.find(bid);
                out.println("<table border=\"3\" width=\"30\" cellspacing=\"5\" cellpadding=\"2\"><tr><th>Booking Date</th><th>Check In Date</th><th>Check Out Date</th> <th>Amount</th><th>Status</th> </tr>");
                out.println("<table><tr><td>" + BookingsDAO.convertDate1(b.getBookingDate()) + "</td><td>" + BookingsDAO.convertDate1(b.getCheckInDate()) + "</td><td>" + BookingsDAO.convertDate1(b.getCheckOutDate()) + "</td> <td>" + b.getAmount() + "</td><td>" + b.getStatus() + "</td> </tr>");
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
