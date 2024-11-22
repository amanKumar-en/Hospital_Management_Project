<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>SignUp Page</title>
<%@ include file="component/allcss.jsp" %>
<style>
.paint-card {
	box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.3);
}
</style>
</head>

<body>
	<%@ include file="component/navbar.jsp" %>

	<div class="container p-5">
		<div class="row">
			<div class="col-md-4 offset-md-4">
				<div class="card paint-card">
					<div class="card-body">
						<p class="fs-4 text-center">User Registration</p>
						
						<c:if test="${not empty successMsg }">
							<p class="text-center text-success fs-3">${successMsg}</p>
							<c:remove var="successMsg" scope="session"/>
						</c:if>
						
						<c:if test="${not empty failedMsg }">
							<p class="text-center text-danger fs-3">${failedMsg}</p>
							<c:remove var="failedMsg" scope="session"/>
						</c:if>

						<form action="user_register" method="post">
							<div class="md-3">
								<label class="form-label">Enter Your Name<span class="text-danger">*</span>
									</label> <input
									type="text" name="name" class="form-control" required>
							</div>

							<div class="md-3">
								<label class="form-label">Enter Your Email<span class="text-danger">*</span>
									</label> <input
									type="email" name="email" class="form-control" required>
							</div>
							
							<div class="md-3">
								<label class="form-label">Enter Your Password<span class="text-danger">*</span>
									</label> <input
									type="password" name="password" class="form-control" required>
							</div>

							<button type="submit"
								class="btn bg-success text-white mt-3 col-md-12">Register</button>

						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	

</body>
</html>