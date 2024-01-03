package com.techblog.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.PrintWriter;

import com.techblog.dao.UserDao;
import com.techblog.entities.User;
import com.techblog.helper.ConnectionProvider;

@MultipartConfig
public class EditServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try (PrintWriter out = resp.getWriter()) {
			
			//Fetch all data from form
			String name = req.getParameter("user_name");
			String email = req.getParameter("user_email");
			String password = req.getParameter("user_password");
			String about = req.getParameter("user_about");
			Part part = req.getPart("user_image");
			String imageName = part.getSubmittedFileName();
			
			
			// Get the user from session
			HttpSession s = req.getSession();
			User user = (User)s.getAttribute("currentUser");
			
			//Setting user details with new data
			user.setName(name);
			user.setEmail(email);
			user.setPassword(password);
			user.setAbout(about);
			user.setProfile(imageName);
			
			//Update Database
			UserDao dao = new UserDao(ConnectionProvider.getConnection());
			boolean ans = dao.saveUser(user);
			
			if(ans) {
				out.println("User details updated");
			}else {
				out.println("Something went wrong while updating user details");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}

}
