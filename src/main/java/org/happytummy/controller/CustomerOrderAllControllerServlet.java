package org.happytummy.controller;

import org.happytummy.model.OrderModel;
import org.happytummy.pojo.OrderPojo;
import org.happytummy.util.Mailer;
import org.happytummy.util.UserProfile;

import javax.mail.MessagingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "CustomerOrderAllControllerServlet", value = "/CustomerOrderAllControllerServlet")
public class CustomerOrderAllControllerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        try {
            List<OrderPojo> orderList = OrderModel.getCartItems(UserProfile.getCustomerId());
            double totalPrice = 0;
            for (OrderPojo orderPojo : orderList) {
                totalPrice += orderPojo.getProductPrice()*orderPojo.getQuantity();
            }
            String ordId = OrderModel.getNewId();
            boolean result = OrderModel.orderAll(ordId,UserProfile.getCustomerId());
            if(result){
                Map<String,String> map = new HashMap<>();
                map.put("emailId","happytummy.foodpartner@gmail.com");
                map.put("securityKey","dsqa sqxm yhki aglv");
                Mailer.sendMail(map,orderList,totalPrice,ordId);
            }
            resp.sendRedirect("CustomerCartControllerServlet");
        } catch (SQLException | MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}