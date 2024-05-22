package org.happytummy.controller;

import org.happytummy.model.ProductModel;
import org.happytummy.pojo.ProductPojo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "AddFoodControllerServlet", value = "/AddFoodControllerServlet")
public class AddFoodControllerServlet extends HttpServlet {
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
        String url = req.getParameter("url");
        String name = req.getParameter("name");
        double price = Double.parseDouble(req.getParameter("price"));
        ProductPojo p = new ProductPojo();
        p.setpURL(url);
        p.setpName(name);
        p.setpPrice(price);
        try{
            boolean result = ProductModel.addProduct(p);
            if(result){
                resp.sendRedirect("SearchFood.jsp");
            }
            else{

                RequestDispatcher rd = req.getRequestDispatcher("SearchFood.jsp");
                rd.include(req, resp);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}