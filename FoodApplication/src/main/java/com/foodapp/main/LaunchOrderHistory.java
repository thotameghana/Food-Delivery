package com.foodapp.main;

import java.util.Scanner;

import com.foodapp.daoimpl.OrderHistoryDAOImpl;
import com.foodapp.model.OrderHistory;

public class LaunchOrderHistory {
	static Scanner sc=new Scanner(System.in);
	
	public static void main(String[] args) {
		OrderHistoryDAOImpl ohd=new OrderHistoryDAOImpl();

		System.out.println("Enter your choice\n"
				+ "1.insert order history\n"
				+"2.fetch all order history\n"
				+"3.fetech specific order history\n"
				+"4.update order history\n"
				+"5.delete order history\n");
		int choice=sc.nextInt();
		switch(choice) {
		case 1:
			System.out.println("Enter order history id :");
			int orderHistoryId=sc.nextInt();
			System.out.println("Enter order id :");
			int orderId=sc.nextInt();
			System.out.println("Enter userId :");
			int userId=sc.nextInt();
			System.out.println("Enter totalAmount: ");
			int totalAmount=sc.nextInt();
			sc.nextLine();
			System.out.println("Enter status: ");
			String status=sc.nextLine();
			OrderHistory o=new OrderHistory(orderHistoryId, orderId, userId, totalAmount, status);
			System.out.println(ohd.insert(o)==1?"Inserted succesfully":"Failed to insert");
			break;
		case 2:
			System.out.println("These are the list of order item");
			for(OrderHistory orderHistory:ohd.fetchAll()) {
				System.out.println(orderHistory);
			}
			break;
		case 3:
			System.out.println("Enter orderHistoryId");
			orderHistoryId=sc.nextInt();
			System.out.println(ohd.fetchSpecific(orderHistoryId)!=null?"Here are the details \n"+ohd.fetchSpecific(orderHistoryId):"No user found");
			break;
		case 4:
			System.out.println("Enter orderHistoryId");
			orderHistoryId=sc.nextInt();
			System.out.println("Enter totalAmount");
			totalAmount=sc.nextInt();
			System.out.println(ohd.update(orderHistoryId,totalAmount)==1?"Sucessfully updated":"Failed to update");
			break;
		case 5:
			System.out.println("Enter orderItemId:");
			orderHistoryId=sc.nextInt();
			System.out.println(ohd.delete(orderHistoryId)==1?"Sucessfully deleted":"failed to delte");
			break;
		}
	}
}
