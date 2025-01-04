package com.foodapp.main;

import java.util.Scanner;

import com.foodapp.daoimpl.MenuDAOImpl;
import com.foodapp.model.Menu;

public class LaunchMenu {
	static Scanner sc=new Scanner(System.in);
	
	public static void main(String[] args) {
		MenuDAOImpl mdi=new MenuDAOImpl();
		System.out.println("Enter your choice\n"
				+ "1.insert new Menu\n"
				+"2.fetch all Menu items\n"
				+"3.fetech specific Menu\n"
				+"4.update Menu\n"
				+"5.delete Menu\n");
		int choice=sc.nextInt();
		switch(choice) {
		case 1:
			System.out.println("Enter MenuId");
			int menuId=sc.nextInt();
			System.out.println("Enter RestaurantId");
			int restaurantId=sc.nextInt();
			sc.nextLine();
			System.out.println("Enter Name");
			String name=sc.nextLine();
			System.out.println("Enter description");
			String description= sc.nextLine();
			System.out.println("Enter price");
			int price=sc.nextInt();
			System.out.println("Enter isAvailable or not");
			boolean isAvailable=sc.nextBoolean();
//			Menu menu =new Menu(menuId,restaurantId,name, description, price, isAvailable);
//			System.out.println(mdi.insert(menu)>=1?"Sucessully inserted":"FAiled to insert");
			break;
		case 2:
			System.out.println("Here are the details");
			for(Menu m:mdi.fetchAll()) {
				System.out.println(m);
			}
			break;
		case 3:
			System.out.println("Eneter menuId");
			menuId=sc.nextInt();
			System.out.println(mdi.fetchSpecific(menuId)!=null?mdi.fetchSpecific(menuId):"User not found");
			break;
		case 4:
		
			System.out.println("Enter menuId");
			menuId=sc.nextInt();
			sc.nextLine();
			System.out.println("Enter name");
			name=sc.nextLine();
			System.out.println(mdi.update(menuId,name)==1?"Sucessfully Updated":"Failed to update");
			break;
		case 5:
			System.out.println("Enter menuId");
			menuId=sc.nextInt();
			System.out.println(mdi.delete(menuId)==1?"sucessfully deleted":"failed to delete");
			
		}
	}
}
