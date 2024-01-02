package Helpers;

import Enums.TicketStatus;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import static Util.PassEncryptor.hashPassword;

public class QueryBuilder {
    public static String loginQuery(String userID, String userPass){
        String hashedPassword = hashPassword(userPass);
        return "SELECT * FROM users WHERE userID='"+userID+"' AND userPassword='"+hashedPassword+"';";
    }
    public static String checkAvailabilityQuery(String userID){
        return "SELECT * FROM users WHERE userID='"+userID+"';";
    }
    public static String registerQuery(String userID, String userPass, String userFirstName, String userLastName){
        String hashedPassword = hashPassword(userPass);
        return "INSERT INTO users (userID, userPassword, userFirstName, userLastName) VALUES ('"+userID+"', '"+hashedPassword+"', '"+userFirstName+"', '"+userLastName+"');";
    }
    public static String getProjectsQuery(String userID){
        return "SELECT * FROM project_users WHERE userID='"+userID+"';";
    }
    public static String countProjectsQuery(String userID){
        return "SELECT COUNT(*) FROM project_users WHERE userID='"+userID+"';";
    }
    public static String getProjectDetailsQuery(int projectID){
        return "SELECT * FROM projects WHERE projectID="+projectID+";";
    }
    public static String countProjectSharedQuery(int projectID){
        return "SELECT COUNT(*) FROM project_users WHERE projectID="+projectID+";";
    }
    public static String getTicketDetailsQuery(int ticketID){
        return "SELECT * FROM tickets WHERE ticketID="+ticketID+";";
    }
    public static String getUserDetailsQuery(String userID){
        return "SELECT * FROM users WHERE userID='"+userID+"';";
    }
    public static String insertProjectQuery(String projectName, String projectOwner, boolean projectSecurity, String projectPassword){
        String hashedPassword = hashPassword(projectPassword);
        return "INSERT INTO projects(projectName, projectOwner, projectSecurity, projectPassword) VALUES ('"+projectName+"', '"+projectOwner+"', "+projectSecurity+", '"+hashedPassword+"');";
    }
    public static String setProjectAccessQuery(int projectID, String userID){
        return "INSERT INTO project_users(projectID, userID) VALUES ("+projectID+", '"+userID+"');";
    }
    public static String deleteUserQuery(String userID){
        return "DELETE FROM users WHERE userID='"+userID+"';";
    }
    public static String updateUserNamesQuery(String userID, String userFirstName, String userLastName){
        return "UPDATE users SET userFirstName='"+userFirstName+"', userLastName='"+userLastName+"' WHERE userID='"+userID+"';";
    }
    public static String updateUserDetailsQuery(String userID, String userFirstName, String userLastName, String userPassword){
        String hashedPassword = hashPassword(userPassword);
        return "UPDATE users SET userFirstName='"+userFirstName+"', userLastName='"+userLastName+"', userPassword='"+hashedPassword+"' WHERE userID='"+userID+"';";
    }
    public static String getTicketsQuery(int projectID){
        return "SELECT * FROM project_tickets WHERE projectID="+projectID+";";
    }
    public static String addTicketQuery(String ticketTask, String ticketDescription){
        return "INSERT INTO tickets(ticketTask, ticketStatus, ticketDescription) VALUES ('"+ticketTask+"', 'ToDo', '"+ticketDescription+"');";
    }
    public static String linkTicketToProjectQuery(int projectID, int ticketID){
        return "INSERT INTO project_tickets(projectID, ticketID) VALUES ("+projectID+", "+ticketID+");";
    }
    public static String updateTicketStatusQuery(int ticketID, String ticketStatus){
        return "UPDATE tickets SET ticketStatus='"+ticketStatus+"' WHERE ticketID="+ticketID+";";
    }
    public static String getLinkableProjectsQuery(int ticketID, String userID){
        return "SELECT * FROM projects WHERE projectOwner = '"+userID+"' AND NOT EXISTS (SELECT 1 FROM project_tickets WHERE projects.projectID = project_tickets.projectID AND project_tickets.ticketID = "+ticketID+");";
    }
    public static String unlockProjectQuery(int projectID, String projectPassword){
        String hashedPassword = hashPassword(projectPassword);
        return "SELECT * FROM projects WHERE projectID="+projectID+" AND projectPassword='"+hashedPassword+"';";
    }
    public static String countProjectAccessQuery(int projectID){
        return "SELECT COUNT(*) FROM project_users WHERE projectID="+projectID+";";
    }
    public static String countTicketsQuery(int projectID){
        return "SELECT COUNT(*) FROM project_tickets WHERE projectID="+projectID+";";
    }
    public static String countTicketsByStatusQuery(int projectID, TicketStatus ticketStatus){
        return "select count(distinct(project_tickets.ticketID)) from project_tickets left join tickets on project_tickets.ticketID=tickets.ticketID WHERE ticketStatus='"+ticketStatus.getValue()+"' and projectID="+projectID+";";
    }
    public static String setReportValueQuery(String stat, String value){
        return "UPDATE report SET value='"+value+"' WHERE stat='"+stat+"';";
    }
    public static String searchProjectsQuery(String userID, String search){
        return "SELECT * FROM project_users pu WHERE userID = '"+userID+"' AND EXISTS (SELECT 1 FROM projects pr WHERE pr.projectID = pu.projectID AND pr.projectName LIKE '%"+search+"%');";
    }
    public static String searchTicketsQuery(int projectID, String search){
        return "SELECT * FROM project_tickets pt WHERE projectID = "+projectID+" AND EXISTS (SELECT 1 FROM tickets t WHERE t.ticketID = pt.ticketID AND t.ticketTask LIKE '%"+search+"%');";
    }
    public static String filterTicketsQuery(int projectID, TicketStatus ticketStatus){
        return "SELECT * FROM project_tickets pt WHERE projectID = "+projectID+" AND EXISTS (SELECT 1 FROM tickets t WHERE t.ticketID = pt.ticketID AND t.ticketStatus = '"+ticketStatus.getValue()+"');";
    }
}
