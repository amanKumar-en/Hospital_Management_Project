package com.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.mindrot.jbcrypt.BCrypt;

import com.Entity.User;

public class UserDAO {
	
	private Connection connection;

	public UserDAO(Connection connection) {
		super();
		this.connection = connection;
	}
	
	public boolean register(User user) {
		
		boolean f = false;
		
		try {
			String insert = "insert into user_details(name,email,password) values(?,?,?)";
			PreparedStatement ps = connection.prepareStatement(insert);
			ps.setString(1, user.getName());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getPassword());
			
			int affected = ps.executeUpdate();
			
			if(affected == 1) {
				f = true;
			}
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}	
		return f;
	}
	
	
//	public User login(String em, String pw) {
//		User u = null;
//		
//		try {
//			String query = "select * from user_details where email=? and password=?";
//			PreparedStatement ps = connection.prepareStatement(query);
//			ps.setString(1, em);
//			ps.setString(2, pw);
//			
//			ResultSet rs = ps.executeQuery();
//			
//			while(rs.next()) {
//				u = new User();
//				u.setId(rs.getInt(1));
//				u.setName(rs.getString(2));
//				u.setEmail(rs.getString(3));
//				u.setPassword(rs.getString(4));
//			}
//			
//		}catch(Exception e) {
//			System.out.println(e.getMessage());
//		}
//		return u;
//	}
	
	public User login(String em, String pw) {
	    User u = null;
	    
	    try {
	        String query = "SELECT * FROM user_details WHERE email=?";
	        PreparedStatement ps = connection.prepareStatement(query);
	        ps.setString(1, em);
	        
	        ResultSet rs = ps.executeQuery();
	        
	        if (rs.next()) {
	            String storedHashedPassword = rs.getString("password");

	            // Check if the entered password matches the stored hashed password using BCrypt
	            if (BCrypt.checkpw(pw, storedHashedPassword)) {
	                u = new User();
	                u.setId(rs.getInt("id"));
	                u.setName(rs.getString("name"));
	                u.setEmail(rs.getString("email"));
	                u.setPassword("password"); // You can choose whether to set the password here
	            }
	        }
	        
	    } catch (Exception e) {
	        System.out.println(e.getMessage());
	    }
	    return u;
	}

	
//	public boolean checkOldPassword(int userid, String oldPassword) {
//		boolean f = false;
//		
//		try {
//			String sql = "select * from user_details where id=? and password=?";
//			PreparedStatement ps = connection.prepareStatement(sql);
//			ps.setInt(1, userid);
//			ps.setString(2, oldPassword);
//			
//			ResultSet rs = ps.executeQuery();
//			
//			while(rs.next()) {
//				f = true;
//			}
//			
//		}catch(Exception e) {
//			System.out.println(e.getMessage());
//		}
//		
//		return f;
//	}
	
	public boolean checkOldPassword(int userid, String oldPassword) {
	    boolean f = false;
	    
	    try {
	        // Retrieve the hashed password using the user ID
	        String sql = "SELECT password FROM user_details WHERE id=?";
	        PreparedStatement ps = connection.prepareStatement(sql);
	        ps.setInt(1, userid);
	        
	        ResultSet rs = ps.executeQuery();
	        
	        if (rs.next()) {
	            String storedHashedPassword = rs.getString("password");
	            
	            // Verify the old password against the stored hashed password
	            if (BCrypt.checkpw(oldPassword, storedHashedPassword)) {
	                f = true; // Password matches
	            }
	        }
	        
	    } catch (Exception e) {
	        System.out.println(e.getMessage());
	    }
	    
	    return f;
	}

	
	
//	public boolean changePassword(int userid, String newPassword) {
//		boolean f = false;
//		
//		try {
//			String sql = "update user_details set password=? where id=?";
//			PreparedStatement ps = connection.prepareStatement(sql);
//			ps.setString(1, newPassword);
//			ps.setInt(2, userid);
//			
//			int i = ps.executeUpdate();
//			
//			if(i == 1) {
//				f = true;
//			}
//			
//		}catch(Exception e) {
//			System.out.println(e.getMessage());
//		}
//		
//		return f;
//	}
	
	
	public boolean changePassword(int userid, String newPassword) {
	    boolean f = false;
	    
	    try {
	        // Hash the new password using BCrypt
	        String hashedNewPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt(12));
	        
	        // Update the database with the hashed password
	        String sql = "UPDATE user_details SET password=? WHERE id=?";
	        PreparedStatement ps = connection.prepareStatement(sql);
	        ps.setString(1, hashedNewPassword);
	        ps.setInt(2, userid);
	        
	        int i = ps.executeUpdate();
	        
	        if (i == 1) {
	            f = true; // Password updated successfully
	        }
	        
	    } catch (Exception e) {
	        System.out.println(e.getMessage());
	    }
	    
	    return f;
	}


}
