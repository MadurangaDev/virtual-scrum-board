package Screens;

import Actions.Authentications;
import Actions.UserDBActions;
import Assets.Colors;
import Assets.Messages;
import Components.Atoms.CloseBtn;
import Components.Atoms.CustomBtn;
import Components.Atoms.CustomTxtBx;
import Components.Atoms.RoundBtn;
import Components.ProjectCard;
import Enums.AuthType;
import Enums.InputStatus;
import Screens.Auth.LoginScreen;
import Screens.Dialogs.AddProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import static Components.Atoms.CustomBtn.createFlatButton;
import static Components.Atoms.FAIcon.FAicon;
import static Components.Atoms.ThemedText.BoldText;
import static Components.Atoms.ThemedText.RegularText;
import static Helpers.SharedVariables.loggedUser;
import static Util.Validations.Validations.emptyCheck;
import static Util.Validations.Validations.equalCheck;

public class AccountScreen extends javax.swing.JFrame {

    public AccountScreen() {
        UserDBActions userDBActions = new UserDBActions();
        this.setUndecorated(true);
        this.getContentPane().setBackground(Colors.primaryBlack);
        initComponents();

        this.setSize(504, 672);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout(0, 0));

        JPanel windowContent = new JPanel();
        windowContent.setBackground(Colors.primaryBlack);
        windowContent.setLayout(new BorderLayout(0, 0));
        windowContent.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
        windowContent.setPreferredSize(new Dimension(504, 672));

        JPanel topBar = new JPanel();
        topBar.setBackground(Colors.primaryBlack);
        topBar.setLayout(new BorderLayout(0, 0));
        topBar.setPreferredSize(new Dimension(504, 28));


        JPanel topBarRight = new JPanel();
        topBarRight.setBackground(Colors.primaryBlack);
        topBarRight.setLayout(new FlowLayout(FlowLayout.TRAILING, 0, 0));
        topBarRight.setPreferredSize(new Dimension(232, 26));
        CloseBtn closeBtn = new CloseBtn(false);
        closeBtn.addActionListener(e -> {
            new MainScreen().setVisible(true);
            this.dispose();
        });

        topBarRight.add(closeBtn);







        topBar.add(topBarRight, BorderLayout.EAST);

        JPanel windowBody = new JPanel();
        windowBody.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 16));
        windowBody.setBackground(Colors.transparent);

        JPanel accLogoContainer = new JPanel();
        accLogoContainer.setBackground(Colors.primaryBlack);
        accLogoContainer.setLayout(new BorderLayout(0, 0));
        accLogoContainer.setPreferredSize(new Dimension(504, 160));
        JLabel accLogo = FAicon("\uf2bd", 120, Colors.neutralBlue);
        accLogo.setHorizontalAlignment(SwingConstants.CENTER);
        accLogoContainer.add(accLogo, BorderLayout.CENTER);

        JLabel txt = RegularText("Edit Account", 30, Colors.neutralBlue);
        txt.setHorizontalAlignment(SwingConstants.CENTER);
        txt.setPreferredSize(new Dimension(504, 40));


        JPanel formContainer = new JPanel();
        formContainer.setBackground(Colors.primaryBlack);
        formContainer.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 16));
        formContainer.setPreferredSize(new Dimension(252, 350));

        JLabel userIDLabel = RegularText("User ID", 13, Colors.neutralBlue);
        userIDLabel.setPreferredSize(new Dimension(65, 18));

        JLabel userID = BoldText(loggedUser==null?"Unknown":loggedUser, 16, Colors.neutralBlue_0_2);
        userID.setPreferredSize(new Dimension(133, 18));
        JLabel userIDEditRestriction = RegularText("  Can't Edit", 10, Colors.warning_0_6);

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
        firstNameObj.setText(userDBActions.getUserFirstName(loggedUser));


        JPanel lastNameContainer = new JPanel();
        lastNameContainer.setPreferredSize(new Dimension(118, 48));
        lastNameContainer.setBackground(Colors.transparent);
        lastNameContainer.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));
        JLabel lastNameLabel = RegularText("Last Name", 13);
        CustomTxtBx lastNameObj = new CustomTxtBx("\uf007", 118, 28, InputStatus.REGULAR, false, 45, false);
        JPanel lastNameInput = lastNameObj.getElement();
        lastNameContainer.add(lastNameLabel);
        lastNameContainer.add(lastNameInput);
        lastNameObj.setText(userDBActions.getUserLastName(loggedUser));

        userNameContainer.add(firstNameContainer);
        userNameContainer.add(lastNameContainer);

        JPanel userPasswordInputContainer = new JPanel();
        userPasswordInputContainer.setBackground(Colors.transparent);
        userPasswordInputContainer.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));
        userPasswordInputContainer.setPreferredSize(new Dimension(250, 48));
        JLabel userPasswordLabel = RegularText("New Password", 13);
        CustomTxtBx userPasswordObj = new CustomTxtBx("\uf023", 250, 28, InputStatus.REGULAR, true, 16, false);
        JPanel userPasswordInput = userPasswordObj.getElement();
        userPasswordInputContainer.add(userPasswordLabel);
        userPasswordInputContainer.add(userPasswordInput);

        JPanel userConfPasswordInputContainer = new JPanel();
        userConfPasswordInputContainer.setBackground(Colors.transparent);
        userConfPasswordInputContainer.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));
        userConfPasswordInputContainer.setPreferredSize(new Dimension(250, 48));
        JLabel userConfPasswordLabel = RegularText("Confirm New Password", 13);
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

        JPanel buttonContainer = new JPanel();
        buttonContainer.setBackground(Colors.primaryBlack);
        buttonContainer.setLayout(new BorderLayout(0, 0));
        buttonContainer.setPreferredSize(new Dimension(252, 50));
        buttonContainer.setBorder(BorderFactory.createEmptyBorder(16, 0, 0, 0));

        JPanel deleteBtn = createFlatButton("Delete Account", Colors.warning, Colors.neutralBlue, 13);
        deleteBtn.setPreferredSize(new Dimension(118, 34));
        buttonContainer.add(deleteBtn, BorderLayout.WEST);
        deleteBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(loggedUser==null){
                    Messages.fullyCustomError("You are not logged in to delete the account!");
                }
                else{
                    int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete your account?", "Warning! This can't be undone", JOptionPane.YES_NO_OPTION);
                    if(dialogResult == JOptionPane.YES_OPTION){
                        if(userDBActions.deleteUser(loggedUser)){
                            Messages.customSuccessMessage("Account Deleted");
                            loggedUser=null;
                            new LoginScreen().setVisible(true);
                            dispose();
                        }
                        else{
                            Messages.fullyCustomError("Something went wrong when deleting account");
                        }
                    }
                }
            }
        });

        JPanel updateBtn = createFlatButton("Update Account", Colors.success, Colors.secondaryBlack, 13);
        updateBtn.setPreferredSize(new Dimension(118, 34));
        buttonContainer.add(updateBtn, BorderLayout.EAST);
        updateBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(loggedUser==null){
                    Messages.fullyCustomError("You are not logged in to update the account!");
                }
                else if(emptyCheck(firstNameObj.getText()) || emptyCheck(lastNameObj.getText())){
                    Messages.fullyCustomError("First & Last Names cannot be empty!");
                }
                else if(!equalCheck(userPasswordObj.getText(), userConfPasswordObj.getText())){
                    Messages.passMissmatch();
                }
                else if(emptyCheck(userPasswordObj.getText())){
                    // Update data without password
                    int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to update your name?", "Warning! this can't be undone", JOptionPane.YES_NO_OPTION);
                    if (dialogResult == JOptionPane.YES_OPTION) {
                        if(userDBActions.updateUserNames(loggedUser, firstNameObj.getText(), lastNameObj.getText())){
                            Messages.customSuccessMessage("Names Updated");
                        }
                        else{
                            Messages.customFailedMessage("Update Names");
                        }
                    }
                }
                else{
                    // Update data with password
                    int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to update your account details?", "Warning! this can't be undone", JOptionPane.YES_NO_OPTION);
                    if (dialogResult == JOptionPane.YES_OPTION) {
                        if(userDBActions.updateUserDetails(loggedUser, firstNameObj.getText(), lastNameObj.getText(), userPasswordObj.getText())){
                            Messages.customSuccessMessage("Account Details Updated");
                        }
                        else{
                            Messages.customFailedMessage("Update Account Details");
                        }
                    }
                }
            }
        });


        formContainer.add(userIDLabel);
        formContainer.add(userID);
        formContainer.add(userIDEditRestriction);
        formContainer.add(userNameContainer);
        formContainer.add(userPasswordInputContainer);
        formContainer.add(userConfPasswordInputContainer);
        formContainer.add(buttonContainer);



        windowBody.add(accLogoContainer);
        windowBody.add(txt);
        windowBody.add(formContainer);

        windowContent.add(topBar, BorderLayout.NORTH);
        windowContent.add(windowBody, BorderLayout.CENTER);

        this.add(windowContent, BorderLayout.SOUTH);
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
            java.util.logging.Logger.getLogger(AccountScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AccountScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AccountScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AccountScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AccountScreen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
