package com.foodapp.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.foodapp.connection.MyConnection;
import com.foodapp.dao.UserDAO;
import com.foodapp.model.User;

public class UserDAOImpl implements UserDAO {
	static Connection con;
	static Statement stmt;
	static PreparedStatement pstmt;
	static ArrayList<User> users=new ArrayList<>();
	private final String INSERT="insert into user(userName,password,email,address) values(?,?,?,?)";
	private final String FETCHALL="select * from user";
	private final String FETCHONE="select * from user where userId=?";
	private final String UPDATE="update user set address=? where userId=?";
	private final String DELETE="delete from user where userId=?";
	private final String FETCHONEEMAIL="select * from user where email=?";
	private ResultSet resultSet;
	static {
		con=MyConnection.connect();
	}
	@Override
	public int insert(User u) {
		try {
			pstmt=con.prepareStatement(INSERT);
			pstmt.setString(1, u.getUserName());
			pstmt.setString(2, u.getPassword());
			pstmt.setString(3, u.getEmail());
			pstmt.setString(4, u.getAddress());
			
			return pstmt.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
		
	}
	@Override
	public ArrayList<User> fetchAll() {
		try {
			stmt=con.createStatement();
			resultSet=stmt.executeQuery(FETCHALL);
			return fetchDataFromTable(resultSet);
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public User fetchSpecificUser(int userId) {
		try {
			pstmt=con.prepareStatement(FETCHONE);
			pstmt.setInt(1, userId);
			resultSet=pstmt.executeQuery();
			return fetchDataFromTable(resultSet).get(0);
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public int update(int userId,String address) {
		try {
			pstmt=con.prepareStatement(UPDATE);
			pstmt.setString(1, address);
			pstmt.setInt(2, userId);
			return pstmt.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public int delete(int userId) {
		try {
			pstmt=con.prepareStatement(DELETE);
			pstmt.setInt(1, userId);
			return pstmt.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public User fetchSpecificEmail(String email) {
		try {
			pstmt=con.prepareStatement(FETCHONEEMAIL);
			pstmt.setString(1, email);
			return fetchDataFromTable(pstmt.executeQuery()).get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	private ArrayList<User> fetchDataFromTable(ResultSet resultSet){
		users.clear();
		try {
			while(resultSet.next()) {
				users.add(new User(
						resultSet.getInt("userId"),
						resultSet.getString("userName"),
						resultSet.getString("password"),
						resultSet.getString("email"),
						resultSet.getString("address")));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return users;
	}
}
