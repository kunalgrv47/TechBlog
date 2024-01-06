package com.techblog.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import com.techblog.dao.PostDao;
import com.techblog.entities.Post;
import com.techblog.entities.User;
import com.techblog.helper.ConnectionProvider;
import com.techblog.helper.Helper;

@MultipartConfig
public class AddPostServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		try (PrintWriter out = resp.getWriter()){
			
			// Fetching details from Post
			int cid = Integer.parseInt(req.getParameter("cid"));
			String pTitle = req.getParameter("pTitle");
			String pContent = req.getParameter("pContent");
			String pCode = req.getParameter("pCode");
			Part part = req.getPart("pic");
			
			// Getting current user ID to update that which user has written the POST
			
			HttpSession session = req.getSession();
			User user = (User)session.getAttribute("currentUser");
			
//			out.println("Your post title is : "+pTitle);
//			out.println(part.getSubmittedFileName());
			
			Post p = new Post(pTitle, pContent, pCode, part.getSubmittedFileName(), null, cid, user.getId());
			
			PostDao dao = new PostDao(ConnectionProvider.getConnection());
			if(dao.savePost(p)) {
				
				// Upload image to blog_post folder
				String uploadFilePath = "C:\\Users\\kunal\\eclipse-workspace\\TechBlog\\src\\main\\webapp\\blog_pics"+File.separator+part.getSubmittedFileName();
				Helper.saveFile(part.getInputStream(), uploadFilePath);
				out.println("done");
				
				
			}else {
				out.println("error");
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
	}

}
