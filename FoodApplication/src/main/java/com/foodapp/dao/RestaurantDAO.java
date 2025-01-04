package com.foodapp.dao;

import java.util.ArrayList;

import com.foodapp.model.Restaurant;

public interface RestaurantDAO {
	int insert(Restaurant restaurant);
	ArrayList<Restaurant> fetchAll();
	Restaurant fetchSpecific(int restaurantId);
	int update(int restaurantId,String address);
	int delete(int restaurantId);
}
