package org.happytummy.model;

import org.happytummy.pojo.StaffPojo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StaffModel {
    private static Connection conn;
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/happytummy", "root", "neeraj");

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            ex.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static String getNewId()throws SQLException{

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select max(id) from staff");
            rs.next();
            String id = rs.getString(1);
            String staffId = "";
            if (id != null) {
                id = id.substring(4);
                staffId = "STF-" + (Integer.parseInt(id) + 1);
            } else {
                staffId = "STF-101";
            }
            return staffId;


    }

    public static boolean addStaff(StaffPojo staff)throws SQLException{
        PreparedStatement ps = conn.prepareStatement("insert into staff values(?,?,?,?,?,?,?)");
        ps.setString(1,staff.getFname());
        ps.setString(2,staff.getLname());
        ps.setString(3,staff.getEmailId());
        ps.setString(4,staff.getGender());
        ps.setString(5,staff.getRole());
        ps.setDouble(6,staff.getSalary());
        ps.setString(7,getNewId());
        return ps.executeUpdate()==1;
    }

    public static List<StaffPojo> getAllStaff()throws SQLException{
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select * from staff");
        List<StaffPojo> list = new ArrayList<StaffPojo>();
        while (rs.next()) {
            StaffPojo staff = new StaffPojo();
            staff.setFname(rs.getString(1));
            staff.setLname(rs.getString(2));
            staff.setEmailId(rs.getString(3));
            staff.setGender(rs.getString(4));
            staff.setRole(rs.getString(5));
            staff.setSalary(rs.getDouble(6));
            staff.setId(rs.getString(7));
            list.add(staff);
        }
        return list;
    }
}
