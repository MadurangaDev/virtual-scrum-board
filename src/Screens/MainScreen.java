/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Screens;

import Actions.UserDBActions;
import Assets.Colors;
import Assets.Messages;
import Components.Atoms.*;
import Components.ProjectCard;
import Enums.InputStatus;
import Screens.Auth.LoginScreen;
import Screens.Dialogs.AddProject;

//import static Components.Atoms.RoundBtn.createRoundButton;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

import static Components.Atoms.FAIcon.FAicon;
import static Components.Atoms.ThemedText.BoldText;
import static Components.Atoms.ThemedText.RegularText;
import static Helpers.SharedVariables.loggedUser;
import static Util.Validations.Validations.emptyCheck;

public class MainScreen extends javax.swing.JFrame {
    private String userFirstName;
    public MainScreen() {
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

        JPanel topBarLeft = new JPanel();
        topBarLeft.setBackground(Colors.primaryBlack);
        topBarLeft.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));

        loadFirstName();

        JLabel name = BoldText(userFirstName, 22);
        name.setForeground(Colors.neutralBlue);
        topBarLeft.add(name);
        JLabel txt = RegularText("'s Projects", 20);
        txt.setForeground(Colors.neutralBlue);
        topBarLeft.add(txt);

        JPanel topBarRight = new JPanel();
        topBarRight.setBackground(Colors.primaryBlack);
        topBarRight.setLayout(new FlowLayout(FlowLayout.TRAILING, 0, 0));
        topBarRight.setPreferredSize(new Dimension(232, 26));
        CloseBtn closeBtn = new CloseBtn(true);

        JPanel logOutBtnContainer = new JPanel();
        logOutBtnContainer.setBackground(Colors.primaryBlack);
        logOutBtnContainer.setLayout(null);
        logOutBtnContainer.setPreferredSize(new Dimension(106, 25));

        JPanel accBtnContainer = new JPanel();
        accBtnContainer.setBackground(Colors.primaryBlack);
        accBtnContainer.setLayout(null);
        accBtnContainer.setPreferredSize(new Dimension(106, 25));

        JPanel logOutBtnContent = new JPanel();
        logOutBtnContent.setPreferredSize(new Dimension(60, 14));
        logOutBtnContent.setBackground(Colors.transparent);
        logOutBtnContent.setLayout(null);
        JLabel logOutBtnIcon = FAicon("\uf2f5", 12, Colors.secondaryBlue);
        logOutBtnIcon.setBounds(50, 0, 18, 14);
        JLabel logOutBtnText = RegularText("Logout", 12, Colors.secondaryBlue);
        logOutBtnText.setBounds(5, 0, 45, 14);
        logOutBtnContent.add(logOutBtnText);
        logOutBtnContent.add(logOutBtnIcon);
        RoundBtn logoutBtn = new RoundBtn(logOutBtnContent, Colors.secondaryBlack, Colors.neutralBlue, 25, 90, 25);
        logoutBtn.setBounds(0, 0, 90, 25);
        logoutBtn.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(
                    null,
                    "Are you sure you want to logout?",
                    "Confirm Exit",
                    JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                // User clicked "Yes," close the application
                new LoginScreen().setVisible(true);
                this.dispose();
            }
        });
        logOutBtnContainer.add(logoutBtn);

        JPanel accBtnContent = new JPanel();
        accBtnContent.setPreferredSize(new Dimension(70, 14));
        accBtnContent.setBackground(Colors.transparent);
        accBtnContent.setLayout(null);
        JLabel accBtnIcon = FAicon("\uf0ad", 12, Colors.secondaryBlue);
        accBtnIcon.setBounds(50, 0, 18, 14);
        JLabel accBtnText = RegularText("Account", 12, Colors.secondaryBlue);
        accBtnText.setBounds(0, 0, 55, 14);
        accBtnContent.add(accBtnText);
        accBtnContent.add(accBtnIcon);
        RoundBtn accBtn = new RoundBtn(accBtnContent, Colors.secondaryBlack, Colors.neutralBlue, 25, 90, 25);
        accBtn.setBounds(0, 0, 90, 25);
        accBtnContainer.add(accBtn);
        accBtn.addActionListener(e -> {
            new AccountScreen().setVisible(true);
            this.dispose();
        });

        topBarRight.add(accBtnContainer);
        topBarRight.add(logOutBtnContainer);
        topBarRight.add(closeBtn);



        JPanel bottomBar = new JPanel();
        bottomBar.setBackground(Colors.primaryBlack);
        bottomBar.setLayout(new BorderLayout(0, 0));
        bottomBar.setPreferredSize(new Dimension(504, 30));

        JPanel newProjBtnContent = new JPanel();
        newProjBtnContent.setPreferredSize(new Dimension(70, 14));
        newProjBtnContent.setBackground(Colors.transparent);
        newProjBtnContent.setLayout(null);
        JLabel newProjBtnIcon = FAicon("\u002b", 18, Colors.darkBlack);
        newProjBtnIcon.setBounds(17, 0, 18, 18);
        JLabel newProjBtnText = BoldText("New Project", 18, Colors.darkBlack);
        newProjBtnText.setBounds(42, 0, 110, 18);
        newProjBtnContent.add(newProjBtnIcon);
        newProjBtnContent.add(newProjBtnText);
        RoundBtn newProjBtn = new RoundBtn(newProjBtnContent, Colors.success, Colors.neutralBlue, 30, 200, 30);
        newProjBtn.setBounds(0, 0, 90, 25);
        newProjBtn.addActionListener(e -> {
            new AddProject().setVisible(true);
            this.dispose();
        });


        JLabel projCount = RegularText("Total Projects: N/A", 18, Colors.neutralBlue);
        bottomBar.add(newProjBtn, BorderLayout.WEST);
        bottomBar.add(projCount, BorderLayout.EAST);

        topBar.add(topBarLeft, BorderLayout.WEST);
        topBar.add(topBarRight, BorderLayout.EAST);

        JPanel windowBody = new JPanel();
        windowBody.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 16));
        windowBody.setBackground(Colors.transparent);

        JPanel searchSection = new JPanel();
        searchSection.setBackground(Colors.primaryBlack);
        searchSection.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));
        searchSection.setPreferredSize(new Dimension(472, 30));

        JPanel searchInputContainer = new JPanel();
        searchInputContainer.setBackground(Colors.primaryBlack);
        searchInputContainer.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));
        searchInputContainer.setPreferredSize(new Dimension(319, 30));
        CustomTxtBx searchInputObj = new CustomTxtBx("\uf002", 303, 30, InputStatus.REGULAR, false, 128, false);
        JPanel searchInput = searchInputObj.getElement();
        searchInput.setBounds(0, 0, 303, 30);
        searchInputContainer.add(searchInput);

        JPanel searchBtn = CustomBtn.createFlatButton("Search Projects", Colors.primaryBlue, Colors.neutralBlue, 16);
        searchBtn.setPreferredSize(new Dimension(153, 30));


        searchSection.add(searchInputContainer);
        searchSection.add(searchBtn);

        JPanel divider = new JPanel();
        divider.setBackground(Colors.neutralBlue_0_2);
        divider.setPreferredSize(new Dimension(440, 2));

        JPanel projectsList = new JPanel();
        JScrollPane projectsListContainer = new JScrollPane(projectsList);

        searchBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                String searchInput = searchInputObj.getText();
                    ArrayList<Integer> projectIDs = userDBActions.searchProjects(searchInput, loggedUser);
                    if(projectIDs==null){
                        Messages.customFailedMessage("search projects");
                    }
                    else{
                        int projectCount=projectIDs.size();
                        projCount.setText("Total Projects: "+projectCount);
//                        ((JLabel) searchBtn.getComponent(0)).setText("Reset Search");
                        projectsList.removeAll();
                        for(int i=0; i<projectCount; i++){
                            RoundBtn project = new ProjectCard((MainScreen) SwingUtilities.getWindowAncestor(searchBtn), projectIDs.get(i)).getElement();
                            projectsList.add(project);
                        }
                        projectsList.setPreferredSize(new Dimension(472, ((Math.ceilDiv(projectCount, 3)*152)+(16*(Math.ceilDiv(projectCount, 3)-1))+32)));
                        projectsListContainer.setPreferredSize(new Dimension(472, 488));
                        projectsListContainer.revalidate();
                        projectsListContainer.repaint();
                    }

            }
        });


        int projectCount=0;
        ArrayList<Integer> projectIDs = userDBActions.getProjectIDs(loggedUser);
        if(projectIDs==null){
            Messages.customFailedMessage("load projects");
        }
        else{
            try {
                projectCount=projectIDs.size();
                projCount.setText("Total Projects: "+projectCount);

                for(int i=0; i<projectCount; i++){
                    RoundBtn project = new ProjectCard(this, projectIDs.get(i)).getElement();
                    projectsList.add(project);
                }
            } catch (Exception e) {
                Messages.customFailedMessage("load projects");
            }
        }


        projectsList.setBackground(Colors.primaryBlack);
        projectsList.setPreferredSize(new Dimension(472, ((Math.ceilDiv(projectCount, 3)*152)+(16*(Math.ceilDiv(projectCount, 3)-1))+32)));
        projectsList.setLayout(new FlowLayout(FlowLayout.LEADING, 16, 16));


        projectsListContainer.setPreferredSize(new Dimension(472, 488));
        projectsListContainer.setBorder(null);
        projectsListContainer.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        projectsListContainer.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);


        windowBody.add(searchSection);
        windowBody.add(divider);
        windowBody.add(projectsListContainer);

        windowContent.add(topBar, BorderLayout.NORTH);
        windowContent.add(windowBody, BorderLayout.CENTER);
        windowContent.add(bottomBar, BorderLayout.SOUTH);
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


    private void loadFirstName(){
        UserDBActions userDBActions = new UserDBActions();
        userFirstName = userDBActions.getUserFirstName(loggedUser);
    }

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
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainScreen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
