package org.happytummy.model;

import org.happytummy.pojo.OwnerPojo;

import java.sql.*;

public class OwnerModel {
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

    public static OwnerPojo  validateOwner(String username, String password)throws SQLException {
        PreparedStatement ps = conn.prepareStatement("select * from owner where username=? and password=?");
        ps.setString(1,username);
        ps.setString(2,password);
        ResultSet rs = ps.executeQuery();
        OwnerPojo o=null;
        if(rs.next()){
            o=new OwnerPojo();
            o.setUsername(rs.getString("username"));
            o.setEmailId(rs.getString("emailid"));
            o.setSecurityKey(rs.getString("security_key"));
        }
        return o;
    }
}
