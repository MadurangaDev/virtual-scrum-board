/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Screens.Dialogs;

import Actions.UserDBActions;
import Assets.Colors;
import Assets.Messages;
import Components.Atoms.CloseBtn;
import Components.Atoms.CustomCmbBx;
import Components.Atoms.CustomComboItem;
import Enums.InputStatus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static Components.Atoms.CustomBtn.createFlatButton;
import static Components.Atoms.CustomCmbBx.customCmbBx;
import Components.Atoms.CustomTxtBx;
import Screens.ProjectScreen;

import static Components.Atoms.CustomCmbBx.getSelectedValue;
import static Components.Atoms.ThemedText.RegularText;
import static Helpers.SharedVariables.loggedUser;

public class LinkTicket extends JFrame {

    public LinkTicket(int projectID, int ticketID, String[] linkableProjects, int[] linkableProjectIDs) {
        this.setUndecorated(true);
        this.getContentPane().setBackground(Colors.primaryBlack);
        initComponents();
        UserDBActions userDBActions = new UserDBActions();

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
        JLabel Title = RegularText("Link Ticket", 18);
        topBar.add(Title, BorderLayout.WEST);
        CloseBtn closeBtn = new CloseBtn(false);
        closeBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new ProjectScreen(projectID).setVisible(true);
                dispose();
            }
        });
        topBar.add(closeBtn, BorderLayout.EAST);

        JPanel dialogBody = new JPanel();
        dialogBody.setBackground(Colors.primaryBlack);
        dialogBody.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 14));
        dialogBody.setPreferredSize(new Dimension(245, 190));





        JPanel ticketNameInputContainer = new JPanel();
        ticketNameInputContainer.setBackground(Colors.primaryBlack);
        ticketNameInputContainer.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));
        ticketNameInputContainer.setPreferredSize(new Dimension(245, 48));
        JLabel ticketNameLabel = RegularText("Project ID", 13);
        JPanel ticketNameInput = customCmbBx("addProjIDCmbBx", "\uf2c2", 245, 28, InputStatus.REGULAR, linkableProjects, linkableProjectIDs);
        ticketNameInputContainer.add(ticketNameLabel);
        ticketNameInputContainer.add(ticketNameInput);

        JPanel ticketDescInputContainer = new JPanel();
        ticketDescInputContainer.setBackground(Colors.primaryBlack);
        ticketDescInputContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        ticketDescInputContainer.setPreferredSize(new Dimension(245, 70));
        JLabel ticketDescLabel = RegularText("Select your project and verify the Project ID at the", 10);
        JLabel ticketDescLabel2 = RegularText("bottom right when your project is open to avoid", 10);
        JLabel ticketDescLabel3 = RegularText("confusion with duplicate names.", 10);
        JLabel ticketDescLabel4 = RegularText("Kindly note that, here only listed the projects where", 10);
        JLabel ticketDescLabel5 = RegularText("you hold the role of project owner.", 10);
        ticketDescLabel.setPreferredSize(new Dimension(245, 14));
        ticketDescLabel2.setPreferredSize(new Dimension(230, 14));
        ticketDescLabel3.setPreferredSize(new Dimension(150, 14));
        ticketDescLabel4.setPreferredSize(new Dimension(245, 14));
        ticketDescLabel5.setPreferredSize(new Dimension(170, 14));
        ticketDescLabel.setForeground(Colors.neutralBlue_0_5);
        ticketDescLabel2.setForeground(Colors.neutralBlue_0_5);
        ticketDescLabel3.setForeground(Colors.neutralBlue_0_5);
        ticketDescLabel4.setForeground(Colors.warning_0_6);
        ticketDescLabel5.setForeground(Colors.warning_0_6);
        ticketDescInputContainer.add(ticketDescLabel);
        ticketDescInputContainer.add(ticketDescLabel2);
        ticketDescInputContainer.add(ticketDescLabel3);
        ticketDescInputContainer.add(ticketDescLabel4);
        ticketDescInputContainer.add(ticketDescLabel5);

        JPanel addBtn = createFlatButton("Link Ticket", Colors.primaryBlue, Colors.darkBlack, 14);
        addBtn.setPreferredSize(new Dimension(245, 28));
        addBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                UserDBActions userDBActions = new UserDBActions();
                if(userDBActions.linkTicketToProject(CustomCmbBx.getSelectedValue((JComboBox<CustomComboItem>) ticketNameInput.getComponent(2)).getValue(), ticketID)>0){
                    Messages.customSuccessMessage("Ticket linked to project");
                    new ProjectScreen(projectID).setVisible(true);
                    dispose();
                }
                else{
                    Messages.customFailedMessage("link ticket to project");
                }
//                CustomComboItem selectedValue = CustomCmbBx.getSelectedValue((JComboBox<CustomComboItem>) ticketNameInput.getComponent(2));
//                if (selectedValue != null) {
//                    // Get the text value of the selected item
//                    int selectedText = selectedValue.getValue();
//
//                    // Print or use the selected text as needed
//                    System.out.println("Selected Value: " + selectedText);
//                } else {
//                    System.out.println("No item selected");
//                }
            }
        });


        dialogBody.add(ticketNameInputContainer);
        dialogBody.add(ticketDescInputContainer);
        dialogBody.add(addBtn);


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
            java.util.logging.Logger.getLogger(LinkTicket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LinkTicket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LinkTicket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LinkTicket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LinkTicket(1, 1, new String[]{}, new int[]{}).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
