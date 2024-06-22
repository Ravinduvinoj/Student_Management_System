/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sms_system.businesslogic;

import com.mycompany.sms_system.entity.StaffBean;
import com.mycompany.sms_system.utill.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author user
 */
public class StaffLogic {

    public boolean addStaff(StaffBean staff) {
        Connection conn = null;
        boolean flag = false;
        try {
            conn = new DBConnection().getConnection();
            String sql = "INSERT INTO staff(Staff_ID, Employee_Name, NIC, Contact_number, Email) VALUES (?,?,?,?,?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, staff.getStaff_ID());
            pst.setString(2, staff.getEmpName());
            pst.setString(3, staff.getNIC());
            pst.setString(4, staff.getContact_number());
            pst.setString(5, staff.getEmail());

            if (pst.executeUpdate() == 1) {
                System.out.println("data insertd success");

                flag = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public boolean updateStaff(StaffBean staff) {
        Connection conn = null;
        boolean flag = false;
        try {
            conn = new DBConnection().getConnection();
            String sql = "UPDATE staff set Employee_Name=?, NIC=?, Contact_number=?, Email=?  WHERE Staff_ID=?";
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, staff.getEmpName());
            pst.setString(2, staff.getNIC());
            pst.setString(3, staff.getContact_number());
            pst.setString(4, staff.getEmail());
            pst.setString(5, staff.getStaff_ID());

            if (pst.executeUpdate() == 1) {
                System.out.println("updated success");

                flag = true;
            }

        } catch (Exception e) {
            System.out.println("unable to update");
            e.printStackTrace();
        }
        return flag;
    }

    public boolean removeStaff(StaffBean staff) {
         Connection conn = null;
        boolean flag = false;
        try {
            conn = new DBConnection().getConnection();
            String sql = ("DELETE FROM staff WHERE Staff_ID=?");
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, staff.getStaff_ID());
             if(pst.executeUpdate()==1){
                System.out.println("deleted success");
                flag = true;
            }
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
}
