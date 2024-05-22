package org.happytummy.controller;

import org.happytummy.model.OrderModel;
import org.happytummy.pojo.OrderPojo;
import org.happytummy.util.UserProfile;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AddToCartControllerServlet", value = "/AddToCartControllerServlet")
public class AddToCartControllerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pId=req.getParameter("id");
        int quantity=Integer.parseInt(req.getParameter("quantity"));
        String customerId = UserProfile.getCustomerId();
        OrderPojo order = new OrderPojo();
        order.setCustomerId(customerId);
        order.setQuantity(quantity);
        order.setProductId(pId);
        try{
            OrderModel.addToCart(order);
            resp.sendRedirect("CustomerMenuControllerServlet");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}