/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Screens.Auth;

import Actions.Authentications;
import Assets.Colors;
import Components.Atoms.CloseBtn;
import Components.Atoms.CustomTxtBx;
import Components.Atoms.ImageBackgroundPanel;
import Enums.AuthType;
import Enums.AvailabilityType;
import Enums.InputStatus;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import Assets.Messages;
import Screens.MainScreen;

import static Assets.Messages.passMissmatch;
import static Components.Atoms.CustomBtn.createFlatButton;
import static Components.Atoms.CustomTxtBx.*;
import static Components.Atoms.FAIcon.FAicon;
import static Components.Atoms.ThemedText.BoldText;
import static Components.Atoms.ThemedText.RegularText;
import static Util.Validations.Validations.emptyCheck;
import static Util.Validations.Validations.equalCheck;
import static Helpers.SharedVariables.*;

public class RegisterScreen extends javax.swing.JFrame {

    public RegisterScreen() {
        Authentications authObj = new Authentications();

        this.setUndecorated(true);
        this.getContentPane().setBackground(Colors.primaryBlack);
        initComponents();

        this.setSize(691, 461);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        // Left Section
        JPanel leftSection = new JPanel();
        leftSection.setPreferredSize(new Dimension(277, 461));
        leftSection.setLayout(null);

        try {
            URL imageUrl = getClass().getResource("/Assets/Images/AuthPageBack.jpg");
            BufferedImage image = ImageIO.read(imageUrl);
            ImageBackgroundPanel backgroundPanel = new ImageBackgroundPanel(image);
            backgroundPanel.setBounds(0, 0, 277, 461);

            JPanel leftSectionContent = new JPanel();
            leftSectionContent.setSize(277, 461);
            leftSectionContent.setBackground(Colors.primaryBlackImageOverlay);
            leftSectionContent.setBounds(0,0,277,461);
            leftSectionContent.setLayout(new BorderLayout());

            JPanel topPanel = new JPanel();
            topPanel.setBackground(Colors.transparent);
            topPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
            JLabel devByIcon = FAicon("\uf121", 20);
            JLabel devByName = RegularText(" Maduranga", 18);
            devByIcon.setForeground(Colors.neutralBlue);
            topPanel.add(devByIcon);
            topPanel.add(devByName);

            JPanel centerPanel = new JPanel();
            centerPanel.setLayout(null);
            centerPanel.setBackground(Colors.transparent);
            JPanel centerChild = new JPanel();
            centerChild.setBackground(Colors.transparent);
            centerChild.setBounds(0, 135, 245, 90);

            JLabel bannerTitle = BoldText("#1 Virtual Kanban", 28);
            JLabel bannerText1 = RegularText("Welcome to our efficient virtual scrum board.", 12);
            JLabel bannerText2 = RegularText("Your path to productivity starts here!", 12);
            bannerText2.setHorizontalAlignment(SwingConstants.CENTER);
            bannerText1.setPreferredSize(new Dimension(245, 12));
            bannerText2.setPreferredSize(new Dimension(245, 12));
            centerChild.setLayout(new FlowLayout());
            centerChild.add(bannerTitle);
            centerChild.add(bannerText1);
            centerChild.add(bannerText2);

            centerPanel.add(centerChild);

            JPanel bottomPanel = new JPanel();
            bottomPanel.setBackground(Colors.transparent);
            bottomPanel.setLayout(new FlowLayout(FlowLayout.TRAILING));
            JLabel version= RegularText("Version 1.0", 12, Colors.neutralBlue_0_5);
            bottomPanel.add(version);


            leftSectionContent.add(topPanel, BorderLayout.NORTH);
            leftSectionContent.add(centerPanel, BorderLayout.CENTER);
            leftSectionContent.add(bottomPanel, BorderLayout.SOUTH);
            leftSectionContent.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));


            leftSection.add(leftSectionContent);
            leftSection.add(backgroundPanel);


        } catch (IOException e) {
            e.printStackTrace();
        }

        // Right Section
        JPanel rightSection = new JPanel();
        rightSection.setPreferredSize(new Dimension(414, 461));
        rightSection.setLayout(null);

        JPanel rightSectionContent = new JPanel();
        rightSectionContent.setSize(414, 461);
        rightSectionContent.setBackground(Colors.transparent);
        rightSectionContent.setBounds(0,0,414,461);
        rightSectionContent.setLayout(new BorderLayout());

        JPanel rightTopPanel = new JPanel();
        rightTopPanel.setBackground(Colors.primaryBlack);
        rightTopPanel.setLayout(new FlowLayout(FlowLayout.TRAILING));

        CloseBtn closeBtn = new CloseBtn(true);
        rightTopPanel.add(closeBtn);

        JPanel rightCenterPanel = new JPanel();
        rightCenterPanel.setLayout(null);
        rightCenterPanel.setBackground(Colors.transparent);
        JPanel rightCenterChild = new JPanel();
        rightCenterChild.setBackground(Colors.transparent);
        rightCenterChild.setBounds(68, -16, 250, 414);
        rightCenterChild.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 13));


        JLabel signInTitle = RegularText("Sign Up", 24);
        signInTitle.setHorizontalAlignment(SwingConstants.CENTER);
        signInTitle.setPreferredSize(new Dimension(250, 24));

        JPanel userNameContainer = new JPanel();
        userNameContainer.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));
        userNameContainer.setPreferredSize(new Dimension(250, 48));
        userNameContainer.setBackground(Colors.transparent);

        JPanel firstNameContainer = new JPanel();
        firstNameContainer.setPreferredSize(new Dimension(132, 48));
        firstNameContainer.setBackground(Colors.transparent);
        firstNameContainer.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));
        JLabel firstNameLabel = RegularText("First Name", 13);
        CustomTxtBx firstNameObj = new CustomTxtBx("\uf007", 118, 28, InputStatus.REGULAR, false, 45, false);
        JPanel firstNameInput = firstNameObj.getElement();
        firstNameContainer.add(firstNameLabel);
        firstNameContainer.add(firstNameInput);

        JPanel lastNameContainer = new JPanel();
        lastNameContainer.setPreferredSize(new Dimension(118, 48));
        lastNameContainer.setBackground(Colors.transparent);
        lastNameContainer.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));
        JLabel lastNameLabel = RegularText("Last Name", 13);
        CustomTxtBx lastNameObj = new CustomTxtBx("\uf007", 118, 28, InputStatus.REGULAR, false, 45, false);
        JPanel lastNameInput = lastNameObj.getElement();
        lastNameContainer.add(lastNameLabel);
        lastNameContainer.add(lastNameInput);

        userNameContainer.add(firstNameContainer);
        userNameContainer.add(lastNameContainer);


        JPanel userIDInputContainer = new JPanel();
        userIDInputContainer.setBackground(Colors.primaryBlack);
        userIDInputContainer.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));
        userIDInputContainer.setPreferredSize(new Dimension(250, 48));
        JPanel userIDLabelContainer = new JPanel();
        userIDLabelContainer.setPreferredSize(new Dimension(250, 18));
        userIDLabelContainer.setBackground(Colors.primaryBlack);
        userIDLabelContainer.setLayout(new BorderLayout(0, 0));
        JLabel userIDLabel = RegularText("User ID", 13);
        JLabel userIDAvailability = RegularText("Taken", 13, Colors.transparent);
        userIDLabelContainer.add(userIDLabel, BorderLayout.WEST);
        userIDLabelContainer.add(userIDAvailability, BorderLayout.EAST);
        CustomTxtBx userIDObj = new CustomTxtBx("\uf007", 250, 28, InputStatus.REGULAR, false, 16, false);
        JPanel userIDInput = userIDObj.getElement();
        userIDInputContainer.add(userIDLabelContainer);
        userIDInputContainer.add(userIDInput);

        userIDObj.textInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                if(emptyCheck(userIDObj.getText())) {
                    userIDAvailability.setForeground(Colors.transparent);
                    userIDObj.setStatus(InputStatus.REGULAR);
                }
                else{
                    AvailabilityType availability = authObj.checkAvailability(userIDObj.getText());
                    if(availability== AvailabilityType.UNAVAILABLE){
                        userIDAvailability.setText("Taken");
                        userIDAvailability.setForeground(Colors.warning);
                        userIDObj.setStatus(InputStatus.WARNING);
                    }
                    else if(availability== AvailabilityType.AVAILABLE){
                        userIDAvailability.setText("Available");
                        userIDAvailability.setForeground(Colors.success);
                        userIDObj.setStatus(InputStatus.SUCCESS);
                    }
                    else{
                        userIDAvailability.setForeground(Colors.transparent);
                        userIDObj.setStatus(InputStatus.REGULAR);
                    }
                }
            }
        });


        JPanel userPasswordInputContainer = new JPanel();
        userPasswordInputContainer.setBackground(Colors.transparent);
        userPasswordInputContainer.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));
        userPasswordInputContainer.setPreferredSize(new Dimension(250, 48));
        JLabel userPasswordLabel = RegularText("Password", 13);
        CustomTxtBx userPasswordObj = new CustomTxtBx("\uf023", 250, 28, InputStatus.REGULAR, true, 16, false);
        JPanel userPasswordInput = userPasswordObj.getElement();
        userPasswordInputContainer.add(userPasswordLabel);
        userPasswordInputContainer.add(userPasswordInput);

        JPanel userConfPasswordInputContainer = new JPanel();
        userConfPasswordInputContainer.setBackground(Colors.transparent);
        userConfPasswordInputContainer.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));
        userConfPasswordInputContainer.setPreferredSize(new Dimension(250, 48));
        JLabel userConfPasswordLabel = RegularText("Confirm Password", 13);
        CustomTxtBx userConfPasswordObj = new CustomTxtBx("\uf023", 250, 28, InputStatus.REGULAR, true, 16, false);
        JPanel userConfPasswordInput = userConfPasswordObj.getElement();
        userConfPasswordInputContainer.add(userConfPasswordLabel);
        userConfPasswordInputContainer.add(userConfPasswordInput);
        userConfPasswordObj.passInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                if(equalCheck(userPasswordObj.getText(), userConfPasswordObj.getText())){
                    userConfPasswordObj.setStatus(InputStatus.SUCCESS);
                }
                else{
                    userConfPasswordObj.setStatus(InputStatus.WARNING);
                }
            }
        });
        userPasswordObj.passInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                if(emptyCheck(userConfPasswordObj.getText())){
                    userConfPasswordObj.setStatus(InputStatus.REGULAR);
                }
                else if(equalCheck(userPasswordObj.getText(), userConfPasswordObj.getText())){
                    userConfPasswordObj.setStatus(InputStatus.SUCCESS);
                }
                else{
                    userConfPasswordObj.setStatus(InputStatus.WARNING);
                }
            }
        });


        JPanel signInBtn = createFlatButton("Sign Up", Colors.primaryBlue, Colors.darkBlack, 14);
        signInBtn.setPreferredSize(new Dimension(250, 32));
        signInBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(emptyCheck(firstNameObj.getText()) || emptyCheck(lastNameObj.getText()) || emptyCheck(userIDObj.getText()) || emptyCheck(userPasswordObj.getText()) || emptyCheck(userConfPasswordObj.getText())) {
                    Messages.emptyFields();
                }
                else if(!equalCheck(userPasswordObj.getText(), userConfPasswordObj.getText())){
                    passMissmatch();
                } else {
                    AvailabilityType availability = authObj.checkAvailability(userIDObj.getText());
                    if (availability == AvailabilityType.UNAVAILABLE) {
                        Messages.fullyCustomError("User ID is already taken");
                    }
                    else if(availability == AvailabilityType.AVAILABLE){
                        AuthType registerRes = authObj.register(userIDObj.getText(), userPasswordObj.getText(), firstNameObj.getText(), lastNameObj.getText());
                        if (registerRes == AuthType.SUCCESS) {
                            Messages.fullyCustomMessage("Registration Successful. Logging in to your account...");
                            loggedUser = userIDObj.getText();
                            MainScreen mainScreen = new MainScreen();
                            mainScreen.setVisible(true);
                            dispose();
                        }
                        else if (registerRes == AuthType.DATABASE_ERROR) {
                            Messages.databaseError();
                        }
                        else {
                            Messages.somethingWrong();
                        }
                    }
                    else{
                        Messages.databaseError();
                    }
                }
            }
        });

        JPanel loginRegisterRedirectorContainer = new JPanel();
        loginRegisterRedirectorContainer.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));
        loginRegisterRedirectorContainer.setPreferredSize(new Dimension(250, 16));
        loginRegisterRedirectorContainer.setBackground(Colors.transparent);
        JLabel txt1 = RegularText("Already here? ", 12);
        JLabel txt2 = RegularText("Sign In", 12);
        txt2.setForeground(Colors.primaryBlue);
        txt2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                LoginScreen loginScreen = new LoginScreen();
                loginScreen.setVisible(true);
                dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                txt2.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                txt2.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
        loginRegisterRedirectorContainer.add(txt1);
        loginRegisterRedirectorContainer.add(txt2);

        JPanel registerTermsContainer = new JPanel();
        registerTermsContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        registerTermsContainer.setPreferredSize(new Dimension(250, 32));
        registerTermsContainer.setBackground(Colors.transparent);
        JLabel termsTxt1 = RegularText("By clicking sign up, you agree to our ", 10);
        termsTxt1.setForeground(Colors.neutralBlue_0_65);
        JLabel termsTxt2 = RegularText("<html><u>Terms of Service</u></html>", 10);
        termsTxt2.setForeground(Colors.primaryBlue);
        JLabel termsTxt3 = RegularText("and that you have read our ", 10);
        termsTxt3.setForeground(Colors.neutralBlue_0_65);
        JLabel termsTxt4 = RegularText("<html><u>Privacy Policy</u>.</html>", 10);
        termsTxt4.setForeground(Colors.primaryBlue);
        registerTermsContainer.add(termsTxt1);
        registerTermsContainer.add(termsTxt2);
        registerTermsContainer.add(termsTxt3);
        registerTermsContainer.add(termsTxt4);

        rightCenterChild.add(signInTitle);
        rightCenterChild.add(userNameContainer);
        rightCenterChild.add(userIDInputContainer);
        rightCenterChild.add(userPasswordInputContainer);
        rightCenterChild.add(userConfPasswordInputContainer);
        rightCenterChild.add(signInBtn);
        rightCenterChild.add(loginRegisterRedirectorContainer);
        rightCenterChild.add(registerTermsContainer);

        rightCenterPanel.add(rightCenterChild);



        rightSectionContent.add(rightTopPanel, BorderLayout.NORTH);
        rightSectionContent.add(rightCenterPanel, BorderLayout.CENTER);
        rightSectionContent.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));

        rightSection.add(rightSectionContent);




        this.add(leftSection, BorderLayout.WEST);
        this.add(rightSection, BorderLayout.EAST);
//        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(RegisterScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegisterScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegisterScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegisterScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegisterScreen().setVisible(true);
            }
        });
    }
}
