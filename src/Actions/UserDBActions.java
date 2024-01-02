package Actions;

import Enums.TicketStatus;
import Helpers.DBConnect;
import Helpers.QueryBuilder;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDBActions {
    private DBConnect dbConnect;
    public UserDBActions(){
        dbConnect = new DBConnect();
    }
    public ArrayList getProjectIDs(String userID){
        Connection connection = dbConnect.openConnection();
        if(connection!=null){
            try{
                String query = QueryBuilder.getProjectsQuery(userID);

                ResultSet rs = dbConnect.select(query);

                ArrayList<Integer> projectIDs = new ArrayList<>();

                while (rs.next()) {
                    projectIDs.add(rs.getInt("projectID"));
                }
                rs.close();
                return projectIDs;
            }
            catch(Exception e){
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, e.getMessage());
                return null;
            }
            finally{
                dbConnect.closeConnection();
            }
        }
        else{
            return null;
        }
    }
    public ArrayList getTicketIDs(int projectID){
        Connection connection = dbConnect.openConnection();
        if(connection!=null){
            try{
                String query = QueryBuilder.getTicketsQuery(projectID);

                ResultSet rs = dbConnect.select(query);

                ArrayList<Integer> ticketIDs = new ArrayList<>();

                while (rs.next()) {
                    ticketIDs.add(rs.getInt("ticketID"));
                }
                rs.close();
                return ticketIDs;
            }
            catch(Exception e){
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, e.getMessage());
                return null;
            }
            finally{
                dbConnect.closeConnection();
            }
        }
        else{
            return null;
        }
    }
    public int getProjectCount(String userID){
        Connection connection = dbConnect.openConnection();
        if(connection!=null){
            try{
                String query = QueryBuilder.countProjectsQuery(userID);
                return dbConnect.count(query);
            }
            catch(Exception e){
                e.printStackTrace();
                return -1;
            }
            finally{
                dbConnect.closeConnection();
            }
        }
        else{
            return -1;
        }
    }
    public List<Map<Boolean, String>> getProjectDetails(int projectID) {
        Connection connection = dbConnect.openConnection();
        List<Map<Boolean, String>> resultList = new ArrayList<>();

        try {
            if (connection != null) {
                String query = QueryBuilder.getProjectDetailsQuery(projectID);
                ResultSet rs = dbConnect.select(query);

                while (rs.next()) {
                    Map<Boolean, String> dataMap = new HashMap<>();
                    dataMap.put(rs.getBoolean("projectSecurity"), rs.getString("projectName"));
                    resultList.add(dataMap);
                }

                return resultList;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            dbConnect.closeConnection();
        }
    }
    public int getProjectSharedCount(int projectID){
        Connection connection = dbConnect.openConnection();
        if(connection!=null){
            try{
                String query = QueryBuilder.countProjectSharedQuery(projectID);
                return dbConnect.count(query);
            }
            catch(Exception e){
                e.printStackTrace();
                return -1;
            }
            finally{
                dbConnect.closeConnection();
            }
        }
        else{
            return -1;
        }
    }
    public String getTicketTask(int ticketID){
        Connection connection = dbConnect.openConnection();
        if(connection!=null){
            try{
                String query = QueryBuilder.getTicketDetailsQuery(ticketID);
                ResultSet rs = dbConnect.select(query);
                String ticketTask = null;
                while (rs.next()) {
                    ticketTask = rs.getString("ticketTask");
                }
                rs.close();
                return ticketTask;
            }
            catch(Exception e){
                e.printStackTrace();
                return null;
            }
            finally{
                dbConnect.closeConnection();
            }
        }
        else{
            return null;
        }
    }
    public TicketStatus getTicketStatus(int ticketID){
        Connection connection = dbConnect.openConnection();
        if(connection!=null){
            try{
                String query = QueryBuilder.getTicketDetailsQuery(ticketID);
                ResultSet rs = dbConnect.select(query);
                String ticketTask = null;
                while (rs.next()) {
                    ticketTask = rs.getString("ticketStatus");
                }
                rs.close();
                if(ticketTask.equals(TicketStatus.DONE.getValue())){
                    return TicketStatus.DONE;
                }
                else if(ticketTask.equals(TicketStatus.ONGOING.getValue())){
                    return TicketStatus.ONGOING;
                }
                else{
                    return TicketStatus.TO_DO;
                }
            }
            catch(Exception e){
                e.printStackTrace();
                return null;
            }
            finally{
                dbConnect.closeConnection();
            }
        }
        else{
            return null;
        }
    }
    public String getTicketDescription(int ticketID){
        Connection connection = dbConnect.openConnection();
        if(connection!=null){
            try{
                String query = QueryBuilder.getTicketDetailsQuery(ticketID);
                ResultSet rs = dbConnect.select(query);
                String ticketDesc = null;
                while (rs.next()) {
                    ticketDesc = rs.getString("ticketDescription");
                }
                rs.close();
                return ticketDesc;
            }
            catch(Exception e){
                e.printStackTrace();
                return null;
            }
            finally{
                dbConnect.closeConnection();
            }
        }
        else{
            return null;
        }
    }
    public String getUserFirstName(String userID){
        Connection connection = dbConnect.openConnection();
        if(connection!=null){
            try{
                String query = QueryBuilder.getUserDetailsQuery(userID);
                ResultSet rs = dbConnect.select(query);
                String userFullName = null;
                while (rs.next()) {
                    userFullName = rs.getString("userFirstName");
                }
                rs.close();
                return userFullName;
            }
            catch(Exception e){
                e.printStackTrace();
                return null;
            }
            finally{
                dbConnect.closeConnection();
            }
        }
        else{
            return null;
        }
    }
    public int insertProject(String projectName, String projectOwner, boolean projectSecurity, String projectPassword){
        Connection connection = dbConnect.openConnection();
        if(connection!=null){
            try{
                String query = QueryBuilder.insertProjectQuery(projectName, projectOwner, projectSecurity, projectPassword);
                return dbConnect.insertAndGetGeneratedKey(query);
            }
            catch(Exception e){
                e.printStackTrace();
                return -1;
            }
            finally{
                dbConnect.closeConnection();
            }
        }
        else{
            return -1;
        }
    }
    public int setProjectAccess(int projectID, String userID){
        Connection connection = dbConnect.openConnection();
        if(connection!=null){
            try{
                String query = QueryBuilder.setProjectAccessQuery(projectID, userID);
                return dbConnect.insert(query);
            }
            catch(Exception e){
                e.printStackTrace();
                return -1;
            }
            finally{
                dbConnect.closeConnection();
            }
        }
        else{
            return -1;
        }
    }
    public boolean deleteUser(String userID){
        Connection connection = dbConnect.openConnection();
        if(connection!=null){
            try{
                String query = QueryBuilder.deleteUserQuery(userID);
                return dbConnect.delete(query);
            }
            catch(Exception e){
                e.printStackTrace();
                return false;
            }
            finally{
                dbConnect.closeConnection();
            }
        }
        return false;
    }
    public String getUserLastName(String userID){
        Connection connection = dbConnect.openConnection();
        if(connection!=null){
            try{
                String query = QueryBuilder.getUserDetailsQuery(userID);
                ResultSet rs = dbConnect.select(query);
                String userFullName = null;
                while (rs.next()) {
                    userFullName = rs.getString("userLastName");
                }
                rs.close();
                return userFullName;
            }
            catch(Exception e){
                e.printStackTrace();
                return null;
            }
            finally{
                dbConnect.closeConnection();
            }
        }
        else{
            return null;
        }
    }
    public boolean updateUserNames(String userID, String userFirstName, String userLastName){
        Connection connection = dbConnect.openConnection();
        if(connection!=null){
            try{
                String query = QueryBuilder.updateUserNamesQuery(userID, userFirstName, userLastName);
                return dbConnect.update(query);
            }
            catch(Exception e){
                e.printStackTrace();
                return false;
            }
            finally{
                dbConnect.closeConnection();
            }
        }
        else{
            return false;
        }
    }
    public boolean updateUserDetails(String userID, String userFirstName, String userLastName, String userPassword){
        Connection connection = dbConnect.openConnection();
        if(connection!=null){
            try{
                String query = QueryBuilder.updateUserDetailsQuery(userID, userFirstName, userLastName, userPassword);
                return dbConnect.update(query);
            }
            catch(Exception e){
                e.printStackTrace();
                return false;
            }
            finally{
                dbConnect.closeConnection();
            }
        }
        else{
            return false;
        }
    }
    public String getProjectName(int projectID){
        Connection connection = dbConnect.openConnection();
        if(connection!=null){
            try{
                String query = QueryBuilder.getProjectDetailsQuery(projectID);
                ResultSet rs = dbConnect.select(query);
                String projectName = null;
                while (rs.next()) {
                    projectName = rs.getString("projectName");
                }
                rs.close();
                return projectName;
            }
            catch(Exception e){
                e.printStackTrace();
                return null;
            }
            finally{
                dbConnect.closeConnection();
            }
        }
        else{
            return null;
        }
    }
    public int addTicket(String ticketTask, String ticketDescription){
        Connection connection = dbConnect.openConnection();
        if(connection!=null){
            try{
                String query = QueryBuilder.addTicketQuery(ticketTask, ticketDescription);
                return dbConnect.insertAndGetGeneratedKey(query);
            }
            catch(Exception e){
                e.printStackTrace();
                return -1;
            }
            finally{
                dbConnect.closeConnection();
            }
        }
        else{
            return -1;
        }
    }
    public int linkTicketToProject(int projectID, int ticketID){
        Connection connection = dbConnect.openConnection();
        if(connection!=null){
            try{
                String query = QueryBuilder.linkTicketToProjectQuery(projectID, ticketID);
                return dbConnect.insert(query);
            }
            catch(Exception e){
                e.printStackTrace();
                return -1;
            }
            finally{
                dbConnect.closeConnection();
            }
        }
        else{
            return -1;
        }
    }
    public boolean updateTicketStatus(int ticketID, TicketStatus ticketStatus){
        Connection connection = dbConnect.openConnection();
        if(connection!=null){
            try{
                String query = QueryBuilder.updateTicketStatusQuery(ticketID, ticketStatus.getValue());
                return dbConnect.update(query);
            }
            catch(Exception e){
                e.printStackTrace();
                return false;
            }
            finally{
                dbConnect.closeConnection();
            }
        }
        else{
            return false;
        }
    }

    public String[] getLinkableProjectNames(int ticketID, String userID){
        Connection connection = dbConnect.openConnection();
        if(connection!=null){
            try{
                String query = QueryBuilder.getLinkableProjectsQuery(ticketID, userID);
                ResultSet rs = dbConnect.select(query);
                ArrayList<String> projectNames = new ArrayList<>();
                while (rs.next()) {
                    String combinedName = rs.getInt("projectID")+" - "+rs.getString("ProjectName");
                    projectNames.add(combinedName);
                }
                rs.close();
                return projectNames.toArray(new String[0]);
            }
            catch(Exception e){
                e.printStackTrace();
                return null;
            }
            finally{
                dbConnect.closeConnection();
            }
        }
        else{
            return null;
        }
    }

    public int[] getLinkableProjectIDs(int ticketID, String userID){
        Connection connection = dbConnect.openConnection();
        if(connection!=null){
            try{
                String query = QueryBuilder.getLinkableProjectsQuery(ticketID, userID);
                ResultSet rs = dbConnect.select(query);
                ArrayList<Integer> projectIDs = new ArrayList<>();
                while (rs.next()) {
                    projectIDs.add(rs.getInt("projectID"));
                }
                rs.close();
                return projectIDs.stream().mapToInt(i -> i).toArray();
            }
            catch(Exception e){
                e.printStackTrace();
                return null;
            }
            finally{
                dbConnect.closeConnection();
            }
        }
        else{
            return null;
        }
    }

    public boolean unlockProject(int projectID, String projectPassword){
        Connection connection = dbConnect.openConnection();
        if(connection!=null){
            try{
                String query = QueryBuilder.unlockProjectQuery(projectID, projectPassword);
                return dbConnect.select(query).next();
            }
            catch(Exception e){
                e.printStackTrace();
                return false;
            }
            finally{
                dbConnect.closeConnection();
            }
        }
        else{
            return false;
        }
    }
    public String getProjectOwnerID(int projectID){
        Connection connection = dbConnect.openConnection();
        if(connection!=null){
            try{
                String query = QueryBuilder.getProjectDetailsQuery(projectID);
                ResultSet rs = dbConnect.select(query);
                String projectOwnerID = null;
                while (rs.next()) {
                    projectOwnerID = rs.getString("projectOwner");
                }
                rs.close();
                return projectOwnerID;
            }
            catch(Exception e){
                e.printStackTrace();
                return null;
            }
            finally{
                dbConnect.closeConnection();
            }
        }
        else{
            return null;
        }
    }
    public boolean getProjectSecurity(int projectID){
        Connection connection = dbConnect.openConnection();
        if(connection!=null){
            try{
                String query = QueryBuilder.getProjectDetailsQuery(projectID);
                ResultSet rs = dbConnect.select(query);
                boolean projectSecurity = false;
                while (rs.next()) {
                    projectSecurity = rs.getBoolean("projectSecurity");
                }
                rs.close();
                return projectSecurity;
            }
            catch(Exception e){
                e.printStackTrace();
                return false;
            }
            finally{
                dbConnect.closeConnection();
            }
        }
        else{
            return false;
        }
    }
    public int getTicketCount(int projectID){
        Connection connection = dbConnect.openConnection();
        if(connection!=null){
            try{
                String query = QueryBuilder.countTicketsQuery(projectID);
                return dbConnect.count(query);
            }
            catch(Exception e){
                e.printStackTrace();
                return -1;
            }
            finally{
                dbConnect.closeConnection();
            }
        }
        else{
            return -1;
        }
    }
    public int getTicketsCountByStatus(int projectID, TicketStatus ticketStatus){
        Connection connection = dbConnect.openConnection();
        if(connection!=null){
            try{
                String query = QueryBuilder.countTicketsByStatusQuery(projectID, ticketStatus);
                return dbConnect.count(query);
            }
            catch(Exception e){
                e.printStackTrace();
                return -1;
            }
            finally{
                dbConnect.closeConnection();
            }
        }
        else{
            return -1;
        }
    }
    public boolean generateReport(int projectID, String projectName, String projectOwnerName, boolean projectSecurity, int accessCount, int ticketCount, int toDoTicketCount, int ongoingTicketCount, int finishedTicketCount, float progress){
        Connection connection = dbConnect.openConnection();
        if(connection!=null){
            try {
                if (dbConnect.update(QueryBuilder.setReportValueQuery("01. Project ID", projectID + ""))) {
                    if (dbConnect.update(QueryBuilder.setReportValueQuery("02. Project Name", projectName))) {
                        if (dbConnect.update(QueryBuilder.setReportValueQuery("03. Project Owner", projectOwnerName))) {
                            if (dbConnect.update(QueryBuilder.setReportValueQuery("05. Project Security", projectSecurity ? "Secured" : "Not Secured"))) {
                                if (dbConnect.update(QueryBuilder.setReportValueQuery("04. Accessed User Count", accessCount + ""))) {
                                    if (dbConnect.update(QueryBuilder.setReportValueQuery("06. Total Tickets Count", ticketCount + ""))) {
                                        if (dbConnect.update(QueryBuilder.setReportValueQuery("07. ToDo Tickets Count", toDoTicketCount + ""))) {
                                            if (dbConnect.update(QueryBuilder.setReportValueQuery("08. Ongoing Tickets Count ", ongoingTicketCount + ""))) {
                                                if (dbConnect.update(QueryBuilder.setReportValueQuery("09. Finished Tickets Count", finishedTicketCount + ""))) {
                                                    if (dbConnect.update(QueryBuilder.setReportValueQuery("10. Finished vs. Pending (%) ", progress + " %"))) {
                                                        return true;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                return false;
            }
            catch(Exception e){
                e.printStackTrace();
                return false;
            }
            finally{
                dbConnect.closeConnection();
            }
        }
        else{
            return false;
        }
    }
    public ArrayList<Integer> searchProjects(String searchQuery, String userID){
        Connection connection = dbConnect.openConnection();
        if(connection!=null){
            try{
                String query = QueryBuilder.searchProjectsQuery(userID, searchQuery);
                ResultSet rs = dbConnect.select(query);
                ArrayList<Integer> projectIDs = new ArrayList<>();
                while (rs.next()) {
                    projectIDs.add(rs.getInt("projectID"));
                }
                rs.close();
                return projectIDs;
            }
            catch(Exception e){
                e.printStackTrace();
                return null;
            }
            finally{
                dbConnect.closeConnection();
            }
        }
        else{
            return null;
        }
    }

    public ArrayList<Integer> searchTickets(String searchQuery, int projectID){
        Connection connection = dbConnect.openConnection();
        if(connection!=null){
            try{
                String query = QueryBuilder.searchTicketsQuery(projectID, searchQuery);
                ResultSet rs = dbConnect.select(query);
                ArrayList<Integer> ticketIDs = new ArrayList<>();
                while (rs.next()) {
                    ticketIDs.add(rs.getInt("ticketID"));
                }
                rs.close();
                return ticketIDs;
            }
            catch(Exception e){
                e.printStackTrace();
                return null;
            }
            finally{
                dbConnect.closeConnection();
            }
        }
        else{
            return null;
        }
    }

    public ArrayList<Integer> filterTickets(int projectID, TicketStatus ticketStatus){
        Connection connection = dbConnect.openConnection();
        if(connection!=null){
            try{
                String query = QueryBuilder.filterTicketsQuery(projectID, ticketStatus);
                ResultSet rs = dbConnect.select(query);
                ArrayList<Integer> ticketIDs = new ArrayList<>();
                while (rs.next()) {
                    ticketIDs.add(rs.getInt("ticketID"));
                }
                rs.close();
                return ticketIDs;
            }
            catch(Exception e){
                e.printStackTrace();
                return null;
            }
            finally{
                dbConnect.closeConnection();
            }
        }
        else{
            return null;
        }
    }
    public Connection getConnection(){
        return dbConnect.openConnection();
    }
}
