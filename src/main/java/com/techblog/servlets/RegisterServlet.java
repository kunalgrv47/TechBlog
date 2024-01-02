package com.techblog.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import com.techblog.dao.UserDao;
import com.techblog.entities.User;
import com.techblog.helper.ConnectionProvider;

@MultipartConfig
public class RegisterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("text/html;charset=UTF-8");

		try (PrintWriter out = resp.getWriter()) {

			String check = req.getParameter("check");

			if (check == null) {
				out.println("Please accept Terms & Condition.");
			} else {
				String name = req.getParameter("user_name");
				String email = req.getParameter("user_email");
				String password = req.getParameter("user_password");
				String gender = req.getParameter("gender");
				String about = req.getParameter("about");

				// Creating User
				User user = new User(name, email, password, gender, about);

				// Create a User DAO object to save user
				UserDao dao = new UserDao(ConnectionProvider.getConnection());
				if (dao.saveUser(user)) {
					out.println("done");
				}else {
					out.println("Error");
				}


			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
