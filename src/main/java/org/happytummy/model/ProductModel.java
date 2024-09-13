package org.happytummy.model;

import org.happytummy.pojo.ProductPojo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductModel {
    private static Connection conn;
    static {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@//localhost/xe","happytummyweb","happytummyweb");

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
        ResultSet rs = st.executeQuery("select max(p_id) from products");
        rs.next();
        String id = rs.getString(1);
        if (id != null) {
            id = id.substring(4);
            return "PRD-" + (Integer.parseInt(id) + 1);
        } else {
            return "PRD-101";
        }
    }

    public static boolean addProduct(ProductPojo p)throws SQLException{
        PreparedStatement ps = conn.prepareStatement("insert into products values(?,?,?,?)");
        ps.setString(1,getNewId());
        ps.setString(2,p.getpName());
        ps.setDouble(3,p.getpPrice());
        ps.setString(4,p.getpURL());
        return ps.executeUpdate()==0;
    }

    public static List<ProductPojo> getAllProduct() throws SQLException{
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select * from products");
        List<ProductPojo> list = new ArrayList<ProductPojo>();
        while(rs.next()){
            ProductPojo p = new ProductPojo();
            p.setpID(rs.getString(1));
            p.setpName(rs.getString(2));
            p.setpPrice(rs.getDouble(3));
            p.setpURL(rs.getString(4));
            list.add(p);
        }
        return list;
    }

    public static ProductPojo getProductById(String id) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("select * from products where p_id=?");
        ps.setString(1,id);
        ResultSet rs = ps.executeQuery();
        ProductPojo p = new ProductPojo();
        if(rs.next()){
            p.setpID(rs.getString(1));
            p.setpName(rs.getString(2));
            p.setpPrice(rs.getDouble(3));
            p.setpURL(rs.getString(4));
        }
        return p;
    }

    public static boolean updateProduct(ProductPojo p) throws SQLException{
        String url = "update products set p_name=?,p_price=? where p_id=?";
        PreparedStatement ps = conn.prepareStatement(url);
        ps.setString(1,p.getpName());
        ps.setDouble(2,p.getpPrice());
        ps.setString(3,p.getpID());
        return ps.executeUpdate()==1;
    }
}
