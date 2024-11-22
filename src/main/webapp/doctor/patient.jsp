<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<%@page import="com.Entity.Doctor"%>
<%@page import="com.Dao.DoctorDAO"%>
<%@page import="com.Entity.Appointment"%>
<%@page import="java.util.List"%>
<%@page import="com.Db.DBConnect"%>
<%@page import="com.Dao.AppointmentDAO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Patients</title>
<%@include file="../component/allcss.jsp"%>

<style type="text/css">
.paint-card {
	box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.3);
}
</style>
</head>

<body>
	<%@include file="navbar.jsp"%>

	<!-- if without login someone trying to login then JSTL used -->
	<c:if test="${empty doctorObj }">
		<c:redirect url="../doctor_login.jsp"></c:redirect>
	</c:if>

	<div class="col-md-12">
		<div class="card paint-card">
			<div class="card-body">
				<p class="fs-3 text-center">Patient Details</p>

				<c:if test="${not empty errorMsg}">
					<p class="fs-3 text-center text-danger">${errorMsg}</p>
					<c:remove var="errorMsg" scope="session" />
				</c:if>
				<c:if test="${not empty successMsg}">
					<div class="fs-3 text-center text-success" role="alert">${successMsg}</div>
					<c:remove var="successMsg" scope="session" />
				</c:if>

				<table class="table">
					<thead>
						<tr>
							<th scope="col">Full Name</th>
							<th scope="col">Gender</th>
							<th scope="col">Age</th>
							<th scope="col">Appointment</th>
							<th scope="col">Email</th>
							<th scope="col">Mob No</th>
							<th scope="col">Diseases</th>
							
							<th scope="col">Address</th>
							<th scope="col">Status</th>
							<th scope="col">Remarks</th>
						</tr>
					</thead>
					
					<tbody>
						<%
						Doctor d = (Doctor) session.getAttribute("doctorObj");
						AppointmentDAO dao = new AppointmentDAO(DBConnect.getConnection());
						DoctorDAO dao2 = new DoctorDAO(DBConnect.getConnection());
						List<Appointment> list = dao.getAllAppointmentByDoctorLogin(d.getId());
						for (Appointment ap : list) {
							
						%>
						<tr>
							<th><%=ap.getFullName()%></th>
							<td><%=ap.getGender()%></td>
							<td><%=ap.getAge()%></td>
							<td><%=ap.getAppointmentDate()%></td>
							<td><%=ap.getEmail()%></td>
							<td><%=ap.getPhNo()%></td>
							<td><%=ap.getDiseases()%></td>
							
							<td><%=ap.getAddress()%></td>
							<td><%=ap.getStatus()%></td>
							
							<td>
								<!-- to disables the comment button --> 
								<%
									if("pending".equals(ap.getStatus()))
										{%> <a href="comment.jsp?id=<%=ap.getId()%>"
								class="btn btn-success btn-sm"> <i
									class="fa-regular fa-comment"></i> Comment
							</a> 
								<% }
									
									else { %> <a href="#" class="btn btn-success btn-sm disabled"> <i
									class="fa-regular fa-comment"></i> Comment
							</a>
								 <%
								 }
							%>


							</td>
						</tr>
						<%
							}
						%>

					</tbody>
				</table>

			</div>
		</div>
	</div>



</body>
</html>