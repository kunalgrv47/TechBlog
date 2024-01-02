<%@page import="com.techblog.entities.Message"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>

<!-- css -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<link rel="stylesheet" href="css/mystyle.css" type="text/css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
.banner-background {
	clip-path: polygon(0 0, 100% 0, 100% 95%, 64% 100%, 30% 96%, 0 100%);
}
</style>

</head>
<body>

	<!-- Navbar -->
	<%@include file="normal_navbar.jsp"%>

	<main
		class="d-flex align-items-center primary-background banner-background"
		style="height: 80vh">
		<div class="container">
			<div class="row">
				<div class="col-md-4 offset-md-4">
					<div class="card">

						<!-- card header -->
						<div class="card-header primary-background text-white text-center">
							<span class="fa fa-user-plus fa-2x"></span> <br>
							<p>Login here</p>
						</div>

						<!-- Showing alert message if any -->
						<%
						Message m = (Message) session.getAttribute("msg");
						if (m != null) {
						%>
						<div class="alert <%= m.getCssClass() %>" role="alert"><%= m.getContent() %></div>

						<%
						session.removeAttribute("msg");
						}
						%>




						<!-- card body -->
						<div class="card-body">

							<form action="LoginServlet" method="post">

								<div class="form-group">
									<label for="exampleInputEmail1">Email address</label> <input
										name="user_email" required type="email" class="form-control"
										id="exampleInputEmail1" aria-describedby="emailHelp"
										placeholder="Enter email">
								</div>

								<div class="form-group">
									<label for="exampleInputPassword1">Password</label> <input
										name="user_password" required type="password"
										class="form-control" id="exampleInputPassword1"
										placeholder="Password">
								</div>

								<div class="container text-center">
									<button type="submit" class="btn btn-primary">Login</button>
								</div>



							</form>
						</div>
						<div class="card-footer"></div>


					</div>
				</div>
			</div>
		</div>
	</main>






	<script src="https://code.jquery.com/jquery-3.7.1.min.js"
		integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
		integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
		integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
		crossorigin="anonymous"></script>

	<script src="js/myjs.js" type="text/javascript"></script>

</body>
</html>