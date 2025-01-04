package com.foodapp.dao;

import java.util.ArrayList;

import com.foodapp.model.Orders;

public interface OrdersDAO {
	int insert(Orders order);
	ArrayList<Orders> fetchAll();
	Orders fetchSpecific(int orderId);
	int update(int orderId,String status);
	int delete(int orderId);
	Orders fetchByRestaurantId(int restaurantId);
}
