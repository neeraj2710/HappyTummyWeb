package org.happytummy.controller;

import org.happytummy.model.StaffModel;
import org.happytummy.pojo.StaffPojo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ViewStaffControllerServlet", value = "/ViewStaffControllerServlet")
public class ViewStaffControllerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<StaffPojo> staffList = StaffModel.getAllStaff();
            req.setAttribute("staffList", staffList);
            req.getRequestDispatcher("viewStaff.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}