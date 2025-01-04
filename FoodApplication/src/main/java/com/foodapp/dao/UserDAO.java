package com.foodapp.dao;

import java.util.ArrayList;

import com.foodapp.model.User;

public interface UserDAO {
	public int insert(User u);
	ArrayList<User> fetchAll();
	User fetchSpecificUser(int userIdid);
	User fetchSpecificEmail(String email);
	int update(int userId,String address);
	int delete(int userId);
	
}
