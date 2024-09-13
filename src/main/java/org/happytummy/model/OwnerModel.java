package org.happytummy.model;

import org.happytummy.pojo.OwnerPojo;

import java.sql.*;

public class OwnerModel {
    private static Connection conn;
    static {
        try{

            conn = DriverManager.getConnection("jdbc:oracle:thin:@//localhost/xe","happytummyweb","happytummyweb");

        }catch (SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
            ex.printStackTrace();
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
