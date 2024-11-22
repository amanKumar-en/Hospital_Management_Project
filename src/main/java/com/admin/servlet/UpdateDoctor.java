package com.admin.servlet;

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



@WebServlet("/updateDoctor")
public class UpdateDoctor extends HttpServlet{

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
			String password = req.getParameter("password");

			// from edit_doctor.jsp, we get the doctor ID to update the details
			int id = Integer.parseInt(req.getParameter("id"));
			
			Doctor d = new Doctor(id, fullName, dob, qualification, spec, email, mobno, password);

			//to store in db
			DoctorDAO dao = new DoctorDAO(DBConnect.getConnection());
			
			HttpSession session = req.getSession();

			if (dao.updateDoctor(d)) {
				session.setAttribute("successMsg", "Doctor Updated Sucessfully..");
				resp.sendRedirect("admin/view_doctor.jsp");
			} else {
				session.setAttribute("errorMsg", "something wrong on server");
				resp.sendRedirect("admin/view_doctor.jsp");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
}
