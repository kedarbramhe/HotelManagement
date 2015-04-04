/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vedisoft.Rooms;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author vedisoft
 */
public class RoomsForm extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String roomno = request.getParameter("roomno");
        String floorno = request.getParameter("floorno");
        String rcategoryid = request.getParameter("rcategoryid");
        Rooms c = new Rooms();
        RoomsDAO rd = new RoomsDAO();
        if ((roomno != null) && (floorno != null) && (rcategoryid != null)) {
            c.setRoomNo(Integer.parseInt(roomno));
            c.setFloorNo(Integer.parseInt(floorno));
            c.setRcategoryId(Integer.parseInt(rcategoryid));
        }
        int id = 0;
        try {
            String opn = request.getParameter("opn");
            if (opn.equals("add")) {
                rd.create(c);
            } else if (opn.equals("update")) {
                id = Integer.parseInt(roomno);
                System.out.println(id);
                c.setRoomNo(id);
                rd.edit(c);
            } else if (opn.equals("delete")) {
                id = Integer.parseInt(roomno);
                c.setRoomNo(id);
                rd.remove(id);
            }
            response.sendRedirect("RoomsView");
        } finally {
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
