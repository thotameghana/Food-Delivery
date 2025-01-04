package com.foodapp.dao;

import java.util.ArrayList;

import com.foodapp.model.Menu;

public interface MenuDAO {
	int insert(Menu menu);
	ArrayList<Menu> fetchAll();
	Menu fetchSpecific(int menuId);
	ArrayList<Menu> fetchByRestaurantId(int restaurantId);
	int update(int menuId,String name);
	int delete(int menuId);
}
