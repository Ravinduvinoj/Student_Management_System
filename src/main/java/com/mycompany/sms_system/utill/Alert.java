/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sms_system.utill;

/**
 *
 * @author User
 */
public class Alert {
    private static boolean wrongStudent;

    public static boolean isWrongStudent() {
        return wrongStudent;
    }

    public static void setWrongStudent(boolean wrongStudent) {
        Alert.wrongStudent = wrongStudent;
    }

    
    
}
