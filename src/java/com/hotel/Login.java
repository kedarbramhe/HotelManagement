package com.vedisoft;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author vedisoft
 */
public class Login extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
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
            dispatcher = request.getRequestDispatcher("/design/home.html");
            dispatcher.include(request, response);
            out.println("<div id=\"content-wrap\">");
            out.println("<div id=\"main\">");
            out.println("<form method=\"post\" action=\"Authentication\">");
            out.println("<center><b>Login</b><table><tr><td>Username</td><td><input type=\"text\" name=\"user\" ></td></tr>");
            out.println("<tr><td>Password</td><td><input type='password' name=\"pass\" ></td></tr>");
            out.println("<tr><td colspan=\"2\" align=\"center\"><input type=\"submit\" value=\"Submit\"></td></tr></table></center>");
            out.println("</form>");
            String msg = request.getParameter("msg");
            if (msg != null) {
                out.println("<font color=\"red\"><h3>Incorrect Username and password<h3></font>");
            }
            out.println("</div></div>");
            dispatcher = request.getRequestDispatcher("/design/footer.html");
            dispatcher.include(request, response);
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
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
