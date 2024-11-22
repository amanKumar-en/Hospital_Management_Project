package com.user.servlet;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordUtil {
	
	   public static String hashPassword(String password) {
	        try {
	            // Create a MessageDigest instance for SHA-256
	            MessageDigest md = MessageDigest.getInstance("SHA-256");
	            
	            // Convert the password to bytes and update the digest
	            byte[] hashedBytes = md.digest(password.getBytes());
	            
	            // Convert the byte array into a hex string
	            StringBuilder hexString = new StringBuilder();
	            for (byte b : hashedBytes) {
	                String hex = Integer.toHexString(0xff & b);
	                if (hex.length() == 1) hexString.append('0');
	                hexString.append(hex);
	            }
	            
	            return hexString.toString();
	        } catch (NoSuchAlgorithmException e) {
	            throw new RuntimeException(e);
	        }
	    }
}
