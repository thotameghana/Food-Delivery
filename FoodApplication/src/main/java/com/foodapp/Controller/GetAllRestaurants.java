package com.foodapp.Controller;

import java.io.IOException;
import java.util.ArrayList;

import com.foodapp.daoimpl.RestaurantDAOImpl;
import com.foodapp.model.Restaurant;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class GetAllRestaurants extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RestaurantDAOImpl rdi=new RestaurantDAOImpl();
		ArrayList<Restaurant> restaurants=rdi.fetchAll();
		HttpSession session=req.getSession();
		session.setAttribute("restaurants", restaurants);
		resp.sendRedirect("home.jsp");
		
	}
}
