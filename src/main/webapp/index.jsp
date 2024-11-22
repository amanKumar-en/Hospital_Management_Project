<%@ page import="com.Db.DBConnect"%>
<%@ page import="java.sql.Connection"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Index Page</title>

<%@ include file="component/allcss.jsp"%>

<style>
.paint-card {
	box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.3);
}
</style>
</head>

<body>
	<%@ include file="component/navbar.jsp"%>

	<!-- <% Connection connection = DBConnect.getConnection(); 
		out.print(connection);
	%> -->

	<div id="carouselExampleInterval" class="carousel slide"
		data-bs-ride="carousel">
		<div class="carousel-inner">
			<div class="carousel-item active" data-bs-interval="4000">
				<img src="image/hos1.jpg" class="d-block w-100" alt="..."
					height="500px">
			</div>
			<div class="carousel-item" data-bs-interval="4000">
				<img src="image/hos2.jpg" class="d-block w-100" alt="..."
					height="500px">
			</div>
			<div class="carousel-item" data-bs-interval="4000">
				<img src="image/hos3.jpg" class="d-block w-100" alt="..."
					height="500px">
			</div>
		</div>
		<button class="carousel-control-prev" type="button"
			data-bs-target="#carouselExampleInterval" data-bs-slide="prev">
			<span class="carousel-control-prev-icon" aria-hidden="true"></span> <span
				class="visually-hidden">Previous</span>
		</button>
		<button class="carousel-control-next" type="button"
			data-bs-target="#carouselExampleInterval" data-bs-slide="next">
			<span class="carousel-control-next-icon" aria-hidden="true"></span> <span
				class="visually-hidden">Next</span>
		</button>
	</div>

	<div class="container p-3">
		<p class="text-center fs-2">Key features of our hospital</p>
		<div class="row">
			<div class="col-md-8 p-5">
				<div class="row">
					<div class="col-md-6">
						<div class="card paint-card">
							<div class="card-body">
								<p class="fs-5">100% Safety</p>
								<p>Loremcdneij jebvihb v ehvbi vhebvhb hbecv chebvbe cheb
									ehcb</p>
							</div>
						</div>
					</div>

					<div class="col-md-6">
						<div class="card paint-card">
							<div class="card-body">
								<p class="fs-5">Clean Environment</p>
								<p>Loremcdneij jebvihb v ehvbi vhebvhb hbecv chebvbe cheb
									ehcb</p>
							</div>
						</div>
					</div>

					<div class="col-md-6 mt-2">
						<div class="card paint-card">
							<div class="card-body">
								<p class="fs-5">Friendly Doctors</p>
								<p>Loremcdneij jebvihb v ehvbi vhebvhb hbecv chebvbe cheb
									ehcb</p>
							</div>
						</div>
					</div>

					<div class="col-md-6 mt-2">
						<div class="card paint-card">
							<div class="card-body">
								<p class="fs-5">Medical Research</p>
								<p>Loremcdneij jebvihb v ehvbi vhebvhb hbecv chebvbe cheb
									ehcb</p>
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="col-md-4 ml-7">
				<img alt="" src="image/doc1.jpg">
			</div>

		</div>
	</div>

	<hr>


	<div class="container p-2">
		<p class="text-center fs-2">Our Team</p>
		<div class="row">
			<div class="col-md-3">
				<div class="card paint-card">
					<div class="card-body text-center">
						<img src="image/doc2.jpg" width="250px" height="300px">
						<p class="fw-bold fs-5">Doctor Name</p>
						<p class="fs-7">(CEO & chairman)</p>

					</div>
				</div>
			</div>

			<div class="col-md-3">
				<div class="card paint-card">
					<div class="card-body text-center">
						<img src="image/doc3.jpg" width="250px" height="300px">
						<p class="fw-bold fs-5">Doctor Name</p>
						<p class="fs-7">(Cheif doctor)</p>

					</div>
				</div>
			</div>

			<div class="col-md-3">
				<div class="card paint-card">
					<div class="card-body text-center">
						<img src="image/doc4.jpg" width="250px" height="300px">
						<p class="fw-bold fs-5">Doctor Name</p>
						<p class="fs-7">(Cheif doctor)</p>

					</div>
				</div>
			</div>

			<div class="col-md-3">
				<div class="card paint-card">
					<div class="card-body text-center">
						<img src="image/doc5.jpg" width="250px" height="300px">
						<p class="fw-bold fs-5">Doctor Name</p>
						<p class="fs-7">(Cheif doctor)</p>

					</div>
				</div>
			</div>

		</div>
	</div>






</body>
</html>