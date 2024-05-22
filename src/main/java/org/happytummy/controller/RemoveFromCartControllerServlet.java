package org.happytummy.controller;

import org.happytummy.model.OrderModel;
import org.happytummy.util.UserProfile;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "RemoveFromCartControllerServlet", value = "/RemoveFromCartControllerServlet")
public class RemoveFromCartControllerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pId = req.getParameter("remove");
        try{
            OrderModel.removeFromCart(pId, UserProfile.getCustomerId());
            resp.sendRedirect("CustomerCartControllerServlet");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}