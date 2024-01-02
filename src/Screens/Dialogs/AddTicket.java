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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static Components.Atoms.CustomBtn.createFlatButton;
import Components.Atoms.CustomTxtBx;
import Screens.ProjectScreen;

import static Components.Atoms.ThemedText.RegularText;
import static Util.Validations.Validations.emptyCheck;

public class AddTicket extends javax.swing.JFrame {

    public AddTicket(int projectID) {
        this.setUndecorated(true);
        this.getContentPane().setBackground(Colors.primaryBlack);
        initComponents();

        this.setSize(277, 242);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout(0, 0));

        JPanel dialogContent = new JPanel();
        dialogContent.setBackground(Colors.primaryBlack);
        dialogContent.setLayout(new BorderLayout(0, 0));
        dialogContent.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
        dialogContent.setPreferredSize(new Dimension(277, 242));

        JPanel topBar = new JPanel();
        topBar.setBackground(Colors.primaryBlack);
        topBar.setLayout(new BorderLayout(0, 0));
        topBar.setPreferredSize(new Dimension(277, 20));
        JLabel Title = RegularText("Add Task Ticket", 18);
        topBar.add(Title, BorderLayout.WEST);
        CloseBtn closeBtn = new CloseBtn(false);
        topBar.add(closeBtn, BorderLayout.EAST);
        closeBtn.addActionListener(e -> {
            new ProjectScreen(projectID).setVisible(true);
            dispose();
        });

        JPanel dialogBody = new JPanel();
        dialogBody.setBackground(Colors.transparent);
        dialogBody.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 16));
        dialogBody.setPreferredSize(new Dimension(245, 174));

        JPanel ticketNameInputContainer = new JPanel();
        ticketNameInputContainer.setBackground(Colors.transparent);
        ticketNameInputContainer.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));
        ticketNameInputContainer.setPreferredSize(new Dimension(245, 48));
        JLabel ticketNameLabel = RegularText("Task", 13);
        CustomTxtBx ticketNameInputObj = new CustomTxtBx("\uf772", 245, 28, InputStatus.REGULAR, false, 128, false);
        JPanel ticketNameInput = ticketNameInputObj.getElement();
        ticketNameInputContainer.add(ticketNameLabel);
        ticketNameInputContainer.add(ticketNameInput);

        JPanel ticketDescInputContainer = new JPanel();
        ticketDescInputContainer.setBackground(Colors.transparent);
        ticketDescInputContainer.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));
        ticketDescInputContainer.setPreferredSize(new Dimension(245, 48));
        JLabel ticketDescLabel = RegularText("Description (optional)", 13);
        CustomTxtBx ticketDescInputObj = new CustomTxtBx("\uf303", 245, 28, InputStatus.REGULAR, false, 128, false);
        JPanel ticketDescInput = ticketDescInputObj.getElement();
        ticketDescInputContainer.add(ticketDescLabel);
        ticketDescInputContainer.add(ticketDescInput);

        JPanel addBtn = createFlatButton("Add Ticket", Colors.primaryBlue, Colors.darkBlack, 14);
        addBtn.setPreferredSize(new Dimension(245, 28));
        addBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(emptyCheck(ticketNameInputObj.getText())){
                    Messages.fullyCustomError("Task is required.");
                }
                else{
                    UserDBActions userDBActions = new UserDBActions();
                    int result = userDBActions.addTicket(ticketNameInputObj.getText(), ticketDescInputObj.getText());
                    if(result>0){
                        int linkingResult = userDBActions.linkTicketToProject(projectID, result);

                        if(linkingResult>0){
                            Messages.customSuccessMessage("ticket added");
                            new ProjectScreen(projectID).setVisible(true);
                            dispose();
                        }
                        else{
                            Messages.customFailedMessage("add ticket");
                        }
                    }
                    else{
                        Messages.customFailedMessage("add ticket");
                    }
                }
            }
        });


        dialogBody.add(ticketNameInputContainer);
        dialogBody.add(ticketDescInputContainer);
        dialogBody.add(addBtn);


        dialogContent.add(topBar, BorderLayout.NORTH);
        dialogContent.add(dialogBody, BorderLayout.SOUTH);
        this.add(dialogContent, BorderLayout.SOUTH);

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
            java.util.logging.Logger.getLogger(AddTicket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddTicket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddTicket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddTicket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddTicket(1).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
