package com.admin.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Dao.SpecialistDAO;
import com.Db.DBConnect;


@WebServlet("/addSpecialist")
public class AddSpecialist extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String specName = req.getParameter("specName");
		
		SpecialistDAO dao = new SpecialistDAO(DBConnect.getConnection());
		boolean f = dao.addSpecialist(specName);
		
		HttpSession session = req.getSession();
		
		
		if(f) {
			session.setAttribute("successMsg", "Specialist Doctor Added!!");
			resp.sendRedirect("admin/index.jsp");
		} else {
			session.setAttribute("errorMsg", "Something Wrong on Server, Please check!!");
			resp.sendRedirect("admin/index.jsp");
		}
		
	}

	
}
