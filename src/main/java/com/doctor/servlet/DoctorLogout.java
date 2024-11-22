package com.doctor.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/doctorLogout")
public class DoctorLogout extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		 // Get the session
	    HttpSession session = req.getSession();

	    // Remove the "adminobj" attribute to log out the admin
	    session.removeAttribute("doctorObj");

	    // Set a success message to show after logout
	    session.setAttribute("successMsg", "Doctor Logout Successfully!!");

	    // Redirect to the login page
	   
	    resp.sendRedirect("doctor_login.jsp");
	}

	
}
