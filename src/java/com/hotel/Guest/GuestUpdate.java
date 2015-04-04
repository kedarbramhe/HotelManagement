package com.vedisoft.Guest;

import java.io.IOException;
import java.io.PrintWriter;
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
public class GuestUpdate extends HttpServlet {

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
            out.println("<script language=\"JavaScript\">");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/validate.js");
            dispatcher.include(request, response);
            out.println("</script>");
            out.println("<title>Vedisoft: Java Web and Enterprise Technologies</title>");
            out.println(" </head>");
            out.println(" <body>");
            out.println("<h1 align=\"center\">Vedisoft: Java Web and Enterprise Technologies</h1>");
            out.println(" <form method=\"post\" action=\"GuestOptions?opn=update\"  onSubmit=\"return validate(this);\">");
            int id = Integer.parseInt(request.getParameter("guestid"));
            GuestDAO gd = new GuestDAO();
            Guest g = gd.find(id);
            out.println(" <input type=\"hidden\" name=\"guestid\" value=\"" + g.getGuestId() + "\"/> <br/>");
            out.println("  <b>Guest Name : </b> <input type=\"text\" name=\"guestname\" value=\"" + g.getGuestName() + "\"/> <br/>");
            out.println("  <b>Age  : </b> <input type=\"text\" name=\"age\" value=\"" + g.getAge() + "\"/><br/> ");
            if (g.getGender().equals("male")) {
                out.println("  <b>Gender  : </b> <input type=\"radio\" group=\"gender\"  checked= \"true\" value=\"male\" name=\"gender\"/>Male ");
                out.println("<input type=\"radio\" group=\"gender\" value=\"female\" name=\"gender\"/>Female<br/>");
            } else {
                out.println("  <b>Gender  : </b> <input type=\"radio\" group=\"gender\"  value=\"male\" name=\"gender\"/>Male ");
                out.println("<input type=\"radio\" group=\"gender\" checked= \"true\"  value=\"female\" name=\"gender\"/>Female<br/>");
            }
            out.println("  <b>Primary  : </b> <input type=\"text\" name=\"primary\" value=\"" + g.getPrimary() + "\"/><br/> ");
            out.println(" <input type=\"submit\" value=\"Add\"/>");
            out.println("    <input type=\"reset\" value=\"Clear Contents\"/>");
            out.println(" </form>");
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
