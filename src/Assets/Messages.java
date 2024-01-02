package Assets;

import javax.swing.*;

public class Messages {
    public static void emptyFields(){
        JOptionPane.showMessageDialog(null, "Empty Field(s) Detected", "Error", JOptionPane.ERROR_MESSAGE);
    }
    public static void passMissmatch(){
        JOptionPane.showMessageDialog(null, "Password Confirmation Failed", "Error", JOptionPane.ERROR_MESSAGE);
    }
    public static void invalidCredentials(){
        JOptionPane.showMessageDialog(null, "Invalid Credentials Entered", "Error", JOptionPane.ERROR_MESSAGE);
    }
    public static void databaseError(){
        JOptionPane.showMessageDialog(null, "Database Error Occurred", "Error", JOptionPane.ERROR_MESSAGE);
    }
    public static void somethingWrong(){
        JOptionPane.showMessageDialog(null, "Something Went Wrong", "Error", JOptionPane.ERROR_MESSAGE);
    }
    public static void customSuccessMessage(String task){
        JOptionPane.showMessageDialog(null, task+" Successfully");
    }
    public static void customFailedMessage(String task){
        JOptionPane.showMessageDialog(null, "Failed To "+task, "Error", JOptionPane.ERROR_MESSAGE);
    }
    public static void fullyCustomError(String message){
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
    public static void fullyCustomMessage(String message){
        JOptionPane.showMessageDialog(null, message);
    }
}
