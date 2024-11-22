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


@WebServlet("/userChangePassword")
public class changePassword extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int uid = Integer.parseInt(req.getParameter("uid"));
		String oldPassword = req.getParameter("oldPassword");
		String newPassword = req.getParameter("newPassword");
		
		UserDAO dao = new UserDAO(DBConnect.getConnection());
		
		HttpSession session = req.getSession();
		
		if(dao.checkOldPassword(uid, oldPassword)) {
			if(dao.changePassword(uid, newPassword)) {
				session.setAttribute("successMsg", "Password Changed Successfully!!");
				resp.sendRedirect("change_Password.jsp");
			}else {
				session.setAttribute("errorMsg", "Something went wrong on SERVER");
				resp.sendRedirect("change_Password.jsp");
			}
		} else {
			session.setAttribute("errorMsg", "Incorrect Old Password");
			resp.sendRedirect("change_Password.jsp");
		}
		
	}

	
}
