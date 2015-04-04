package com.vedisoft.documents;

import com.vedisoft.Guest.Guest;
import com.vedisoft.Guest.GuestDAO;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
/**
 *
 * @author vedisoft
 */
public class DocumentForm extends HttpServlet {

    private static final String TMP_DIR_PATH = "/home/vedisoft/Desktop";
    private File tmpDir;
    private File destinationDir;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        tmpDir = new File(TMP_DIR_PATH);
        if (!tmpDir.isDirectory()) {
            throw new ServletException(TMP_DIR_PATH + " is not a directory");
        }
        String realPath = getServletContext().getRealPath("files");
        System.out.println("realpath" + realPath);
        destinationDir = new File(realPath);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        response.setContentType("text/plain");
        DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
        fileItemFactory.setSizeThreshold(1 * 1024 * 1024);
        fileItemFactory.setRepository(tmpDir);
        ServletFileUpload uploadHandler = new ServletFileUpload(fileItemFactory);
        try {
            List items = uploadHandler.parseRequest(request);
            Iterator itr = items.iterator();
            Documents d = new Documents();
            Guest g = new Guest();
            while (itr.hasNext()) {
                FileItem item = (FileItem) itr.next();
                if (item.isFormField()) {
                    System.out.println("File Name = " + item.getFieldName() + ", Value = " + item.getString());
                    if (item.getFieldName().equals("guestid")) {
                        d.setGuestId(Integer.parseInt(item.getString()));
                    } else if (item.getFieldName().equals("title")) {
                        d.setDocTitle(item.getString());
                    }
                } else {
                    d.setFile(item.getName());
                    System.out.println("Field Name = " + item.getFieldName()
                            + ", File Name = " + item.getName()
                            + ", Content type = " + item.getContentType()
                            + ", File Size = " + item.getSize());

                    File file = new File(destinationDir, item.getName());
                    item.write(file);
                }
            }
            GuestDAO gd = new GuestDAO();
            g = gd.find(d.getGuestId());
            DocumentsDAO dao = new DocumentsDAO();
            dao.create(d);
            response.sendRedirect("BookingsAddMorGuests?bookingid=" + g.getBookingId());
        } catch (FileUploadException ex) {
            log("Error encountered while parsing the request", ex);
        } catch (Exception ex) {
            log("Error encountered while uploading file", ex);
        } finally {
            out.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
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
