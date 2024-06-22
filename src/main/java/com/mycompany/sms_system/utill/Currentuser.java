/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sms_system.utill;

/**
 *
 * @author User
 */
public class Currentuser {
    private static String CurrentUserName;
    private static String CurrentUserType;
    private static String currentUserID;

    public static String getCurrentUserID() {
        return currentUserID;
    }

    public static void setCurrentUserID(String currentUserID) {
        Currentuser.currentUserID = currentUserID;
    }

    public static String getCurrentUserName() {
        return CurrentUserName;
    }

    public static void setCurrentUserName(String CurrentUserName) {
        Currentuser.CurrentUserName = CurrentUserName;
    }

    public static String getCurrentUserType() {
        return CurrentUserType;
    }

    public static void setCurrentUserType(String CurrentUserType) {
        Currentuser.CurrentUserType = CurrentUserType;
    }
    
}
