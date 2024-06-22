/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sms_system.entity;

import com.mysql.cj.util.DnsSrv;

/**
 *
 * @author User
 */
public class AttendanceBean {

    private String AtuId;
    private String stuId;
    private String Dates;
    private String Time;
    private String AStatus;
    private String Subject;

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String Subject) {
        this.Subject = Subject;
    }

    public String getAtuId() {
        return AtuId;
    }

    public void setAtuId(String AtuId) {
        this.AtuId = AtuId;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getDates() {
        return Dates;
    }

    public void setDates(String Dates) {
        this.Dates = Dates;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String Time) {
        this.Time = Time;
    }

    public String getAStatus() {
        return AStatus;
    }

    public void setAStatus(String AStatus) {
        this.AStatus = AStatus;
    }
}
