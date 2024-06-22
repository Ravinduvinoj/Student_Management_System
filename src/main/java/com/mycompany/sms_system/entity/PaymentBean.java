/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sms_system.entity;

/**
 *
 * @author User
 */
public class PaymentBean {

    private String P_ID;
    private String S_ID;
    private String Subject;
    private String Amount;
    private String month;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String Amount) {
        this.Amount = Amount;
    }
    private String phone_Num;
    private String paid_date;
    private String paid_Time;
    private String paid_Status;
    private String Discount;

    public String getPaid_Status() {
        return paid_Status;
    }

    public void setPaid_Status(String paid_Status) {
        this.paid_Status = paid_Status;
    }

    public String getPaid_Time() {
        return paid_Time;
    }

    public void setPaid_Time(String paid_Time) {
        this.paid_Time = paid_Time;
    }
    private String paid_status;

    public String getPaid_date() {
        return paid_date;
    }

    public void setPaid_date(String paid_date) {
        this.paid_date = paid_date;
    }

    public String getPaid_status() {
        return paid_status;
    }

    public void setPaid_status(String paid_status) {
        this.paid_status = paid_status;
    }

    public String getP_ID() {
        return P_ID;
    }

    public void setP_ID(String P_ID) {
        this.P_ID = P_ID;
    }

    public String getS_ID() {
        return S_ID;
    }

    public void setS_ID(String S_ID) {
        this.S_ID = S_ID;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String Subject) {
        this.Subject = Subject;
    }

    public String getPhone_Num() {
        return phone_Num;
    }

    public void setPhone_Num(String phone_Num) {
        this.phone_Num = phone_Num;
    }

    public String getDiscount() {
        return Discount;
    }

    public void setDiscount(String Discount) {
        this.Discount = Discount;
    }

}
