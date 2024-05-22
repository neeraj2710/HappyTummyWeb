package org.happytummy.controller;

import org.happytummy.model.ProductModel;
import org.happytummy.pojo.ProductPojo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "UpdateFoodControllerServlet", value = "/UpdateFoodControllerServlet")
public class UpdateFoodControllerServlet extends HttpServlet {
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
        String name = req.getParameter("name");
        String price = req.getParameter("price");
        ProductPojo p = new ProductPojo();
        p.setpID(id);
        p.setpName(name);
        p.setpPrice(Double.parseDouble(price));
        try{
            ProductModel.updateProduct(p);
            resp.sendRedirect("ViewFoodToUpdateControllerServlet");
        }catch (SQLException e){
            throw new RuntimeException();
        }
    }
}