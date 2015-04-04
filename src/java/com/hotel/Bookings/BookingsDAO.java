/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vedisoft.Bookings;

import com.vedisoft.BookingRooms.BookingRoomsDAO;
import com.vedisoft.utilities.ConnectionPool;
import com.vedisoft.Rooms.Rooms;
import com.vedisoft.Rooms.RoomsDAO;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


/**
 *
 * @author vedisoft
 */
public class BookingsDAO {

    ConnectionPool c = null;
    Connection conn = null;

    public void create(Bookings bookingsBean) {
        try {
            if (c == null) {
                c = ConnectionPool.getInstance();
                c.initialize();
            }
            conn = c.getConnection();
            String sql = "Insert into bookings (BookingDate,RoomNo,CheckInDate,CheckOutDate,GuestId,Advance,Amount,Tax,Status)" + " values(?,?,?,?,?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, bookingsBean.getBookingDate());
            pstmt.setInt(2, bookingsBean.getRoomNo());
            pstmt.setString(3, bookingsBean.getCheckInDate());
            pstmt.setString(4, bookingsBean.getCheckOutDate());
            pstmt.setInt(5, bookingsBean.getGuestId());
            pstmt.setFloat(6, bookingsBean.getAdvance());
            pstmt.setFloat(7, bookingsBean.getAmount());
            pstmt.setFloat(8, bookingsBean.getTax());
            pstmt.setString(9, bookingsBean.getStatus());
            pstmt.executeUpdate();
            c.putConnection(conn);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {

            c.putConnection(conn);


        }
    }

    public void edit(Bookings bookingBean) {
        try {

            if (c == null) {
                c = ConnectionPool.getInstance();
                c.initialize();
            }
            conn = c.getConnection();
            String sql = "update bookings set bookingdate=? , roomno=? ,checkinDate=?,checkoutDate=?,guestid=?,advance=?,amount=?,tax=?,status=? where bookingid=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, bookingBean.getBookingDate());
            pstmt.setInt(2, bookingBean.getRoomNo());
            pstmt.setString(3, bookingBean.getCheckInDate());
            pstmt.setString(4, bookingBean.getCheckOutDate());
            pstmt.setInt(5, bookingBean.getGuestId());
            pstmt.setFloat(6, bookingBean.getAdvance());
            pstmt.setFloat(7, bookingBean.getAmount());
            pstmt.setFloat(8, bookingBean.getTax());
            pstmt.setString(9, bookingBean.getStatus());
            pstmt.setInt(10, bookingBean.getBookingId());
            pstmt.executeUpdate();
            c.putConnection(conn);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {

            c.putConnection(conn);


        }
    }

    public void remove(int id) {
        try {
            if (c == null) {
                c = ConnectionPool.getInstance();
                c.initialize();
            }
            conn = c.getConnection();
            String sql = "delete from bookings where bookingid=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            c.putConnection(conn);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {

            c.putConnection(conn);


        }
    }

    public ArrayList<Bookings> findAll() {
        ArrayList<Bookings> al = new ArrayList<Bookings>();
        try {
            if (c == null) {
                c = ConnectionPool.getInstance();
                c.initialize();
            }
            conn = c.getConnection();
            String sql = "select bookingid,BookingDate,RoomNo,CheckInDate,CheckOutDate,GuestId,Advance,Amount,Tax,Status from bookings";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Bookings bookingBean = new Bookings();
                bookingBean.setBookingId(rs.getInt("bookingid"));
                bookingBean.setBookingDate(rs.getString("bookingdate"));
                bookingBean.setRoomNo(rs.getInt("roomno"));
                bookingBean.setCheckInDate(rs.getString("checkindate"));
                bookingBean.setCheckOutDate(rs.getString("checkoutdate"));
                bookingBean.setGuestId(rs.getInt("guestid"));
                bookingBean.setAdvance(rs.getFloat("advance"));
                bookingBean.setAmount(rs.getFloat("amount"));
                bookingBean.setTax(rs.getFloat("tax"));
                bookingBean.setStatus(rs.getString("status"));

                al.add(bookingBean);
            }
            c.putConnection(conn);
        } catch (Exception e) {
            System.out.println("Exception " + e);
        } finally {
            c.putConnection(conn);

        }
        return al;
    }

    public ArrayList<Bookings> findBookingsBetweenDates() {
        ArrayList<Bookings> al = new ArrayList<Bookings>();
        try {
            if (c == null) {
                c = ConnectionPool.getInstance();
                c.initialize();
            }
            conn = c.getConnection();
            java.util.Date current = BookingsDAO.getCurrentDate();
            SimpleDateFormat adf = new SimpleDateFormat("yyyy-MM-dd");
            String curr = adf.format(current);
            String sql = "select bookingid,BookingDate,RoomNo,CheckInDate,CheckOutDate,GuestId,Advance,Amount,Tax,Status from bookings where '" + curr + "' between checkindate and checkoutdate";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Bookings bookingBean = new Bookings();
                bookingBean.setBookingId(rs.getInt("bookingid"));
                bookingBean.setBookingDate(rs.getString("bookingdate"));
                bookingBean.setRoomNo(rs.getInt("roomno"));
                bookingBean.setCheckInDate(rs.getString("checkindate"));
                bookingBean.setCheckOutDate(rs.getString("checkoutdate"));
                bookingBean.setGuestId(rs.getInt("guestid"));
                bookingBean.setAdvance(rs.getFloat("advance"));
                bookingBean.setAmount(rs.getFloat("amount"));
                bookingBean.setTax(rs.getFloat("tax"));
                bookingBean.setStatus(rs.getString("status"));
                al.add(bookingBean);
            }
            c.putConnection(conn);
        } catch (Exception e) {
            System.out.println("Exception " + e);
        } finally {
            c.putConnection(conn);

        }
        return al;
    }

    public ArrayList<Bookings> findBookingsBetweenDates(String fromdate, String todate) {
        ArrayList<Bookings> al = new ArrayList<Bookings>();
        try {
            if (c == null) {
                c = ConnectionPool.getInstance();
                c.initialize();
            }
            conn = c.getConnection();
            java.util.Date current = BookingsDAO.getCurrentDate();
            SimpleDateFormat adf = new SimpleDateFormat("yyyy-MM-dd");
            String curr = adf.format(current);
            String sql = "select bookingid,BookingDate,RoomNo,CheckInDate,CheckOutDate,GuestId,Advance,sum(Amount) as amount,Tax,Status from bookings where bookingdate between '" + fromdate + "' and '" + todate + "' group by Day(bookingdate)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Bookings bookingBean = new Bookings();
                bookingBean.setBookingId(rs.getInt("bookingid"));
                bookingBean.setBookingDate(BookingsDAO.convertDate1(rs.getString("bookingdate")));
                bookingBean.setRoomNo(rs.getInt("roomno"));
                bookingBean.setCheckInDate(rs.getString("checkindate"));
                bookingBean.setCheckOutDate(rs.getString("checkoutdate"));
                bookingBean.setGuestId(rs.getInt("guestid"));
                bookingBean.setAdvance(rs.getFloat("advance"));
                bookingBean.setAmount(rs.getFloat("amount"));
                bookingBean.setTax(rs.getFloat("tax"));
                bookingBean.setStatus(rs.getString("status"));

                al.add(bookingBean);
            }
            c.putConnection(conn);
        } catch (Exception e) {
            System.out.println("Exception " + e);
        } finally {
            c.putConnection(conn);

        }
        return al;
    }

    public ArrayList<Bookings> findBookingsBetweenDatesByMonth(String fromdate, String todate) {
        ArrayList<Bookings> al = new ArrayList<Bookings>();
        try {
            if (c == null) {
                c = ConnectionPool.getInstance();
                c.initialize();
            }
            conn = c.getConnection();
            java.util.Date current = BookingsDAO.getCurrentDate();
            SimpleDateFormat adf = new SimpleDateFormat("yyyy-MM-dd");
            String curr = adf.format(current);
            String sql = "select bookingid,BookingDate,RoomNo,CheckInDate,CheckOutDate,GuestId,Advance,sum(Amount) as amount,Tax,Status from bookings where bookingdate between '" + fromdate + "' and '" + todate + "' group by Month(bookingdate) order by Month(bookingdate)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Bookings bookingBean = new Bookings();
                bookingBean.setBookingId(rs.getInt("bookingid"));
                String bdate = BookingsDAO.convertDateMonth(rs.getString("bookingdate"));
                bookingBean.setBookingDate(bdate);
                bookingBean.setRoomNo(rs.getInt("roomno"));
                bookingBean.setCheckInDate(rs.getString("checkindate"));
                bookingBean.setCheckOutDate(rs.getString("checkoutdate"));
                bookingBean.setGuestId(rs.getInt("guestid"));
                bookingBean.setAdvance(rs.getFloat("advance"));
                bookingBean.setAmount(rs.getFloat("amount"));
                bookingBean.setTax(rs.getFloat("tax"));
                bookingBean.setStatus(rs.getString("status"));

                al.add(bookingBean);
            }
            c.putConnection(conn);
        } catch (Exception e) {
            System.out.println("Exception " + e);
        } finally {
            c.putConnection(conn);

        }
        return al;
    }

    public ArrayList<Bookings> findBookingsBetweenDatesByWeek(String fromdate, String todate) {
        ArrayList<Bookings> al = new ArrayList<Bookings>();
        try {
            if (c == null) {
                c = ConnectionPool.getInstance();
                c.initialize();
            }
            conn = c.getConnection();
            java.util.Date current = BookingsDAO.getCurrentDate();
            SimpleDateFormat adf = new SimpleDateFormat("yyyy-MM-dd");
            String curr = adf.format(current);
            String sql = "select bookingid,BookingDate,RoomNo,CheckInDate,CheckOutDate,GuestId,Advance,sum(Amount) as amount,Tax,Status from bookings where bookingdate between '" + fromdate + "' and '" + todate + "' group by Week(bookingdate) order by Week(bookingdate)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Bookings bookingBean = new Bookings();
                bookingBean.setBookingId(rs.getInt("bookingid"));
                bookingBean.setBookingDate(convertDateWeek(rs.getString("bookingdate")));
                bookingBean.setRoomNo(rs.getInt("roomno"));
                bookingBean.setCheckInDate(rs.getString("checkindate"));
                bookingBean.setCheckOutDate(rs.getString("checkoutdate"));
                bookingBean.setGuestId(rs.getInt("guestid"));
                bookingBean.setAdvance(rs.getFloat("advance"));
                bookingBean.setAmount(rs.getFloat("amount"));
                bookingBean.setTax(rs.getFloat("tax"));
                bookingBean.setStatus(rs.getString("status"));

                al.add(bookingBean);
            }
            c.putConnection(conn);
        } catch (Exception e) {
            System.out.println("Exception " + e);
        } finally {
            c.putConnection(conn);

        }
        return al;
    }

    public ArrayList<Integer> findAllByDate(String bookingdate) {
        ArrayList<Integer> al = new ArrayList<Integer>();
        try {
            if (c == null) {
                c = ConnectionPool.getInstance();
                c.initialize();
            }
            conn = c.getConnection();
            String sql = "select bookingid from bookings where bookingdate='" + bookingdate + "'";
            System.out.println(sql);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {

                al.add(rs.getInt("bookingid"));
            }
            c.putConnection(conn);
        } catch (Exception e) {
            System.out.println("Exception " + e);
        } finally {
            c.putConnection(conn);

        }
        return al;
    }

    public ArrayList<Integer> findAllTodaysCheckin(String checkindate) {
        ArrayList<Integer> al = new ArrayList<Integer>();
        try {
            if (c == null) {
                c = ConnectionPool.getInstance();
                c.initialize();
            }
            conn = c.getConnection();
            String sql = "select bookingid from bookings where checkindate='" + checkindate + "' and status not like 'paid'";
            System.out.println(sql);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {

                al.add(rs.getInt("bookingid"));
            }
            c.putConnection(conn);
        } catch (Exception e) {
            System.out.println("Exception " + e);
        } finally {
            c.putConnection(conn);

        }
        return al;
    }

    public Bookings find(int id) {
        Bookings bookingBean = new Bookings();

        try {
            if (c == null) {
                c = ConnectionPool.getInstance();
                c.initialize();
            }
            conn = c.getConnection();
            String sql = "select * from bookings where bookingid=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                bookingBean.setBookingId(id);
                bookingBean.setBookingDate(rs.getString("bookingdate"));
                bookingBean.setRoomNo(rs.getInt("roomno"));
                bookingBean.setCheckInDate(rs.getString("checkindate"));
                bookingBean.setCheckOutDate(rs.getString("checkoutdate"));
                bookingBean.setGuestId(rs.getInt("guestid"));
                bookingBean.setAdvance(rs.getFloat("advance"));
                bookingBean.setAmount(rs.getFloat("amount"));
                bookingBean.setTax(rs.getFloat("tax"));
                bookingBean.setStatus(rs.getString("status"));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            c.putConnection(conn);

        }
        return bookingBean;
    }

    public ArrayList<Bookings> findRooms(int id) {
        java.util.Date current = BookingsDAO.getCurrentDate();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        ArrayList<Bookings> al = new ArrayList<Bookings>();
        BookingsDAO bd = new BookingsDAO();
        try {

            if (c == null) {
                c = ConnectionPool.getInstance();
                c.initialize();
            }
            conn = c.getConnection();
            BookingRoomsDAO br = new BookingRoomsDAO();
            ArrayList<Integer> ai = br.findAllBookedRooms(id);
            for (Integer i : ai) {
                Bookings b = bd.find(i.intValue());
                java.util.Date fromdate = df.parse(b.getBookingDate());
                java.util.Date todate = df.parse(b.getCheckOutDate());

                if (current.after(fromdate) || current.compareTo(fromdate) == 0) {
                    al.add(b);
                }
            }
            c.putConnection(conn);

        } catch (Exception e) {
            System.out.println("Exception " + e);
        } finally {
            c.putConnection(conn);

        }
        return al;
    }

    public ArrayList<Rooms> findRoomsEmpty(int id) {
        ArrayList<Bookings> abc = new ArrayList<Bookings>();
        RoomsDAO rd = new RoomsDAO();
        ArrayList<Rooms> notThere = new ArrayList<Rooms>();
        ArrayList<Rooms> ar = rd.findRoomsForCategory(id);
        for (Rooms r : ar) {
            abc = findRooms(r.getRoomNo());
            if (abc.isEmpty()) {
                notThere.add(r);
            }
        }


        return notThere;
    }

    public int findDateDifference(int id) {
        int diff = 0;
        try {
            if (c == null) {
                c = ConnectionPool.getInstance();
                c.initialize();
            }
            conn = c.getConnection();
            String sql = "select datediff(checkoutdate,checkindate) from bookings where bookingid=" + id;
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                diff = rs.getInt(1);
            }
            c.putConnection(conn);
        } catch (Exception e) {
            System.out.println("Exception " + e);
        } finally {
            c.putConnection(conn);

        }
        return diff;
    }

    public int findDateDifference1(String fromDate, String toDate) {
        int diff = 0;
        try {
            if (c == null) {
                c = ConnectionPool.getInstance();
                c.initialize();
            }
            conn = c.getConnection();
            String sql = "select datediff ('" + fromDate + "','" + toDate + "') from dual";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                diff = rs.getInt(1);

            }
            c.putConnection(conn);
        } catch (Exception e) {
            System.out.println("Exception " + e);
        } finally {
            c.putConnection(conn);

        }
        return diff;
    }

    public int MaxID() {
        int id = 0;
        try {
            if (c == null) {
                c = ConnectionPool.getInstance();
                c.initialize();
            }
            conn = c.getConnection();
            String sql = "select max(bookingid) from  bookings";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            c.putConnection(conn);

        }
        return id;
    }

    public static java.util.Date getCurrentDate() {
        Calendar cal = Calendar.getInstance();
        java.util.Date current = cal.getTime();
        return current;
    }

    public static String convertDate(String date) {
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String convertedDate = new String();

        DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date d = new java.util.Date();
        try {

            d = df.parse(date);
            convertedDate = df1.format(d);
        } catch (ParseException ex) {
        }
        return convertedDate;
    }

    public static String convertDate1(String date) {
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String convertedDate = new String();

        DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date d = new java.util.Date();
        try {

            d = df1.parse(date);
            convertedDate = df.format(d);
        } catch (ParseException ex) {
        }
        return convertedDate;
    }

    public static String convertDate2(String date) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String convertedDate = new String();

        DateFormat df1 = new SimpleDateFormat("dd-MMMM");
        java.util.Date d = new java.util.Date();
        try {

            d = df.parse(date);
            convertedDate = df1.format(d);
        } catch (ParseException ex) {
        }
        return convertedDate;
    }

    public static String convertDateMonth(String date) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String convertedDate = new String();

        DateFormat df1 = new SimpleDateFormat("MMMM");
        java.util.Date d = new java.util.Date();
        try {

            d = df.parse(date);
            convertedDate = df1.format(d);
        } catch (ParseException ex) {
        }
        return convertedDate;
    }

    public static String convertDateWeek(String d) {
        String interval = new String();
        try {
            java.util.Date date, date1;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            date = sdf.parse(d);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            while (cal.get(Calendar.DAY_OF_WEEK) > cal.getActualMinimum(Calendar.DAY_OF_WEEK_IN_MONTH)) {
                cal.add(Calendar.DATE, -1);
            }
            date = cal.getTime();
            String d1 = sdf.format(date);
            cal.add(Calendar.DATE, 6);
            date1 = cal.getTime();
            String d2 = sdf.format(date1);
            interval = convertDate2(d1) + "-" + convertDate2(d2);
            return interval;
        } catch (ParseException ex) {
            
        }
        return interval;
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
