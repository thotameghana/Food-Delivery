package com.foodapp.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.foodapp.connection.MyConnection;
import com.foodapp.dao.OrdersDAO;
import com.foodapp.model.Orders;

public class OrdersDAOImpl implements OrdersDAO {
	private static Connection con;
	static ArrayList<Orders> orders=new ArrayList<>();
	private final static String insert="insert into orders(userId,restaurantId,totalAmount,status,paymentMode) values(?,?,?,?,?)";
	private final static String FETCHALL="Select * from orders";
	private final static String FETCHONE="select * from orders where orderId=?";
	private final static String UPDATE="update orders set status=? where orderId=?";
	private final static String DELETE="delete from orders where orderId=?";
	private final static String FETCHBYRID="select * from orders where restaurantId=?";
	static {
		con=MyConnection.connect();
	}
	private PreparedStatement pstmt;
	private Statement stmt;
	private ResultSet resultSet;
	@Override
	public int insert(Orders order) {
		try {
			pstmt=con.prepareStatement(insert);
			pstmt.setInt(1,order.getUserId());
			pstmt.setInt(2,order.getRestaurantId());
			pstmt.setInt(3, order.getTotalAmount());
			pstmt.setString(4,order.getStatus());
			pstmt.setString(5, order.getPaymentMode());
			return pstmt.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public ArrayList<Orders> fetchAll() {
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
	private ArrayList<Orders> fetchDataFromTable(ResultSet resultSet){
		try {
			while(resultSet.next()) {
				orders.add(new Orders(
						resultSet.getInt("orderId"),
						resultSet.getInt("userId"),
						resultSet.getInt("restaurantId"),
						resultSet.getInt("totalAmount"),
						resultSet.getString("status"),
						resultSet.getString("paymentMode"))
				);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return orders;
	}
	@Override
	public Orders fetchSpecific(int orderId) {
		try {
			pstmt=con.prepareStatement(FETCHONE);
			pstmt.setInt(1, orderId);
			resultSet=pstmt.executeQuery();
			return fetchDataFromTable(resultSet).get(0);
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public int update(int orderId, String status) {
		try {
			pstmt=con.prepareStatement(UPDATE);
			pstmt.setString(1, status);
			pstmt.setInt(2, orderId);
			return pstmt.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public int delete(int orderId) {
		try {
			pstmt=con.prepareStatement(DELETE);
			pstmt.setInt(1, orderId);
			return pstmt.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public Orders fetchByRestaurantId(int restaurantId) {
		try {
			pstmt=con.prepareStatement(FETCHBYRID);
			pstmt.setInt(1, restaurantId);
			resultSet=pstmt.executeQuery();
			return fetchDataFromTable(resultSet).get(0);
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
