package com.vedisoft.documents;

import com.vedisoft.Guest.Guest;
import com.vedisoft.Guest.GuestDAO;
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
public class DocumentAdd extends HttpServlet {

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
            out.println("<script language=\"JavaScript\">");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/validate.js");
            dispatcher.include(request, response);
            out.println("</script>");
            out.println(" </head>");
            out.println(" <body>");
            out.println("<div id=\"wrap\">");
            dispatcher = request.getRequestDispatcher("/design/header.html");
            dispatcher.include(request, response);
            out.println("<div id=\"content-wrap\">");
            dispatcher = request.getRequestDispatcher("/design/operationsidebar.html");
            dispatcher.include(request, response);
            out.println("<div id=\"main\">");
            String guestid = request.getParameter("guestid");
            if (guestid != null) {
                int gid = Integer.parseInt(guestid);
                GuestDAO gd = new GuestDAO();
                Guest g = gd.find(gid);
                out.println("<form method=\"post\" action=\"DocumentForm\" enctype=\"multipart/form-data\" onSubmit=\"return validate(this);\">");
                out.println("<input type=\"hidden\" name=\"guestid\" value=" + guestid + " />");
                out.println("<b>Guest Name : </b> <input type=\"text\" value=" + g.getGuestName() + " /> <br/>");
                out.println("<b>Document Title  : </b> <input type=\"text\" name=\"title\"/><br/> ");
                out.println("<b>Document File  : </b> <input type=\"file\" name=\"file1\"/><br/> ");
                out.println("<input type=\"submit\" value=\"Add\"/>");
                out.println("<input type=\"reset\" value=\"Clear Contents\"/>");
                out.println("</form>");
                out.println("</div></div>");
                dispatcher = request.getRequestDispatcher("/design/footer.html");
                dispatcher.include(request, response);
                out.println("</div>");
                out.println("</body>");
                out.println("</html>");
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