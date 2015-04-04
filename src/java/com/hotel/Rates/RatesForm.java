package com.vedisoft.Rates;

import com.vedisoft.Bookings.BookingsDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *
 * @author vedisoft
 */
public class RatesForm extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String fromdate = request.getParameter("fromdate");
        String todate = request.getParameter("todate");
        String rcategoryid = request.getParameter("rcategoryid");
        String tax = request.getParameter("tax");
        String rate = request.getParameter("rate");
        String frdate = new String();
        String tdate = new String();
        Rates c = new Rates();
        RatesDAO rd = new RatesDAO();
        if ((fromdate != null) && (todate != null) && (rcategoryid != null) && (tax != null)) {
            frdate = BookingsDAO.convertDate(fromdate);
            tdate = BookingsDAO.convertDate(todate);
            c.setFromDate(frdate);
            c.setToDate(tdate);
            c.setTax(Float.parseFloat(tax));
            c.setRate(Float.parseFloat(rate));
            c.setRcategoryId(Integer.parseInt(rcategoryid));
        }
        int id = 0;
        try {
            String opn = request.getParameter("opn");
            if (opn.equals("add")) {
                rd.create(c);
            } else if (opn.equals("update")) {
                id = Integer.parseInt(request.getParameter("rateid"));
                c.setRateId(id);
                rd.edit(c);
            } else if (opn.equals("delete")) {
                id = Integer.parseInt(request.getParameter("rateid"));
                c.setRateId(id);
                rd.remove(id);
            }
            response.sendRedirect("RatesView");

        } finally {
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
