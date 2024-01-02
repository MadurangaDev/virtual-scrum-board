package Screens.Auth;
import Assets.Colors;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import Components.Atoms.CloseBtn;
import Components.Atoms.CustomBtn;
import Components.Atoms.CustomTxtBx;
import Components.Atoms.ImageBackgroundPanel;
import Enums.AuthType;
import Enums.InputStatus;
import Screens.MainScreen;
import jiconfont.swing.IconFontSwing;
import java.io.InputStream;

import Assets.Messages;
import static Components.Atoms.CustomBtn.createFlatButton;
import static Components.Atoms.CustomTxtBx.*;
import static Components.Atoms.FAIcon.*;
import static Components.Atoms.ThemedText.*;
import static Components.Atoms.CloseBtn.*;
import static Util.Validations.Validations.emptyCheck;
import Actions.Authentications;
import static Helpers.SharedVariables.*;

public class LoginScreen extends javax.swing.JFrame {
    
    public LoginScreen() {
        
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
        rightCenterChild.setBounds(68, 12, 250, 333);
        rightCenterChild.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 22));


        JLabel signInTitle = RegularText("Sign In", 28);
        signInTitle.setHorizontalAlignment(SwingConstants.CENTER);
        signInTitle.setPreferredSize(new Dimension(250, 40));

        JPanel userIDInputContainer = new JPanel();
        userIDInputContainer.setBackground(Colors.transparent);
        userIDInputContainer.setLayout(new FlowLayout(FlowLayout.LEADING));
        userIDInputContainer.setPreferredSize(new Dimension(250, 62));
        JLabel userIDLabel = RegularText("User ID", 13);
        CustomTxtBx userIDInputObj = new CustomTxtBx("\uf2bb", 245, 32, InputStatus.REGULAR, false, 16, false);
        JPanel userIDInput = userIDInputObj.getElement();

        JPanel userPasswordInputContainer = new JPanel();
        userPasswordInputContainer.setBackground(Colors.transparent);
        userPasswordInputContainer.setLayout(new FlowLayout(FlowLayout.LEADING));
        userPasswordInputContainer.setPreferredSize(new Dimension(250, 62));
        JLabel userPasswordLabel = RegularText("Password", 13);
        CustomTxtBx userPasswordObj = new CustomTxtBx("\uf023", 245, 32, InputStatus.REGULAR, true, 16, false);
        JPanel userPasswordInput = userPasswordObj.getElement();

        userIDInputContainer.add(userIDLabel);
        userIDInputContainer.add(userIDInput);

        userPasswordInputContainer.add(userPasswordLabel);
        userPasswordInputContainer.add(userPasswordInput);

        JPanel signInBtn = createFlatButton("Sign In", Colors.primaryBlue, Colors.darkBlack, 14);
        signInBtn.setPreferredSize(new Dimension(250, 32));
        signInBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(emptyCheck(userIDInputObj.getText()) || emptyCheck(userPasswordObj.getText())){
                    Messages.emptyFields();
                }
                else{
                    Authentications authObj = new Authentications();
                    AuthType loginResult = authObj.login(userIDInputObj.getText(), userPasswordObj.getText());
                    if(loginResult== AuthType.DATABASE_ERROR){
                        Messages.databaseError();
                    }
                    else if(loginResult== AuthType.INVALID_CREDENTIALS){
                        Messages.invalidCredentials();
                    }
                    else if(loginResult== AuthType.SUCCESS){
                        loggedUser = userIDInputObj.getText();
                        MainScreen mainScreen = new MainScreen();
                        mainScreen.setVisible(true);
                        dispose();
                    }
                }
            }
        });

        JPanel loginRegisterRedirectorContainer = new JPanel();
        loginRegisterRedirectorContainer.setLayout(new FlowLayout(FlowLayout.LEADING));
        loginRegisterRedirectorContainer.setPreferredSize(new Dimension(250, 28));
        loginRegisterRedirectorContainer.setBackground(Colors.transparent);
        JLabel txt1 = RegularText("New to here? ", 12);
        JLabel txt2 = RegularText("Sign Up", 12);
        txt2.setForeground(Colors.primaryBlue);
        txt2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                RegisterScreen registerScreen = new RegisterScreen();
                registerScreen.setVisible(true);
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

        rightCenterChild.add(signInTitle);
        rightCenterChild.add(userIDInputContainer);
        rightCenterChild.add(userPasswordInputContainer);
        rightCenterChild.add(signInBtn);
        rightCenterChild.add(loginRegisterRedirectorContainer);

        rightCenterPanel.add(rightCenterChild);



        rightSectionContent.add(rightTopPanel, BorderLayout.NORTH);
        rightSectionContent.add(rightCenterPanel, BorderLayout.CENTER);
        rightSectionContent.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));

        rightSection.add(rightSectionContent);




        this.add(leftSection, BorderLayout.WEST);
        this.add(rightSection, BorderLayout.EAST);
        
    }

    
    
    
    // Generated Code
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
            java.util.logging.Logger.getLogger(LoginScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        

        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginScreen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}

