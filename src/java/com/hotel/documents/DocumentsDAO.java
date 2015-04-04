/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vedisoft.documents;

import com.vedisoft.Bookings.Bookings;
import com.vedisoft.Bookings.BookingsDAO;
import com.vedisoft.utilities.ConnectionPool;
import java.sql.*;
import java.sql.PreparedStatement;
import java.util.ArrayList;

/**
 *
 * @author vedisoft
 */
public class DocumentsDAO {

    ConnectionPool c = null;
    Connection conn = null;

    public void create(Documents documentBean) {
        try {
            if (c == null) {
                c = ConnectionPool.getInstance();
                c.initialize();
            }
            conn = c.getConnection();
            String sql = "Insert into documents (doctitle,GuestId,File)" + " values(?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, documentBean.getDocTitle());
            pstmt.setInt(2, documentBean.getGuestId());
            pstmt.setString(3, documentBean.getFile());
            pstmt.executeUpdate();
            c.putConnection(conn);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            c.putConnection(conn);
        }
    }

    public void edit(Documents documentBean) {
        try {

            if (c == null) {
                c = ConnectionPool.getInstance();
                c.initialize();
            }
            conn = c.getConnection();
            String sql = "update documents set doctitle=? , guestid=? ,file=? where docid=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, documentBean.getDocTitle());
            pstmt.setInt(2, documentBean.getGuestId());
            pstmt.setString(3, documentBean.getFile());
            pstmt.setInt(4, documentBean.getDocId());
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
            String sql = "delete from documents where docid=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            c.putConnection(conn);
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            c.putConnection(conn);
        }
    }

    public ArrayList<Documents> findAll() {
        ArrayList<Documents> al = new ArrayList<Documents>();
        try {
            if (c == null) {
                c = ConnectionPool.getInstance();
                c.initialize();
            }
            conn = c.getConnection();
            String sql = "select docid,doctitle,guestid,file from documents";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Documents documentBean = new Documents();
                documentBean.setDocId(rs.getInt("docid"));
                documentBean.setDocTitle(rs.getString("doctitle"));
                documentBean.setGuestId(rs.getInt("guestid"));
                documentBean.setFile(rs.getString("file"));
                al.add(documentBean);
            }
            c.putConnection(conn);
        } catch (Exception e) {
            System.out.println("Exception " + e);
        } finally {
            c.putConnection(conn);
        }
        return al;
    }

    public Documents find(int id) {
        Documents documentBean = new Documents();

        try {
            if (c == null) {
                c = ConnectionPool.getInstance();
                c.initialize();
            }
            conn = c.getConnection();
            String sql = "select * from documents where documentid=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                documentBean.setDocId(id);
                documentBean.setDocTitle(rs.getString("doctitle"));
                documentBean.setGuestId(rs.getInt("guestid"));
                documentBean.setFile(rs.getString("file"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            c.putConnection(conn);
        }
        return documentBean;
    }

    public Documents findbyCurrent(int id) {
        Documents documentBean = new Documents();
        BookingsDAO bd = new BookingsDAO();
        Bookings b = bd.find(id);
        try {
            if (c == null) {
                c = ConnectionPool.getInstance();
                c.initialize();
            }
            conn = c.getConnection();
            String sql = "select * from documents where guestid=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, b.getGuestId());
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                documentBean.setDocId(id);
                documentBean.setGuestId(rs.getInt("guestid"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            c.putConnection(conn);
        }
        return documentBean;
    }

    public int getCount(int id) {
        int a = 0;

        try {
            if (c == null) {
                c = ConnectionPool.getInstance();
                c.initialize();
            }
            conn = c.getConnection();
            String sql = "select count(*) from documents where guestid=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            a = rs.getInt(1);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            c.putConnection(conn);

        }
        return a;
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
