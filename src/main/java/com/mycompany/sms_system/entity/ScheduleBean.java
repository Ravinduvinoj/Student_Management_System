/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sms_system.entity;

/**
 *
 * @author User
 */
public class ScheduleBean {
    private String sch_ID;
    private String Subject;
    private String T_Name;
    private String Date;
    private String S_Time;
    private String E_Time;

    public String getSch_ID() {
        return sch_ID;
    }

    public void setSch_ID(String sch_ID) {
        this.sch_ID = sch_ID;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String Subject) {
        this.Subject = Subject;
    }

    public String getT_Name() {
        return T_Name;
    }

    public void setT_Name(String T_Name) {
        this.T_Name = T_Name;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public String getS_Time() {
        return S_Time;
    }

    public void setS_Time(String S_Time) {
        this.S_Time = S_Time;
    }

    public String getE_Time() {
        return E_Time;
    }

    public void setE_Time(String E_Time) {
        this.E_Time = E_Time;
    }


}
