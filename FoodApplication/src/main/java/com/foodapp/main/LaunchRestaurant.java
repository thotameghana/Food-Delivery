package com.foodapp.main;

import java.util.Scanner;

import com.foodapp.daoimpl.RestaurantDAOImpl;
import com.foodapp.model.Restaurant;

public class LaunchRestaurant {
	static Scanner sc=new Scanner(System.in);
	
	public static void main(String[] args) {
		RestaurantDAOImpl rdi=new RestaurantDAOImpl();
		System.out.println("Enter your choice\n"
				+ "1.insert new Restaurant\n"
				+"2.fetch all Restaurants\n"
				+"3.fetech specific Restaurant\n"
				+"4.update restaurant\n"
				+"5.delete Restaurant\n");
		int choice=sc.nextInt();
		switch(choice) {
		case 1:
			System.out.println("Enter restaurant id");
			int restaurantId=sc.nextInt();
			sc.nextLine();
			System.out.println("Enter Restaurant name");
			String name=sc.nextLine();
			System.out.println("Enter Cuisine Type ");
			String cuisine=sc.nextLine();
			System.out.println("Enter Delivery Time ");
			int time=sc.nextInt();
			sc.nextLine();
			System.out.println("Enter Address ");
			String address=sc.nextLine();
			System.out.println("Enter ratings ");
			float ratings=sc.nextFloat();
			sc.nextLine();
			System.out.println("Enter isActive status");
			boolean isActive=sc.nextBoolean();
//			Restaurant r=new Restaurant(restaurantId, name, cuisine, time, address, ratings, isActive);
//			System.out.println(rdi.insert(r)==1?"Inserted successfully":"failed to insert");
//			break;
		case 2:
			System.out.println(rdi.fetchAll()!=null?"Here is the details":"no data present");
			for(Restaurant res:rdi.fetchAll()) {
				System.out.println(res);
			}
			break;
		case 3:
			System.out.println("Enter the restaurantId");
			restaurantId=sc.nextInt();
			System.out.println(rdi.fetchSpecific(restaurantId)!=null?"here are the details "+rdi.fetchSpecific(restaurantId):"No Restaurant found");
		case 4:
			System.out.println("Enter restaurantId");
			restaurantId=sc.nextInt();
			sc.nextLine();
			System.out.println("Enter new Addres");
			address=sc.nextLine();
			System.out.println(rdi.update(restaurantId,address)==1?"Sucessfully updated":"Failed to update");
			break;
		case 5:
			System.out.println("Enter restaurantId");
			restaurantId=sc.nextInt();
			System.out.println(rdi.delete(restaurantId)==1?"deleted sucessfully":"failed to delete");
			break;
			
			
			
		}
			
	}
}
