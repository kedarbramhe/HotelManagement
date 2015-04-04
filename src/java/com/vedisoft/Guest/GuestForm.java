/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vedisoft.Guest;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *
 * @author vedisoft
 */
public class GuestForm extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            response.setContentType("text/html;charset=UTF-8");
            String guestname = request.getParameter("guestname");
            String age = request.getParameter("age");
            String gender = request.getParameter("gender");
            String primary = request.getParameter("primary");
            System.out.println("kk   " + guestname + age + gender + primary);
            Guest c = new Guest();
            GuestDAO rd = new GuestDAO();
            if ((guestname != null) && (age != null) && (gender != null) && (primary != null)) {
                c.setAge(Integer.parseInt(age));
                c.setGender(gender);
                c.setPrimary(primary);
                c.setGuestName(guestname);
                System.out.println("lala" + c.getGuestName());
            }
            int id = 0;
            try {
                String opn = request.getParameter("opn");
                if (opn.equals("add")) {
                    System.out.println(c.getGuestName());
                    rd.create(c);
                } else if (opn.equals("update")) {
                    id = Integer.parseInt(request.getParameter("guestid"));
                    c.setGuestId(id);
                    rd.edit(c);
                } else if (opn.equals("delete")) {
                    id = Integer.parseInt(request.getParameter("guestid"));
                    c.setGuestId(id);
                    rd.remove(id);
                }
                response.sendRedirect("GuestView");
            } finally {
            }
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
