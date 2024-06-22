/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sms_system.utill;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author user
 */

public class DBConnection {

    public Connection getConnection() throws SQLException {

        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
          con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sms_system","root", "");
            System.out.println("DB Connected Success");
        } catch (Exception e) {
            System.out.println("Error in DB Connection");
            e.printStackTrace();
        }
        
        return con;
    }}
    

    


