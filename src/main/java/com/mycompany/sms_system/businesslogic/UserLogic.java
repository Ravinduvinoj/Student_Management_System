/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sms_system.businesslogic;

import com.mycompany.sms_system.entity.ChangePassBean;
import com.mycompany.sms_system.entity.UserBean;
import com.mycompany.sms_system.utill.Currentuser;
import com.mycompany.sms_system.utill.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author user
 */
public class UserLogic {

    public boolean createUser(UserBean usb) {
        Connection conn = null;
        Boolean flag = false;
        try {
            conn = new DBConnection().getConnection();
            String sql = "INSERT INTO login (Login_ID,Login_Name,Login_Password,Login_type) VALUES (?,?,?,?)";
            PreparedStatement statement = conn.prepareStatement(sql);

            // Set the parameters for the insert statement
            statement.setString(1, usb.getLogin_ID());
            statement.setString(2, usb.getLogin_name());
            statement.setString(3, usb.getLogin_pass());
            statement.setString(4, usb.getLogin_type());

            if (statement.executeUpdate() == 1) {
                System.out.println("data insertd success");

                flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public boolean updateUser(UserBean usb) {
        Connection conn = null;
        Boolean flag = false;
        try {
            conn = new DBConnection().getConnection();
            String sql = "UPDATE login set Login_Name=?,Login_Password=?,Login_type=? WHERE Login_ID=?";
            PreparedStatement statement = conn.prepareStatement(sql);

            // Set the parameters for the insert statement
            statement.setString(1, usb.getLogin_name());
            statement.setString(2, usb.getLogin_pass());
            statement.setString(3, usb.getLogin_type());
            statement.setString(4, usb.getLogin_ID());

            if (statement.executeUpdate() == 1) {
                System.out.println("data update success");

                flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public boolean removeUser(UserBean usb) {
        Connection conn = null;
        Boolean flag = false;
        try {
            conn = new DBConnection().getConnection();
            String sql = "DELETE FROM login WHERE Login_ID=?";
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, usb.getLogin_ID());

            if (statement.executeUpdate() == 1) {
                System.out.println("deleted success");

                flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public boolean Login(UserBean usb) {

        Connection conn = null;
        Boolean flag = false;
        try {
            conn = new DBConnection().getConnection();
            String sql = "SELECT * FROM login WHERE Login_Name=? AND Login_Password=? ";
            PreparedStatement statement = conn.prepareStatement(sql);

            // Set the parameters for the insert statement
            statement.setString(1, usb.getLogin_name());
            statement.setString(2, usb.getLogin_pass());

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                flag = true;
                System.out.println("data login success");

                //  String usttype = rs.getString("Login_type");
                //    String usname = usb.getLogin_name();
                Currentuser.setCurrentUserName(rs.getString("Login_Name"));
                Currentuser.setCurrentUserType(rs.getString("Login_type"));
                Currentuser.setCurrentUserID(rs.getString("Login_ID"));

            }

            //      usb.setShow_name();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public boolean changePassword(ChangePassBean chngPass) {

        Connection conn = null;
        Boolean flag = false;
        try {
            conn = new DBConnection().getConnection();
            String sql = "SELECT * FROM login WHERE Login_ID=? AND Login_Password=? ";
            PreparedStatement statement = conn.prepareStatement(sql);

            // Set the parameters for the insert statement
            statement.setString(1, chngPass.getLogin_Id());
            statement.setString(2, chngPass.getOldPass());
            

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                flag = true;
                System.out.println("correct Password");

                String sql2 = "UPDATE login set Login_Password=? WHERE Login_ID=?";
                PreparedStatement statement1 = conn.prepareStatement(sql2);
                
                statement1.setString(1, chngPass.getNewPass());
                statement1.setString(2, chngPass.getLogin_Id());
                
                statement1.executeUpdate();
                
            } else {
                System.out.println("Incorrect Password");
            }

            //      usb.setShow_name();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

}
