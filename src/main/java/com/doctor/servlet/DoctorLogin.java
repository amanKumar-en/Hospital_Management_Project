package com.doctor.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Dao.DoctorDAO;
import com.Dao.UserDAO;
import com.Db.DBConnect;
import com.Entity.Doctor;
import com.Entity.User;

@WebServlet("/doctorLogin")
public class DoctorLogin extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			
			HttpSession session = req.getSession();
			
			DoctorDAO dao = new DoctorDAO(DBConnect.getConnection());
			
			Doctor doctor = dao.login(email, password);
			
			
			if(doctor != null) {
				session.setAttribute("doctorObj", doctor);
				resp.sendRedirect("doctor/index.jsp");
			} else {
				session.setAttribute("errorMsg", "@Invalid Credentials");
				resp.sendRedirect("doctor_login.jsp");
			}
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	
}
