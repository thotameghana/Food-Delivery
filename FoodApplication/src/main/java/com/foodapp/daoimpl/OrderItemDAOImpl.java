package com.foodapp.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.foodapp.connection.MyConnection;
import com.foodapp.dao.OrderItemDAO;
import com.foodapp.model.OrderItem;

public class OrderItemDAOImpl implements OrderItemDAO {
	private static Connection con;
	static ArrayList<OrderItem> orderItem=new ArrayList<>();
	private final static String INSERT="insert into orderitem(orderId,menuId,quantity,itemTotal) values(?,?,?,?)";
	private final static String FETCHALL="select * from orderItem";
	private final static String FETCHONE="select * from orderItem where orderitemid=?";
	private final static String UPDATE="update orderitem set quantity=? where orderitemid=?";
	private final static String DELETE="delete from orderitem where orderitemid=?";
	
	static {
		con=MyConnection.connect();
	}
	private PreparedStatement pstmt;
	private Statement stmt;
	private ResultSet resultSet;
	@Override
	public int insert(OrderItem orderItem) {
		try 
		{
			pstmt=con.prepareStatement(INSERT);
			pstmt.setInt(1, orderItem.getOrderId());
			pstmt.setInt(2, orderItem.getMenuId());
			pstmt.setInt(3, orderItem.getQuantity());
			pstmt.setInt(4, orderItem.getItemTotal());
			
			
			return pstmt.executeUpdate();

		}
		catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public ArrayList<OrderItem> fetchAll() {
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
	public OrderItem fetchSpecific(int orderItemId) {
		try {
			pstmt=con.prepareStatement(FETCHONE);
			pstmt.setInt(1, orderItemId);
			resultSet=pstmt.executeQuery();
			return fetchDataFromTable(resultSet).get(0);
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public int update(int orderItemId, int quantity) {
		try {
			pstmt=con.prepareStatement(UPDATE);
			pstmt.setInt(1, quantity);
			pstmt.setInt(2, orderItemId);
			return pstmt.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public int delete(int orderItemId) {
		try {
			pstmt=con.prepareStatement(DELETE);
			pstmt.setInt(1, orderItemId);
			return pstmt.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	private ArrayList<OrderItem> fetchDataFromTable(ResultSet resultSet){
		try {
			while(resultSet.next()) {
				orderItem.add(new OrderItem(
						resultSet.getInt("orderItemId"),
						resultSet.getInt("orderId"),
						resultSet.getInt("menuId"),
						resultSet.getInt("quantity"),
						resultSet.getInt("itemtotal")
					));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return orderItem;
	}

}
