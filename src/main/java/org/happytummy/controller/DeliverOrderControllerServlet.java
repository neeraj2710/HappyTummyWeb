package org.happytummy.controller;

import org.happytummy.model.OrderModel;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "DeliverOrderControllerServlet", value = "/DeliverOrderControllerServlet")
public class DeliverOrderControllerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String customerID = req.getParameter("deliver");
        try{
            OrderModel.updateStatus(customerID);
            resp.sendRedirect("ViewPendingOrderControllerServlet");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}