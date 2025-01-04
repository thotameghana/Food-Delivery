package com.foodapp.main;

import java.util.Scanner;

import com.foodapp.daoimpl.UserDAOImpl;
import com.foodapp.model.User;

public class LaunchUser {
	static Scanner sc=new Scanner(System.in);
	public static void main(String[] args) {
		UserDAOImpl udi=new UserDAOImpl();
		System.out.println("Enter your choice\n"
				+ "1.insert new user\n"
				+"2.fetch all users\n"
				+"3.fetech specific user\n"
				+"4.update user\n"
				+"5.delete user\n");
		int choice=sc.nextInt();
		switch(choice) {
		case 1:
			System.out.println("Enter userId :");
			int userId=sc.nextInt();
			sc.nextLine();
			System.out.println("Enter userName: ");
			String userName=sc.nextLine();
			System.out.println("Enter password: ");
			String password=sc.nextLine();
			System.out.println("Enter email: ");
			String email=sc.nextLine();
			System.out.println("Enter address: ");
			String address=sc.nextLine();
			User u=new User(userName,password,email,address);
			System.out.println(udi.insert(u)==1?"Inserted succesfully":"Failed to insert");
			break;
		case 2:
			System.out.println("These are the list of Users");
			for(User user:udi.fetchAll()) {
				System.out.println(user);
			}
			break;
		case 3:
			System.out.println("Enter userId");
			userId=sc.nextInt();
			System.out.println("Here are the details \n"+udi.fetchSpecificUser(userId));
			break;
		case 4:
			System.out.println("Enter userId to whom you want to update");
			userId=sc.nextInt();
			sc.nextLine();
			System.out.println("enter address");
			address=sc.nextLine();
			System.out.println(udi.update(userId,address)==1?"Sucessfully updated":"Failed to update");
			break;
		case 5:
			System.out.println("Enter userId:");
			userId=sc.nextInt();
			System.out.println(udi.delete(userId)==1?"Sucessfully deleted":"failed to delte");

		}
		
	}

}
