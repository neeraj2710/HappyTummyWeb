package org.happytummy.model;

import org.happytummy.pojo.StaffPojo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StaffModel {
    private static Connection conn;
    static {
        try {

            conn = DriverManager.getConnection("jdbc:oracle:thin:@//localhost/xe","happytummyweb","happytummyweb");

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            ex.printStackTrace();
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
        ps.setString(1,getNewId());
        ps.setString(2,staff.getFname());
        ps.setString(3,staff.getLname());
        ps.setString(4,staff.getEmailId());
        ps.setString(5,staff.getGender());
        ps.setString(6,staff.getRole());
        ps.setDouble(7,staff.getSalary());

        return ps.executeUpdate()==1;
    }

    public static List<StaffPojo> getAllStaff()throws SQLException{
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select * from staff");
        List<StaffPojo> list = new ArrayList<StaffPojo>();
        while (rs.next()) {
            StaffPojo staff = new StaffPojo();
            staff.setId(rs.getString(1));
            staff.setFname(rs.getString(2));
            staff.setLname(rs.getString(3));
            staff.setEmailId(rs.getString(4));
            staff.setGender(rs.getString(4));
            staff.setRole(rs.getString(6));
            staff.setSalary(rs.getDouble(7));

            list.add(staff);
        }
        return list;
    }
}
