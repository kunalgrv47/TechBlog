
<%@page import="com.techblog.entities.Category"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.techblog.helper.ConnectionProvider"%>
<%@page import="com.techblog.dao.PostDao"%>
<%@page import="com.techblog.entities.Post"%>
<%@page import="com.techblog.entities.Message"%>
<%@page import="com.techblog.entities.User"%>
<%@page errorPage="error_page.jsp"%>
<%
User user = (User) session.getAttribute("currentUser");
if (user == null) {
	response.sendRedirect("login_page.jsp");
}
%>


<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Profile</title>

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

	<!-- Start of NavBar -->

	<nav class="navbar navbar-expand-lg navbar-dark primary-background">
		<a class="navbar-brand" href="index.jsp"><span
			class="fa fa-asterisk"></span> TechBlog</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active"><a class="nav-link" href="#"><span
						class="fa fa-book"></span> LearnCodeWithKunal <span
						class="sr-only">(current)</span> </a></li>

				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
					role="button" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false"> <span class="fa fa-check-square-o"></span>
						Categories
				</a>
					<div class="dropdown-menu" aria-labelledby="navbarDropdown">
						<a class="dropdown-item" href="#">Programming language</a> <a
							class="dropdown-item" href="#">Project Implementation</a>
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="#">Data Structure</a>
					</div></li>
				<li class="nav-item"><a class="nav-link" href="#"><span
						class="fa fa-phone"></span> Contacts</a></li>

				<li class="nav-item"><a class="nav-link" href="#"
					data-toggle="modal" data-target="#add-post-modal"><span
						class="fa fa-asterisk"></span> Do Post</a></li>

			</ul>


			<ul class="navbar-nav mr-right">

				<li class="nav-item"><a class="nav-link" href="#!"
					data-toggle="modal" data-target="#profile-modal"><span
						class="fa fa-user-circle"></span> <%=user.getName()%></a></li>

				<li class="nav-item"><a class="nav-link" href="LogoutServlet"><span
						class="fa fa-user-plus"></span> Logout</a></li>
			</ul>
		</div>
	</nav>

	<!-- End of NavBar -->




	<!-- Showing alert message to profile if any changes made -->
	<%
	Message m = (Message) session.getAttribute("msg");
	if (m != null) {
	%>
	<div class="alert <%=m.getCssClass()%>" role="alert"><%=m.getContent()%></div>

	<%
	session.removeAttribute("msg");
	}
	%>





	<!-- profile modal -->

	<!-- Modal -->
	<div class="modal fade" id="profile-modal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header primary-background text-white">
					<h5 class="modal-title" id="exampleModalLabel">TechBlog</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="container text-center">
						<img alt="<%=user.getProfile()%>"
							src="pics/<%=user.getProfile()%>" class="img-fluid"
							style="border-radius: 50%; max-width: 200px">
						<h5 class="modal-title mt-3" id="exampleModalLabel"><%=user.getName()%></h5>

						<!-- User details -->

						<div id="profile-details">
							<table class="table">

								<tbody>
									<tr>
										<th scope="row">ID :</th>
										<td><%=user.getId()%></td>

									</tr>
									<tr>
										<th scope="row">Name :</th>
										<td><%=user.getName()%></td>

									</tr>
									<tr>
										<th scope="row">Gender :</th>
										<td><%=user.getGender()%></td>

									</tr>
									<tr>
										<th scope="row">About :</th>
										<td><%=user.getAbout()%></td>

									</tr>
									<tr>
										<th scope="row">Registered on :</th>
										<td><%=user.getDateTime()%></td>

									</tr>
								</tbody>
							</table>
						</div>


						<!-- Edit user details. This Div remains hidden until click on Edit -->

						<div id="profile-edit" style="display: none">
							<h3 style="">Please Edit Carefully</h3>
							<form action="EditServlet" method="post"
								enctype="multipart/form-data">
								<table class="table">
									<tr>
										<td>ID :</td>
										<td><%=user.getId()%></td>
									</tr>
									<tr>
										<td>Name :</td>
										<td><input class="form-control" type="text"
											name="user_name" value="<%=user.getName()%>"></td>
									</tr>
									<tr>
										<td>Email :</td>
										<td><input class="form-control" type="email"
											name="user_email" value="<%=user.getEmail()%>"></td>
									</tr>
									<tr>
										<td>Password :</td>
										<td><input class="form-control" type="password"
											name="user_password" value="<%=user.getPassword()%>"></td>
									</tr>
									<tr>
										<td>Gender :</td>
										<td><%=user.getGender().toUpperCase()%></td>

									</tr>
									<tr>
										<td>About :</td>
										<td><textarea class="form-control" name="user_about"
												rows="3"><%=user.getAbout()%>
										</textarea></td>
									</tr>
									<tr>
										<td>Change photo :</td>
										<td><input type="file" name="user_image"
											value="<%=user.getProfile()%>" class="form-control"></td>
									</tr>

								</table>

								<div class="container">
									<button type="submit" class="btn primary-background text-white">Save</button>
								</div>

							</form>
						</div>



					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Close</button>
					<button id="edit-profile-button" type="button"
						class="btn btn-primary">Edit</button>
				</div>
			</div>
		</div>
	</div>


	<!-- End of profile modal -->




	<!-- Add post modal -->

	<!-- Modal -->
	<div class="modal fade" id="add-post-modal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">

		<div class="modal-dialog" role="document">

			<div class="modal-content">

				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Provide the
						post details.</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>


				<div class="modal-body">

					<!-- Form for writing post -->
					<form id="add-post-form" action="AddPostServlet" method="post">

						<div class="form-group">

							<select class="form-control" name="cid">

								<option selected disabled>---Select category---</option>

								<%
								PostDao postd = new PostDao(ConnectionProvider.getConnection());
								ArrayList<Category> list = postd.getAllCategories();

								for (Category c : list) {
								%>
								<option value="<%=c.getCid()%>"><%=c.getName()%></option>
								<%
								}
								%>

							</select>

						</div>

						<div class="form-group">
							<input name="pTitle" type="text" placeholder="Enter Post Title"
								class="form-control" />
						</div>

						<div class="form-group">
							<textarea name="pContent" class="form-control"
								placeholder="Enter your contenet" style="height: 200px" rows=""
								cols=""></textarea>
						</div>

						<div class="form-group">
							<textarea name="pCode" class="form-control"
								placeholder="Enter your program (if any)" style="height: 200px"
								rows="" cols=""></textarea>
						</div>

						<div class="form-group">
							<label>Select your pic.</label> <br> <input type="file"
								name="pic">
						</div>

						<div class="container text-center">
							<button type="submit" class="btn btn-outline-primary">Post</button>
						</div>

					</form>

				</div>


			</div>
		</div>
	</div>


	<!-- End of post modal -->






	<!-- Javascript -->

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

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>

	<script src="js/myjs.js" type="text/javascript"></script>

	<script>
		$(document).ready(function() {

			let editStatus = false;

			$('#edit-profile-button').click(function() {

				if (editStatus == false) {
					$('#profile-details').hide()
					$('#profile-edit').show()
					$(this).text("Back")
					editStatus = true;
				} else {
					$('#profile-details').show()
					$('#profile-edit').hide()
					$(this).text("Edit")
					editStatus = false;
				}
			})

		});
	</script>


	<!-- JS for adding post -->

	<script>
		$(document).ready(function(e) {

			$("#add-post-form").on("submit", function(event) {
				// This code gets called when for submitted
				event.preventDefault();
				console.log("you have clicked on submit..")
				let form = new FormData(this);

				// Now requesting to server
				$.ajax({
					url : "AddPostServlet",
					type : 'POST',
					data : form,

					success : function(data, textStatus, jqXHR) {
						// successfully request came
						console.log(data);
						if(data.trim() == 'done'){
							swal("Good job!", "Post saved successfully", "success");
						}else{
							swal("Error!", "Something went wrong, try again", "error");
						}

					},

					error : function(jqXHR, textStatus, errorThrown) {
						// Error..
						swal("Error!", "Something went wrong, try again", "error");

					},

					processData : false,
					contentType : false
				})
			})
		})
	</script>





</body>
</html>