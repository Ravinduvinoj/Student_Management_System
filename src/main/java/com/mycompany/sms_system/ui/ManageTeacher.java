/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.sms_system.ui;

import com.mycompany.sms_system.businesslogic.TeacherLogic;
import com.mycompany.sms_system.entity.TeacherBean;
import com.mycompany.sms_system.utill.DBConnection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author kavin
 */
public class ManageTeacher extends javax.swing.JFrame {

    /**
     * Creates new form TeacherRegistationUI
     */
    public ManageTeacher() {
        initComponents();
        dt(); // daet

        times(); // time

        //set full screen
        // setExtendedState(ManageTeacher.MAXIMIZED_BOTH);
        table_update();
        AutoID();
        updateComboSubject();
    }

    public void dt() {

        java.util.Date d = new java.util.Date();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MMM-dd");

        String dd = sdf.format(d);
        lblDate.setText(dd);

    }
    // time
    Timer t;
    SimpleDateFormat st;

    public void times() {

        t = new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

                java.util.Date dt = new java.util.Date();
                st = new SimpleDateFormat("hh:mm:ss a");

                String tt = st.format(dt);
                lblTime.setText(tt);

            }
        });

        t.start();

    }
    PreparedStatement pst;
    ResultSet rs;

    private void updateComboSubject() {
        Connection conn = null;
        String sql = "select * from subject";
        try {
            conn = new DBConnection().getConnection();
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                cbAssSub.addItem(rs.getString("Subject_Name"));
            }
        } catch (Exception e) {
        }
    }

    public final void AutoID() {
        Connection conn = null;
        try {
            conn = new DBConnection().getConnection();
            java.sql.Statement s = conn.createStatement();
            rs = s.executeQuery("SELECT MAX(Teacher_ID) FROM teacher");
            rs.next();
            String maxTeuID = rs.getString("MAX(Teacher_ID)");
            if (maxTeuID == null) {
                lblTID.setText("T00001");
            } else {
                int id = Integer.parseInt(maxTeuID.substring(1));
                id++;
                String newTeuID = String.format("T%05d", id);
                lblTID.setText(newTeuID);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManageTeacher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void table_update() {

        try {
            Connection conn = new DBConnection().getConnection();
            Statement st1 = conn.createStatement();
            Statement st2 = conn.createStatement();
            Statement st3 = conn.createStatement();

            ResultSet rs1 = st1.executeQuery("SELECT * FROM teacher");

            // Clear existing rows from the table
            DefaultTableModel model = (DefaultTableModel) rSTableMetro1.getModel();
            model.setRowCount(0);

            while (rs1.next()) {
                String Teacher_ID = rs1.getString("Teacher_ID");
                String Teacher_Name = rs1.getString("Teacher_Name");
                String Teacher_Address = rs1.getString("Teacher_Address");
                String Gender = rs1.getString("Gender");
                String Contact_Number = rs1.getString("Contact_Number");
                String Email = rs1.getString("Email");
                String Qualification = rs1.getString("Qualification");

                ResultSet rs3 = st3.executeQuery("SELECT * FROM teacher_subject WHERE Teacher_ID = '" + Teacher_ID + "'");
                String sub_i = "";
                String Reg_D = "";
                String Reg_T = "";
                if (rs3.next()) {
                    sub_i = rs3.getString("Subject_ID");
                    Reg_D = rs3.getString("registerd_date");
                    Reg_T = rs3.getString("registerd_time");
                }
                rs3.close();
                ResultSet rs2 = st2.executeQuery("SELECT Subject_Name FROM subject WHERE Subject_ID = '" + sub_i + "'");

                String Sub_N = "";
                if (rs2.next()) {
                    Sub_N = rs2.getString("Subject_Name");
                }

                rs2.close();

                Object[] obj = {Teacher_ID, Teacher_Name, Teacher_Address, Gender, Contact_Number, Email, Qualification, Sub_N, Reg_D, Reg_T};
                model.addRow(obj);
            }

            // Close the database resources
            rs1.close();

            st1.close();
            st2.close();
            st3.close();

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();

        }

    }
    

//    private void table_update() {
//        int CC;
//        Connection conn = null;
//        try {
//            conn = new DBConnection().getConnection();
//
//            pst = conn.prepareStatement("SELECT * FROM teacher");
//            ResultSet Rs = pst.executeQuery();
//            java.sql.ResultSetMetaData RSMD = Rs.getMetaData();
//            CC = RSMD.getColumnCount();
//            DefaultTableModel DFT = (DefaultTableModel) rSTableMetro1.getModel();
//            DFT.setRowCount(0);
//
//            while (Rs.next()) {
//                Vector v2 = new Vector();
//                for (int ii = 1; ii <= CC; ii++) {
//                    v2.add(Rs.getString("Teacher_ID"));
//                    v2.add(Rs.getString("Teacher_Name"));
//                    v2.add(Rs.getString("Teacher_Address"));
//                    v2.add(Rs.getString("Gender"));
//                    v2.add(Rs.getString("Contact_Number"));
//                    v2.add(Rs.getString("Email"));
//                    v2.add(Rs.getString("Qualification"));
//                    v2.add(Rs.getString("Subject"));
//
//                }
//                DFT.addRow(v2);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(ManageTeacher.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
       public static boolean validateEmail(String email) {
        String pattern = "^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$";
        return email.matches(pattern);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grpTeagender = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnClose = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtTeaName = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtTeaAddress = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtTeaContact = new javax.swing.JTextField();
        txtTeaEmail = new javax.swing.JTextField();
        lblinvalidEmail = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        rbtTeaMale = new javax.swing.JRadioButton();
        rbtTeaFemale = new javax.swing.JRadioButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        lblTID = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        cbAssSub = new javax.swing.JComboBox<>();
        btnUpdate = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnRemove = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtaTeaQulifi = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        txtTeaID = new javax.swing.JTextField();
        jPanel18 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        lblDate = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        lblTime = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        rSTableMetro1 = new rojeru_san.complementos.RSTableMetro();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(153, 204, 255));
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(153, 204, 255));

        jPanel2.setBackground(new java.awt.Color(153, 204, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(1184, 100));

        jLabel1.setFont(new java.awt.Font("Serif", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Teacher");

        btnClose.setBackground(new java.awt.Color(204, 0, 0));
        btnClose.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 20)); // NOI18N
        btnClose.setForeground(new java.awt.Color(255, 255, 255));
        btnClose.setText("X");
        btnClose.setBorder(null);
        btnClose.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnClose.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnClose.setName(""); // NOI18N
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 486, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnClose)
            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel3.setBackground(new java.awt.Color(153, 204, 255));

        jPanel5.setBackground(new java.awt.Color(153, 204, 255));

        jPanel6.setBackground(new java.awt.Color(204, 255, 255));

        jLabel3.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 24)); // NOI18N
        jLabel3.setText("Personal details");

        jLabel4.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 16)); // NOI18N
        jLabel4.setText("Name");

        jLabel6.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 16)); // NOI18N
        jLabel6.setText("Address");

        jLabel8.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 16)); // NOI18N
        jLabel8.setText("Gender");

        jLabel10.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 16)); // NOI18N
        jLabel10.setText("Contact Number");

        jLabel11.setText(" ");

        txtTeaContact.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTeaContactKeyTyped(evt);
            }
        });

        txtTeaEmail.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtTeaEmailFocusLost(evt);
            }
        });

        lblinvalidEmail.setForeground(new java.awt.Color(204, 0, 0));
        lblinvalidEmail.setText(" ");

        jLabel13.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 16)); // NOI18N
        jLabel13.setText("Email");

        grpTeagender.add(rbtTeaMale);
        rbtTeaMale.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 16)); // NOI18N
        rbtTeaMale.setText("Male");

        grpTeagender.add(rbtTeaFemale);
        rbtTeaFemale.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 16)); // NOI18N
        rbtTeaFemale.setText("Female");

        jLabel9.setText(" ");

        jLabel18.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        jLabel18.setText("Teacher_ID :");

        lblTID.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        lblTID.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTID.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(txtTeaAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(txtTeaContact, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtTeaEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(rbtTeaMale, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(rbtTeaFemale, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblinvalidEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 47, Short.MAX_VALUE))))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(txtTeaName, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(55, 55, 55)
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblTID, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3)
                        .addGap(36, 36, 36))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblTID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtTeaName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtTeaAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(rbtTeaMale)
                        .addComponent(rbtTeaFemale)))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtTeaContact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtTeaEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblinvalidEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE))
                .addGap(24, 24, 24))
        );

        jPanel7.setBackground(new java.awt.Color(204, 255, 255));

        jLabel14.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 24)); // NOI18N
        jLabel14.setText("Other details");

        jLabel15.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 16)); // NOI18N
        jLabel15.setText("Qualifications");

        jLabel16.setText(" ");

        jLabel17.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 16)); // NOI18N
        jLabel17.setText("Assign subjects");

        cbAssSub.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 16)); // NOI18N

        btnUpdate.setBackground(new java.awt.Color(204, 204, 204));
        btnUpdate.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 20)); // NOI18N
        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnClear.setBackground(new java.awt.Color(204, 204, 204));
        btnClear.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 20)); // NOI18N
        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        btnAdd.setBackground(new java.awt.Color(204, 204, 204));
        btnAdd.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 20)); // NOI18N
        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnRemove.setBackground(new java.awt.Color(204, 204, 204));
        btnRemove.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 20)); // NOI18N
        btnRemove.setText("Remove");
        btnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveActionPerformed(evt);
            }
        });

        txtaTeaQulifi.setColumns(20);
        txtaTeaQulifi.setRows(5);
        jScrollPane2.setViewportView(txtaTeaQulifi);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(80, 80, 80)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(27, 27, 27)
                                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(cbAssSub, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnRemove, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 70, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addGap(33, 33, 33)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jLabel16))
                        .addGap(0, 57, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbAssSub, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addGap(20, 20, 20)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRemove, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44))
        );

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Teacher_ID");

        txtTeaID.setFont(new java.awt.Font("Yu Gothic Light", 0, 14)); // NOI18N
        txtTeaID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTeaIDKeyReleased(evt);
            }
        });

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));

        jLabel20.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 16)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Date :");

        lblDate.setFont(new java.awt.Font("PMingLiU-ExtB", 0, 16)); // NOI18N
        lblDate.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        jLabel21.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 16)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("Time :");

        lblTime.setFont(new java.awt.Font("PMingLiU-ExtB", 0, 16)); // NOI18N
        lblTime.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(lblDate, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTime, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblDate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        rSTableMetro1.setBackground(new java.awt.Color(153, 204, 255));
        rSTableMetro1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Teacher_ID", "T_Name", "T_Address", "Gender", "Contact Number", "Email", "Qualifications", "Subject", "Reg Date", "Reg Time"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        rSTableMetro1.setFuenteFilas(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        rSTableMetro1.setFuenteFilasSelect(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        rSTableMetro1.setFuenteHead(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        rSTableMetro1.setRowHeight(30);
        rSTableMetro1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rSTableMetro1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(rSTableMetro1);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtTeaID, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(30, 30, 30)
                                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(23, 23, 23))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTeaID, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1269, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 714, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = formatter.format(date);

        String T_ID = lblTID.getText();
        String T_Name = txtTeaName.getText();
        String T_Address = txtTeaAddress.getText();
        rbtTeaMale.setActionCommand("Male");
        rbtTeaFemale.setActionCommand("Female");
        String T_Gender = grpTeagender.getSelection().getActionCommand();
        String T_Contact = txtTeaContact.getText();
        String T_Email = txtTeaEmail.getText();
        String T_qulf = txtaTeaQulifi.getText();
        String T_Subject = cbAssSub.getSelectedItem().toString();
        String R_Time = lblTime.getText();
        System.out.println(T_Subject);
        TeacherBean tBean = new TeacherBean();
        tBean.setT_id(T_ID);
        tBean.setT_name(T_Name);
        tBean.setT_address(T_Address);
        tBean.setT_gen(T_Gender);
        tBean.setT_contact(T_Contact);
        tBean.setT_email(T_Email);
        tBean.setT_qualif(T_qulf);
        tBean.setT_subject(T_Subject);
        tBean.setR_Date(strDate);
        tBean.setR_Time(R_Time);

        boolean AddTeacher = new TeacherLogic().AddTeacher(tBean);

        if (AddTeacher) {
            JOptionPane.showMessageDialog(this, "successfully ADD");
            AutoID();

            txtTeaName.setText(null);
            txtTeaAddress.setText(null);
            cbAssSub.setSelectedIndex(0);
            rbtTeaMale.setSelected(false);
            rbtTeaFemale.setSelected(false);
            txtTeaContact.setText(null);
            txtTeaEmail.setText(null);
            txtaTeaQulifi.setText(null);
            grpTeagender.clearSelection();
            txtTeaName.requestFocus();
            table_update();

        } else {
            JOptionPane.showMessageDialog(this, "Unable to ADD ");
        }
        table_update();

    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        String T_ID = lblTID.getText();
        String T_Name = txtTeaName.getText();
        String T_Address = txtTeaAddress.getText();
        rbtTeaMale.setActionCommand("Male");
        rbtTeaFemale.setActionCommand("Female");
        String T_Gender = grpTeagender.getSelection().getActionCommand();

        String T_Contact = txtTeaContact.getText();
        String T_Email = txtTeaEmail.getText();
        String T_qulf = txtaTeaQulifi.getText();
        String T_Subject = cbAssSub.getSelectedItem().toString();

        TeacherBean tBean = new TeacherBean();
        tBean.setT_id(T_ID);
        tBean.setT_name(T_Name);
        tBean.setT_address(T_Address);
        tBean.setT_gen(T_Gender);
        tBean.setT_contact(T_Contact);
        tBean.setT_email(T_Email);
        tBean.setT_qualif(T_qulf);
        tBean.setT_subject(T_Subject);

        boolean UpdateTeacher = new TeacherLogic().UpdateTeacher(tBean);

        if (UpdateTeacher) {
            JOptionPane.showMessageDialog(this, "successfully updated");
            AutoID();

            txtTeaName.setText(null);
            txtTeaAddress.setText(null);
            cbAssSub.setSelectedIndex(0);
            rbtTeaMale.setSelected(false);
            rbtTeaFemale.setSelected(false);
            txtTeaContact.setText(null);
            txtTeaEmail.setText(null);
            txtaTeaQulifi.setText(null);
            btnAdd.setEnabled(true);
            txtTeaName.requestFocus();
            grpTeagender.clearSelection();
            table_update();

        } else {
            JOptionPane.showMessageDialog(this, "Unable to update ");
        }
        table_update();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
        int opt = JOptionPane.showConfirmDialog(null, "Are you Want To Delete ?", "Delete", JOptionPane.YES_NO_OPTION);
        if (opt == 0) {
            String T_ID = lblTID.getText();
            TeacherBean tBean = new TeacherBean();
            tBean.setT_id(T_ID);

            boolean RemoveTeacher = new TeacherLogic().RemoveTeacher(tBean);

            if (RemoveTeacher) {
                JOptionPane.showMessageDialog(this, "successfully deleted");
                AutoID();
                txtTeaName.setText(null);
                txtTeaAddress.setText(null);
                cbAssSub.setSelectedIndex(0);
                rbtTeaMale.setSelected(false);
                rbtTeaFemale.setSelected(false);
                txtTeaContact.setText(null);
                txtTeaEmail.setText(null);
                txtaTeaQulifi.setText(null);
                grpTeagender.clearSelection();
                txtTeaName.requestFocus();
                btnAdd.setEnabled(true);
                table_update();

            } else {
                JOptionPane.showMessageDialog(this, "Unable to Delete");
            }
            table_update();

        }
    }//GEN-LAST:event_btnRemoveActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        lblTID.setText(null);
        AutoID();
        txtTeaName.setText(null);
        txtTeaAddress.setText(null);
        cbAssSub.setSelectedIndex(0);
        rbtTeaMale.setSelected(false);
        rbtTeaFemale.setSelected(false);
        txtTeaContact.setText(null);
        txtTeaEmail.setText(null);
        txtaTeaQulifi.setText(null);
        grpTeagender.clearSelection();
        txtTeaName.requestFocus();
        btnAdd.setEnabled(true);

    }//GEN-LAST:event_btnClearActionPerformed

    private void rSTableMetro1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSTableMetro1MouseClicked

        DefaultTableModel d1 = (DefaultTableModel) rSTableMetro1.getModel();
        int SelectIndex = rSTableMetro1.getSelectedRow();

        lblTID.setText(d1.getValueAt(SelectIndex, 0).toString());
        txtTeaName.setText(d1.getValueAt(SelectIndex, 1).toString());
        txtTeaAddress.setText(d1.getValueAt(SelectIndex, 2).toString());
        String gende = d1.getValueAt(SelectIndex, 3).toString();
        if (gende.equals("Male")) {
            rbtTeaMale.setSelected(true);
        } else {
            rbtTeaFemale.setSelected(true);
        }
        txtTeaContact.setText(d1.getValueAt(SelectIndex, 4).toString());
        txtTeaEmail.setText(d1.getValueAt(SelectIndex, 5).toString());
        txtaTeaQulifi.setText(d1.getValueAt(SelectIndex, 6).toString());
        cbAssSub.setSelectedItem(d1.getValueAt(SelectIndex, 7).toString());
        btnAdd.setEnabled(false);

    }//GEN-LAST:event_rSTableMetro1MouseClicked

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        dispose();
    }//GEN-LAST:event_btnCloseActionPerformed

    private void txtTeaContactKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTeaContactKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if(!Character.isDigit(c)){
            evt.consume();
        }
    }//GEN-LAST:event_txtTeaContactKeyTyped

    private void txtTeaEmailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTeaEmailFocusLost
 String email = txtTeaEmail.getText().trim();
                if (!email.isEmpty()) {
                    if (!validateEmail(email)) {
                        lblinvalidEmail.setText("invalid Email");
                        JOptionPane.showMessageDialog(null, "Invalid email address");
                        txtTeaEmail.setText(null);
                    }
                }   
                
    }//GEN-LAST:event_txtTeaEmailFocusLost

    private void txtTeaIDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTeaIDKeyReleased
        Pattern pattern = Pattern.compile("^\\s*$");
        if (!txtTeaID.getText().isEmpty() && !pattern.matcher(txtTeaID.getText()).matches()) {

            String searchText = txtTeaID.getText().trim();

            String columnName = "Teacher_ID";

            Connection conn = null;
            try {
  
                conn = new DBConnection().getConnection();
                String sql = "SELECT * FROM teacher WHERE " + columnName + " LIKE ?";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1, "%" + searchText + "%");
                ResultSet rs = pst.executeQuery();

                DefaultTableModel model = (DefaultTableModel) rSTableMetro1.getModel();
                model.setRowCount(0); // Clear previous search results
                while (rs.next()) {
                    String Teacher_ID = rs.getString("Teacher_ID");
                String Teacher_Name = rs.getString("Teacher_Name");
                String Teacher_Address = rs.getString("Teacher_Address");
                String Gender = rs.getString("Gender");
                String Contact_Number = rs.getString("Contact_Number");
                String Email = rs.getString("Email");
                String Qualification = rs.getString("Qualification");

                    
 String sql3 =("SELECT * FROM teacher_subject WHERE Teacher_ID = '" + txtTeaID + "'");

               
 PreparedStatement pst2 = conn.prepareStatement(sql3);
  ResultSet rs3 = pst2.executeQuery();
  String sub_i = "";
                 String Reg_D = "";
                String Reg_T = "";
                if (rs3.next()) {
                    sub_i = rs3.getString("Subject_ID");
                    Reg_D = rs3.getString("registerd_date");
                    Reg_T = rs3.getString("registerd_time");
                }
                rs3.close();
               String sql4 = ("SELECT Subject_Name FROM subject WHERE Subject_ID = '" + sub_i + "'");
               PreparedStatement pst4 = conn.prepareStatement(sql4);
                ResultSet rs2 = pst4.executeQuery();

                String Sub_N = "";
                if (rs2.next()) {
                    Sub_N = rs2.getString("Subject_Name");
                }

                rs2.close();

                Object[] obj = {Teacher_ID, Teacher_Name, Teacher_Address, Gender, Contact_Number, Email, Qualification, Sub_N, Reg_D, Reg_T};
                model.addRow(obj);

                   

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
table_update();
        }   
    }//GEN-LAST:event_txtTeaIDKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ManageTeacher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManageTeacher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManageTeacher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManageTeacher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManageTeacher().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnRemove;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cbAssSub;
    private javax.swing.ButtonGroup grpTeagender;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblTID;
    private javax.swing.JLabel lblTime;
    private javax.swing.JLabel lblinvalidEmail;
    private rojeru_san.complementos.RSTableMetro rSTableMetro1;
    private javax.swing.JRadioButton rbtTeaFemale;
    private javax.swing.JRadioButton rbtTeaMale;
    private javax.swing.JTextField txtTeaAddress;
    private javax.swing.JTextField txtTeaContact;
    private javax.swing.JTextField txtTeaEmail;
    private javax.swing.JTextField txtTeaID;
    private javax.swing.JTextField txtTeaName;
    private javax.swing.JTextArea txtaTeaQulifi;
    // End of variables declaration//GEN-END:variables
}
