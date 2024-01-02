/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Screens.Dialogs;

import Actions.UserDBActions;
import Assets.Colors;
import Assets.Messages;
import Components.Atoms.CloseBtn;
import Enums.InputStatus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static Components.Atoms.CustomBtn.createFlatButton;
import static Components.Atoms.CustomCmbBx.customCmbBx;
import Components.Atoms.CustomTxtBx;
import Screens.MainScreen;

import static Components.Atoms.ThemedText.RegularText;
import static Helpers.SharedVariables.loggedUser;
import static Util.Validations.Validations.emptyCheck;

public class AddProject extends javax.swing.JFrame {

    public AddProject() {
        this.setUndecorated(true);
        this.getContentPane().setBackground(Colors.primaryBlack);
        initComponents();

        this.setSize(277, 339);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout(0, 0));

        JPanel dialogContent = new JPanel();
        dialogContent.setBackground(Colors.primaryBlack);
        dialogContent.setLayout(new BorderLayout(0, 0));
        dialogContent.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
        dialogContent.setPreferredSize(new Dimension(277, 339));

        JPanel topBar = new JPanel();
        topBar.setBackground(Colors.primaryBlack);
        topBar.setLayout(new BorderLayout(0, 0));
        topBar.setPreferredSize(new Dimension(277, 20));
        JLabel Title = RegularText("Add New Project", 18);
        topBar.add(Title, BorderLayout.WEST);
        CloseBtn closeBtn = new CloseBtn(false);
        closeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainScreen().setVisible(true);
                dispose();
            }
        });
        topBar.add(closeBtn, BorderLayout.EAST);

        JPanel dialogBody = new JPanel();
        dialogBody.setBackground(Colors.transparent);
        dialogBody.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 15));
        dialogBody.setPreferredSize(new Dimension(245, 270));

        JPanel ticketNameInputContainer = new JPanel();
        ticketNameInputContainer.setBackground(Colors.primaryBlack);
        ticketNameInputContainer.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));
        ticketNameInputContainer.setPreferredSize(new Dimension(245, 48));
        JLabel ticketNameLabel = RegularText("Project Name", 13);
        CustomTxtBx ticketNameObj = new CustomTxtBx("\uf2c2", 245, 28, InputStatus.REGULAR, false, 45, false);
        JPanel ticketNameInput = ticketNameObj.getElement();
        ticketNameInputContainer.add(ticketNameLabel);
        ticketNameInputContainer.add(ticketNameInput);

        JPanel ticketSecurityContainer = new JPanel();
        ticketSecurityContainer.setBackground(Colors.primaryBlack);
        ticketSecurityContainer.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));
        ticketSecurityContainer.setPreferredSize(new Dimension(245, 21));
        JCheckBox ticketSecurityCheckBox = new JCheckBox();
        ticketSecurityCheckBox.setSelected(true);
        ticketSecurityCheckBox.setBackground(Colors.primaryBlack);
        ticketSecurityCheckBox.setPreferredSize(new Dimension(18, 16));
        JLabel ticketSecurityLabel = RegularText("Secured Project", 13);
        ticketSecurityLabel.setBorder(BorderFactory.createEmptyBorder(0, 12, 0, 0));
        ticketSecurityContainer.add(ticketSecurityCheckBox);
        ticketSecurityContainer.add(ticketSecurityLabel);


        JPanel projectPasswordInputContainer = new JPanel();
        projectPasswordInputContainer.setBackground(Colors.primaryBlack);
        projectPasswordInputContainer.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));
        projectPasswordInputContainer.setPreferredSize(new Dimension(245, 48));
        JLabel projectPasswordLabel = RegularText("Project Password", 13);
        CustomTxtBx projectPasswordObj = new CustomTxtBx("\uf023", 245, 28, InputStatus.REGULAR, true, 16, !ticketSecurityCheckBox.isSelected());
        JPanel projectPasswordInput = projectPasswordObj.getElement();
        projectPasswordInputContainer.add(projectPasswordLabel);
        projectPasswordInputContainer.add(projectPasswordInput);

        JPanel projectConfPasswordInputContainer = new JPanel();
        projectConfPasswordInputContainer.setBackground(Colors.primaryBlack);
        projectConfPasswordInputContainer.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));
        projectConfPasswordInputContainer.setPreferredSize(new Dimension(245, 48));
        JLabel projectConfPasswordLabel = RegularText("Confirm Password", 13);
        CustomTxtBx projectConfPasswordObj = new CustomTxtBx("\uf023", 245, 28, InputStatus.REGULAR, true, 16, !ticketSecurityCheckBox.isSelected());
        JPanel projectConfPasswordInput = projectConfPasswordObj.getElement();
        projectConfPasswordInputContainer.add(projectConfPasswordLabel);
        projectConfPasswordInputContainer.add(projectConfPasswordInput);


        ticketSecurityCheckBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean isSecurityEnabled = ticketSecurityCheckBox.isSelected();

                if(isSecurityEnabled){
                    projectPasswordObj.setDisabled(false);
                    projectPasswordObj.passInput.setEnabled(true);
                    projectPasswordInputContainer.remove(projectPasswordInput);
                    projectPasswordInputContainer.repaint();
                    projectPasswordInputContainer.revalidate();
                    projectPasswordInputContainer.add(projectPasswordObj.getElement());

                    projectConfPasswordObj.setDisabled(false);
                    projectConfPasswordObj.passInput.setEnabled(true);
                    projectConfPasswordInputContainer.remove(projectConfPasswordInput);
                    projectConfPasswordInputContainer.repaint();
                    projectConfPasswordInputContainer.revalidate();
                    projectConfPasswordInputContainer.add(projectConfPasswordObj.getElement());
                }
                else{
                    projectPasswordObj.setDisabled(true);
                    projectPasswordObj.passInput.setEnabled(false);
                    projectPasswordInputContainer.remove(projectPasswordInput);
                    projectPasswordInputContainer.repaint();
                    projectPasswordInputContainer.revalidate();
                    projectPasswordInputContainer.add(projectPasswordObj.getElement());

                    projectConfPasswordObj.setDisabled(true);
                    projectConfPasswordObj.passInput.setEnabled(false);
                    projectConfPasswordInputContainer.remove(projectConfPasswordInput);
                    projectConfPasswordInputContainer.repaint();
                    projectConfPasswordInputContainer.revalidate();
                    projectConfPasswordInputContainer.add(projectConfPasswordObj.getElement());
                }
                // Update the state of password fields based on the checkbox selection
//                projectPasswordInputContainer.remove(projectPasswordInput[0]);
//                projectPasswordInput[0] = new CustomTxtBx("\uf023", 245, 28, InputStatus.REGULAR, true, 16, !isSecurityEnabled).getElement();
//                projectPasswordInputContainer.add(projectPasswordInput[0]);
//                projectPasswordInputContainer.revalidate();
//                projectPasswordInputContainer.repaint();
//
//                projectConfPasswordInputContainer.remove(projectConfPasswordInput[0]);
//                projectConfPasswordInput[0] = new CustomTxtBx("\uf023", 245, 28, InputStatus.REGULAR, true, 16, !isSecurityEnabled).getElement();
//                projectConfPasswordInputContainer.add(projectConfPasswordInput[0]);
//                projectConfPasswordInputContainer.revalidate();
//                projectConfPasswordInputContainer.repaint();

                // Optionally, you can update the visual style or perform additional actions here
            }
        });

        JPanel submitBtn = createFlatButton("Add Project", Colors.primaryBlue, Colors.darkBlack, 14);
        submitBtn.setPreferredSize(new Dimension(245, 28));
        submitBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(emptyCheck(ticketNameObj.textInput.getText())){
                    Messages.emptyFields();
                }
                else if(ticketSecurityCheckBox.isSelected() && (emptyCheck(projectPasswordObj.getText()) || emptyCheck(projectConfPasswordObj.getText()))){
                    Messages.emptyFields();
                }
                else if(ticketSecurityCheckBox.isSelected() && !projectPasswordObj.getText().equals(projectConfPasswordObj.getText())){
                    Messages.passMissmatch();
                }
                else{
                    UserDBActions userDBActions = new UserDBActions();
                    int addProjectRes = userDBActions.insertProject(ticketNameObj.getText(), loggedUser, ticketSecurityCheckBox.isSelected(), projectPasswordObj.getText());
                    if(addProjectRes>0){
                        int setProjectAccessRes = userDBActions.setProjectAccess(addProjectRes, loggedUser);
                        if(setProjectAccessRes>0){
                            Messages.customSuccessMessage("Project Added");
                            new MainScreen().setVisible(true);
                            dispose();
                        }
                        else{
                            Messages.customFailedMessage("Add Project");
                        }
                    }
                    else{
                        Messages.customFailedMessage("Add Project");
                    }
                }
            }
        });


        dialogBody.add(ticketNameInputContainer);
        dialogBody.add(ticketSecurityContainer);
        dialogBody.add(projectPasswordInputContainer);
        dialogBody.add(projectConfPasswordInputContainer);
        dialogBody.add(submitBtn);


        dialogContent.add(topBar, BorderLayout.NORTH);
        dialogContent.add(dialogBody, BorderLayout.SOUTH);
        this.add(dialogContent, BorderLayout.SOUTH);;
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
            java.util.logging.Logger.getLogger(AddProject.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddProject.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddProject.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddProject.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddProject().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
