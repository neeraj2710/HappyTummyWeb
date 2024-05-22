package org.happytummy.controller;

import org.happytummy.model.ProductModel;
import org.happytummy.pojo.ProductPojo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ViewSelectedFoodControllerServlet", value = "/ViewSelectedFoodControllerServlet")
public class ViewSelectedFoodControllerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        try{
            ProductPojo p = ProductModel.getProductById(id);
            req.setAttribute("product", p);
            req.getRequestDispatcher("UpdateFood.jsp").forward(req, resp);
        }catch (SQLException e){
            throw new RuntimeException();
        }
    }
}