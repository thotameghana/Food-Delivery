package com.foodapp.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.foodapp.connection.MyConnection;
import com.foodapp.dao.MenuDAO;
import com.foodapp.model.Menu;

public class MenuDAOImpl implements MenuDAO {
	static private String INSERT="insert into Menu(MenuId,restaurantId,Name,Description,Price,IsAvailable) values(?,?,?,?,?,?)";
	static private String FETCHALL="select * from menu";
	static private String FETCHONE="select * from menu where menuId=?";
	static private String FETCHBYRESTAURANTID="select * from menu where restaurantId=?";
	static private String UPDATE="update menu set name=? where menuId=?";
	static private String DELETE="delete from menu where menuid=?";
	
	static ArrayList<Menu> al=new ArrayList<>();
	private static Connection con;
	static {
		con=MyConnection.connect();
	}
	private PreparedStatement pstmt;
	private Statement stmt;
	private ResultSet resultSet;
	@Override
	public int insert(Menu menu) {
		
		try {
			pstmt=con.prepareStatement(INSERT);
			pstmt.setInt(1, menu.getMenuId());
			pstmt.setInt(2, menu.getRestaurantId());
			pstmt.setString(3, menu.getName());
			pstmt.setString(4, menu.getDescription());
			pstmt.setInt(5, menu.getPrice());
			pstmt.setBoolean(6, menu.isActive());
			
			return pstmt.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public ArrayList<Menu> fetchAll() {
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
	public Menu fetchSpecific(int menuId) {
		try {
			pstmt=con.prepareStatement(FETCHONE);
			pstmt.setInt(1, menuId);
			
			resultSet=pstmt.executeQuery();
			if(!al.isEmpty()) {
				return fetchDataFromTable(resultSet).get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}
	@Override
	public int update(int menuId,String name) {
		try {
			pstmt=con.prepareStatement(UPDATE);
			pstmt.setString(1, name);
			pstmt.setInt(2, menuId);
			
			return pstmt.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public int delete(int menuId) {
		try {
			pstmt=con.prepareStatement(DELETE);
			pstmt.setInt(1, menuId);
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public ArrayList<Menu> fetchByRestaurantId(int restaurantId) {
		try {
			pstmt=con.prepareStatement(FETCHBYRESTAURANTID);
			pstmt.setInt(1, restaurantId);
			fetchDataFromTable(pstmt.executeQuery());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return al;
	}
	private ArrayList<Menu> fetchDataFromTable(ResultSet resultSet){
		try {
			al.clear();
			while(resultSet.next()) {
				al.add(new Menu(
						resultSet.getInt("menuId"),
						resultSet.getInt("restaurantId"),
						resultSet.getString("name"),
						resultSet.getString("description"),
						resultSet.getInt("price"),
						resultSet.getBoolean("isAvailable"),
						resultSet.getString("imagePath")
						));
			}
			return al;
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
