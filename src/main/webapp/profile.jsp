<%@page import="com.techblog.entities.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
User user = (User) session.getAttribute("currentUser");
if (user == null) {
	response.sendRedirect("login_page.jsp");
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Profile</title>
</head>
<body>

<%= user.getName() %>
<br>
<%= user.getEmail() %>
<br>
<%= user.getGender() %>
<br>
<%= user.getAbout() %>
<br>

</body>
</html>