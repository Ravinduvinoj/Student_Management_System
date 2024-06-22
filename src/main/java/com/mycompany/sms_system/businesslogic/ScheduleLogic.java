/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sms_system.businesslogic;

import com.mycompany.sms_system.entity.ScheduleBean;
import com.mycompany.sms_system.utill.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author User
 */
public class ScheduleLogic {

    public boolean addSchedule(ScheduleBean sch) {
        Connection conn = null;
        boolean flag = false;
        try {
            conn = new DBConnection().getConnection();
            String sql = "SELECT Subject_ID FROM subject WHERE Subject_Name =?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, sch.getSubject());
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                System.out.println("new");
                String r = rs.getString("Subject_ID");
                System.out.println(r);

                String sql2 = "SELECT Teacher_ID FROM teacher WHERE Teacher_Name =?";
                PreparedStatement pst1 = conn.prepareStatement(sql2);
                pst1.setString(1, sch.getT_Name());
                ResultSet rs1 = pst1.executeQuery();

                if (rs1.next()) {

                    System.out.println("new1");
                    String rl = rs1.getString("Teacher_ID");
                    System.out.println(rl);

                    String sql3 = "INSERT INTO schedule(Schedule_ID, Subject_ID, Teacher_ID, Date, Start_Time,End_Time) VALUES (?,?,?,?,?,?)";
                    PreparedStatement pst2 = conn.prepareStatement(sql3);
                    pst2.setString(1, sch.getSch_ID());
                    pst2.setString(2, r);
                    pst2.setString(3, rl);
                    pst2.setString(4, sch.getDate());
                    pst2.setString(5, sch.getS_Time());
                    pst2.setString(6, sch.getE_Time());
                    if (pst2.executeUpdate() == 1) {
                        System.out.println("data insertd success");
                        flag = true;
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public boolean UpdateSchedule(ScheduleBean sch) {
        Connection conn = null;
        boolean flag = false;
        try {
            conn = new DBConnection().getConnection();
            
            String sql = "SELECT Subject_ID FROM subject WHERE Subject_Name =?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, sch.getSubject());
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                System.out.println("new");
                String r = rs.getString("Subject_ID");
                System.out.println(r);

                String sql2 = "SELECT Teacher_ID FROM teacher WHERE Teacher_Name =?";
                PreparedStatement pst1 = conn.prepareStatement(sql2);
                pst1.setString(1, sch.getT_Name());
                ResultSet rs1 = pst1.executeQuery();

                if (rs1.next()) {

                    System.out.println("new1");
                    String rl = rs1.getString("Teacher_ID");
                    System.out.println(rl);

                    String sql3 = "Update schedule set  Subject_ID=?, Teacher_ID=?, Date=?, Start_Time=?,End_Time=? Where Schedule_ID=?";
                    PreparedStatement pst2 = conn.prepareStatement(sql3);

                    pst2.setString(1, r);
                    pst2.setString(2, rl);
                    pst2.setString(3, sch.getDate());
                    pst2.setString(4, sch.getS_Time());
                    pst2.setString(5, sch.getE_Time());
                    pst2.setString(6, sch.getSch_ID());
                    if (pst2.executeUpdate() == 1) {
                        System.out.println("data Updated success");
                        flag = true;
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public boolean RemoveSchedule(ScheduleBean sch) {
        Connection conn = null;
        boolean flag = false;
        try {
            conn = new DBConnection().getConnection();
            String sql = ("DELETE FROM schedule WHERE Schedule_ID=?");
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, sch.getSch_ID());
            if (pst.executeUpdate() == 1) {
                System.out.println("deleted success");
                flag = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
}
