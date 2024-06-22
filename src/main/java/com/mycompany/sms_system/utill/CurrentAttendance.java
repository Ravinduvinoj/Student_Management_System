/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sms_system.utill;

/**
 *
 * @author User
 */
public class CurrentAttendance {
    private static String CurrentStudentName;
    private static String CurrentStudentID;
    private static String CurrentPaidStatus;
    private static String CurrentPaidDate;

    public static String getCurrentStudentName() {
        return CurrentStudentName;
    }

    public static void setCurrentStudentName(String CurrentStudentName) {
        CurrentAttendance.CurrentStudentName = CurrentStudentName;
    }

    public static String getCurrentStudentID() {
        return CurrentStudentID;
    }

    public static void setCurrentStudentID(String CurrentStudentID) {
        CurrentAttendance.CurrentStudentID = CurrentStudentID;
    }

    public static String getCurrentPaidStatus() {
        return CurrentPaidStatus;
    }

    public static void setCurrentPaidStatus(String CurrentPaidStatus) {
        CurrentAttendance.CurrentPaidStatus = CurrentPaidStatus;
    }

    public static String getCurrentPaidDate() {
        return CurrentPaidDate;
    }

    public static void setCurrentPaidDate(String CurrentPaidDate) {
        CurrentAttendance.CurrentPaidDate = CurrentPaidDate;
    }
}
