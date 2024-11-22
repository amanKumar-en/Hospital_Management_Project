<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession" %>
<%
	
    // Get the session
    HttpSession session2 = request.getSession();

    // Remove the "adminobj" attribute to log out the admin
    session.removeAttribute("adminobj");

    // Set a success message to show after logout
    session.setAttribute("successMsg", "Admin Logout Successfully!!");

    // Redirect to the login page
   
    response.sendRedirect("../admin_login.jsp");
%>
