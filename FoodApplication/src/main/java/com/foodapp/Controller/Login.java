package com.foodapp.Controller;

import java.io.IOException;

import com.foodapp.daoimpl.UserDAOImpl;
import com.foodapp.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class Login extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email=req.getParameter("email");
		String password=req.getParameter("password");
		if(email!=null && password!=null) {
			email=SecureData.encrypt(email);
			password=SecureData.encrypt(password);
		}
		
		UserDAOImpl udi=new UserDAOImpl();
		User u=udi.fetchSpecificEmail(email);
		if(u!=null) {
			if(email.equals(u.getEmail())) {
				if(password.equals(u.getPassword())) {
					User user=new User(u.getUserId() ,SecureData.decrypt(u.getUserName()), 
							SecureData.decrypt(u.getPassword()), SecureData.decrypt(u.getEmail()), 
							SecureData.decrypt(u.getAddress()));
					HttpSession session=req.getSession();
					session.setAttribute("user", user);
					System.out.println(session.getAttribute("user"));
					resp.sendRedirect("GetAllRestaurants");
				}
				else {
					resp.getWriter().println("Incorrect Password");
				}
	
			}
			else {
				resp.getWriter().println("No user found with email");
			}	
		}
		else {
			resp.getWriter().println("Invalid user name");
		}
	}
}
