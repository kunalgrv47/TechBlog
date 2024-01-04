package com.techblog.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

import com.techblog.dao.UserDao;
import com.techblog.entities.Message;
import com.techblog.entities.User;
import com.techblog.helper.ConnectionProvider;

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("text/html;charset=UTF-8");

		try (PrintWriter out = resp.getWriter()) {

			// Getting Login Form Details
			String email = req.getParameter("user_email");
			String password = req.getParameter("user_password");
			

			// Getting user from database using email and password
			UserDao dao = new UserDao(ConnectionProvider.getConnection());
			User user = dao.getUserByEmailAndPassword(email, password);
			
			if (user == null) {

				// No user Found or Invalid details
				Message msg = new Message("Invalid details ! Try with another", "error", "alert-danger");  //alert-danger is a class from bootstrap for generating alert 
				HttpSession s = req.getSession();
				s.setAttribute("msg", msg);
				
				resp.sendRedirect("login_page.jsp");
				
			} else {

				// User found
				// Creating Session for user
				
				HttpSession session = req.getSession();
				session.setAttribute("currentUser", user);
				resp.sendRedirect("profile.jsp");
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
