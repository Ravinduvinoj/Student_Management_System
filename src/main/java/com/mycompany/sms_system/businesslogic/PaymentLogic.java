/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sms_system.businesslogic;

import com.mycompany.sms_system.entity.PaymentBean;
import com.mycompany.sms_system.utill.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author User
 */
public class PaymentLogic {

    public boolean payment(PaymentBean pay) {
        Connection conn = null;
        boolean flag = false;
        try {
            conn = new DBConnection().getConnection();

            String sql = "SELECT Subject_ID FROM subject WHERE Subject_Name =?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, pay.getSubject());
            ResultSet rs = pst.executeQuery();
            String Sub_ID = "";
            while (rs.next()) {
                Sub_ID = rs.getString("Subject_ID");
            }
            String sql3 = "SELECT * FROM assign_subject WHERE Student_ID=?";
            PreparedStatement pst3 = conn.prepareStatement(sql3);
            pst3.setString(1, pay.getS_ID());
            ResultSet rs3 = pst3.executeQuery();

            while (rs3.next()) {
                String pay_S = rs3.getString("Payment_Status");
                String p_status = "";
                if (pay_S.equals("Pending")) {
                    p_status = "Paid";

                }
                System.out.println(p_status);

                String sql1 = "INSERT INTO payment(Payment_ID, Student_ID, Subject_ID, Phone_Number, Discount,Amount,Pay_Month,Paid_Date,Paid_Time) VALUES (?,?,?,?,?,?,?,?,?)";
                PreparedStatement pst1 = conn.prepareStatement(sql1);
                pst1.setString(1, pay.getP_ID());
                pst1.setString(2, pay.getS_ID());
                pst1.setString(3, Sub_ID);
                pst1.setString(4, pay.getPhone_Num());
                pst1.setString(5, pay.getDiscount());
                pst1.setString(6, pay.getAmount());
                pst1.setString(7, pay.getMonth());
                pst1.setString(8, pay.getPaid_date());
                pst1.setString(9, pay.getPaid_Time());

                String sql2 = "Update assign_subject set Payment_Status=? Where Student_ID=?";
                PreparedStatement pst2 = conn.prepareStatement(sql2);
                pst2.setString(1, p_status);
                pst2.setString(2, pay.getS_ID());

                if (pst1.executeUpdate() == 1 && pst2.executeUpdate() == 1) {
                    System.out.println("data insertd success");

                    flag = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

}
