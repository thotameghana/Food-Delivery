package com.foodapp.Controller;

import java.io.IOException;
import java.util.ArrayList;

import com.foodapp.daoimpl.MenuDAOImpl;
import com.foodapp.daoimpl.RestaurantDAOImpl;
import com.foodapp.model.Menu;
import com.foodapp.model.Restaurant;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class MenuServlet extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MenuDAOImpl mdi=new MenuDAOImpl();
		RestaurantDAOImpl rdi=new RestaurantDAOImpl();

		HttpSession session=req.getSession();
		int restaurantId = Integer.parseInt(req.getParameter("restaurantId"));
		ArrayList<Menu> menu = mdi.fetchByRestaurantId(restaurantId);
		
		Restaurant restaurant = rdi.fetchSpecific(restaurantId);
		session.setAttribute("menu", menu);
		session.setAttribute("restaurant", restaurant);
		
		resp.sendRedirect("menu.jsp");
	}
}
