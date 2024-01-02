package com.techblog.dao;

import java.sql.*;

import com.techblog.entities.User;

public class UserDao {

	private Connection con;

	public UserDao(Connection con) {
		this.con = con;
	}

	// Method to insert user into database

	public boolean saveUser(User user) {

		// Boolean variable for checking if data inserted successfully or not. 
		// false -> not inserted | true -> inserted successfully
		boolean f = false;

		try {
			
			String query = "insert into user(name, email, password, gender, about) values (?, ?, ?, ?, ?)";
			PreparedStatement pstm = this.con.prepareStatement(query);
			pstm.setString(1, user.getName());
			pstm.setString(2, user.getEmail());
			pstm.setString(3, user.getPassword());
			pstm.setString(4, user.getGender());
			pstm.setString(5, user.getAbout());
			
			pstm.executeUpdate();
			f = true; //making f as true if data inserted successfully
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}

		return f;
	}
	
	
	// Method to get User from database using Email and Password
	
	public User getUserByEmailAndPassword(String email, String password) {
		
		User user = null;
		
		try {
			
			String query = "select * from user where email=? and password=?";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			ResultSet set = pstmt.executeQuery();
			
			// Checking if next row available 
			//Here we know only 1 row will be there so using if-else otherwise we can use while()
			if(set.next()) {
				user = new User();
				user.setId(set.getInt("id"));
				user.setName(set.getString("name"));
				user.setEmail(set.getString("email"));
				user.setPassword(set.getString("password"));
				user.setGender(set.getString("gender"));
				user.setAbout(set.getString("about"));
				user.setDateTime(set.getTimestamp("rdate"));
				user.setProfile(set.getString("profile"));
				
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return user;
	}
	
	
	
	
	

}
