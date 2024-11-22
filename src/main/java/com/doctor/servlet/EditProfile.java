package com.doctor.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Dao.DoctorDAO;
import com.Db.DBConnect;
import com.Entity.Doctor;


@WebServlet("/doctorUpdateProfile")
public class EditProfile extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// copy AddDoctor code
		try {
			// doctor.jsp
			// getting user input from fields
			String fullName = req.getParameter("fullname");
			String dob = req.getParameter("dob");
			String qualification = req.getParameter("qualification");
			String spec = req.getParameter("spec");
			String email = req.getParameter("email");
			String mobno = req.getParameter("mobno");
			

			// from edit_doctor.jsp, we get the doctor ID to update the details
			int id = Integer.parseInt(req.getParameter("id"));
			
			Doctor d = new Doctor(id, fullName, dob, qualification, spec, email, mobno, "");

			//to store in db
			DoctorDAO dao = new DoctorDAO(DBConnect.getConnection());
			
			HttpSession session = req.getSession();

			if (dao.editDoctorProfile(d)) {
				// overWrite the session to show the changes in the edit doctor profile page dynamically when doctor changes his profile
				Doctor updateDoctor = dao.getDoctorById(id);
				
				session.setAttribute("successMsgd", "Doctor Updated Sucessfully..");
				session.setAttribute("doctorObj", updateDoctor);
				resp.sendRedirect("doctor/edit_profile.jsp");
			} else {
				session.setAttribute("errorMsgd", "something wrong on server");
				resp.sendRedirect("doctor/edit_profile.jsp");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
