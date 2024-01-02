package Helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import Assets.Messages;

public class DBConnect {
    private Connection connection;
    private String server;
    private String database;
    private String uid;
    private String password;

    public DBConnect(){
        initialize();
    }
    private void initialize(){
        server = "localhost";
        database = "virtual_scrum_board";
        uid = "root";
        password = "";
        String connetionString = "jdbc:mysql://"+server+"/"+database+"?user="+uid+"&password="+password;

        try{
            connection = DriverManager.getConnection(connetionString);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    // Open Connection
    public Connection openConnection(){
        try{
            if(connection != null && !connection.isClosed()){
                return connection;
            }
            else{
                try {
                    connection = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database, uid, password);
                }
                catch(SQLException e){
                    e.printStackTrace();
                    return null;
                }
                return connection;
            }
        }
        catch(SQLException ex){
            Messages.customFailedMessage("Connect To The Server");
            return null;
        }
    }

    // Close Connection
    public boolean closeConnection(){
        try{
            if(connection != null && !connection.isClosed()){
                connection.close();
            }
            return true;
        }
        catch(SQLException e){
            Messages.customFailedMessage("Close Connection");
            return false;
        }
    }

    // Select Statement
    public ResultSet select(String query){
        try{
            openConnection();
            if(connection!=null){
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                return preparedStatement.executeQuery();
            }
            else{
                return null;
            }
        }
        catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    // Insert Statement
    public int insert(String query){
        try{
            openConnection();
            if(connection!=null){
                PreparedStatement preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
                int res = preparedStatement.executeUpdate();
                closeConnection();
                return res;
            }
            else{
                return -404;
            }
        }
        catch(SQLException e){
            e.printStackTrace();
            return -1;
        }
    }
    public int insertAndGetGeneratedKey(String query) {
        try {
            openConnection();
            if (connection != null) {
                PreparedStatement preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
                int rowsInserted = preparedStatement.executeUpdate();

                if (rowsInserted > 0) {
                    ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        int generatedKey = generatedKeys.getInt(1);
                        closeConnection();
                        return generatedKey;
                    }
                }

                closeConnection();
                return -1; // No rows were inserted or no generated key was retrieved
            } else {
                return -404; // Connection not established
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1; // Exception occurred during database operation
        }
    }


    // Update Statement
    public boolean update(String query){
        boolean rowsUpdated = false;
        try {
            openConnection();
            if(connection!=null){
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    rowsUpdated = true;
                }
                closeConnection();
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return rowsUpdated;
    }

    // Delete Statement
    public boolean delete(String query){
        boolean rowDeleted = false;
        try{
            openConnection();
            if(connection!=null){
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    rowDeleted = true;
                }
                closeConnection();
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return rowDeleted;
    }


    public int count(String query) {
        int count = -1;
        try {
            openConnection();
            if(connection!=null){
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    count = resultSet.getInt(1);
                }
                closeConnection();
                return count;
            } else {
                return count;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return count;
        }
    }
}
