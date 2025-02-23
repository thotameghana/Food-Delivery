package com.foodapp.Controller;

import java.io.IOException;
import java.text.SimpleDateFormat;

import com.foodapp.dao.OrderHistoryDAO;
import com.foodapp.daoimpl.OrderHistoryDAOImpl;
import com.foodapp.daoimpl.OrderItemDAOImpl;
import com.foodapp.daoimpl.OrdersDAOImpl;
import com.foodapp.model.CartItem;
import com.foodapp.model.OrderHistory;
import com.foodapp.model.OrderItem;
import com.foodapp.model.Orders;
import com.foodapp.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


public class Checkout extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		User u=(User) session.getAttribute("user");
		if(u==null) {
			resp.sendRedirect("login.jsp");
			return;
		}
		Cart cart=(Cart) session.getAttribute("cart");
		if(cart==null || cart.getAllItems().isEmpty()) {
			resp.sendRedirect("MenuServlet?restaurantId="+session.getAttribute("restaurantId"));
			return;
		}
		//for Orders
		int userId=u.getUserId();
		int restaurantId=(int) session.getAttribute("restaurantId");
		int totalAmount=0;
		for(CartItem ci:cart.getAllItems().values()) {
			totalAmount+=ci.getQuantity()*ci.getPrice();
		}	
		
		String status="pending";
		String paymentMode=req.getParameter("payment");
		
		Orders o=new Orders(userId, restaurantId, totalAmount, status, paymentMode);
		
		OrdersDAOImpl orderDaoImpl=new OrdersDAOImpl();
		
		if (orderDaoImpl.insert(o)==-1)
		{
	        // If order insertion fails, redirect with error message
            resp.sendRedirect("checkout.jsp?error=Order failed. Please try again.");
            return;
	    }
		
		
		//for OrderItem
		int orderId=orderDaoImpl.fetchByRestaurantId(restaurantId).getOrderId();
		
//		System.out.println(orderId);
		
		for(CartItem item:cart.getAllItems().values()) {
			int menuId=item.getCartItemId();
			int quantity=item.getQuantity();
			int itemTotal=item.getPrice()*item.getQuantity();
			
			OrderItem orderItem=new OrderItem(orderId, menuId, quantity, itemTotal);
			
			OrderItemDAOImpl orderItemDaoImpl=new OrderItemDAOImpl();
			orderItemDaoImpl.insert(orderItem);
		}
		
		//for order history
		status="Delivered";
		OrderHistory orderHistory=new OrderHistory(orderId, userId, totalAmount, status);
		
		OrderHistoryDAO orderHistoryDaoImpl=new OrderHistoryDAOImpl();
		orderHistoryDaoImpl.insert(orderHistory);
		
		cart.clear();
		session.removeAttribute("cart");
		
		resp.sendRedirect("orderConfirmation.jsp");
		
	}
}
