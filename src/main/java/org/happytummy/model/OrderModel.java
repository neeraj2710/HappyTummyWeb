package org.happytummy.model;

import org.happytummy.pojo.OrderPojo;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderModel {
    private static Connection conn;
    static {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/happytummy","root","neeraj");

        }catch (SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
            ex.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static String getNewId() throws SQLException {
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select max(order_id) from orders");
        rs.next();
        String id = rs.getString(1);
        String orderId = "";
        if (id != null) {
            id = id.substring(4);
            orderId = "ORD-" + (Integer.parseInt(id) + 1);
        } else {
            orderId = "ORD-101";
        }
        return orderId;
    }

    public static void addToCart(OrderPojo order)throws SQLException{
        PreparedStatement ps = conn.prepareStatement("insert into orders values(?,?,?,?,?,?)");
        ps.setString(1, null);
        ps.setString(2, order.getProductId());
        ps.setString(3, order.getCustomerId());
        ps.setString(4, "Cart");
        ps.setInt(5, order.getQuantity());
        ps.setString(6, null);
        ps.executeUpdate();
    }

    public static List<OrderPojo> getCartItems(String CustomerId)throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT p.p_id,p.p_name,p.p_price,o.quantity FROM orders o JOIN products p ON o.p_id=p.p_id WHERE o.customer_id=? and o.status='Cart'");
        ps.setString(1, CustomerId);
        ResultSet rs = ps.executeQuery();
        List<OrderPojo> cartItems = new ArrayList<OrderPojo>();
        while (rs.next()) {
            OrderPojo order = new OrderPojo();
            order.setProductId(rs.getString(1));
            order.setProductName(rs.getString(2));
            order.setProductPrice(rs.getInt(3));
            order.setQuantity(rs.getInt(4));
            cartItems.add(order);
        }
        return cartItems;
    }

    public static void removeFromCart(String pId,String customerId)throws SQLException{
        PreparedStatement ps = conn.prepareStatement("delete from orders where p_id=? and customer_id=? and status='Cart';");
        ps.setString(1, pId);
        ps.setString(2, customerId);
        ps.executeUpdate();
    }

    public static boolean orderAll(String ordId, String CustomerId)throws SQLException{
        String url = "update orders set order_id=?,status='Pending', order_date=? where status='Cart' and customer_id=?";
        PreparedStatement ps = conn.prepareStatement(url);
        ps.setString(1, ordId);
        ps.setString(2, new Date().toString());
        ps.setString(3, CustomerId);
        return ps.executeUpdate() > 0;
    }

    public static List<OrderPojo> getAllPendingOrders(String CustomerId)throws SQLException{
        String url = "select o.order_id, p.p_name,p_price,o.quantity, o.status, o.order_date from orders o join products p on o.p_id=p.p_id where status='Pending' and customer_id=?";
        PreparedStatement ps = conn.prepareStatement(url);
        ps.setString(1, CustomerId);
        ResultSet rs = ps.executeQuery();
        List<OrderPojo> orderList = new ArrayList<>();
        while (rs.next()) {
            OrderPojo order = new OrderPojo();
            order.setOrderId(rs.getString(1));
            order.setProductName(rs.getString(2));
            order.setProductPrice(rs.getInt(3));
            order.setQuantity(rs.getInt(4));
            order.setStatus(rs.getString(5));
            order.setOrderDate(rs.getString(6));
            orderList.add(order);
        }
        return orderList;
    }

    public static List<OrderPojo> getAllDeliveredOrders(String CustomerId)throws SQLException{
        PreparedStatement ps = conn.prepareStatement("select o.order_id, p.p_name,p_price,o.quantity, o.status, o.order_date from orders o join products p on o.p_id=p.p_id where status='Delivered' and customer_id=?");
        ps.setString(1, CustomerId);
        ResultSet rs = ps.executeQuery();
        List<OrderPojo> orderList = new ArrayList<>();
        while (rs.next()) {
            OrderPojo order = new OrderPojo();
            order.setOrderId(rs.getString(1));
            order.setProductName(rs.getString(2));
            order.setProductPrice(rs.getInt(3));
            order.setQuantity(rs.getInt(4));
            order.setStatus(rs.getString(5));
            order.setOrderDate(rs.getString(6));
            orderList.add(order);
        }
        return orderList;
    }

    public static List<OrderPojo> getAllPendingOrders()throws SQLException{
        String url = "select o.order_id, p.p_name,p_price,o.quantity, o.status,o.customer_id, o.order_date from orders o join products p on o.p_id=p.p_id where status='Pending'";
        PreparedStatement ps = conn.prepareStatement(url);
        ResultSet rs = ps.executeQuery();
        List<OrderPojo> orderList = new ArrayList<>();
        while (rs.next()) {
            OrderPojo order = new OrderPojo();
            order.setOrderId(rs.getString(1));
            order.setProductName(rs.getString(2));
            order.setProductPrice(rs.getInt(3));
            order.setQuantity(rs.getInt(4));
            order.setStatus(rs.getString(5));
            order.setCustomerId(rs.getString(6));
            order.setOrderDate(rs.getString(7));
            orderList.add(order);
        }
        return orderList;
    }

    public static void updateStatus(String customerId)throws SQLException{
        PreparedStatement ps = conn.prepareStatement("update orders set status='Delivered' where customer_id=? and status='Pending'");
        ps.setString(1, customerId);
        ps.executeUpdate();
    }

    public static List<OrderPojo> getAllDeliveredOrders()throws SQLException{
        PreparedStatement ps = conn.prepareStatement("select o.order_id,o.customer_id, p.p_name,p_price,o.quantity, o.status, o.order_date from orders o join products p on o.p_id=p.p_id where status='Delivered'");
        ResultSet rs = ps.executeQuery();
        List<OrderPojo> orderList = new ArrayList<>();
        while (rs.next()) {
            OrderPojo order = new OrderPojo();
            order.setOrderId(rs.getString(1));
            order.setCustomerId(rs.getString(2));
            order.setProductName(rs.getString(3));
            order.setProductPrice(rs.getInt(4));
            order.setQuantity(rs.getInt(5));
            order.setStatus(rs.getString(6));
            order.setOrderDate(rs.getString(7));
            orderList.add(order);
        }
        return orderList;
    }
}
