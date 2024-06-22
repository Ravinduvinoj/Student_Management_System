/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sms_system.businesslogic;

import com.mycompany.sms_system.entity.AttendanceBean;
import com.mycompany.sms_system.utill.Alert;
import com.mycompany.sms_system.utill.CurrentAttendance;
import com.mycompany.sms_system.utill.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author User
 */
public class AttendanceLogic {
        public boolean addAttend(AttendanceBean a) {
        Connection conn = null;
        boolean flag = false;
        try {
            conn = new DBConnection().getConnection();
            String sql = "SELECT Subject_ID FROM subject WHERE Subject_Name =?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, a.getSubject());
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                

                System.out.println("new");
                String r = rs.getString("Subject_ID");
                System.out.println(r);
                 
                String sql4 = "select * from student where Student_ID =?";
                PreparedStatement pst4 = conn.prepareStatement(sql4);
                pst4.setString(1, a.getStuId());
                ResultSet stu = pst4.executeQuery();
                if(stu.next()){
                    CurrentAttendance.setCurrentStudentID(stu.getString("Student_ID"));
                    CurrentAttendance.setCurrentStudentName(stu.getString("Student_Name"));
                    String sql5 = "select * from assign_subject where Student_ID =?";
                    PreparedStatement pst5 = conn.prepareStatement(sql5);
                     pst5.setString(1, a.getStuId());
                      ResultSet pa = pst5.executeQuery();
                      if(pa.next()){
                          CurrentAttendance.setCurrentPaidStatus(pa.getString("Payment_Status"));
                      }
                    
                   String sql3 = "INSERT INTO attendance(Attendance_ID, Student_ID, Subject_ID, Date, Time,Status) VALUES (?,?,?,?,?,?)";
                    PreparedStatement pst2 = conn.prepareStatement(sql3);
                    pst2.setString(1, a.getAtuId());
                    pst2.setString(2, a.getStuId());
                    pst2.setString(3, r);
                    pst2.setString(4, a.getDates());
                    pst2.setString(5, a.getTime());
                    pst2.setString(6, a.getAStatus());
                    if (pst2.executeUpdate() == 1) {
                        System.out.println("data insertd success");
                        flag = true;
                        					
                    } 
                }
                else{
                    Alert.setWrongStudent(true);
                }



                    
                
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
}
