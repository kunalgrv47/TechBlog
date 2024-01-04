package com.techblog.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import com.techblog.dao.UserDao;
import com.techblog.entities.Message;
import com.techblog.entities.User;
import com.techblog.helper.ConnectionProvider;
import com.techblog.helper.Helper;

@MultipartConfig
public class EditServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try (PrintWriter out = resp.getWriter()) {
			

			// Fetch all data from form
			String name = req.getParameter("user_name");
			String email = req.getParameter("user_email");
			String password = req.getParameter("user_password");
			String about = req.getParameter("user_about");
			Part part = req.getPart("user_image");
			String imageName = part.getSubmittedFileName();
			
			
			

			// Get the user from session
			HttpSession s = req.getSession();
			User user = (User) s.getAttribute("currentUser");
			
			
			

			// Setting user details with new data
			user.setName(name);
			user.setEmail(email);
			user.setPassword(password);
			user.setAbout(about);
			String oldProfile = user.getProfile();
			// Set profile only when user uploads new image
			if (imageName != null && !imageName.isEmpty()) {
				user.setProfile(imageName);
			}
			
			
			

			// Check if a new file is selected
			if (imageName != null && !imageName.isEmpty()) {
				
				// Upload image and then update details to database

				String uploadFilePath = "C:\\Users\\kunal\\eclipse-workspace\\TechBlog\\src\\main\\webapp\\pics"+File.separator+user.getProfile();
				out.println("upload file path: "+uploadFilePath);
				if (Helper.saveFile(part.getInputStream(), uploadFilePath)) {

					UserDao dao = new UserDao(ConnectionProvider.getConnection());
					if (dao.updateUser(user)) {
//						out.println("Profile details and profile photo updated successfully");
						Message msg = new Message("Profile details and profile photo updated successfully", "success", "alert-success");  //alert-success is a class from bootstrap for generating alert 
						s.setAttribute("msg", msg);

						// Delete old profile picture if it's not the default one
						if (!oldProfile.equals("default.png")) {
							String deleteFilePath = "C:\\Users\\kunal\\eclipse-workspace\\TechBlog\\src\\main\\webapp\\pics" + File.separator + oldProfile;
							out.println("Delete file path: "+deleteFilePath);
							Helper.deleteFile(deleteFilePath);
						}

					} else {
//						out.println("Something went wrong while updating profile details");
						Message msg = new Message("Something went wrong while updating profile details", "error", "alert-danger");  //alert-danger is a class from bootstrap for generating alert 
						s.setAttribute("msg", msg);
					}

				} else {
//					out.println("Something went wrong while uploading profile photo");
					Message msg = new Message("Something went wrong while uploading profile photo", "error", "alert-danger");  //alert-danger is a class from bootstrap for generating alert 
					s.setAttribute("msg", msg);
				}
			} else {
				
				// Update details to the database (no need to upload an image)
				UserDao dao = new UserDao(ConnectionProvider.getConnection());
				
				if (dao.updateUser(user)) {
					
//					out.println("Profile updated successfully, and no changes in the profile picture");
					Message msg = new Message("Profile updated successfully, and no changes in the profile picture", "success", "alert-success");  //alert-success is a class from bootstrap for generating alert 
					s.setAttribute("msg", msg);
					
					
				} else {
					
//					out.println("Something went wrong while updating profile details");
					Message msg = new Message("Something went wrong while updating profile photo", "error", "alert-danger");  //alert-danger is a class from bootstrap for generating alert 
					s.setAttribute("msg", msg);
				}
			}
			
			
			resp.sendRedirect("profile.jsp");
			

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
