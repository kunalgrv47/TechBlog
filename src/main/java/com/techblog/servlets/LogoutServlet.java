package com.techblog.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.techblog.entities.Message;


public class LogoutServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// Removing the user from session session
		HttpSession s = req.getSession();
		s.removeAttribute("currentUser");
		
		// Creating an object for logout successful message
		Message m = new Message("Logout Successfully", "success", "alert-success");
		
		//Setting new attribute in session for displaying message on logout page
		s.setAttribute("msg", m);
		
		//Redirecting to Login page
		resp.sendRedirect("login_page.jsp");
		
	}

}
