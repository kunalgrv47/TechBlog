package com.techblog.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.techblog.entities.Category;
import com.techblog.entities.Post;

public class PostDao {

	Connection con;

	public PostDao(Connection con) {
		this.con = con;
	}

	// Method to fetch all categories from DB
	public ArrayList<Category> getAllCategories() {

		ArrayList<Category> list = new ArrayList<Category>();

		try {

			String query = "select * from categories";
			Statement st = this.con.createStatement();
			ResultSet set = st.executeQuery(query);

			while (set.next()) {

				int cid = set.getInt("cid");
				String name = set.getString("name");
				String description = set.getString("description");

				Category c = new Category(cid, name, description);
				list.add(c);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	// Method to save post
	public boolean savePost(Post p) {

		boolean f = false;

		try {

			String query = "insert into posts (pTitle, pContent, pCode, pPic, catId, userId) values (?,?,?,?,?,?)";
			PreparedStatement pstm = con.prepareStatement(query);

			pstm.setString(1, p.getpTitle());
			pstm.setString(2, p.getpContent());
			pstm.setString(3, p.getpCode());
			pstm.setString(4, p.getpPic());
			pstm.setInt(5, p.getCatId());
			pstm.setInt(6, p.getUserId());

			pstm.executeUpdate();

			f = true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return f;
	}
	
	

	// Method to fetch all Post
	public List<Post> getAllPosts() {

		List<Post> list = new ArrayList<Post>();

		// Code to fetch all the post
		try {

			String query = "select * from posts order by pid desc";
			PreparedStatement pstm = con.prepareStatement(query);
			ResultSet set = pstm.executeQuery();

			while (set.next()) {

				int pid = set.getInt("pid");
				String pTitle = set.getString("pTitle");
				String pContent = set.getString("pContent");
				String pCode = set.getString("pCode");
				String pPic = set.getString("pPic");
				Timestamp pDate = set.getTimestamp("pDate");
				int catId = set.getInt("catId");
				int userId = set.getInt("userId");

				Post post = new Post(pid, pTitle, pContent, pCode, pPic, pDate, catId, userId);

				list.add(post);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
	

	// Method to fetch posts by catId (category ID)
	public List<Post> getPostByCatId(int catId) {

		List<Post> list = new ArrayList<Post>();

		// Code to fetch post by catId

		try {

			String query = "select * from posts where catId=?";
			PreparedStatement pstm = con.prepareStatement(query);
			pstm.setInt(1, catId);
			ResultSet set = pstm.executeQuery();

			while (set.next()) {

				int pid = set.getInt("pid");
				String pTitle = set.getString("pTitle");
				String pContent = set.getString("pContent");
				String pCode = set.getString("pCode");
				String pPic = set.getString("pPic");
				Timestamp pDate = set.getTimestamp("pDate");
				int userId = set.getInt("userId");

				Post post = new Post(pid, pTitle, pContent, pCode, pPic, pDate, catId, userId);

				list.add(post);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

}
