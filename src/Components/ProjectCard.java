package Components;

import Actions.UserDBActions;
import Assets.Colors;
import Assets.Messages;
import Components.Atoms.FAIcon;
import Components.Atoms.RoundBtn;
import Screens.Dialogs.UnlockProject;
import Screens.MainScreen;
import Screens.ProjectScreen;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static Components.Atoms.FAIcon.FAicon;
import static Components.Atoms.ThemedText.BoldText;

public class ProjectCard {
    private int projectID;
    private boolean isProjectSecured;
    private String projectTitle;
    private boolean isProjectShared;
    private RoundBtn projectCard;

    public ProjectCard(MainScreen parent, int projectID) {
        this.projectID = projectID;
        loadDetails();
        loadSharedStatus();
        JPanel projectCardContent = new JPanel();
        projectCardContent.setPreferredSize(new Dimension(118, 125));
        projectCardContent.setBackground(Colors.transparent);
        projectCardContent.setLayout(new BorderLayout(0, 0));


        JPanel securityContainer = new JPanel();
        securityContainer.setPreferredSize(new Dimension(118, 25));
        securityContainer.setBackground(Colors.transparent);
        securityContainer.setLayout(new FlowLayout(FlowLayout.TRAILING, 0, 0));
        JLabel securityIcon = FAicon("\uf023", 24, (isProjectSecured?Colors.primaryBlack_0_68:Colors.transparent));
        securityContainer.add(securityIcon);

        JPanel projectTitleContainer = new JPanel();
        projectTitleContainer.setPreferredSize(new Dimension(118, 18));
        projectTitleContainer.setBackground(Colors.transparent);
        projectTitleContainer.setLayout(new BorderLayout(0, 0));
        JLabel projectTitleLabel = BoldText(projectTitle, 18, Colors.darkBlack);
        projectTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        projectTitleContainer.add(projectTitleLabel, BorderLayout.CENTER);

        JPanel sharedContainer = new JPanel();
        sharedContainer.setPreferredSize(new Dimension(118, 43));
        sharedContainer.setBackground(Colors.transparent);
        sharedContainer.setLayout(new BorderLayout(0, 0));
        JLabel sharedIcon = FAicon(isProjectShared?"\uf0c0":"\uf007", 43, Colors.darkBlack);
        sharedIcon.setHorizontalAlignment(SwingConstants.CENTER);
        sharedIcon.setBorder(BorderFactory.createMatteBorder(0, 0, 15, 0, Colors.transparent));
        sharedContainer.add(sharedIcon, BorderLayout.CENTER);


        projectCardContent.add(securityContainer, BorderLayout.NORTH);
        projectCardContent.add(sharedContainer, BorderLayout.CENTER);
        projectCardContent.add(projectTitleContainer, BorderLayout.SOUTH);


        projectCard = new RoundBtn(projectCardContent, Colors.secondaryBlue, Colors.neutralBlue, 10, 136, 152);
        projectCard.addActionListener(e -> {
            if(isProjectSecured){
                new UnlockProject(projectID).setVisible(true);
                parent.dispose();
            }
            else{
                new ProjectScreen(projectID).setVisible(true);
                parent.dispose();
            }
        });
    }
    private void loadDetails(){
        UserDBActions userDBActions = new UserDBActions();
        List<Map<Boolean, String>> projectDetails = userDBActions.getProjectDetails(projectID);
        if(projectDetails != null && !projectDetails.isEmpty()){
            for (Map<Boolean, String> dataMap : projectDetails) {
                // Iterate through the map entries
                for (Map.Entry<Boolean, String> entry : dataMap.entrySet()) {
                    isProjectSecured = entry.getKey();
                    projectTitle = entry.getValue();
                }
            }
        }
        else{
            Messages.customFailedMessage("Load Project Details");
        }
//        ResultSet projectDetails = userDBActions.getProjectDetails(projectID);
//        if(projectDetails==null){
//            Messages.customFailedMessage("load project details");
//        }
//        else{
//            try {
//                while (projectDetails.next()) {
//                    isProjectSecured = projectDetails.getBoolean("projectSecurity");
//                    projectTitle = projectDetails.getString("projectName");
//                    JOptionPane.showMessageDialog(null, projectTitle);
//                    JOptionPane.showMessageDialog(null, isProjectSecured);
//                }
//            } catch (Exception e) {
//                Messages.customFailedMessage("load project details"+e.getMessage());
//            }
//        }
    }
    private void loadSharedStatus(){
        UserDBActions userDBActions = new UserDBActions();
        int sharedCount = userDBActions.getProjectSharedCount(projectID);
        if(sharedCount>=1){
            isProjectShared = sharedCount>1;
        }
        else{
            Messages.customFailedMessage("Load Project Shared Count");
        }
    }
    public RoundBtn getElement(){
        return projectCard;
    }
}
