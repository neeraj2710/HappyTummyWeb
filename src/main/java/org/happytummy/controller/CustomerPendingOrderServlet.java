package org.happytummy.controller;

import org.happytummy.model.OrderModel;
import org.happytummy.pojo.OrderPojo;
import org.happytummy.util.UserProfile;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "CustomerPendingOrderServlet", value = "/CustomerPendingOrderServlet")
public class CustomerPendingOrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            List<OrderPojo> orderList = OrderModel.getAllPendingOrders(UserProfile.getCustomerId());
            req.setAttribute("pendingOrders", orderList);
            req.getRequestDispatcher("CustomerPendingOrder.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}