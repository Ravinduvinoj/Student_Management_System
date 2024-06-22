/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.sms_system.ui;

import com.google.zxing.qrcode.encoder.QRCode;
import com.mycompany.sms_system.businesslogic.StudentLogic;
import com.mycompany.sms_system.entity.StudentBean;
import com.mycompany.sms_system.smallUi.GenerateQRCode;
import com.mycompany.sms_system.smallUi.PaymentStatus;
import com.mycompany.sms_system.utill.DBConnection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
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
public class ManageStudent extends javax.swing.JFrame {

    /**
     * Creates new form ManageStudent
     */
    public ManageStudent() {
        initComponents();
        AutoID();
        table_update();
        dt(); // daet

        times(); // time
        updateComboSubject();
        btnUpdate.setVisible(false);
        btnRemove.setVisible(false);

    }

    public void dt() {

        java.util.Date d = new java.util.Date();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MMM-dd");
        String dd = sdf.format(d);
        lblDate2.setText(dd);

    }
    // time
    Timer t;
    SimpleDateFormat st;

    public void times() {

        t = new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                java.util.Date dt = new java.util.Date();
                st = new SimpleDateFormat("hh:mm:ss a");

                String tt = st.format(dt);
                lblTime2.setText(tt);

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
                cmdSubject.addItem(rs.getString("Subject_Name"));
            }
        } catch (Exception e) {
        }
    }

    public final void AutoID() {
        Connection conn = null;
        try {
            conn = new DBConnection().getConnection();
            java.sql.Statement s = conn.createStatement();
            rs = s.executeQuery("SELECT MAX(Student_ID) FROM student");
            rs.next();
            String maxstuID = rs.getString("MAX(Student_ID)");
            if (maxstuID == null) {
                lblS_ID.setText("stu000000001");
            } else {
                int id = Integer.parseInt(maxstuID.substring(3));
                id++;
                String newstuID = String.format("stu%09d", id);
                lblS_ID.setText(newstuID);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManageStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void table_update() {

        try {
            Connection conn = new DBConnection().getConnection();
            Statement st1 = conn.createStatement();
            Statement st2 = conn.createStatement();
            Statement st3 = conn.createStatement();

            ResultSet rs1 = st1.executeQuery("SELECT * FROM student");

            // Clear existing rows from the table
            DefaultTableModel model = (DefaultTableModel) rSTableMetro1.getModel();
            model.setRowCount(0);

            while (rs1.next()) {
                String Stu_ID = rs1.getString("Student_ID");
                String Stu_Name = rs1.getString("Student_Name");
                String FUll_Name = rs1.getString("Full_Name");
                String S_Address = rs1.getString("S_Address");
                String Contact_Number = rs1.getString("Contact_Number");
                String Date_of_Birth = rs1.getString("Date_of_Birth");
                String NIC = rs1.getString("NIC");
                String Gender = rs1.getString("Gender");
                String English_OL = rs1.getString("English_OL");
                String Year_of_OL = rs1.getString("Year_of_OL");
                //   String Subject_ID = rs1.getString("Subject_ID");

                ResultSet rs3 = st3.executeQuery("SELECT * FROM assign_subject WHERE Student_ID = '" + Stu_ID + "'");
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

                Object[] obj = {Stu_ID, Stu_Name, FUll_Name, S_Address, Contact_Number, Date_of_Birth, NIC, Gender, English_OL, Year_of_OL, Sub_N, Reg_D, Reg_T};
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        gender = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtStuName = new javax.swing.JTextField();
        txtFName = new javax.swing.JTextField();
        txtAddress = new javax.swing.JTextField();
        rduMale = new javax.swing.JRadioButton();
        rduFemale = new javax.swing.JRadioButton();
        txtContact = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtNIC = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblInName = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jDateDOB = new com.toedter.calendar.JDateChooser();
        jLabel26 = new javax.swing.JLabel();
        lblS_ID = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        cbCreditOL = new javax.swing.JComboBox<>();
        jLabel24 = new javax.swing.JLabel();
        jTextOLYear = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btnClose = new javax.swing.JButton();
        jPanel20 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        lblDate2 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        lblTime2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        rSTableMetro1 = new rojeru_san.complementos.RSTableMetro();
        jPanel5 = new javax.swing.JPanel();
        btnUpdate = new javax.swing.JButton();
        btnRemove = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnView = new javax.swing.JButton();
        btnCreateBarcode = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        cmdSubject = new javax.swing.JComboBox<>();
        lblpayMonth = new javax.swing.JLabel();
        cbRegPay = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(0, 102, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setToolTipText("");

        jPanel2.setBackground(new java.awt.Color(204, 255, 255));

        jLabel3.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jLabel3.setText("Personal Details");

        jLabel4.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 16)); // NOI18N
        jLabel4.setText("Student Name");

        jLabel5.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 16)); // NOI18N
        jLabel5.setText("Address");

        jLabel6.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 16)); // NOI18N
        jLabel6.setText("Full Name");

        jLabel8.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 16)); // NOI18N
        jLabel8.setText("Gender");

        txtStuName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtStuName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtStuNameKeyReleased(evt);
            }
        });

        txtFName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtAddress.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        gender.add(rduMale);
        rduMale.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 16)); // NOI18N
        rduMale.setText("Male");
        rduMale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rduMaleActionPerformed(evt);
            }
        });

        gender.add(rduFemale);
        rduFemale.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 16)); // NOI18N
        rduFemale.setText("Female");

        txtContact.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtContact.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtContactKeyTyped(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 16)); // NOI18N
        jLabel12.setText("Contact Number");

        txtNIC.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtNIC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNICActionPerformed(evt);
            }
        });
        txtNIC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNICKeyTyped(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 16)); // NOI18N
        jLabel16.setText("NIC");

        jLabel9.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 16)); // NOI18N
        jLabel9.setText("Date of Birth");

        lblInName.setForeground(new java.awt.Color(204, 0, 0));

        jDateDOB.setDateFormatString("yyyy-MM-dd");

        jLabel26.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 16)); // NOI18N
        jLabel26.setText("Student ID :");

        lblS_ID.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 16)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel3))
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtContact, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtAddress, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                        .addComponent(txtFName, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtStuName, javax.swing.GroupLayout.Alignment.LEADING)))
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblInName, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(rduMale)
                                .addGap(18, 18, 18)
                                .addComponent(rduFemale))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jDateDOB, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                                    .addComponent(txtNIC, javax.swing.GroupLayout.Alignment.LEADING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(145, 145, 145)
                                .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblS_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblS_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtStuName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblInName, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jDateDOB, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtFName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel16)
                        .addComponent(txtNIC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rduMale, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rduFemale, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(txtContact)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );

        jPanel3.setBackground(new java.awt.Color(204, 255, 255));

        jLabel15.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jLabel15.setText("Educational Details");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel17.setText("O/L English");

        cbCreditOL.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "None", "A", "B", "C", "S", "F" }));
        cbCreditOL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbCreditOLActionPerformed(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        jLabel24.setText("Year");

        jTextOLYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextOLYearActionPerformed(evt);
            }
        });
        jTextOLYear.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextOLYearKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel15)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(cbCreditOL, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jTextOLYear, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(11, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15)
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(cbCreditOL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24)
                    .addComponent(jTextOLYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jLabel11.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(204, 204, 204));
        jLabel11.setText("Student_ID   :");

        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(0, 102, 204));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Serif", 1, 48)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Manage Student ");

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

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(383, 383, 383)
                .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(btnClose)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel20.setBackground(new java.awt.Color(255, 255, 255));

        jLabel31.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 16)); // NOI18N
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setText("Date :");

        lblDate2.setFont(new java.awt.Font("PMingLiU-ExtB", 0, 16)); // NOI18N
        lblDate2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        jLabel32.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 16)); // NOI18N
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setText("Time :");

        lblTime2.setFont(new java.awt.Font("PMingLiU-ExtB", 0, 16)); // NOI18N
        lblTime2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(lblDate2, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTime2, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblDate2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel32, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblTime2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        rSTableMetro1.setBackground(new java.awt.Color(0, 102, 204));
        rSTableMetro1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Student_ID", "Student_Name", "Full Name", "Address", "Contact No", "Date of Birth", "NIC", "Gender", "O/L English", "Year_OF_O/L", "Subject", "Reg Date", "Reg Time"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        rSTableMetro1.setFuenteFilas(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        rSTableMetro1.setFuenteFilasSelect(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        rSTableMetro1.setFuenteHead(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        rSTableMetro1.setRowHeight(30);
        rSTableMetro1.getTableHeader().setReorderingAllowed(false);
        rSTableMetro1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rSTableMetro1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(rSTableMetro1);

        btnUpdate.setBackground(new java.awt.Color(204, 204, 204));
        btnUpdate.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnRemove.setBackground(new java.awt.Color(204, 204, 204));
        btnRemove.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        btnRemove.setText("Remove");
        btnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveActionPerformed(evt);
            }
        });

        btnClear.setBackground(new java.awt.Color(204, 204, 204));
        btnClear.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        btnClear.setText("Clear All");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        btnAdd.setBackground(new java.awt.Color(204, 204, 204));
        btnAdd.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        btnAdd.setText("Register");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnView.setBackground(new java.awt.Color(204, 204, 204));
        btnView.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        btnView.setText("View Payments");
        btnView.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewActionPerformed(evt);
            }
        });

        btnCreateBarcode.setBackground(new java.awt.Color(204, 204, 204));
        btnCreateBarcode.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        btnCreateBarcode.setText("Create QRcode");
        btnCreateBarcode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateBarcodeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(198, 198, 198)
                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnRemove, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCreateBarcode, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnView, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRemove, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnView, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCreateBarcode, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5))
        );

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel18.setText("Enroll Subject");

        cmdSubject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdSubjectActionPerformed(evt);
            }
        });

        lblpayMonth.setFont(new java.awt.Font("Serif", 0, 15)); // NOI18N
        lblpayMonth.setText("Current Month Payment");

        cbRegPay.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Released", "To Pay" }));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(lblpayMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cbRegPay, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmdSubject, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(54, 54, 54))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmdSubject, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblpayMonth, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbRegPay, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addGap(47, 47, 47))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(31, 31, 31)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(30, 30, 30))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(14, 14, 14))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(828, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(487, 487, 487))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(140, 140, 140)
                .addComponent(jLabel1)
                .addGap(600, 615, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
if (txtStuName.equals("")&&txtAddress.equals("")&&txtFName.equals("")&&txtNIC.equals("")&&txtContact.equals("")&&jTextOLYear.equals("")&&jDateDOB.equals("")){
    JOptionPane.showMessageDialog(null, "Please fill out all");
}else{
    Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = formatter.format(date);

        String S_ID = lblS_ID.getText();
        String s_Name = txtStuName.getText();
        String Full_Name = txtFName.getText();
        String S_Address = txtAddress.getText();
        String C_No = txtContact.getText();
        SimpleDateFormat Date_Format = new SimpleDateFormat("yyyy-MM-dd");
        String Dob = Date_Format.format(jDateDOB.getDate());
        String NIC = txtNIC.getText();

        rduMale.setActionCommand("Male");
        rduFemale.setActionCommand("Female");
        String gend = gender.getSelection().getActionCommand();
        String credit = cbCreditOL.getSelectedItem().toString();
        String year = jTextOLYear.getText();
        String Subject_ = cmdSubject.getSelectedItem().toString();
        String Payble = cbRegPay.getSelectedItem().toString();

        String R_Time = lblTime2.getText();

        StudentBean stu = new StudentBean();
        stu.setStudent_ID(S_ID);
        stu.setStudent_Name(s_Name);
        stu.setFull_Name(Full_Name);
        stu.setS_Address(S_Address);
        stu.setContact_Number(C_No);
        stu.setDate_of_Birth(Dob);
        stu.setNIC(NIC);
        stu.setGender(gend);
        stu.setEnglish_OL(credit);
        stu.setYear_of_OL(year);
        stu.setSubject(Subject_);
        stu.setR_Date(strDate);
        stu.setR_Time(R_Time);
        stu.setCurrentMonthPayment(Payble);

        boolean AddStudent = new StudentLogic().AddStudent(stu);

        if (AddStudent) {
            JOptionPane.showMessageDialog(this, "successfully ADD");
            AutoID();

            txtStuName.setText("");
            txtFName.setText("");
            cbCreditOL.setSelectedIndex(0);
            cmdSubject.setSelectedIndex(0);
            txtAddress.setText(null);
            txtContact.setText(null);
            txtNIC.setText(null);
            rduMale.setSelected(false);
            rduFemale.setSelected(false);
            jTextOLYear.setText(null);
            jDateDOB.setDate(null);
            txtAddress.setText(null);
            cbRegPay.setSelectedIndex(0);

            txtStuName.requestFocus();
            table_update();

        } else {
            JOptionPane.showMessageDialog(this, "Unable to ADD ");
        }
        table_update();

}

        

    }//GEN-LAST:event_btnAddActionPerformed


    private void rduMaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rduMaleActionPerformed

    }//GEN-LAST:event_rduMaleActionPerformed


    private void txtNICActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNICActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNICActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        lblS_ID.setText(null);
        AutoID();
        txtStuName.setText(null);
        txtFName.setText(null);
        cbCreditOL.setSelectedIndex(0);
        cmdSubject.setSelectedIndex(0);
        txtAddress.setText(null);
        txtContact.setText(null);
        rduMale.setSelected(false);
        rduFemale.setSelected(false);
        txtNIC.setText(null);
        jTextOLYear.setText(null);
        jDateDOB.setDate(null);
        btnAdd.setVisible(true);
gender.clearSelection();
        txtStuName.requestFocus();
        lblpayMonth.setVisible(true);
        cbRegPay.setVisible(true);

    }//GEN-LAST:event_btnClearActionPerformed

    private void jTextOLYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextOLYearActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextOLYearActionPerformed

    private void cbCreditOLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbCreditOLActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbCreditOLActionPerformed

    private void cmdSubjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdSubjectActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmdSubjectActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed

        String S_ID = lblS_ID.getText();
        String s_Name = txtStuName.getText();
        String Full_Name = txtFName.getText();
        String S_Address = txtAddress.getText();
        String C_No = txtContact.getText();
        SimpleDateFormat Date_Format = new SimpleDateFormat("yyyy-MM-dd");
        String Dob = Date_Format.format(jDateDOB.getDate());
        String NIC = txtNIC.getText();

        rduMale.setActionCommand("Male");
        rduFemale.setActionCommand("Female");
        String gend = gender.getSelection().getActionCommand();
        String credit = cbCreditOL.getSelectedItem().toString();
        String year = jTextOLYear.getText();
        String Subject_ = cmdSubject.getSelectedItem().toString();

        StudentBean stu = new StudentBean();
        stu.setStudent_ID(S_ID);
        stu.setStudent_Name(s_Name);
        stu.setFull_Name(Full_Name);
        stu.setS_Address(S_Address);
        stu.setContact_Number(C_No);
        stu.setDate_of_Birth(Dob);
        stu.setNIC(NIC);
        stu.setGender(gend);
        stu.setEnglish_OL(credit);
        stu.setYear_of_OL(year);
        stu.setSubject(Subject_);

        boolean UpdateStudent = new StudentLogic().UpdateStudent(stu);

        if (UpdateStudent) {
            JOptionPane.showMessageDialog(this, "successfully updtaed");
            AutoID();

            txtStuName.setText("");
            txtFName.setText("");
            cbCreditOL.setSelectedIndex(0);
            cmdSubject.setSelectedIndex(0);
            txtAddress.setText(null);
            txtContact.setText(null);
gender.clearSelection();
            rduMale.setSelected(false);
            rduFemale.setSelected(false);
            txtNIC.setText(null);
            jTextOLYear.setText(null);
            jDateDOB.setDate(null);

            txtStuName.requestFocus();
            lblpayMonth.setVisible(true);
            cbRegPay.setVisible(true);
            table_update();

        } else {
            JOptionPane.showMessageDialog(this, "Unable to update");
        }
        table_update();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void rSTableMetro1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSTableMetro1MouseClicked

        // TODO add your handling code here:
        DefaultTableModel d1 = (DefaultTableModel) rSTableMetro1.getModel();
        int SelectIndex = rSTableMetro1.getSelectedRow();

        lblS_ID.setText(d1.getValueAt(SelectIndex, 0).toString());
        txtStuName.setText(d1.getValueAt(SelectIndex, 1).toString());
        txtFName.setText(d1.getValueAt(SelectIndex, 2).toString());
        txtAddress.setText(d1.getValueAt(SelectIndex, 3).toString());
        txtContact.setText(d1.getValueAt(SelectIndex, 4).toString());
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse((String) d1.getValueAt(SelectIndex, 5));
            jDateDOB.setDate(date);
        } catch (ParseException ex) {
            Logger.getLogger(ManageStudent.class.getName()).log(Level.SEVERE, null, ex);
        }

        txtNIC.setText(d1.getValueAt(SelectIndex, 6).toString());
        String gende = d1.getValueAt(SelectIndex, 7).toString();
        if (gende.equals("Male")) {
            rduMale.setSelected(true);
        } else {
            rduFemale.setSelected(true);
        }

        cbCreditOL.setSelectedItem(d1.getValueAt(SelectIndex, 8).toString());
        jTextOLYear.setText(d1.getValueAt(SelectIndex, 9).toString());
        cmdSubject.setSelectedItem(d1.getValueAt(SelectIndex, 10).toString());
        btnAdd.setVisible(false);
        btnUpdate.setVisible(true);
        btnRemove.setVisible(true);
        lblpayMonth.setVisible(false);
        cbRegPay.setVisible(false);

    }//GEN-LAST:event_rSTableMetro1MouseClicked

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
        int opt = JOptionPane.showConfirmDialog(null, "Are you Want To Delete ?", "Delete", JOptionPane.YES_NO_OPTION);
        if (opt == 0) {
            String Student_ID = lblS_ID.getText();
            StudentBean stu = new StudentBean();
            stu.setStudent_ID(Student_ID);

            boolean RemoveStudent = new StudentLogic().RemoveStudent(stu);

            if (RemoveStudent) {
                JOptionPane.showMessageDialog(this, "successfully deleted");
                AutoID();

                txtStuName.setText("");
                txtFName.setText("");
                cbCreditOL.setSelectedIndex(0);
                cmdSubject.setSelectedIndex(0);
                txtAddress.setText(null);
                txtContact.setText(null);
                rduMale.setSelected(false);
                rduFemale.setSelected(false);
                txtNIC.setText(null);
                jTextOLYear.setText(null);
                jDateDOB.setDate(null);

                txtStuName.requestFocus();
                btnAdd.setVisible(true);
                lblpayMonth.setVisible(true);
                cbRegPay.setVisible(true);
                gender.clearSelection();
                table_update();

            } else {
                JOptionPane.showMessageDialog(this, "Unable to Delete");
            }
            table_update();

    }//GEN-LAST:event_btnRemoveActionPerformed
    }
    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        dispose();
    }//GEN-LAST:event_btnCloseActionPerformed

    private void btnViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewActionPerformed
        PaymentStatus py = new PaymentStatus();
        py.setVisible(true);
    }//GEN-LAST:event_btnViewActionPerformed

    private void btnCreateBarcodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateBarcodeActionPerformed
      GenerateQRCode n = new GenerateQRCode();
      n.setVisible(true);
    }//GEN-LAST:event_btnCreateBarcodeActionPerformed

    private void txtContactKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtContactKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if(!Character.isDigit(c)){
            evt.consume();
        }
    }//GEN-LAST:event_txtContactKeyTyped

    private void txtNICKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNICKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if(!Character.isDigit(c)){
            evt.consume();
        }
    }//GEN-LAST:event_txtNICKeyTyped

    private void jTextOLYearKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextOLYearKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if(!Character.isDigit(c)){
            evt.consume();
        }
    }//GEN-LAST:event_jTextOLYearKeyTyped

    private void txtStuNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStuNameKeyReleased
         Pattern pattern = Pattern.compile("&#@!;:.,<>?``~~=-_+||/*-+^\\s*$");
        if (txtStuName.getText().isEmpty() && pattern.matcher(txtStuName.getText()).matches()){
            lblInName.setText("Invalid");
        }
    }//GEN-LAST:event_txtStuNameKeyReleased

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
 Pattern pattern = Pattern.compile("^\\s*$");
        if (!txtSearch.getText().isEmpty() && !pattern.matcher(txtSearch.getText()).matches()) {

            String searchText = txtSearch.getText().trim();

            String columnName = "Student_ID";

            Connection conn = null;
            try {
  
                conn = new DBConnection().getConnection();
                String sql = "SELECT * FROM student WHERE " + columnName + " LIKE ?";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1, "%" + searchText + "%");
                ResultSet rs = pst.executeQuery();

                DefaultTableModel model = (DefaultTableModel) rSTableMetro1.getModel();
                model.setRowCount(0); // Clear previous search results
                while (rs.next()) {
                    String Stu_ID = rs.getString("Student_ID");
                    String stu_Name = rs.getString("Student_Name");
                     String FUll_Name = rs.getString("Full_Name");
                String S_Address = rs.getString("S_Address");
                String Contact_Number = rs.getString("Contact_Number");
                String Date_of_Birth = rs.getString("Date_of_Birth");
                String NIC = rs.getString("NIC");
                String Gender = rs.getString("Gender");
                String English_OL = rs.getString("English_OL");
                String Year_of_OL = rs.getString("Year_of_OL");
                    
 String sql3 =("SELECT * FROM assign_subject WHERE Student_ID = '" + Stu_ID + "'");

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

                Object[] obj = {Stu_ID, stu_Name, FUll_Name, S_Address, Contact_Number, Date_of_Birth, NIC, Gender, English_OL, Year_of_OL, Sub_N, Reg_D, Reg_T};
                model.addRow(obj);

                   

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
table_update();
        }       
    }//GEN-LAST:event_txtSearchKeyReleased

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
            java.util.logging.Logger.getLogger(ManageStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManageStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManageStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManageStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManageStudent().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnCreateBarcode;
    private javax.swing.JButton btnRemove;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btnView;
    private javax.swing.JComboBox<String> cbCreditOL;
    private javax.swing.JComboBox<String> cbRegPay;
    private javax.swing.JComboBox<String> cmdSubject;
    private javax.swing.ButtonGroup gender;
    private com.toedter.calendar.JDateChooser jDateDOB;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextOLYear;
    private javax.swing.JLabel lblDate2;
    private javax.swing.JLabel lblInName;
    private javax.swing.JLabel lblS_ID;
    private javax.swing.JLabel lblTime2;
    private javax.swing.JLabel lblpayMonth;
    private rojeru_san.complementos.RSTableMetro rSTableMetro1;
    private javax.swing.JRadioButton rduFemale;
    private javax.swing.JRadioButton rduMale;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtContact;
    private javax.swing.JTextField txtFName;
    private javax.swing.JTextField txtNIC;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtStuName;
    // End of variables declaration//GEN-END:variables

}
