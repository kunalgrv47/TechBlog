<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sorry ! Something went wrong</title>

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

	<div class="container text-center">

		<img src="Images/error.png" class="img-fluid">
		<h3 class="display-3">Sorry ! Something went wrong...</h3>
		<p><%=exception%></p>
		<a href="index.jsp"
			class="btn btn-lg primary-background text-white mt-3">Home</a>

	</div>

</body>
</html>