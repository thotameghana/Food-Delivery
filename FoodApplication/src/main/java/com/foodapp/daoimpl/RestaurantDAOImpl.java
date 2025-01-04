package com.foodapp.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.foodapp.connection.MyConnection;
import com.foodapp.dao.RestaurantDAO;
import com.foodapp.model.Restaurant;

public class RestaurantDAOImpl implements RestaurantDAO {
	
	static Connection con;
	static ArrayList<Restaurant> restaurant=new ArrayList<>();
	private static final String INSERT="insert into restarant(restaurantId,name,cuisineType,deliveryTime,address,ratings,isActive) values(?,?,?,?,?,?,?)";
	private static final String FETCHALL="select * from restarant";
	private static final String FETCHONE="select * from restarant where restaurantId=?";
	private static final String UPDATE="update restarant set address=? where restaurantId=?";
	private static final String DELETE="delete from restarant where restaurantId=?";
	
	static {
		con=MyConnection.connect();
	}
	private PreparedStatement pstmt;
	private Statement stmt;
	@Override
	public int insert(Restaurant restaurant) {
		try {
			pstmt=con.prepareStatement(INSERT);
			pstmt.setInt(1, restaurant.getRestaurantId());
			pstmt.setString(2, restaurant.getName());
			pstmt.setString(3, restaurant.getCuisineType());
			pstmt.setInt(4, restaurant.getDeliveryTime());
			pstmt.setString(5, restaurant.getAddress());
			pstmt.setFloat(6, restaurant.getRatings());
			pstmt.setBoolean(7, restaurant.isActive());
			
			return pstmt.executeUpdate();
			
		}
		catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public ArrayList<Restaurant> fetchAll() {
		try {
			stmt=con.createStatement();
			
			return fetchDataFromTable(stmt.executeQuery(FETCHALL));
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public Restaurant fetchSpecific(int restaurantId) {
		try {
			
			pstmt=con.prepareStatement(FETCHONE);
			pstmt.setInt(1, restaurantId);
			
			if(!restaurant.isEmpty()) {
			return fetchDataFromTable(pstmt.executeQuery()).get(0);
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}
	@Override
	public int update(int restaurantId,String address) {
		
		try {
			pstmt=con.prepareStatement(UPDATE);
			pstmt.setString(1, address);
			pstmt.setInt(2, restaurantId);
			
			return pstmt.executeUpdate();	
		}
		catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public int delete(int restaurantId) {
		try {
			pstmt=con.prepareStatement(DELETE);
			pstmt.setInt(1, restaurantId);
			return pstmt.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	private ArrayList<Restaurant> fetchDataFromTable(ResultSet resultSet){
		restaurant.clear();
		try {
			while(resultSet.next()) {
				restaurant.add(
						new Restaurant(
						resultSet.getInt("restaurantId"),
						resultSet.getString("name"),
						resultSet.getString("CuisineType"),
						resultSet.getInt("deliveryTime"),
						resultSet.getString("address"),
						resultSet.getFloat("ratings"),
						resultSet.getBoolean("isactive"),
						resultSet.getString("imagePath")
						)
					);
			}
			return restaurant;
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	

}
