/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vedisoft.reports;

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
public class DateWiseCollections extends HttpServlet {

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
            out.println("<link rel=\"stylesheet\" href=\"images/HarvestField.css\" type=\"text/css\" />");
            out.println("<link rel=\"stylesheet\" href=\"images/calender.css\" type=\"text/css\" />");
            out.println("<link rel=\"stylesheet\" href=\"images/epoch_styles.css\" type=\"text/css\" />");
            out.println("<script language=\"JavaScript\">");
            out.println("var dp_cal1");
            out.println("var dp_cal2");
            out.println("window.onload = function () {");
            out.println(" dp_cal2  = new Epoch('epoch_popup','popup',document.getElementById('popup_container1'));");
            out.println(" dp_cal2  = new Epoch('epoch_popup','popup',document.getElementById('popup_container2'));};");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/validate.js");
            dispatcher.include(request, response);
            dispatcher = request.getRequestDispatcher("/images/epoch_classes.js");
            dispatcher.include(request, response);
            out.println("</script>");
            out.println("</head>");
            out.println("<title>Vedisoft: Java Web and Enterprise Technologies</title>");
            out.println(" </head>");
            out.println(" <body>");
            out.println("<div id=\"wrap\">");
            dispatcher = request.getRequestDispatcher("/design/header.html");
            dispatcher.include(request, response);
            out.println("<div id=\"content-wrap\">");
            dispatcher = request.getRequestDispatcher("/design/reportssidebar.html");
            dispatcher.include(request, response);
            out.println("<div id=\"main\">");
            out.println("<form name=\"thisform\" action=\"DateWiseCollections\">");
            out.println("Enter From When: &nbsp &nbsp");
            out.println("<input type=\"text\" name=\"fromdate\" id=\"popup_container1\"></br>");
            out.println("Enter Till Date: &nbsp &nbsp");
            out.println("<input type=\"text\" name=\"todate\" id=\"popup_container2\"></br>");
            out.println("<input type=\"radio\" group=\"option\" checked=\"true\" name=\"option\" value=\"daily\">Daily ");
            out.println("<input type=\"radio\" group=\"option\" name=\"option\" value=\"weekly\">Weekly");
            out.println("<input type=\"radio\" group=\"option\" name=\"option\" value=\"monthly\">Monthly</br>");
            out.println("<input type=\"submit\" value=\"Find\">");
            out.println("</form>");
            String fromDate = request.getParameter("fromdate");
            String toDate = request.getParameter("todate");
            String option = request.getParameter("option");
            if (fromDate != null && toDate != null && option != null) {
                System.out.println("HERE mONTHLYGDGDH");
                fromDate = BookingsDAO.convertDate(fromDate);
                toDate = BookingsDAO.convertDate(toDate);
                BookingsDAO bd = new BookingsDAO();
                GuestDAO gd = new GuestDAO();
                Guest g = new Guest();
                ArrayList<Bookings> ab = new ArrayList<Bookings>();
                if (option.equals("daily")) {
                    ab = bd.findBookingsBetweenDates(fromDate, toDate);
                } else if (option.equals("monthly")) {
                    ab = bd.findBookingsBetweenDatesByMonth(fromDate, toDate);
                } else if (option.equals("weekly")) {
                    ab = bd.findBookingsBetweenDatesByWeek(fromDate, toDate);
                }
                out.println("<table><tr><th>Booking Date</th><th>Gross Collections</th></tr>");
                for (Bookings b : ab) {
                    out.println("<tr><td>" + b.getBookingDate() + "</td><td>" + b.getAmount() + "</td></tr>");
                }
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
