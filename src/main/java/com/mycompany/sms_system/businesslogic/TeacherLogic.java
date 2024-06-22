/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sms_system.businesslogic;

import com.mycompany.sms_system.entity.StudentBean;
import com.mycompany.sms_system.entity.TeacherBean;
import com.mycompany.sms_system.utill.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author User
 */
public class TeacherLogic {

    public boolean AddTeacher(TeacherBean tBean) {
        Connection conn = null;
        Boolean flag = false;
        try {
            conn = new DBConnection().getConnection();

            String sql1 = "SELECT Subject_ID FROM subject WHERE Subject_Name =?";
            PreparedStatement pst = conn.prepareStatement(sql1);
            pst.setString(1, tBean.getT_subject());
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                System.out.println("new");
                String r = rs.getString("Subject_ID");
                System.out.println(r);

                String sql = "INSERT INTO teacher (Teacher_ID,Teacher_Name,Teacher_Address,"
                        + "Gender,Contact_Number,Email,Qualification) VALUES (?,?,?,?,?,?,?)";
                PreparedStatement statement = conn.prepareStatement(sql);

                // Set the parameters for the insert statement
                statement.setString(1, tBean.getT_id());
                statement.setString(2, tBean.getT_name());
                statement.setString(3, tBean.getT_address());
                statement.setString(4, tBean.getT_gen());
                statement.setString(5, tBean.getT_contact());
                statement.setString(6, tBean.getT_email());
                statement.setString(7, tBean.getT_qualif());
              

                String sql2 = "INSERT INTO teacher_subject(Teacher_ID,Subject_ID,registerd_date,registerd_time) VALUES (?,?,?,?)";
                PreparedStatement statement1 = conn.prepareStatement(sql2);
                statement1.setString(1, tBean.getT_id());
                statement1.setString(2, r);
                statement1.setString(3, tBean.getR_Date());
                statement1.setString(4, tBean.getR_Time());

                if (statement.executeUpdate() == 1 &&statement1.executeUpdate() == 1) {
                    System.out.println("data insertd success");

                    flag = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public boolean UpdateTeacher(TeacherBean tBean) {
        Connection conn = null;
        Boolean flag = false;
        try {
            conn = new DBConnection().getConnection();
            String sql1 = "SELECT Subject_ID FROM subject WHERE Subject_Name =?";
            PreparedStatement pst = conn.prepareStatement(sql1);
            pst.setString(1, tBean.getT_subject());
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                System.out.println("new");
                String r = rs.getString("Subject_ID");
                System.out.println(r);
            
            String sql = "UPDATE teacher set Teacher_Name=?,Teacher_Address=?,Gender=?,Contact_Number=?,Email=?,Qualification=? WHERE Teacher_ID=?";
            PreparedStatement statement = conn.prepareStatement(sql);

            // Set the parameters for the insert statement
            statement.setString(1, tBean.getT_name());
            statement.setString(2, tBean.getT_address());
            statement.setString(3, tBean.getT_gen());
            statement.setString(4, tBean.getT_contact());
            statement.setString(5, tBean.getT_email());
            statement.setString(6, tBean.getT_qualif());
            statement.setString(7, tBean.getT_id());
            
             String sql2 = "UPDATE teacher_subject set Subject_ID=? Where Teacher_ID=?";
            PreparedStatement statement1 = conn.prepareStatement(sql2);
            statement1.setString(1, r);
            statement1.setString(2, tBean.getT_id());

            if (statement.executeUpdate() == 1&&statement1.executeUpdate() == 1) {
                System.out.println("data update success");

                flag = true;
            }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public boolean RemoveTeacher(TeacherBean tBean) {
        Connection conn = null;
        Boolean flag = false;
        try {
            conn = new DBConnection().getConnection();
            String sql = "DELETE FROM teacher Where Teacher_ID=?";
            PreparedStatement statement = conn.prepareStatement(sql);

            // Set the parameters for the insert statement
            statement.setString(1, tBean.getT_id());

            if (statement.executeUpdate() == 1) {
                System.out.println("deleted success");

                flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

}
