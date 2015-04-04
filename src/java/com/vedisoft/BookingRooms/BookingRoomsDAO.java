/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vedisoft.BookingRooms;

import com.mysql.jdbc.*;
import com.vedisoft.utilities.ConnectionPool;
import java.util.ArrayList;

/**
 *
 * @author vedisoft
 */
public class BookingRoomsDAO {

    ConnectionPool c = null;
    Connection conn = null;

    public void create(BookingRooms brBean) {
        try {
            if (c == null) {
                c = ConnectionPool.getInstance();
                c.initialize();
            }
            conn = (Connection) c.getConnection();
            String sql = "Insert into BookingRooms (bookingid,roomno)" + " values(?,?)";
            PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setInt(1, brBean.getBookingId());
            pstmt.setInt(2, brBean.getRoomNo());

            pstmt.executeUpdate();
            c.putConnection(conn);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {

            c.putConnection(conn);


        }
    }

    public ArrayList<BookingRooms> findAll() {
        ArrayList<BookingRooms> al = new ArrayList<BookingRooms>();
        try {
            if (c == null) {
                c = ConnectionPool.getInstance();
                c.initialize();
            }
            conn = (Connection) c.getConnection();
            String sql = "select bookingid,roomno from BookingRooms";
            PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);
            ResultSet rs = (ResultSet) pstmt.executeQuery();
            while (rs.next()) {
                BookingRooms brBean = new BookingRooms();
                brBean.setBookingId(rs.getInt("bookingid"));
                brBean.setRoomNo(rs.getInt("roomno"));
                al.add(brBean);
            }
            c.putConnection(conn);
        } catch (Exception e) {
            System.out.println("Exception " + e);
        } finally {
            c.putConnection(conn);

        }
        return al;
    }

    public ArrayList<Integer> findAll(int bookingid) {
        ArrayList<Integer> al = new ArrayList<Integer>();
        try {
            if (c == null) {
                c = ConnectionPool.getInstance();
                c.initialize();
            }
            conn = (Connection) c.getConnection();
            String sql = "select roomno from BookingRooms where bookingid=" + bookingid;
            PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);
            ResultSet rs = (ResultSet) pstmt.executeQuery();
            while (rs.next()) {
                al.add(rs.getInt("roomno"));
            }
            c.putConnection(conn);
        } catch (Exception e) {
            System.out.println("Exception " + e);
        } finally {
            c.putConnection(conn);

        }
        return al;
    }

    public ArrayList<Integer> findAllBookings(int bookingid) {
        ArrayList<Integer> al = new ArrayList<Integer>();
        try {
            if (c == null) {
                c = ConnectionPool.getInstance();
                c.initialize();
            }
            conn = (Connection) c.getConnection();
            String sql = "select id from BookingRooms where bookingid=" + bookingid;
            PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);
            ResultSet rs = (ResultSet) pstmt.executeQuery();
            while (rs.next()) {
                BookingRooms brBean = new BookingRooms();
                al.add(rs.getInt("id"));
            }
            c.putConnection(conn);
        } catch (Exception e) {
            System.out.println("Exception " + e);
        } finally {
            c.putConnection(conn);

        }
        return al;
    }

    public ArrayList<Integer> findAllBookedRooms(int roomno) {
        ArrayList<Integer> al = new ArrayList<Integer>();
        try {
            if (c == null) {
                c = ConnectionPool.getInstance();
                c.initialize();
            }
            conn = (Connection) c.getConnection();
            String sql = "select bookingid from BookingRooms where roomno=" + roomno;
            PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);
            ResultSet rs = (ResultSet) pstmt.executeQuery();
            while (rs.next()) {
                BookingRooms brBean = new BookingRooms();
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

    public void remove(int id) {
        try {
            if (c == null) {
                c = ConnectionPool.getInstance();
                c.initialize();
            }
            conn = (Connection) c.getConnection();
            String sql = "delete from BookingRooms where id=?";
            PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            c.putConnection(conn);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {

            c.putConnection(conn);


        }
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
