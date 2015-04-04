/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vedisoft.Rates;

import com.vedisoft.Bookings.BookingsDAO;
import com.vedisoft.utilities.ConnectionPool;
import com.vedisoft.Rates.Rates;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 * @author vedisoft
 */
public class RatesDAO {

    ConnectionPool c = null;
    Connection conn = null;

    public void create(Rates ratesBean) {
        try {
            if (c == null) {
                c = ConnectionPool.getInstance();
                c.initialize();
            }
            conn = c.getConnection();
            String sql = "insert into Rates (rcategoryid,fromdate,todate,rate,tax)" + " values(?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, ratesBean.getRcategoryId());
            pstmt.setString(2, ratesBean.getFromDate());
            pstmt.setString(3, ratesBean.getToDate());
            pstmt.setFloat(4, ratesBean.getRate());
            pstmt.setFloat(5, ratesBean.getTax());
            pstmt.executeUpdate();
            c.putConnection(conn);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            c.putConnection(conn);
        }
    }

    public void edit(Rates ratesBean) {
        try {

            if (c == null) {
                c = ConnectionPool.getInstance();
                c.initialize();
            }
            conn = c.getConnection();
            String sql = "update Rates set rcategoryid=? , fromdate=?,todate=? ,rate=?,tax=? where rateid=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, ratesBean.getRcategoryId());
            pstmt.setString(2, ratesBean.getFromDate());
            pstmt.setString(3, ratesBean.getToDate());
            pstmt.setFloat(4, ratesBean.getRate());
            pstmt.setFloat(5, ratesBean.getTax());
            pstmt.setInt(6, ratesBean.getRateId());
            pstmt.executeUpdate();
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
            String sql = "delete from Rates where rateid=?";
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

    public ArrayList<Rates> findAll() {
        ArrayList<Rates> al = new ArrayList<Rates>();
        try {
            if (c == null) {
                c = ConnectionPool.getInstance();
                c.initialize();
            }
            conn = c.getConnection();
            String sql = "select rateid,rcategoryid,fromdate,todate,tax,rate from Rates";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Rates ratesBean = new Rates();
                ratesBean.setRateId(rs.getInt("rateid"));
                ratesBean.setRcategoryId(rs.getInt("rcategoryid"));
                ratesBean.setFromDate(rs.getString("fromdate"));
                ratesBean.setToDate(rs.getString("todate"));
                ratesBean.setTax(rs.getFloat("tax"));
                ratesBean.setRate(rs.getFloat("rate"));
                al.add(ratesBean);
            }
            c.putConnection(conn);
        } catch (Exception e) {
            System.out.println("Exception " + e);
        } finally {
            c.putConnection(conn);
        }
        return al;
    }

    public ArrayList<Rates> findRatesBetweenDates(int rcategoryid) {
        ArrayList<Rates> al = new ArrayList<Rates>();
        try {
            if (c == null) {
                c = ConnectionPool.getInstance();
                c.initialize();
            }
            conn = c.getConnection();
            java.util.Date d = BookingsDAO.getCurrentDate();
            DateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
            String date = sd.format(d);
            String sql = "select rateid,rcategoryid,fromdate,todate,tax,rate from Rates where '" + date + "'between fromdate and todate and rcategoryid=" + rcategoryid;
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Rates ratesBean = new Rates();
                ratesBean.setRateId(rs.getInt("rateid"));
                ratesBean.setRcategoryId(rs.getInt("rcategoryid"));
                ratesBean.setFromDate(rs.getString("fromdate"));
                ratesBean.setToDate(rs.getString("todate"));
                ratesBean.setTax(rs.getFloat("tax"));
                ratesBean.setRate(rs.getFloat("rate"));

                al.add(ratesBean);
            }
            c.putConnection(conn);
        } catch (Exception e) {
            System.out.println("Exception " + e);
        } finally {
            c.putConnection(conn);
        }
        System.out.println("al"+al);
        return al;
    }

    public ArrayList<Rates> findRatesForCategory(int id) {
        ArrayList<Rates> al = new ArrayList<Rates>();
        try {
            if (c == null) {
                c = ConnectionPool.getInstance();
                c.initialize();
            }
            conn = c.getConnection();
            String sql = "select rateid,rcategoryid,fromdate,todate,tax,rate from Rates where rcategoryid=" + id;
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Rates ratesBean = new Rates();
                ratesBean.setRateId(rs.getInt("rateid"));
                ratesBean.setRcategoryId(rs.getInt("rcategoryid"));
                ratesBean.setFromDate(rs.getString("fromdate"));
                ratesBean.setToDate(rs.getString("todate"));
                ratesBean.setTax(rs.getFloat("tax"));
                ratesBean.setRate(rs.getFloat("rate"));
                al.add(ratesBean);
            }
            c.putConnection(conn);
        } catch (Exception e) {
            System.out.println("Exception " + e);
        } finally {
            c.putConnection(conn);
        }
        return al;
    }

    public Rates find(int id) {
        Rates ratesBean = new Rates();
        try {
            if (c == null) {
                c = ConnectionPool.getInstance();
                c.initialize();
            }
            conn = c.getConnection();
            String sql = "select * from Rates where rateid=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                ratesBean.setRateId(id);
                ratesBean.setRcategoryId(rs.getInt("rcategoryid"));
                ratesBean.setFromDate(rs.getString("fromdate"));
                ratesBean.setToDate(rs.getString("todate"));
                ratesBean.setTax(rs.getInt("tax"));
                ratesBean.setRate(rs.getInt("rate"));

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            c.putConnection(conn);
        }
        return ratesBean;
    }

    public ArrayList<Rates> findForCategory(int id) {
        Rates ratesBean = new Rates();
        ArrayList<Rates> ar = new ArrayList<Rates>();
        try {
            if (c == null) {
                c = ConnectionPool.getInstance();
                c.initialize();
            }
            conn = c.getConnection();
            String sql = "select * from Rates where rcategoryid=? group by fromdate  order by rcategoryid asc ";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                ratesBean.setRateId(id);
                ratesBean.setRcategoryId(rs.getInt("rcategoryid"));
                ratesBean.setFromDate(rs.getString("fromdate"));
                ratesBean.setToDate(rs.getString("todate"));
                ratesBean.setTax(rs.getInt("tax"));
                ratesBean.setRate(rs.getInt("rate"));
                ar.add(ratesBean);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            c.putConnection(conn);
        }
        return ar;
    }

    public Rates find1(int id) {
        Rates ratesBean = new Rates();
        ArrayList<Rates> ar = new ArrayList<Rates>();

        try {
            if (c == null) {
                c = ConnectionPool.getInstance();
                c.initialize();
            }
            conn = c.getConnection();
            String sql = "select * from Rates where rcategoryid=? ";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                ratesBean.setRateId(id);
                ratesBean.setRcategoryId(rs.getInt("rcategoryid"));
                ratesBean.setFromDate(rs.getString("fromdate"));
                ratesBean.setToDate(rs.getString("todate"));
                ratesBean.setTax(rs.getInt("tax"));
                ratesBean.setRate(rs.getInt("rate"));
                ar.add(ratesBean);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            c.putConnection(conn);
        }
        return ratesBean;
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
