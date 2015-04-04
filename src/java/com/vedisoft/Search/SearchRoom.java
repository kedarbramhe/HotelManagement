package com.vedisoft.Search;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import com.vedisoft.Bookings.BookingsDAO;
import com.vedisoft.Rates.Rates;
import com.vedisoft.Rates.RatesDAO;
import com.vedisoft.RoomCategories.RoomCategories;
import com.vedisoft.RoomCategories.RoomCategoriesDAO;
import com.vedisoft.Rooms.Rooms;
import com.vedisoft.Rooms.RoomsDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class SearchRoom extends HttpServlet {

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
            out.println("<head>");
            out.println("<link rel=\"stylesheet\" href=\"images/HarvestField.css\" type=\"text/css\" />");
            out.println("<title>Vedisoft: Java Web and Enterprise Technologies</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div id=\"wrap\">");
            RequestDispatcher dispatcher;
            dispatcher = request.getRequestDispatcher("/design/header.html");
            dispatcher.include(request, response);
            out.println("<div id=\"content-wrap\">");
            dispatcher = request.getRequestDispatcher("/design/searchsidebar.html");
            dispatcher.include(request, response);
            out.println("<div id=\"main\">");
            out.println(" <h3>Room Search</h3>");
            out.println("<form name=\"form1\" action=\"SearchRoom\">");
            out.println("Select Room");
            RoomsDAO rd = new RoomsDAO();
            ArrayList<Rooms> list = rd.findAll();
            out.println("<select name=\"roomno\">");
            for (Rooms bean : list) {
                out.println("<option value=\"" + bean.getRoomNo() + "\">" + bean.getRoomNo() + "</option>");
            }
            out.println("</select><br/>");
            out.println("<input type=\"submit\" value=\"submit\" >");
            out.println("</form>");
            if (request.getParameter("roomno") != null) {
                int id = Integer.parseInt(request.getParameter("roomno"));
                RoomsDAO rdd = new RoomsDAO();
                Rooms r = rdd.find(id);
                RoomCategoriesDAO rdd1 = new RoomCategoriesDAO();
                RoomCategories rc = rdd1.find(r.getRcategoryId());
                RatesDAO rrd = new RatesDAO();
                ArrayList<Rates> ar = rrd.findRatesForCategory(r.getRcategoryId());
                Date curr = BookingsDAO.getCurrentDate();
                DateFormat adf = new SimpleDateFormat("yyyy-MM-dd");
                out.println("<table><tr><td>");
                out.println("Room number: " + r.getRoomNo());
                out.println("</td></tr>");
                out.println("<tr><td>");
                out.println("Floor number: " + r.getFloorNo());
                out.println("</td></tr>");
                out.println("<tr><td>");
                out.println("Room Category: " + rc.getRcategoryName());
                out.println("</td></tr>");
                out.println("<tr><td>");
                float amount = 0.0f;
                for (Rates r1 : ar) {
                    Date fromdate = adf.parse(r1.getFromDate());
                    Date todate = adf.parse(r1.getToDate());
                    if ((curr.after(fromdate)) && (curr.before(todate))) {
                        amount = r1.getRate();
                    }
                }
                out.println("Rates: " + amount);
                out.println("</td></tr></table>");
            }
            out.println("</div></div>");
            dispatcher = request.getRequestDispatcher("/design/footer.html");
            dispatcher.include(request, response);
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");

        } catch (ParseException ex) {
            Logger.getLogger(SearchRoom.class.getName()).log(Level.SEVERE, null, ex);
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
