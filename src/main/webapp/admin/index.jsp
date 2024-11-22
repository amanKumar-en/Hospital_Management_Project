<%@page import="com.Entity.Specialist"%>
<%@page import="java.util.List"%>
<%@page import="com.Dao.SpecialistDAO"%>
<%@page import="com.Db.DBConnect"%>
<%@page import="com.Dao.DoctorDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Dashboard</title>


<%@include file="../component/allcss.jsp"%>

<style>
.paint-card {
	box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.3);
}
</style>
</head>
<body>

	<%@ include file="navbar.jsp"%>

	<c:if test="${empty adminobj }">
		<c:redirect url="../admin_login.jsp"></c:redirect>
	</c:if>

	<div class="container p-5">
		<p class="text-center fs-3">Admin Dashboard</p>

		<c:if test="${not empty errorMsg}">
			<p class="text-center text-danger">${errorMsg }</p>
			<c:remove var="errorMsg" scope="session" />
		</c:if>

		<c:if test="${not empty successMsg }">
			<p class="text-center text-success" role="alert">${successMsg }</p>
			<c:remove var="successMsg" scope="session" />
		</c:if>
	</div>
	
	<!-- dynamically numbers of all dashboard grids  -->
	<%
	DoctorDAO dao = new DoctorDAO(DBConnect.getConnection());
	%>
	

	<div class="row">
		<div class="col-sm-12 col-md-6 col-lg-3">
			<div class="card paint-card">
				<div class="card-body text-center text-success">
					<i class="fas fa-user-md fa-3x"></i><br>
					<p class="fs-4 text-center">
						Doctor <br> <%=dao.countDoctor() %>
					</p>
				</div>
			</div>
		</div>

		<div class="col-sm-12 col-md-6 col-lg-3">
			<div class="card paint-card">
				<div class="card-body text-center text-success">
					<i class="fas fa-user-circle fa-3x"></i><br>
					<p class="fs-4 text-center">
						User <br> <%=dao.countUSer() %>
					</p>
				</div>
			</div>
		</div>


		<div class="col-sm-12 col-md-6 col-lg-3">
			<div class="card paint-card">
				<div class="card-body text-center text-success">
					<i class="fa-regular fa-calendar-check fa-3x"></i><br>
					<p class="fs-4 text-center">
						Total Appointment <br> <%=dao.countAppointment() %>
					</p>
				</div>
			</div>
		</div>
		<br>


		<div class="col-sm-12 col-md-6 col-lg-3">
			<div class="card paint-card" data-bs-toggle="modal"
				data-bs-target="#exampleModal">
				<div class="card-body text-center text-success">
					<i class="fa-solid fa-user-doctor fa-3x"></i><br>
					<p class="fs-4 text-center">
						Specialist <br> <%=dao.countSpecialist() %>
					</p>
				</div>
			</div>
		</div>
	</div>



	<!-- Specialist Modal -->
	<div class="modal fade" id="exampleModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h1 class="modal-title fs-5" id="exampleModalLabel">Add
						specialist</h1>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
				
					<form action="../addSpecialist" method="post">
						<div class="form-group">
							<label>Enter Specialist Name</label>
							<input type="text" name="specName" class="form-control">
						</div>
						<div class="text-center mt-4">
							<button type="submit" class="btn btn-primary">Add</button>
						</div>
					</form>
					
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">Close</button>
					
				</div>
			</div>
		</div>
	</div>
	
	
	


</body>
</html>