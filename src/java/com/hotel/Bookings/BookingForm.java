/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vedisoft.Bookings;

import com.vedisoft.BookingRooms.BookingRooms;
import com.vedisoft.BookingRooms.BookingRoomsDAO;
import com.vedisoft.Guest.Guest;
import com.vedisoft.Guest.GuestDAO;
import java.util.*;
import com.vedisoft.Rates.Rates;
import com.vedisoft.Rates.RatesDAO;
import com.vedisoft.Rooms.Rooms;
import com.vedisoft.Rooms.RoomsDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.http.*;
/**
 *
 * @author vedisoft
 */
public class BookingForm extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {

            String chk = request.getParameter("chk");
            if (chk == null) {
                chk = "OFF";
            }
            String guestname = request.getParameter("guestname");
            String rcategoryid = request.getParameter("rcategoryid");
            String gender = request.getParameter("gender");
            String age = request.getParameter("age");
            String mobile = request.getParameter("mobile");
            String roomno[] = request.getParameterValues("roomno");
            String chkindt = request.getParameter("checkInDate");
            String chkoutdt = request.getParameter("checkOutDate");
            String advn = request.getParameter("advance");
            String status = request.getParameter("status");
            Calendar cal = Calendar.getInstance();
            Date current = cal.getTime();
            SimpleDateFormat adf = new SimpleDateFormat("yyyy-MM-dd");
            String bookingDate = adf.format(current);
            Guest g = new Guest();
            GuestDAO gd = new GuestDAO();
            Bookings bg = new Bookings();
            BookingsDAO bd = new BookingsDAO();
            if ((roomno != null) && (chkindt != null) && (chkoutdt != null) && (advn != null) && (status != null) && (guestname != null) && (age != null) && (mobile != null) && (gender != null) && (rcategoryid != null) && (chk != null)) {
                int rcatid = Integer.parseInt(rcategoryid);
                g.setAge(Integer.parseInt(age));
                g.setGender(gender);
                g.setGuestName(guestname);
                g.setPrimary("yes");
                g.setMobile(mobile);
                g.setBookingId(0);
                int noOfRooms = roomno.length;
                bg.setRoomNo(noOfRooms);
                String checkindate = BookingsDAO.convertDate(chkindt);
                String checkoutdate = BookingsDAO.convertDate(chkoutdt);
                bg.setCheckInDate(checkindate);
                bg.setCheckOutDate(checkoutdate);
                bg.setAdvance(Float.parseFloat(advn));
                bg.setStatus(status);
                bg.setBookingDate(bookingDate);
                bg.setGuestId(0);
                RoomsDAO rd = new RoomsDAO();
                Rooms r = rd.find(bg.getRoomNo());
                RatesDAO rad = new RatesDAO();
                ArrayList<Rates> al = rad.findRatesBetweenDates(rcatid);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
                float amount = 0.0f;
                float tax = 0.0f;
                for (Rates r1 : al) {
                    int days = bd.findDateDifference1(bg.getCheckOutDate(), bg.getCheckInDate());
                    if (days == 0) {
                        days = 1;
                    }
                    float intermediate = r1.getTax() + r1.getRate();
                    amount = intermediate * days * noOfRooms;
                    tax = r1.getTax();
                }
                bg.setAmount(amount);
                bg.setTax(tax);
            }
            int id = 0;
            BookingRoomsDAO bd1 = new BookingRoomsDAO();
            String opn = request.getParameter("opn");
            if (opn.equals("Add")) {
                gd.create(g);
                bd.create(bg);
                int maxId = gd.MaxID();
                bg.setBookingId(bd.MaxID());
                bg.setGuestId(maxId);
                bd.edit(bg);
                g.setGuestId(maxId);
                g.setBookingId(bd.MaxID());
                gd.edit(g);
                for (String i : roomno) {
                    int no = Integer.parseInt(i);
                    BookingRooms br = new BookingRooms();
                    br.setBookingId(bg.getBookingId());
                    br.setRoomNo(no);
                    bd1.create(br);
                }
                if (chk.equals("ON")) {
                    response.sendRedirect("DocumentAdd?guestid=" + g.getGuestId());

                } else {
                    response.sendRedirect("BookingView");
                }
            } else if (opn.equals("Update")) {
                int nr = Integer.parseInt(request.getParameter("norooms"));
                id = Integer.parseInt(request.getParameter("bookingId"));
                int guestid = Integer.parseInt(request.getParameter("guestId"));
                float amt = Float.parseFloat(request.getParameter("amount"));
                bg.setGuestId(guestid);
                bg.setBookingId(id);
                bg.setRoomNo(nr);
                bg.setAmount(amt);
                String checkindate = BookingsDAO.convertDate(chkindt);
                String checkoutdate = BookingsDAO.convertDate(chkoutdt);
                bg.setCheckInDate(checkindate);
                bg.setCheckOutDate(checkoutdate);
                bg.setAdvance(Float.parseFloat(advn));
                bg.setStatus(status);
                bg.setBookingDate(bookingDate);
                bd.edit(bg);
                response.sendRedirect("BookingView");
            } else if (opn.equals("Delete")) {
                id = Integer.parseInt(request.getParameter("bookingId"));
                bg.setBookingId(id);
                bd.remove(id);
                ArrayList<Integer> abr = bd1.findAllBookings(id);
                for (Integer i1 : abr) {
                    int brid = i1.intValue();
                    bd1.remove(brid);
                }
                ArrayList<Integer> ai = gd.findGuestsForBooking(id);
                for (Integer i : ai) {
                    int guestid = i.intValue();
                    gd.remove(guestid);
                }
                response.sendRedirect("BookingView");
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
