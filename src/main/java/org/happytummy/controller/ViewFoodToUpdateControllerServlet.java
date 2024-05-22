package org.happytummy.controller;

import org.happytummy.model.ProductModel;
import org.happytummy.pojo.ProductPojo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ViewFoodToUpdateControllerServlet", value = "/ViewFoodToUpdateControllerServlet")
public class ViewFoodToUpdateControllerServlet extends HttpServlet {
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
            List<ProductPojo> productList = ProductModel.getAllProduct();
            req.setAttribute("productList", productList);
            RequestDispatcher dispatcher = req.getRequestDispatcher("ViewFoodToUpdate.jsp");
            dispatcher.forward(req, resp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}