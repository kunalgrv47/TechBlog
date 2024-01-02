package com.techblog.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.techblog.entities.Message;


public class LogoutServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession s = req.getSession();
		s.removeAttribute("currentUser");
		
		Message m = new Message("Logout Successfully", "success", "alert-success");
		
		s.setAttribute("msg", m);
		resp.sendRedirect("login_page.jsp");
		
	}

}
