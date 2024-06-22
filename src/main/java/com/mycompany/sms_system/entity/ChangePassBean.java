/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sms_system.entity;

/**
 *
 * @author User
 */
public class ChangePassBean {
    private String login_Id;
    private String oldPass;
    private String newPass;

    public String getLogin_Id() {
        return login_Id;
    }

    public void setLogin_Id(String login_Id) {
        this.login_Id = login_Id;
    }

    public String getOldPass() {
        return oldPass;
    }

    public void setOldPass(String oldPass) {
        this.oldPass = oldPass;
    }

    public String getNewPass() {
        return newPass;
    }

    public void setNewPass(String newPass) {
        this.newPass = newPass;
    }
    
    
    
}
