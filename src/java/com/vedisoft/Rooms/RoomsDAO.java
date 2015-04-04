/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vedisoft.Rooms;

import com.vedisoft.utilities.ConnectionPool;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author vedisoft
 */
public class RoomsDAO {

    ConnectionPool c = null;
    Connection conn = null;

    public void create(Rooms roomsBean) {
        try {

            if (c == null) {
                c = ConnectionPool.getInstance();
                c.initialize();
            }
            conn = c.getConnection();
            String sql = "insert into  Rooms (roomNo,floorNo,RcategoryId)" + " values(?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, roomsBean.getRoomNo());
            pstmt.setInt(2, roomsBean.getFloorNo());
            pstmt.setInt(3, roomsBean.getRcategoryId());
            pstmt.executeUpdate();
            c.putConnection(conn);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void edit(Rooms roomBean) {
        try {
            Connection conn = null;
            if (c == null) {
                c = ConnectionPool.getInstance();
                c.initialize();
            }
            conn = c.getConnection();
            String sql = "update Rooms set floorNo=? , RcategoryId=? where roomNo=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, roomBean.getFloorNo());
            pstmt.setInt(2, roomBean.getRcategoryId());
            pstmt.setInt(3, roomBean.getRoomNo());
            pstmt.executeUpdate();
            c.putConnection(conn);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void remove(int id) {
        try {
            if (c == null) {
                c = ConnectionPool.getInstance();
                c.initialize();
            }
            conn = c.getConnection();
            String sql = "delete from Rooms where roomNo=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            c.putConnection(conn);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<Rooms> findAll() {
        ArrayList<Rooms> al = new ArrayList<Rooms>();
        try {
            Connection conn = null;
            if (c == null) {
                c = ConnectionPool.getInstance();
                c.initialize();
            }
            conn = c.getConnection();
            String sql = "select roomNo,floorNo,RcategoryId from Rooms";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Rooms roomBean = new Rooms();
                roomBean.setRoomNo(rs.getInt(1));
                roomBean.setFloorNo(rs.getInt(2));
                roomBean.setRcategoryId(rs.getInt(3));
                al.add(roomBean);
            }
            c.putConnection(conn);
        } catch (Exception e) {
            System.out.println("Exception " + e);
        }
        return al;
    }

    public ArrayList<Rooms> findRoomsForCategory(int id) {
        ArrayList<Rooms> al = new ArrayList<Rooms>();
        try {
            Connection conn = null;
            if (c == null) {
                c = ConnectionPool.getInstance();
                c.initialize();
            }
            conn = c.getConnection();
            String sql = "select roomNo,floorno from Rooms where rcategoryid=" + id;
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Rooms roomBean = new Rooms();
                roomBean.setRoomNo(rs.getInt(1));
                roomBean.setFloorNo(rs.getInt(2));
                al.add(roomBean);
            }
            c.putConnection(conn);
        } catch (Exception e) {
            System.out.println("Exception " + e);
        }
        return al;
    }

    public Rooms find(int id) {
        Rooms roomBean = new Rooms();
        try {
            if (c == null) {
                c = ConnectionPool.getInstance();
                c.initialize();
            }
            conn = c.getConnection();
            String sql = "select * from Rooms where roomNo=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                roomBean.setRoomNo(rs.getInt("roomNo"));
                roomBean.setFloorNo(rs.getInt("floorNo"));
                roomBean.setRcategoryId(rs.getInt("RcategoryId"));
            }
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            c.putConnection(conn);
        }
        return roomBean;
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
