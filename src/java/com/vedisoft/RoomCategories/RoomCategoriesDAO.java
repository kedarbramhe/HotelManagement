package com.vedisoft.RoomCategories;

import com.vedisoft.utilities.ConnectionPool;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author vedisoft
 */
public class RoomCategoriesDAO {

    ConnectionPool c = null;
    Connection conn = null;

    public void create(RoomCategories roomCategoryBean) {
        try {
            if (c == null) {
                c = ConnectionPool.getInstance();
                c.initialize();
            }
            conn = c.getConnection();
            String sql = "insert into RoomCategories (rcategoryname,rcategorydetails)" + " values(?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, roomCategoryBean.getRcategoryName());
            pstmt.setString(2, roomCategoryBean.getRcategoryDetails());
            pstmt.executeUpdate();
            c.putConnection(conn);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void edit(RoomCategories roomCategoryBean) {
        try {
            if (c == null) {
                c = ConnectionPool.getInstance();
                c.initialize();
            }
            conn = c.getConnection();
            String sql = "update RoomCategories set rcategoryname=? , rcategorydetails=? where rcategoryid=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, roomCategoryBean.getRcategoryName());
            pstmt.setString(2, roomCategoryBean.getRcategoryDetails());
            pstmt.setInt(3, roomCategoryBean.getRcategoryId());
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
            String sql = "delete from RoomCategories where rCategoryId=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            c.putConnection(conn);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<RoomCategories> findAll() {
        ArrayList<RoomCategories> al = new ArrayList<RoomCategories>();
        try {
            if (c == null) {
                c = ConnectionPool.getInstance();
                c.initialize();
            }
            conn = c.getConnection();
            String sql = "select rcategoryid,rcategoryname,rcategorydetails from RoomCategories";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                RoomCategories roomCategoryBean = new RoomCategories();
                roomCategoryBean.setRcategoryId(rs.getInt("rcategoryid"));
                roomCategoryBean.setRcategoryName(rs.getString("rcategoryname"));
                roomCategoryBean.setRcategoryDetails(rs.getString("rcategorydetails"));
                al.add(roomCategoryBean);
            }
            c.putConnection(conn);
        } catch (Exception e) {
            System.out.println("Exception " + e);
        }
        return al;
    }

    public RoomCategories find(int id) {
        RoomCategories roomCategoryBean = new RoomCategories();
        try {
            if (c == null) {
                c = ConnectionPool.getInstance();
                c.initialize();
            }
            conn = c.getConnection();
            String sql = "select * from RoomCategories where rcategoryid=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                roomCategoryBean.setRcategoryId(rs.getInt("rcategoryid"));
                roomCategoryBean.setRcategoryName(rs.getString("rcategoryname"));
                roomCategoryBean.setRcategoryDetails(rs.getString("rcategorydetails"));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            c.putConnection(conn);
        }
        return roomCategoryBean;
    }
    public static void main(String[] args) {
       RoomCategoriesDAO rd= new RoomCategoriesDAO();
       ArrayList<RoomCategories> r=rd.findAll();
       for(RoomCategories r1:r)
       {System.out.println(r1.getRcategoryDetails());
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
