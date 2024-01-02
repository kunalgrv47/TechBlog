package com.techblog.helper;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionProvider {

	private static Connection con;

	public static Connection getConnection() {

		try {

			if (con == null) {
				//Loading Driver Class
				Class.forName("com.mysql.cj.jdbc.Driver");
				
				//Create a Connection
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TechBlog", "root", "1994");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;
	}

}
