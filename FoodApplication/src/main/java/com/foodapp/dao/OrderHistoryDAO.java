package com.foodapp.dao;

import java.util.ArrayList;

import com.foodapp.model.OrderHistory;

public interface OrderHistoryDAO {
	int insert(OrderHistory orderHostory);
	ArrayList<OrderHistory> fetchAll();
	OrderHistory fetchSpecific(int orderHistoryId);
	int update(int orderHistoryId,int totalAmount);
	int delete(int orderHistoryId);
}
