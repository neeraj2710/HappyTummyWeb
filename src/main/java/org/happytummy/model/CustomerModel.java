package org.happytummy.model;

import org.happytummy.pojo.CustomerPojo;

import java.sql.*;

public class CustomerModel {
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
        ResultSet rs = st.executeQuery("select max(customer_id) from customers");
        rs.next();
        String id = rs.getString(1);
        String customerId = "";
        if (id != null) {
            id = id.substring(4);
            customerId = "CUS-" + (Integer.parseInt(id) + 1);
        } else {
            customerId = "CUS-101";
        }
        return customerId;
    }

    public static boolean addCustomer(CustomerPojo customer) throws SQLException {
        System.out.println(customer.toString());
        PreparedStatement ps = conn.prepareStatement("insert into customers values(?,?,?,?,?,?)");
        customer.setCustomerId(getNewId());
        ps.setString(1, customer.getCustomerId());

        ps.setString(2, customer.getCustomerEmail());
        ps.setString(3, customer.getCustomerPassword());
        ps.setInt(4, customer.getCustomerPhone());
        ps.setString(5, customer.getCustomerAddress());
        ps.setString(6, customer.getCustomerName());
        return ps.executeUpdate() == 1;
    }

    public static CustomerPojo validate(String emailId,String password) throws SQLException{
        System.out.println("["+emailId+","+password+"]");
        PreparedStatement ps = conn.prepareStatement("select * from customers where email_id=? and password=?");
        ps.setString(1, emailId);
        ps.setString(2, password);
        ResultSet rs= ps.executeQuery();
        CustomerPojo customer = null;
        if(rs.next()){
            customer=new CustomerPojo();
            customer.setCustomerId(rs.getString(1));
            customer.setCustomerName(rs.getString(6));
            customer.setCustomerEmail(emailId);

        }
        System.out.println(customer);
        return customer;
    }
}
