package com.user.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Dao.UserDAO;
import com.Db.DBConnect;
import com.Entity.User;

import org.mindrot.jbcrypt.BCrypt;


@WebServlet("/user_register")
public class UserRegister extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			String name = req.getParameter("name");
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			
			String hashedPassword = hashPassword(password);
			
			//setting the value 
			User user = new User(name, email, hashedPassword);
	       
			// operations
			UserDAO dao = new UserDAO(DBConnect.getConnection());
			boolean f = dao.register(user);
			
			HttpSession session = req.getSession();
			
			
			if(f==true) {
				session.setAttribute("successMsg", "Register succesfully!!");
				resp.sendRedirect("user_signup.jsp");
				
			} else {
				session.setAttribute("failedMsg", "Failed!!");
				resp.sendRedirect("user_signup.jsp");
				
			}
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	 private String hashPassword(String password) {
	        // Generate a salted hash using BCrypt
	        return BCrypt.hashpw(password, BCrypt.gensalt(12)); // Cost factor of 12
	    }
	
	
}
