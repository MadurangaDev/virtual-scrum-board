package Components;

import Actions.UserDBActions;
import Assets.Colors;
import static Components.Atoms.CustomBtn.createFlatButton;

import Assets.Messages;
import Components.Atoms.FAIcon;
import Components.Atoms.RoundBtn;
import Enums.TicketStatus;
import Screens.Dialogs.LinkTicket;
import Screens.ProjectScreen;

import javax.swing.*;
import java.awt.*;

import static Components.Atoms.FAIcon.FAicon;
import static Components.Atoms.ThemedText.BoldText;
import static Components.Atoms.ThemedText.RegularText;
import static Helpers.SharedVariables.loggedUser;

public class TaskCard {
    private JPanel taskCard;
    private int ticketID;
    private String ticketTask;
    private TicketStatus ticketStatus;
    private String ticketDescription;

    public TaskCard(ProjectScreen parent, int ticketID){
        this.ticketID = ticketID;
        loadTask();
        loadStatus();
        loadDescription();
        taskCard = new JPanel();
        taskCard.setPreferredSize(new Dimension(440, 152));
        taskCard.setBackground(Colors.secondaryBlue);
        taskCard.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        JPanel taskCardContent = new JPanel();
        taskCardContent.setPreferredSize(new Dimension(440, 152));
        taskCardContent.setBackground(Colors.secondaryBlue);
        taskCardContent.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 12));
        taskCardContent.setBorder(BorderFactory.createEmptyBorder(0, 18, 0, 12));

        JPanel taskCardTop = new JPanel();
        taskCardTop.setPreferredSize(new Dimension(410, 42));
        taskCardTop.setBackground(Colors.secondaryBlue);
        taskCardTop.setLayout(new BorderLayout(0, 0));

        JPanel taskCardTopLeft = new JPanel();
        taskCardTopLeft.setPreferredSize(new Dimension(193, 42));
        taskCardTopLeft.setBackground(Colors.secondaryBlue);
        taskCardTopLeft.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        JPanel taskCardTopRight = new JPanel();
        taskCardTopRight.setPreferredSize(new Dimension(217, 42));
        taskCardTopRight.setBackground(Colors.secondaryBlue);
        taskCardTopRight.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0));


        JLabel taskCardStatusIcon = FAicon(ticketStatus==TicketStatus.DONE?"\uf00c":(ticketStatus==TicketStatus.TO_DO?"\uf0ae":"\uf110"), 32, Colors.primaryBlue);
        taskCardStatusIcon.setPreferredSize(new Dimension(61, 38));

        JLabel taskCardStatus = BoldText(ticketStatus.getValue(), 32, Colors.primaryBlue);

        JPanel linkButtonContent = new JPanel();
        linkButtonContent.setPreferredSize(new Dimension(96, 14));
        linkButtonContent.setBackground(Colors.transparent);
        linkButtonContent.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));

        JLabel linkBtnTxt = BoldText("Link Ticket", 14, Colors.darkBlack);
        linkBtnTxt.setHorizontalAlignment(SwingConstants.LEFT);
        linkBtnTxt.setPreferredSize(new Dimension(90, 14));
        JLabel linkBtnIcon = FAicon("\uf0c1", 14, Colors.darkBlack);

        linkButtonContent.add(linkBtnTxt);
        linkButtonContent.add(linkBtnIcon);

        RoundBtn linkBtn = new RoundBtn(linkButtonContent, Colors.neutralBlue_0_75, Colors.primaryBlue, 26, 140, 26);
        linkBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UserDBActions userDBActions = new UserDBActions();
                String[] linkableProjects = userDBActions.getLinkableProjectNames(ticketID, loggedUser);
                int[] linkableProjectIDs = userDBActions.getLinkableProjectIDs(ticketID, loggedUser);

                if (linkableProjects.length == 0) {
                    Messages.customFailedMessage("find linkable projects");
                }
                else {
                    new LinkTicket(parent.getProjectID(), ticketID, linkableProjects, linkableProjectIDs).setVisible(true);
                    parent.dispose();
                }
            }
        });

        taskCardTopRight.add(linkBtn);
        taskCardTopLeft.add(taskCardStatusIcon);
        taskCardTopLeft.add(taskCardStatus);

        taskCardTop.add(taskCardTopLeft, BorderLayout.WEST);
        taskCardTop.add(taskCardTopRight, BorderLayout.EAST);

        JLabel task = BoldText(ticketTask, 20, Colors.darkBlack);
        task.setPreferredSize(new Dimension(410, 28));

        JLabel taskDescription = RegularText(ticketDescription, 16, Colors.darkBlack);
        taskDescription.setPreferredSize(new Dimension(410, 18));

        JPanel taskDet = new JPanel();
        taskDet.setPreferredSize(new Dimension(410, 46));
        taskDet.setBackground(Colors.secondaryBlue);
        taskDet.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        taskDet.add(task);
        taskDet.add(taskDescription);

        JPanel taskActions = new JPanel();
        taskActions.setPreferredSize(new Dimension(410, 18));
        taskActions.setBackground(Colors.secondaryBlue);
        taskActions.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        JLabel taskActionLabel = RegularText("Update status to: ", 12, Colors.neutralBlack);
        taskActions.add(taskActionLabel);

        JPanel taskActionBtns = new JPanel();
        taskActionBtns.setPreferredSize(new Dimension(305, 16));
        taskActionBtns.setBackground(Colors.secondaryBlue);
        taskActionBtns.setLayout(new FlowLayout(FlowLayout.LEFT, 8, 0));

        JPanel toDoBtn = createFlatButton("ToDo", Colors.primaryBlue_0_5, Colors.neutralBlue, 10);
        toDoBtn.setPreferredSize(new Dimension(72, 16));
        toDoBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                UserDBActions userDBActions = new UserDBActions();
                if(userDBActions.updateTicketStatus(ticketID, TicketStatus.TO_DO)){
                    Messages.customSuccessMessage("Ticket status updated to ToDo");
                    new ProjectScreen(parent.getProjectID()).setVisible(true);
                    parent.dispose();
                }
                else{
                    Messages.customFailedMessage("update ticket status");
                }
            }
        });
        JPanel ongoingBtn = createFlatButton("Ongoing", Colors.primaryBlue_0_5, Colors.neutralBlue, 10);
        ongoingBtn.setPreferredSize(new Dimension(72, 16));
        ongoingBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                UserDBActions userDBActions = new UserDBActions();
                if(userDBActions.updateTicketStatus(ticketID, TicketStatus.ONGOING)){
                    Messages.customSuccessMessage("Ticket status updated to Ongoing");
                    new ProjectScreen(parent.getProjectID()).setVisible(true);
                    parent.dispose();
                }
                else{
                    Messages.customFailedMessage("update ticket status");
                }
            }
        });
        JPanel doneBtn = createFlatButton("Done", Colors.primaryBlue_0_5, Colors.neutralBlue, 10);
        doneBtn.setPreferredSize(new Dimension(72, 16));
        doneBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                UserDBActions userDBActions = new UserDBActions();
                if(userDBActions.updateTicketStatus(ticketID, TicketStatus.DONE)){
                    Messages.customSuccessMessage("Ticket status updated to Done");
                    new ProjectScreen(parent.getProjectID()).setVisible(true);
                    parent.dispose();
                }
                else{
                    Messages.customFailedMessage("update ticket status");
                }
            }
        });

        taskActionBtns.add(toDoBtn);
        taskActionBtns.add(ongoingBtn);
        taskActionBtns.add(doneBtn);

        taskActions.add(taskActionBtns);

        taskCardContent.add(taskCardTop);
        taskCardContent.add(taskDet);
        taskCardContent.add(taskActions);
        taskCard.add(taskCardContent);
    }

    private void loadTask(){
        UserDBActions userDBActions = new UserDBActions();
        this.ticketTask = userDBActions.getTicketTask(ticketID);
    }

    private void loadStatus(){
        UserDBActions userDBActions = new UserDBActions();
        this.ticketStatus = userDBActions.getTicketStatus(ticketID);
    }

    private void loadDescription(){
        UserDBActions userDBActions = new UserDBActions();
        this.ticketDescription = userDBActions.getTicketDescription(ticketID);
    }

    public JPanel getElement(){
        return taskCard;
    }
}
