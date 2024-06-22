/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sms_system.businesslogic;

import com.mycompany.sms_system.entity.StudentBean;
import com.mycompany.sms_system.utill.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author User
 */
public class StudentLogic {

    public boolean AddStudent(StudentBean stu) {
        Connection conn = null;
        Boolean flag = false;
        try {
            conn = new DBConnection().getConnection();
            String sql1 = "SELECT Subject_ID FROM subject WHERE Subject_Name =?";
            PreparedStatement pst = conn.prepareStatement(sql1);
            pst.setString(1, stu.getSubject());
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                System.out.println("new");
                String r = rs.getString("Subject_ID");
                System.out.println(r);
                String sql = "INSERT INTO student (Student_ID,Student_Name,Full_Name,S_Address,Contact_Number,Date_of_Birth,NIC,Gender,English_OL,Year_of_OL) VALUES (?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement statement = conn.prepareStatement(sql);

                // Set the parameters for the insert statement
                statement.setString(1, stu.getStudent_ID());
                statement.setString(2, stu.getStudent_Name());
                statement.setString(3, stu.getFull_Name());
                statement.setString(4, stu.getS_Address());
                statement.setString(5, stu.getContact_Number());
                statement.setString(6, stu.getDate_of_Birth());
                statement.setString(7, stu.getNIC());
                statement.setString(8, stu.getGender());
                statement.setString(9, stu.getEnglish_OL());
                statement.setString(10, stu.getYear_of_OL());

                String payble = "";
                if (stu.getCurrentMonthPayment() == "Released") {
                    payble = "Free";
                } else if (stu.getCurrentMonthPayment() == "To Pay") {
                    payble = "Pending";
                }

                String R_Date = stu.getR_Date();
                SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat getMonth = new SimpleDateFormat("yyyy-MM");

                String currentMonth = "";
                try {
                    Date date = inputFormat.parse(R_Date);
                    currentMonth = getMonth.format(date);

                } catch (ParseException e) {
                    e.printStackTrace();
                }

                String sql2 = "INSERT INTO assign_subject(Student_ID,Subject_ID,registerd_date,registerd_time,Reg_Month_fee,Pay_Month,Payment_Status) VALUES (?,?,?,?,?,?,?)";
                PreparedStatement statement2 = conn.prepareStatement(sql2);
                statement2.setString(1, stu.getStudent_ID());
                statement2.setString(2, r);
                statement2.setString(3, stu.getR_Date());
                statement2.setString(4, stu.getR_Time());
                statement2.setString(5, stu.getCurrentMonthPayment());
                statement2.setString(6, currentMonth);
                statement2.setString(7, payble);
                if ( statement.executeUpdate() == 1 && statement2.executeUpdate() == 1) {
                    System.out.println("data1 insertd success");
                    flag = true;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public boolean UpdateStudent(StudentBean stu) {
        Connection conn = null;
        Boolean flag = false;
        try {
            conn = new DBConnection().getConnection();
            String sql1 = "SELECT Subject_ID FROM subject WHERE Subject_Name =?";
            PreparedStatement pst = conn.prepareStatement(sql1);
            pst.setString(1, stu.getSubject());
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                System.out.println("new");
                String r = rs.getString("Subject_ID");
                System.out.println(r);
                String sql = "UPDATE student set Student_Name=?,Full_Name=?,S_Address=?,Contact_Number=?,Date_of_Birth=?,NIC=?,Gender=?,English_OL=?,Year_of_OL=? Where Student_ID=?";
                PreparedStatement statement = conn.prepareStatement(sql);

                // Set the parameters for the insert statement
                statement.setString(1, stu.getStudent_Name());
                statement.setString(2, stu.getFull_Name());
                statement.setString(3, stu.getS_Address());
                statement.setString(4, stu.getContact_Number());
                statement.setString(5, stu.getDate_of_Birth());
                statement.setString(6, stu.getNIC());
                statement.setString(7, stu.getGender());
                statement.setString(8, stu.getEnglish_OL());
                statement.setString(9, stu.getYear_of_OL());
                statement.setString(10, stu.getStudent_ID());

                String sql2 = "UPDATE assign_subject set Subject_ID=? Where Student_ID=?";
                PreparedStatement statement1 = conn.prepareStatement(sql2);
                statement1.setString(1, r);
                statement1.setString(2, stu.getStudent_ID());

                if (statement.executeUpdate() == 1 && statement1.executeUpdate() == 1) {
                    System.out.println("data update success");

                    flag = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public boolean RemoveStudent(StudentBean stu) {
        Connection conn = null;
        Boolean flag = false;
        try {
            conn = new DBConnection().getConnection();
            String sql = "DELETE FROM student Where Student_ID=?";
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, stu.getStudent_ID());

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
