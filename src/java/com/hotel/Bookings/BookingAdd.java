package com.vedisoft.Bookings;

import com.vedisoft.RoomCategories.RoomCategories;
import com.vedisoft.RoomCategories.RoomCategoriesDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BookingAdd extends HttpServlet {

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
            out.println("<link rel=\"stylesheet\" href=\"images/calender.css\" type=\"text/css\" />");
            out.println("<link rel=\"stylesheet\" href=\"images/epoch_styles.css\" type=\"text/css\" />");
            out.println("<script language=\"JavaScript\">");
            RequestDispatcher dispatcher1 = request.getRequestDispatcher("/WEB-INF/validate.js");
            dispatcher1.include(request, response);
            out.println("</script>");
            out.println("<script language=\"JavaScript\">");
            out.println("var dp_cal1");
            out.println("var dp_cal2");
            out.println("window.onload = function () {");
            out.println(" dp_cal1  = new Epoch('epoch_popup','popup',document.getElementById('popup_container1'));");
            out.println(" dp_cal2  = new Epoch('epoch_popup','popup',document.getElementById('popup_container2'));};");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/validate.js");
            dispatcher.include(request, response);
            dispatcher = request.getRequestDispatcher("/images/epoch_classes.js");
            dispatcher.include(request, response);
            out.println("</script>");
            out.println("</head>");
            out.println("<title>Vedisoft: Java Web and Enterprise Technologies</title>");
            out.println(" </head>");
            out.println(" <body>");
            out.println("<div id=\"wrap\">");
            dispatcher = request.getRequestDispatcher("/design/header.html");
            dispatcher.include(request, response);
            out.println("<div id=\"content-wrap\">");
            dispatcher = request.getRequestDispatcher("/design/operationsidebar.html");
            dispatcher.include(request, response);
            out.println("<div id=\"main\">");
            out.println(" <form method=\"post\" action=\"BookingForm?opn=Add\"  onSubmit=\"return validate(this);\">");
            out.println("  <b>Guest Name : </b> <input type=\"text\" name=\"guestname\" /> <br/>");
            out.println("  <b>Age  : </b> <input type=\"text\" name=\"age\"/><br/> ");
            out.println("  <b>Gender  : </b> <input type=\"radio\" group=\"gender\" value=\"male\" name=\"gender\" checked=\"true\"/>Male ");
            out.println(" <input type=\"radio\" group=\"gender\" value=\"female\" name=\"gender\"/>Female<br/>");
            out.println("  <b>Mobile Number  : </b> <input type=\"text\"  name=\"mobile\"/> </br> ");
            out.println("<b> Check In Date  : </b>    <input type=\"text\" name=\"checkInDate\" id=\"popup_container1\"/> <br/>");
            out.println("<b> Check out Date  : </b>       <input type=\"text\" name=\"checkOutDate\" id=\"popup_container2\"  /> <br/>");
            out.println(" <b> Advance  : </b>    <input type=\"text\" name=\"advance\"/> <br/>");
            out.println("    <input type=\"hidden\" value=\"Not Paid\" name=\"status\"");
            RoomCategoriesDAO rc = new RoomCategoriesDAO();
            ArrayList<RoomCategories> arc = rc.findAll();
            out.println("<b>select Catogory</b>");
            out.println("<select size=1 name=\"rcategoryid\" onchange=\"showsoft(this.value);\">");
            for (RoomCategories c : arc) {

                out.println("<option name=\"" + c.getRcategoryId() + "\" value=\"" + c.getRcategoryId() + "\" selected=\"true\">" + c.getRcategoryName() + "</option>");

            }
            out.println("</select></br>");
            out.println("<div id=\"1\"> ");
            out.println("</div>");
            out.println("<input type=\"checkbox\" name=\"chk\" value=\"ON\">Proceed To Check</br>");
            out.println(" <input type=\"submit\" value=\"Book\"/>");
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
