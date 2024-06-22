/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sms_system;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import com.google.zxing.*;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.github.sarxos.webcam.Webcam;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;

public class QRCodeScanner extends JFrame {

    private JTextField resultTextField;
    private JLabel webcamView;

    public QRCodeScanner() {
        setTitle("QR Code Scanner");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        resultTextField = new JTextField();
        resultTextField.setFont(resultTextField.getFont().deriveFont(16f));
        resultTextField.setEditable(false);

        webcamView = new JLabel();
        webcamView.setPreferredSize(new Dimension(640, 480));
        webcamView.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.add(webcamView);
        contentPane.add(resultTextField);

        setContentPane(contentPane);
        pack();
        setLocationRelativeTo(null);
    }

    public void startScanner() {
        Webcam webcam = Webcam.getDefault();
        webcam.setViewSize(new Dimension(640, 480));
        webcam.open();

        Thread scanThread = new Thread(() -> {
            while (true) {
                try {
                    BufferedImage image = webcam.getImage();
                    ImageIcon imageIcon = new ImageIcon(image);
                    webcamView.setIcon(imageIcon);

                    Result result = decodeQRCode(image);
                    if (result != null) {
                        updateTextField(result.getText());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        scanThread.setDaemon(true);
        scanThread.start();
    }

    private Result decodeQRCode(BufferedImage image) throws NotFoundException, ChecksumException, FormatException {
        LuminanceSource source = new BufferedImageLuminanceSource(image);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        QRCodeReader reader = new QRCodeReader();
        return reader.decode(bitmap);
    }

    private void updateTextField(String text) {
        SwingUtilities.invokeLater(() -> resultTextField.setText(text));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            QRCodeScanner scanner = new QRCodeScanner();
            scanner.setVisible(true);
            scanner.startScanner();
        });
    }
}
