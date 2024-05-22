package org.happytummy.controller;

import org.happytummy.model.StaffModel;
import org.happytummy.pojo.StaffPojo;
import org.happytummy.util.Mailer;

import javax.mail.MessagingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "AddStaffControllerServlet", value = "/AddStaffControllerServlet")
public class AddStaffControllerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        StaffPojo staff = new StaffPojo();
        staff.setFname(req.getParameter("fname"));
        staff.setLname(req.getParameter("lname"));
        staff.setEmailId(req.getParameter("email"));
        staff.setGender(req.getParameter("gender"));
        staff.setRole(req.getParameter("role"));
        staff.setSalary(Double.parseDouble(req.getParameter("salary")));
        try{
            boolean result = StaffModel.addStaff(staff);
            if(result){
                Map<String,String> map = new HashMap<String,String>();
                map.put("emailId","happytummy.foodpartner@gmail.com");
                map.put("securityKey","dsqa sqxm yhki aglv");
                Mailer.sendMail(map,staff);
            }
            RequestDispatcher rd = req.getRequestDispatcher("addStaff.html");
            rd.forward(req, resp);
        } catch (SQLException | MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}