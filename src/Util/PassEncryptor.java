package Util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PassEncryptor {
    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());

            // Convert hashedBytes to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            // Handle the exception appropriately, e.g., log it or throw a custom exception
            throw new RuntimeException("Error hashing password");
        }
    }
}
