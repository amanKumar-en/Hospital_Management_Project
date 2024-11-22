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
import org.mindrot.jbcrypt.BCrypt;

@WebServlet("/doctorPasswordChange")
public class DoctorPasswordChange extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int uid = Integer.parseInt(req.getParameter("uid"));
        String oldPassword = req.getParameter("oldPassword");
        String newPassword = req.getParameter("newPassword");

        DoctorDAO dao = new DoctorDAO(DBConnect.getConnection());
        HttpSession session = req.getSession();

        // Retrieve the stored hashed password for the user
        String storedHashedPassword = dao.getPasswordByUserId(uid);

        if (storedHashedPassword != null && BCrypt.checkpw(oldPassword, storedHashedPassword)) {
            // Hash the new password before storing it
            String newHashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt(12));
            if (dao.changePassword(uid, newHashedPassword)) {
                session.setAttribute("successMsg", "Password Changed Successfully!!");
                resp.sendRedirect("doctor/edit_profile.jsp");
            } else {
                session.setAttribute("errorMsg", "Something went wrong on SERVER");
                resp.sendRedirect("doctor/edit_profile.jsp");
            }
        } else {
            session.setAttribute("errorMsg", "Incorrect Old Password");
            resp.sendRedirect("doctor/edit_profile.jsp");
        }
    }
}

