package Actions;

import Enums.AuthType;
import Enums.AvailabilityType;
import Helpers.*;

import java.sql.*;

public class Authentications{
    private DBConnect dbConnect;
    public Authentications(){
        dbConnect = new DBConnect();
    }

    public AuthType login(String id, String pass){
        Connection connection = dbConnect.openConnection();
        if(connection!=null){
            try{
                String query = QueryBuilder.loginQuery(id, pass);
                ResultSet result = dbConnect.select(query);


                if(result.next()){
                    return AuthType.SUCCESS;
                }
                else{
                    return AuthType.INVALID_CREDENTIALS;
                }
            }
            catch(SQLException e){
                e.printStackTrace();
                return AuthType.DATABASE_ERROR;
            }
            finally{
                dbConnect.closeConnection();
            }
        }
        else{
            return AuthType.DATABASE_ERROR;
        }
    }

    public AvailabilityType checkAvailability(String id){
        Connection connection = dbConnect.openConnection();
        if(connection!=null){
            try{
                String query = QueryBuilder.checkAvailabilityQuery(id);
                ResultSet result = dbConnect.select(query);

                if(result.next()){
                    return AvailabilityType.UNAVAILABLE;
                }
                else{
                    return AvailabilityType.AVAILABLE;
                }
            }
            catch(SQLException e){
                e.printStackTrace();
                return AvailabilityType.DATABASE_ERROR;
            }
            finally{
                dbConnect.closeConnection();
            }
        }
        else{
            return AvailabilityType.DATABASE_ERROR;
        }
    }

    public AuthType register(String id, String pass, String firstName, String lastName){
        Connection connection = dbConnect.openConnection();
        if(connection!=null){
            try{
                String query = QueryBuilder.registerQuery(id, pass, firstName, lastName);
                int result = dbConnect.insert(query);

                if(result>0){
                    return AuthType.SUCCESS;
                }
                else{
                    return AuthType.FAILED;
                }
            }
            catch(Exception e){
                e.printStackTrace();
                return AuthType.DATABASE_ERROR;
            }
            finally{
                dbConnect.closeConnection();
            }
        }
        else{
            return AuthType.DATABASE_ERROR;
        }
    }
}
