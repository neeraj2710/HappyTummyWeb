package org.happytummy.controller;

import org.happytummy.pojo.ProductPojo;
import org.happytummy.util.API;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "APIControllerServlet", value = "/APIControllerServlet")
public class APIControllerServlet extends HttpServlet {
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
            PrintWriter pw  = resp.getWriter();
            String searchFood = req.getParameter("search");
            searchFood = searchFood.replaceAll(" ","-");
            List<ProductPojo> p = API.getAllItemDetailsByName(searchFood);
            System.out.println(p);
            if(p.isEmpty()){
                System.out.println("null");
            }
            if(p.isEmpty()){
                RequestDispatcher rd = req.getRequestDispatcher("SearchFood.jsp");
                pw.println("<h3>no product found</h3>");
                rd.include(req, resp);
            }
            else{
                RequestDispatcher rd = req.getRequestDispatcher("SearchFood.jsp");
                req.setAttribute("products", p);
                rd.forward(req, resp);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}