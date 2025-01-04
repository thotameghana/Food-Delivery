package com.foodapp.dao;

import java.util.ArrayList;

import com.foodapp.model.OrderItem;

public interface OrderItemDAO {
	int insert(OrderItem orderItem);
	ArrayList<OrderItem> fetchAll();
	OrderItem fetchSpecific(int orderItemId);
	int update(int orderItemId,int quantity);
	int delete(int orderItemId);
}
