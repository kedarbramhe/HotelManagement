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
public class BookingUpdate extends HttpServlet {

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
            out.println("<link rel=\"stylesheet\" href=\"images/calender.css\" type=\"text/css\" />");
            out.println("<link rel=\"stylesheet\" href=\"images/epoch_styles.css\" type=\"text/css\" />");
            out.println("<script language=\"JavaScript\">");
            out.println("var dp_cal1");
            out.println("var dp_cal2");
            out.println("window.onload = function () {");
            out.println(" dp_cal1  = new Epoch('epoch_popup','popup',document.getElementById('popup_container1'));");
            out.println(" dp_cal2  = new Epoch('epoch_popup','popup',document.getElementById('popup_container2'));};");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/validate.js");
            dispatcher.include(request, response);
            dispatcher = request.getRequestDispatcher("/images/epoch_classes.js");
            dispatcher.include(request, response);
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
            out.println(" <form method=\"post\" action=\"BookingForm?opn=Update\"  onSubmit=\"return validate(this);\">");
            int id = Integer.parseInt(request.getParameter("bookingId"));
            BookingsDAO b3 = new BookingsDAO();
            Bookings b = b3.find(id);
            out.println(" <input type=\"hidden\" value=\"" + id + "\" name=\"bookingId\"/>");
            out.println("  <b>Room No  : </b>    <input type=\"text\" name=\"norooms\" value=\"" + b.getRoomNo() + "\"/></br>");
            out.println("<b> Check In Date  : </b>    <input type=\"text\" name=\"checkInDate\"  id=\"popup_container1\" value=\"" + BookingsDAO.convertDate1(b.getCheckInDate()) + "\"/> <br/>");
            out.println("<b> Check out Date  : </b>    <input type=\"text\" name=\"checkOutDate\" id=\"popup_container2\"  value=\"" + BookingsDAO.convertDate1(b.getCheckOutDate()) + "\"/> <br/>");
            out.println("  <b>Guest Name : </b>    <select name=\"guestId\" size=\"1\">");
            GuestDAO gd = new GuestDAO();
            ArrayList<Guest> gl = new ArrayList<Guest>();
            gl = gd.findAll();
            for (Guest g1 : gl) {
                if (g1.getGuestId() == b.getGuestId()) {
                    out.println("<option value=\"" + g1.getGuestId() + "\" selected=\"true\">" + g1.getGuestName() + " </option>");
                } else {
                    out.println("<option value=\"" + g1.getGuestId() + "\" >" + g1.getGuestName() + " </option>");
                }
            }
            out.println("</select><br/>");
            out.println(" <b> Advance  : </b>    <input type=\"text\" name=\"advance\" value=\"" + b.getAdvance() + "\"/> <br/>");
            if (b.getStatus().equals("Paid")) {
                out.println("<b> Status  : </b>    <input type=\"radio\" group=\"status\" name=\"status\" checked=\"true\" value=\"Paid\"/>Paid");
                out.println("<input type=\"radio\" group=\"status\" name=\"status\" value=\"Not Paid\"/>Not Paid <br/>");
            } else {
                out.println("<b> Status  : </b>    <input type=\"radio\" group=\"status\" name=\"status\"  value=\"Paid\"/>Paid");
                out.println("<input type=\"radio\" group=\"status\" name=\"status\" checked=\"true\" value=\"Not Paid\"/>Not Paid <br/>");
            }
            out.println(" <input type=\"submit\" value=\"Update\"/>");
            out.println("<input type=\"hidden\" name=\"amount\" value=\"" + b.getAmount() + "\">");
            out.println("    <input type=\"button\" value=\"Cancel\" onClick=\"history.go(-1)\"/>");
            out.println(" </form>");
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
