package com.foodapp.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class MyConnection {
	static Connection con;
	public static Connection connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/foodapp","root","root");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return con;
	}
}
