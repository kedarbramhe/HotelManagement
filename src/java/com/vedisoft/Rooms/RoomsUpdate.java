/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vedisoft.Rooms;

import com.vedisoft.RoomCategories.RoomCategoriesDAO;
import com.vedisoft.RoomCategories.RoomCategories;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

/**
 *
 * @author vedisoft
 */
public class RoomsUpdate extends HttpServlet {

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
            out.println("<script language=\"JavaScript\">");
            RequestDispatcher dispatcher1 = request.getRequestDispatcher("/WEB-INF/validate.js");
            dispatcher1.include(request, response);
            out.println("</script>");
            out.println("<title>Vedisoft: Java Web and Enterprise Technologies</title>");
            out.println(" </head>");
            out.println(" <body>");
            out.println("<div id=\"wrap\">");
            RequestDispatcher dispatcher;
            dispatcher = request.getRequestDispatcher("/design/header.html");
            dispatcher.include(request, response);
            out.println("<div id=\"content-wrap\">");
            dispatcher = request.getRequestDispatcher("/design/mastersidebar.html");
            dispatcher.include(request, response);
            out.println("<div id=\"main\">");
            out.println(" <form method=\"post\" action=\"RoomsOptions?opn=update\"   onSubmit=\"return validate(this);\">");
            int id = Integer.parseInt(request.getParameter("roomno"));
            RoomsDAO rd = new RoomsDAO();
            Rooms c = rd.find(id);
            out.println("  <b>Room Number : </b> <input type=\"text\" name=\"roomno\"  value=\"" + c.getRoomNo() + "\"/> <br/>");
            out.println("  <b>Floor Number  : </b>      <select name=\"floorno\" size=\"1\"><option>1</option><option>2</option><option>3</option><option>4</option><option>5</option></select > <br/> ");
            out.println("<b> Room Category  : </b>      <select name=\"rcategoryid\" size=\"1\">");
            RoomCategoriesDAO r = new RoomCategoriesDAO();
            ArrayList<RoomCategories> al = new ArrayList<RoomCategories>();
            al = r.findAll();
            for (RoomCategories r1 : al) {
                if (c.getRcategoryId() == r1.getRcategoryId()) {
                    out.println("<option selected=\"true\" value=\"" + r1.getRcategoryId() + "\">" + r1.getRcategoryName() + " </option>");
                } else {
                    out.println("<option value=\"" + r1.getRcategoryId() + "\">" + r1.getRcategoryName() + " </option>");
                }
            }
            out.println("</select><br/>");
            out.println(" <input type=\"submit\" value=\"Update\"/>");
            out.println("    <input type=\"reset\" value=\"Clear Contents\"/>");
            out.println(" </form>");
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