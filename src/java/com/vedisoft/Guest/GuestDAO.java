/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vedisoft.Guest;

import com.vedisoft.utilities.ConnectionPool;
import java.sql.*;
import java.sql.PreparedStatement;
import java.util.ArrayList;

/**
 *
 * @author vedisoft
 */
public class GuestDAO {
    ConnectionPool c = null;
    Connection conn = null;

    public void create(Guest guestBean) {
        try {
            if (c == null) {
                c = ConnectionPool.getInstance();
                c.initialize();
            }
            conn = c.getConnection();
            String sql = "Insert into guest (guestname,age,gender,primary1,bookingid,mobile)" + " values(?,?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, guestBean.getGuestName());
            pstmt.setInt(2, guestBean.getAge());
            pstmt.setString(3, guestBean.getGender());
            pstmt.setString(4, guestBean.getPrimary());
            pstmt.setInt(5, guestBean.getBookingId());
            pstmt.setString(6, guestBean.getMobile());
            pstmt.executeUpdate();
            c.putConnection(conn);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {

            c.putConnection(conn);
        }
    }

    public void edit(Guest guestBean) {
        try {

            if (c == null) {
                c = ConnectionPool.getInstance();
                c.initialize();
            }
            conn = c.getConnection();
            String sql = "update guest set guestname=? , age=? ,gender=?,primary1=?,bookingid=?,mobile=? where guestid=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, guestBean.getGuestName());
            pstmt.setInt(2, guestBean.getAge());
            pstmt.setString(3, guestBean.getGender());
            pstmt.setString(4, guestBean.getPrimary());
            pstmt.setInt(5, guestBean.getBookingId());
            pstmt.setString(6, guestBean.getMobile());
            pstmt.setInt(7, guestBean.getGuestId());

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
            String sql = "delete from guest where guestid=?";
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

    public ArrayList<Guest> findAll() {
        ArrayList<Guest> al = new ArrayList<Guest>();
        try {
            if (c == null) {
                c = ConnectionPool.getInstance();
                c.initialize();
            }
            conn = c.getConnection();
            String sql = "select guestid,guestname,age,gender,primary1,bookingid,mobile from guest";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Guest guestBean = new Guest();
                guestBean.setGuestId(rs.getInt("guestid"));
                guestBean.setGuestName(rs.getString("guestname"));
                guestBean.setAge(rs.getInt("age"));
                guestBean.setGender(rs.getString("gender"));
                guestBean.setPrimary(rs.getString("primary1"));
                guestBean.setBookingId(rs.getInt("bookingid"));
                guestBean.setMobile(rs.getString("mobile"));

                al.add(guestBean);
            }
            c.putConnection(conn);
        } catch (Exception e) {
            System.out.println("Exception " + e);
        } finally {
            c.putConnection(conn);

        }
        return al;
    }

    public ArrayList<Integer> findGuestsForBooking(int bookingid) {
        ArrayList<Integer> al = new ArrayList<Integer>();
        try {
            if (c == null) {
                c = ConnectionPool.getInstance();
                c.initialize();
            }
            conn = c.getConnection();
            String sql = "select guestid from guest where bookingid=" + bookingid;
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                al.add(rs.getInt("guestid"));
            }
            c.putConnection(conn);
        } catch (Exception e) {
            System.out.println("Exception " + e);
        } finally {
            c.putConnection(conn);

        }
        return al;
    }

    public ArrayList<Integer> findPrimaryGuestsForBooking(int bookingid) {
        ArrayList<Integer> al = new ArrayList<Integer>();
        try {
            if (c == null) {
                c = ConnectionPool.getInstance();
                c.initialize();
            }
            conn = c.getConnection();
            String sql = "select guestid from guest where bookingid= " + bookingid + " and primary1 like 'yes';";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                al.add(rs.getInt("guestid"));
            }
            c.putConnection(conn);
        } catch (Exception e) {
            System.out.println("Exception " + e);
        } finally {
            c.putConnection(conn);

        }
        return al;
    }

    public Guest find(int id) {
        Guest guestBean = new Guest();

        try {
            if (c == null) {
                c = ConnectionPool.getInstance();
                c.initialize();
            }
            conn = c.getConnection();
            String sql = "select * from guest where guestid=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                guestBean.setGuestId(id);
                guestBean.setGuestName(rs.getString("guestname"));
                guestBean.setAge(rs.getInt("age"));
                guestBean.setGender(rs.getString("gender"));
                guestBean.setPrimary(rs.getString("primary1"));
                guestBean.setBookingId(rs.getInt("bookingid"));
                guestBean.setMobile(rs.getString("mobile"));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            c.putConnection(conn);

        }
        return guestBean;
    }

    public ArrayList<Guest> findAll1(String name) {
        ArrayList<Guest> al = new ArrayList<Guest>();
        try {
            if (c == null) {
                c = ConnectionPool.getInstance();
                c.initialize();
            }
            conn = c.getConnection();
            String sql = "select guestid,guestname,age,gender,primary1 from guest where guestname like '" + name + "%'";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Guest guestBean = new Guest();
                guestBean.setGuestId(rs.getInt("guestid"));
                guestBean.setGuestName(rs.getString("guestname"));
                guestBean.setAge(rs.getInt("age"));
                guestBean.setGender(rs.getString("gender"));
                guestBean.setPrimary(rs.getString("primary1"));

                al.add(guestBean);
            }
            c.putConnection(conn);
        } catch (Exception e) {
            System.out.println("Exception " + e);
        } finally {
            c.putConnection(conn);

        }
        return al;
    }

    public int MaxID() {
        int id = 0;
        try {
            if (c == null) {
                c = ConnectionPool.getInstance();
                c.initialize();
            }
            conn = c.getConnection();
            String sql = "select max(guestid) from  guest";
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
