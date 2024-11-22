package com.admin.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import com.Dao.DoctorDAO;
import com.Db.DBConnect;
import com.Entity.Doctor;

@WebServlet("/addDoctor")
public class AddDoctor extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			// doctor.jsp
			// getting user input from fields
			String fullName = req.getParameter("fullname");
			String dob = req.getParameter("dob");
			String qualification = req.getParameter("qualification");

			String spec = req.getParameter("spec");

			String email = req.getParameter("email");
			String mobno = req.getParameter("mobno");
			String password = req.getParameter("password");
			
			String hashedPassword = hashPassword(password);

			
			Doctor d = new Doctor(fullName, dob, qualification, spec, email, mobno, hashedPassword);
			

			//to store in db
			DoctorDAO dao = new DoctorDAO(DBConnect.getConnection());
			
			

			
			HttpSession session = req.getSession();

			if (dao.registerDoctor(d)) {
				session.setAttribute("successMsg", "Doctor Added Sucessfully..");
				resp.sendRedirect("admin/doctor.jsp");
			} else {
				session.setAttribute("errorMsg", "something wrong on server");
				resp.sendRedirect("admin/doctor.jsp");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	 private String hashPassword(String password) {
	        // Generate a salted hash using BCrypt
	        return BCrypt.hashpw(password, BCrypt.gensalt(12)); // Cost factor of 12
	    }

}
