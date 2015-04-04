package com.vedisoft;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
/**
 *
 * @author vedisoft
 */
public class ReadProperty {

    public static void main(String args[]) {
        try {
            Properties pro = new Properties();
            File f = new File("Hotel.properties");
            FileInputStream in = new FileInputStream(f);
            pro.load(in);
            Enumeration e = pro.propertyNames();
            while (e.hasMoreElements()) {
                System.out.println(e.nextElement());
            }
            String name = pro.getProperty("URL");
            System.out.println(name);

        } catch (IOException e) {
            System.out.println("Error is:" + e.getMessage());
            e.printStackTrace();
        }
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
