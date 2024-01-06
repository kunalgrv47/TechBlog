package com.techblog.dao;

import java.sql.*;
import java.util.ArrayList;

import com.techblog.entities.Category;
import com.techblog.entities.Post;

public class PostDao {
	
	Connection con;

	public PostDao(Connection con) {
		this.con = con;
	}
	
	
	public ArrayList<Category> getAllCategories() {
		
		ArrayList<Category> list= new ArrayList<Category>();
		
		
		try {
			
			String query = "select * from categories";
			Statement st = this.con.createStatement();
			ResultSet set = st.executeQuery(query);
			
			while(set.next()) {
				
				int cid = set.getInt("cid");
				String name = set.getString("name");
				String description  = set.getString("description");
				
				Category c = new Category(cid, name, description);
				list.add(c);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	
	public boolean savePost (Post p) {
		
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
	
	

}
