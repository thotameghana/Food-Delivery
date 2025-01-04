package com.foodapp.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.foodapp.connection.MyConnection;
import com.foodapp.dao.OrderHistoryDAO;
import com.foodapp.model.OrderHistory;

public class OrderHistoryDAOImpl implements OrderHistoryDAO {

	private static Connection con;
	static ArrayList<OrderHistory> orderHistory=new ArrayList<>();
	private final static String INSERT="insert into orderhistory(orderId,userId,totalAmount,status) values(?,?,?,?)";
	private final static String FETCHALL="select * from orderHistory";
	private final static String FETCHONE="select * from orderHistory where orderHistoryid=?";
	private final static String UPDATE="update orderHistory set totalAmount=? where orderHistoryid=?";
	private final static String DELETE="delete from orderHistory where orderhistoryid=?";
	

	static {
		con=MyConnection.connect();
	}

	private PreparedStatement pstmt;
	private ResultSet resultSet;
	private Statement stmt;
	@Override
	public int insert(OrderHistory orderHistory) {
		try 
		{
			pstmt=con.prepareStatement(INSERT);
			pstmt.setInt(1, orderHistory.getOrderId());
			pstmt.setInt(2, orderHistory.getUserId());
			pstmt.setInt(3, orderHistory.getTotalAmount());
			pstmt.setString(4, orderHistory.getStatus());
			
			return pstmt.executeUpdate();

		}
		catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public ArrayList<OrderHistory> fetchAll() {
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
	public OrderHistory fetchSpecific(int orderHistoryId) {
		try {
			pstmt=con.prepareStatement(FETCHONE);
			pstmt.setInt(1, orderHistoryId);
			resultSet=pstmt.executeQuery();
			if(!(orderHistory.isEmpty())) {
				return fetchDataFromTable(resultSet).get(0);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	@Override
	public int update(int orderHistoryId, int totalAmount) {
		try {
			pstmt=con.prepareStatement(UPDATE);
			pstmt.setInt(1, totalAmount);
			pstmt.setInt(2, orderHistoryId);
			return pstmt.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int delete(int orderHistoryId) {
		try {
			pstmt=con.prepareStatement(DELETE);
			pstmt.setInt(1, orderHistoryId);
			return pstmt.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	private ArrayList<OrderHistory> fetchDataFromTable(ResultSet resultSet){
		try {
			while(resultSet.next()) {
				orderHistory.add(new OrderHistory(
						resultSet.getInt("orderHistoryId"),
						resultSet.getInt("orderId"),
						resultSet.getInt("userId"),
						resultSet.getInt("totalAmount"),
						resultSet.getString("status")
					));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return orderHistory;
	}


}
