package com.foodapp.main;

import java.util.Scanner;

import com.foodapp.daoimpl.OrderItemDAOImpl;
import com.foodapp.model.OrderItem;

public class LaunchOrderItem {
	static Scanner sc=new Scanner(System.in);
	public static void main(String[] args) {
		OrderItemDAOImpl oid=new OrderItemDAOImpl();
		
		System.out.println("Enter your choice\n"
				+ "1.insert order item\n"
				+"2.fetch all order item\n"
				+"3.fetech specific order item\n"
				+"4.update order item\n"
				+"5.delete order item\n");
		int choice=sc.nextInt();
		switch(choice) {
		case 1:
			System.out.println("Enter orderItem id :");
			int orderItemId=sc.nextInt();
			System.out.println("Enter order id :");
			int orderId=sc.nextInt();
			System.out.println("Enter menuId :");
			int menuId=sc.nextInt();
			System.out.println("Enter Quantity: ");
			int quantity=sc.nextInt();
			System.out.println("Enter itemTotal: ");
			int itemTotal=sc.nextInt();
			OrderItem o=new OrderItem(orderItemId, orderId, menuId, quantity, itemTotal);
			System.out.println(oid.insert(o)==1?"Inserted succesfully":"Failed to insert");
			break;
		case 2:
			System.out.println("These are the list of order item");
			for(OrderItem orderItem:oid.fetchAll()) {
				System.out.println(orderItem);
			}
			break;
		case 3:
			System.out.println("Enter orderItemId");
			orderItemId=sc.nextInt();
			System.out.println("Here are the details \n"+oid.fetchSpecific(orderItemId));
			break;
		case 4:
			System.out.println("Enter orderItemId");
			orderItemId=sc.nextInt();
			System.out.println("Enter quantity");
			quantity=sc.nextInt();
			System.out.println(oid.update(orderItemId,quantity)==1?"Sucessfully updated":"Failed to update");
			break;
		case 5:
			System.out.println("Enter orderItemId:");
			orderItemId=sc.nextInt();
			System.out.println(oid.delete(orderItemId)==1?"Sucessfully deleted":"failed to delte");

		}
	}

}
