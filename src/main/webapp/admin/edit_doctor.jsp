<%@page import="com.Entity.Doctor"%>
<%@page import="com.Dao.DoctorDAO"%>
<%@page import="com.Entity.Specialist"%>
<%@page import="java.util.List"%>
<%@page import="com.Db.DBConnect"%>
<%@page import="com.Dao.SpecialistDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<%@include file="../component/allcss.jsp"%>
<style type="text/css">
.paint-card {
	box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.3);
}
</style>
</head>
<body>
	<%@include file="navbar.jsp"%>
	
	<div class="container-fluid p-3">
		<div class="row">

			<div class="col-md-4 offset-md-4">
				<div class="card paint-card">
					<div class="card-body">
						<p class="fs-3 text-center">Edit Doctor Details</p>
						
						<c:if test="${not empty errorMsg}">
							<p class="fs-3 text-center text-danger">${errorMsg}</p>
							<c:remove var="errorMsg" scope="session" />
						</c:if>
						<c:if test="${not empty successMsg}">
							<div class="fs-3 text-center text-success" role="alert">${successMsg}</div>
							<c:remove var="successMsg" scope="session" />
						</c:if>
						
						<!-- to show all the doctor details to the edit doctor page, by the help of doctor unique ID -->
						<%
							int id = Integer.parseInt(request.getParameter("id"));
							DoctorDAO dao2 = new DoctorDAO(DBConnect.getConnection());
							Doctor d = dao2.getDoctorById(id);
							
						%>
						<!-- after getting doctor ID edit page, now use script tag in the form field i.e value=d.getFullName() -->
						
						<form action="../updateDoctor" method="post">
							<div class="mb-3">
								<label class="form-label">Full Name</label> <input type="text"
									required name="fullname" class="form-control" value="<%=d.getFullName()%>">
							</div>

							<div class="mb-3">
								<label class="form-label">DOB</label> <input type="date"
									required name="dob" class="form-control" value="<%=d.getDob()%>">
							</div>

							<div class="mb-3">
								<label class="form-label">Qualification</label> <input required
									name="qualification" type="text" class="form-control" value="<%=d.getQualification()%>">
							</div>
							<div class="mb-3">
								<label class="form-label">Specialist</label> <select name="spec"
									required class="form-control" value="<%=d.getSpecialist()%>">
									<option><%=d.getSpecialist() %></option>
									

									<%
									SpecialistDAO dao = new SpecialistDAO(DBConnect.getConnection());
									List<Specialist> list = dao.getAllSpecialist();
									for (Specialist s : list) {
									%>
									<option><%=s.getSpecialistName()%></option>
									<%
									}
									%>


								</select>
							</div>

							<div class="mb-3">
								<label class="form-label">Email</label> <input type="email"
									required name="email" class="form-control" value="<%=d.getEmail()%>">
							</div>

							<div class="mb-3">
								<label class="form-label">Mob No</label> <input type="text"
									required name="mobno" class="form-control" value="<%=d.getMobNo()%>">
							</div>

							<div class="mb-3">
								<label class="form-label">Password</label> <input required
									name="password" type="text" class="form-control" value="<%=d.getPassword()%>">
							</div>
							
							<!-- after clicking update button then we get the unique ID of the particular doctor who is updating their details -->
							<!-- update button clicked then the ID is to be taken but as hidden form -> which goes to servlet file  -->
							<!-- goes to DoctorDAO file, trigger updateDoctor(Doctor d) to update the doctor -->
							<input type="hidden" name="id" value="<%=d.getId()%>">

							<button type="submit" class="btn btn-primary col-md-12">Update</button>
						</form>
					</div>
				</div>
			</div>

			



		</div>
	</div>
</body>
</html>