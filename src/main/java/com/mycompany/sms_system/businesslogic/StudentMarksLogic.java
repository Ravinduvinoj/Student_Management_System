/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sms_system.businesslogic;

import com.mycompany.sms_system.entity.StudentMarksBean;
import com.mycompany.sms_system.utill.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author User
 */
public class StudentMarksLogic {

    public boolean AddMarks(StudentMarksBean m) {
        Connection conn = null;
        Boolean flag = false;
        try {
            conn = new DBConnection().getConnection();
            String sql = "SELECT Subject_ID FROM subject WHERE Subject_Name =?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, m.getSub_Name());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                System.out.println("new");
                String r = rs.getString("Subject_ID");
                System.out.println(r);
                
                String sql1 = "INSERT INTO student_marks(Marks_ID,Student_ID,Subject_ID,Date,Test,Marks,Credit) VALUES (?,?,?,?,?,?,?)";
                PreparedStatement statement = conn.prepareStatement(sql1);
                statement.setString(1, m.getM_ID());
                statement.setString(2, m.getS_ID());
                statement.setString(3, r);
                statement.setString(4, m.getDates());
                statement.setString(5, m.getTest());
                statement.setString(6, m.getMarks());
                statement.setString(7, m.getCredit());
                
                if (statement.executeUpdate() == 1){
                     System.out.println("data1 insertd success");
                     flag= true;
                     
                     
                }
                
            }							

        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

     public boolean UpdateMarks(StudentMarksBean m) {
        Connection conn = null;
        Boolean flag = false;
        try {
            conn = new DBConnection().getConnection();
            String sql = "SELECT Subject_ID FROM subject WHERE Subject_Name =?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, m.getSub_Name());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                System.out.println("new");
                String r = rs.getString("Subject_ID");
                System.out.println(r);
                
                String sql1 = "INSERT INTO student_marks(Student_ID=?,Subject_ID=?,Date=?,Test=?,Marks=?,Credit=?) WHERE Marks_ID=?";
                PreparedStatement statement = conn.prepareStatement(sql1);
                
                statement.setString(1, m.getS_ID());
                statement.setString(2, r);
                statement.setString(3, m.getDates());
                statement.setString(4, m.getTest());
                statement.setString(5, m.getMarks());
                statement.setString(6, m.getCredit());
                statement.setString(7, m.getM_ID());
                
                if (statement.executeUpdate() == 1){
                     System.out.println("data update success");
                     flag= true;
                     
                     
                }
                
            }							

        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
      public boolean RemoveMarks(StudentMarksBean m) {
        Connection conn = null;
        Boolean flag = false;
        try {
            conn = new DBConnection().getConnection();
            String sql = "DELETE FROM student_marks Where Marks_ID=?";
            PreparedStatement statement = conn.prepareStatement(sql);


            statement.setString(1, m.getM_ID());

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
