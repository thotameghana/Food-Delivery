package com.foodapp.main;

import java.util.Scanner;

import com.foodapp.daoimpl.OrdersDAOImpl;
import com.foodapp.model.Orders;

public class LaunchOrders {
	static Scanner sc=new Scanner(System.in);
	public static void main(String[] args) {
		OrdersDAOImpl odi=new OrdersDAOImpl();
		System.out.println("Enter your choice\n"
				+ "1.insert new Order\n"
				+"2.fetch all Orders\n"
				+"3.fetech specific Order\n"
				+"4.update Order\n"
				+"5.delete Order\n");
		int choice=sc.nextInt();
		switch(choice) {
		case 1:
			System.out.println("Enter orderId");
			int orderId=sc.nextInt();
			System.out.println("Enter UserId");
			int userId=sc.nextInt();
			System.out.println("Enter restaurantId");
			int restaurantId=sc.nextInt();
			System.out.println("Enter total amount");
			int totlaAmount=sc.nextInt();
			sc.nextLine();
			System.out.println("Enter status");
			String status=sc.nextLine();
			System.out.println("Enter payment mode");
			String paymentMode=sc.nextLine();
			Orders o=new Orders(orderId,userId,restaurantId, totlaAmount, status, paymentMode);
			System.out.println(odi.insert(o)==1?"Sucessfully inserted":"failed to insert");
			break;
		case 2:
			System.out.println("Here are the details");
			for(Orders order:odi.fetchAll()) {
				System.out.println(order);
			}
			break;
		case 3:
			System.out.println("Enter orderId");
			orderId=sc.nextInt();
			System.out.println(odi.fetchSpecific(orderId)!=null?odi.fetchSpecific(orderId):"user not found");
			break;
		case 4:
			System.out.println("Enter orderId");
			orderId=sc.nextInt();
			sc.nextLine();
			System.out.println("Enter status");
			status=sc.nextLine();
			System.out.println(odi.update(orderId, status)==1?"updated sucessfully":"failed to update");
			break;
		case 5:
			System.out.println("Enter orderId");
			orderId=sc.nextInt();
			System.out.println(odi.delete(orderId)==1?"Deleted sucessfully":"failed to delete");
		}
			
	}
}
