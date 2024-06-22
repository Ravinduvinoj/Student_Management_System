/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sms_system.businesslogic;

import com.mycompany.sms_system.entity.SubjectBean;
import com.mycompany.sms_system.utill.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class SubjectLogic {

    public boolean addSubject(SubjectBean subBean) {

        Connection conn = null;
        boolean flag = false;
        try {
            conn = new DBConnection().getConnection();
            String sql = "INSERT INTO subject(Subject_ID, Subject_Name, Subject_Fee, Grade) VALUES (?,?,?,?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, subBean.getSubject_ID());
            pst.setString(2, subBean.getSubject_Name());
            pst.setString(3, subBean.getSubject_Fee());
            pst.setString(4, subBean.getGrade());
            
            if(pst.executeUpdate()==1){
                System.out.println("data insertd success");
                
                flag = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
    
    public boolean updatesubject(SubjectBean subBean){
        
        Connection conn = null;
        boolean flag = false;
        try {
            conn = new DBConnection().getConnection();
            String sql = "UPDATE subject set Subject_Name=?, Subject_Fee=?, Grade=? WHERE Subject_ID=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            
            pst.setString(1, subBean.getSubject_Name());
            pst.setString(2, subBean.getSubject_Fee());
            pst.setString(3, subBean.getGrade());
            pst.setString(4, subBean.getSubject_ID());
          
            
            if(pst.executeUpdate()==1){
                System.out.println("updated success");
                flag = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public boolean removesubject(SubjectBean subBean){
        
        Connection conn = null;
        boolean flag = false;
        try {
            conn = new DBConnection().getConnection();
            
            String sql = ("DELETE FROM subject WHERE Subject_ID=?");
            PreparedStatement statement = conn.prepareStatement(sql);
            
            statement.setString(1,subBean.getSubject_ID() );
            
                if(statement.executeUpdate()==1){
                System.out.println("deleted success");
                flag = true;
            }
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
}

