/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Screens;

import Actions.UserDBActions;
import Assets.Colors;
import Assets.Messages;
import Components.Atoms.CloseBtn;
import Components.Atoms.CustomBtn;
import Components.Atoms.CustomTxtBx;
import Components.Atoms.RoundBtn;
import Components.ProjectCard;
import Components.TaskCard;
import Enums.InputStatus;
import Enums.TicketStatus;
import Screens.Dialogs.AddDeveloper;
import Screens.Dialogs.AddTicket;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import static Components.Atoms.FAIcon.FAicon;
import static Components.Atoms.ThemedText.BoldText;
import static Components.Atoms.ThemedText.RegularText;
import static Helpers.SharedVariables.loggedUser;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class ProjectScreen extends javax.swing.JFrame {
    private int projectID;
    public int getProjectID(){
        return projectID;
    }
    public ProjectScreen(int projectID) {
        this.projectID = projectID;
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
        topBarLeft.setPreferredSize(new Dimension(220, 28));
        topBarLeft.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));


        JLabel name = BoldText("Unknown", 22);
        name.setForeground(Colors.neutralBlue);
        topBarLeft.add(name);
        String projName = userDBActions.getProjectName(projectID);
        if(projName==null){
            Messages.customFailedMessage("load project name");
        }
        else{
            name.setText(projName);
        }


        JPanel topBarRight = new JPanel();
        topBarRight.setBackground(Colors.primaryBlack);
        topBarRight.setLayout(new FlowLayout(FlowLayout.TRAILING, 0, 0));
        topBarRight.setPreferredSize(new Dimension(232, 26));
        CloseBtn closeBtn = new CloseBtn(false);
        closeBtn.addActionListener(e -> {
            new MainScreen().setVisible(true);
            this.dispose();
        });

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
        JLabel logOutBtnIcon = FAicon("\uf15c", 12, Colors.secondaryBlue);
        logOutBtnIcon.setBounds(50, 0, 18, 14);
        JLabel logOutBtnText = RegularText("Report", 12, Colors.secondaryBlue);
        logOutBtnText.setBounds(5, 0, 45, 14);
        logOutBtnContent.add(logOutBtnText);
        logOutBtnContent.add(logOutBtnIcon);
        RoundBtn logoutBtn = new RoundBtn(logOutBtnContent, Colors.secondaryBlack, Colors.neutralBlue, 25, 90, 25);
        logoutBtn.setBounds(0, 0, 90, 25);
        logOutBtnContainer.add(logoutBtn);
        logoutBtn.addActionListener(e -> {
            String projectName = userDBActions.getProjectName(projectID);
            if(projectName!=null) {
                String projectOwnerID = userDBActions.getProjectOwnerID(projectID);
                if(projectOwnerID!=null) {
                    String fName = userDBActions.getUserFirstName(projectOwnerID);
                    if(fName!=null) {
                        String lName = userDBActions.getUserLastName(projectOwnerID);
                        if(lName!=null) {
                            String projectOwnerName = fName + " " + lName;
                            int accessCount = userDBActions.getProjectSharedCount(projectID);
                            if(accessCount>=0) {
                                boolean projectSecurity = userDBActions.getProjectSecurity(projectID);
                                int ticketCount = userDBActions.getTicketCount(projectID);
                                if(ticketCount>=0) {
                                    int toDoTicketCount = userDBActions.getTicketsCountByStatus(projectID, TicketStatus.TO_DO);
                                    if(toDoTicketCount>=0) {
                                        int ongoingTicketCount = userDBActions.getTicketsCountByStatus(projectID, TicketStatus.ONGOING);
                                        if (ongoingTicketCount >= 0) {
                                            int finishedTicketCount = userDBActions.getTicketsCountByStatus(projectID, TicketStatus.DONE);
                                            if(finishedTicketCount>=0) {
                                                float progress;
                                                try {
                                                   progress  = ((float) finishedTicketCount / (float) ticketCount) * 100;
                                                }
                                                catch (Exception exception){
                                                    progress = 0;
                                                }
                                                if(userDBActions.generateReport(projectID, projectName, projectOwnerName, projectSecurity, accessCount, ticketCount, toDoTicketCount, ongoingTicketCount, finishedTicketCount, progress)){
                                                    try{
                                                        JasperDesign jasrep= JRXmlLoader.load(getClass().getResourceAsStream("/report/report1.jrxml"));
                                                        JRDesignQuery designquery = new JRDesignQuery();
                                                        designquery.setText("select * FROM report");
                                                        jasrep.setQuery(designquery);

                                                        JasperReport report = JasperCompileManager.compileReport(jasrep);
                                                        JasperPrint print= JasperFillManager.fillReport(report, null, userDBActions.getConnection());
                                                        Messages.fullyCustomMessage("Report generating successful. Now opening...");
                                                        JasperViewer.viewReport(print);

                                                    }
                                                    catch(JRException e2){
                                                        System.out.println(rootPane+"Error: "+e2.getMessage());
                                                    }
                                                   catch(NullPointerException e3){
                                                       System.out.println(rootPane+"Error: "+e3.getMessage());
                                                   }
                                                }
                                                else{
                                                    Messages.customFailedMessage("generate report");
                                                }
                                            }
                                            else{
                                                Messages.customFailedMessage("load finished ticket count");
                                            }
                                        }
                                        else{
                                            Messages.customFailedMessage("load ongoing ticket count");
                                        }
                                    }
                                    else{
                                        Messages.customFailedMessage("load to-do ticket count");
                                    }
                                }
                                else{
                                    Messages.customFailedMessage("load ticket count");
                                }
                            }
                            else{
                                Messages.customFailedMessage("load project access count");
                            }
                        }
                        else{
                            Messages.customFailedMessage("load project owner last name");
                        }
                    }
                    else{
                        Messages.customFailedMessage("load project owner first name");
                    }
                }
                else{
                    Messages.customFailedMessage("load project owner ID");
                }
            }
            else{
                Messages.customFailedMessage("load project name");
            }
        });

        JPanel accBtnContent = new JPanel();
        accBtnContent.setPreferredSize(new Dimension(70, 14));
        accBtnContent.setBackground(Colors.transparent);
        accBtnContent.setLayout(null);
        JLabel accBtnIcon = FAicon("\uf234", 12, Colors.secondaryBlue);
        accBtnIcon.setBounds(50, 0, 18, 14);
        JLabel accBtnText = RegularText("Assign", 12, Colors.secondaryBlue);
        accBtnText.setBounds(5, 0, 35, 14);
        accBtnContent.add(accBtnText);
        accBtnContent.add(accBtnIcon);
        RoundBtn accBtn = new RoundBtn(accBtnContent, Colors.secondaryBlack, Colors.neutralBlue, 25, 90, 25);
        accBtn.setBounds(0, 0, 90, 25);
        accBtnContainer.add(accBtn);
        accBtn.addActionListener(e -> {
            new AddDeveloper(projectID).setVisible(true);
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
        newProjBtnIcon.setBounds(1, 0, 18, 18);
        JLabel newProjBtnText = BoldText("New Task Ticket", 18, Colors.darkBlack);
        newProjBtnText.setBounds(24, 0, 145, 18);


        newProjBtnContent.add(newProjBtnIcon);
        newProjBtnContent.add(newProjBtnText);
        RoundBtn newProjBtn = new RoundBtn(newProjBtnContent, Colors.success, Colors.neutralBlue, 30, 200, 30);
        newProjBtn.setBounds(0, 0, 90, 25);
        newProjBtn.addActionListener(e -> {
            new AddTicket(projectID).setVisible(true);
            this.dispose();
        });

        JLabel projCount = RegularText("Project ID: Null", 18, Colors.neutralBlue);
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

        JPanel searchBtn = CustomBtn.createFlatButton("Search Tasks", Colors.primaryBlue, Colors.neutralBlue, 16);
        searchBtn.setPreferredSize(new Dimension(153, 30));

        searchSection.add(searchInputContainer);
        searchSection.add(searchBtn);

        JPanel divider = new JPanel();
        divider.setBackground(Colors.neutralBlue_0_2);
        divider.setPreferredSize(new Dimension(440, 2));

        JPanel ticketsList = new JPanel();
        JScrollPane projectsListContainer = new JScrollPane(ticketsList);

        searchBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String searchQuery = searchInputObj.getText();
                ArrayList<Integer> ticketIDs = userDBActions.searchTickets(searchQuery, projectID);
                    if(ticketIDs==null){
                        Messages.customFailedMessage("search tickets");
                    }
                    else{
                        int ticketCount=ticketIDs.size();

                        ticketsList.removeAll();
                        ticketsList.revalidate();
                        ticketsList.repaint();

                        for(int i=0; i<ticketCount; i++){
                            JPanel ticket = new TaskCard((ProjectScreen) SwingUtilities.getWindowAncestor(ticketsList), ticketIDs.get(i)).getElement();
                            ticketsList.add(ticket);
                        }
                        ticketsList.setPreferredSize(new Dimension(472, (ticketCount*152)+(16*(ticketCount-1))+32));
                        ticketsList.revalidate();
                        ticketsList.repaint();
                    }

            }
        });

        int ticketCount=0;
        ArrayList<Integer> ticketIDs = userDBActions.getTicketIDs(projectID);
        if(ticketIDs==null){
            Messages.customFailedMessage("load tickets");
        }
        else{
            try {
                ticketCount=ticketIDs.size();
                projCount.setText("Project ID: "+projectID);

                for(int i=0; i<ticketCount; i++){
                    JPanel ticket = new TaskCard(this, ticketIDs.get(i)).getElement();
                    ticketsList.add(ticket);
                }
            } catch (Exception e) {
                Messages.customFailedMessage("load tickets");
            }
        }


        ticketsList.setBackground(Colors.primaryBlack);
        ticketsList.setPreferredSize(new Dimension(472, (ticketCount*152)+(16*(ticketCount-1))+32));
        ticketsList.setLayout(new FlowLayout(FlowLayout.LEADING, 16, 16));


        projectsListContainer.setPreferredSize(new Dimension(472, 452));
        projectsListContainer.setBorder(null);
        projectsListContainer.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        projectsListContainer.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

        JPanel filterContainer = new JPanel();
        filterContainer.setBackground(Colors.primaryBlack);
        filterContainer.setLayout(new FlowLayout(FlowLayout.LEADING, 17, 0));
        filterContainer.setPreferredSize(new Dimension(504, 20));

        JPanel allFilterBtn = CustomBtn.createFlatButton("All Tasks", Colors.secondaryBlack, Colors.neutralBlue, 12);
        allFilterBtn.setPreferredSize(new Dimension(146, 20));
        allFilterBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String searchQuery = "";
                ArrayList<Integer> ticketIDs = userDBActions.searchTickets(searchQuery, projectID);
                if(ticketIDs==null){
                    Messages.customFailedMessage("filter tickets");
                }
                else{
                    int ticketCount=ticketIDs.size();

                    ticketsList.removeAll();
                    ticketsList.revalidate();
                    ticketsList.repaint();

                    for(int i=0; i<ticketCount; i++){
                        JPanel ticket = new TaskCard((ProjectScreen) SwingUtilities.getWindowAncestor(ticketsList), ticketIDs.get(i)).getElement();
                        ticketsList.add(ticket);
                    }
                    ticketsList.setPreferredSize(new Dimension(472, (ticketCount*152)+(16*(ticketCount-1))+32));
                    ticketsList.revalidate();
                    ticketsList.repaint();
                }
            }
        });

        JPanel finishedFilterBtn = CustomBtn.createFlatButton("Finished Tasks", Colors.secondaryBlack, Colors.neutralBlue, 12);
        finishedFilterBtn.setPreferredSize(new Dimension(146, 20));
        finishedFilterBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ArrayList<Integer> ticketIDs = userDBActions.filterTickets(projectID, TicketStatus.DONE);
                if(ticketIDs==null){
                    Messages.customFailedMessage("filter tickets");
                }
                else{
                    int ticketCount=ticketIDs.size();

                    ticketsList.removeAll();
                    ticketsList.revalidate();
                    ticketsList.repaint();

                    for(int i=0; i<ticketCount; i++){
                        JPanel ticket = new TaskCard((ProjectScreen) SwingUtilities.getWindowAncestor(ticketsList), ticketIDs.get(i)).getElement();
                        ticketsList.add(ticket);
                    }
                    ticketsList.setPreferredSize(new Dimension(472, (ticketCount*152)+(16*(ticketCount-1))+32));
                    ticketsList.revalidate();
                    ticketsList.repaint();
                }
            }
        });

        JPanel ongoingFilterBtn = CustomBtn.createFlatButton("Ongoing Tasks", Colors.secondaryBlack, Colors.neutralBlue, 12);
        ongoingFilterBtn.setPreferredSize(new Dimension(146, 20));
        ongoingFilterBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ArrayList<Integer> ticketIDs = userDBActions.filterTickets(projectID, TicketStatus.ONGOING);
                if(ticketIDs==null){
                    Messages.customFailedMessage("filter tickets");
                }
                else{
                    int ticketCount=ticketIDs.size();

                    ticketsList.removeAll();
                    ticketsList.revalidate();
                    ticketsList.repaint();

                    for(int i=0; i<ticketCount; i++){
                        JPanel ticket = new TaskCard((ProjectScreen) SwingUtilities.getWindowAncestor(ticketsList), ticketIDs.get(i)).getElement();
                        ticketsList.add(ticket);
                    }
                    ticketsList.setPreferredSize(new Dimension(472, (ticketCount*152)+(16*(ticketCount-1))+32));
                    ticketsList.revalidate();
                    ticketsList.repaint();
                }
            }
        });

        filterContainer.add(allFilterBtn);
        filterContainer.add(ongoingFilterBtn);
        filterContainer.add(finishedFilterBtn);

        windowBody.add(searchSection);
        windowBody.add(filterContainer);
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
            java.util.logging.Logger.getLogger(ProjectScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProjectScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProjectScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProjectScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ProjectScreen(1).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
