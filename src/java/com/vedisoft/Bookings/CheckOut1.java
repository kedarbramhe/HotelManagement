/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vedisoft.Bookings;

import com.vedisoft.BookingRooms.BookingRoomsDAO;
import com.vedisoft.Guest.Guest;
import com.vedisoft.Guest.GuestDAO;
import com.vedisoft.Rates.Rates;
import com.vedisoft.Rates.RatesDAO;
import com.vedisoft.Rooms.Rooms;
import com.vedisoft.Rooms.RoomsDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
public class CheckOut1 extends HttpServlet {

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
            String guestid = request.getParameter("guestid");
            BookingsDAO bd = new BookingsDAO();
            RequestDispatcher dispatcher;
            if (guestid != null) {
                Date current = bd.getCurrentDate();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String curr = sdf.format(current);
                int id = Integer.parseInt(guestid);
                GuestDAO gd1 = new GuestDAO();
                Guest g = gd1.find(id);
                Bookings b = bd.find(g.getBookingId());
                if (curr.equals(b.getCheckOutDate())) {
                } else {
                    float amt = 0.0f;
                    RoomsDAO rd = new RoomsDAO();
                    Rooms r = rd.find(b.getRoomNo());
                    RatesDAO rt = new RatesDAO();
                    ArrayList<Rates> ar = rt.findRatesBetweenDates(r.getRcategoryId());
                    for (Rates r1 : ar) {
                        int diff = bd.findDateDifference1(curr, b.getCheckInDate());
                        if(diff==0)
                        {diff=1;
                        }
                        amt = (diff * r1.getRate()) - b.getAdvance();
                    }
                    b.setAmount(amt);
                }
                b.setCheckOutDate(curr);
                bd.edit(b);
                out.println("<html>");
                out.println(" <head>");
                out.println("<script language=\"JavaScript\">");
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
                out.println("<form  name=\"myform\" action=\"CheckOut1\">");
                if(b.getAmount()>0)
                {
                    out.println("Your Amount to be paid is: " + b.getAmount());
                }
                else
                {float amt=b.getAmount();
                 amt=-amt;
                    out.println("Hotel Has to Refund: " +amt);
                }
                out.println("<input type=\"hidden\" name=\"bookingid\" value=" + b.getBookingId() + ">");
                out.println("<input type=\"submit\" value=\"Pay\">");
                out.println("</form>");
            }
            String bookingid = request.getParameter("bookingid");
            if (bookingid != null) {
                int bid = Integer.parseInt(bookingid);
                Bookings b1 = new Bookings();
                BookingRoomsDAO brd = new BookingRoomsDAO();
                ArrayList<Integer> ai = brd.findAllBookings(bid);
                for (Integer i : ai) {
                    brd.remove(i.intValue());
                }
                b1 = bd.find(bid);
                b1.setStatus("paid");
                bd.edit(b1);
                response.sendRedirect("CheckoutConfirm?bookingid=" + bid);
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
