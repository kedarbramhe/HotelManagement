package com.vedisoft.reports;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import com.vedisoft.Bookings.Bookings;
import com.vedisoft.Bookings.BookingsDAO;
import com.vedisoft.RoomCategories.RoomCategories;
import com.vedisoft.RoomCategories.RoomCategoriesDAO;
import com.vedisoft.Rooms.Rooms;
import com.vedisoft.Rooms.RoomsDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
public class CategoryWiseRoom extends HttpServlet {

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
            out.println(" <head>");
            out.println("<link rel=\"stylesheet\" href=\"images/HarvestField.css\" type=\"text/css\" />");
            out.println("</head>");
            out.println("<title>Vedisoft: Java Web and Enterprise Technologies</title>");
            out.println(" </head>");
            out.println(" <body>");
            out.println("<div id=\"wrap\">");
            RequestDispatcher dispatcher;
            dispatcher = request.getRequestDispatcher("/design/header.html");
            dispatcher.include(request, response);
            out.println("<div id=\"content-wrap\">");
            dispatcher = request.getRequestDispatcher("/design/reportssidebar.html");
            dispatcher.include(request, response);
            out.println("<div id=\"main\">");
            out.println("<ul>");
            BookingsDAO bd = new BookingsDAO();
            ArrayList<Bookings> s = new ArrayList<Bookings>();
            RoomsDAO rd = new RoomsDAO();
            RoomCategoriesDAO rcd = new RoomCategoriesDAO();
            ArrayList<RoomCategories> al = rcd.findAll();
            out.println("<table border=\"3\" ><tr><th>Category</th> <th>Room number</th><th>Status</th></tr>");
            for (RoomCategories rc : al) {
                out.println("<tr><td>" + rc.getRcategoryName() + "</td><td></td><td></td></tr>");
                int category = rc.getRcategoryId();
                ArrayList<Rooms> ar = rd.findRoomsForCategory(category);
                for (Rooms r : ar) {
                    s = bd.findRooms(r.getRoomNo());
                    if (!s.isEmpty()) {
                        for (Bookings b : s) {
                            int diff = bd.findDateDifference(b.getBookingId());
                            if (diff == 0) {
                                diff = 1;
                            }
                            out.println("<tr><td></td><td>" + r.getRoomNo() + "</td><td>Room Booked For  " + diff + " days from " + BookingsDAO.convertDate2(b.getCheckInDate()) + " to " + BookingsDAO.convertDate2(b.getCheckOutDate()) + "</td></tr>");
                        }
                    } else {
                        out.println("<tr><td></td><td>" + r.getRoomNo() + "</td><td><font color=\"red\"> Free</font></td></tr>");
                    }
                }
            }
            out.println("</table>");
            out.println("</ul>");
            out.println("</div></div>");
            dispatcher = request.getRequestDispatcher("/design/footer.html");
            dispatcher.include(request, response);
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
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
