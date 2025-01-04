package com.foodapp.Controller;

import java.io.IOException;

import com.foodapp.dao.MenuDAO;
import com.foodapp.daoimpl.MenuDAOImpl;
import com.foodapp.model.CartItem;
import com.foodapp.model.Menu;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class CartServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	//get session for cart
    	HttpSession session=req.getSession();
        Cart cart=(Cart)session.getAttribute("cart");
        
        //if cart is not there create an object of cart and add to session
        if(cart==null) {
        	cart=new Cart();
        	session.setAttribute("cart", cart);
        }
        
        //fetch the parameters i.e request and itemId
        String action=req.getParameter("action");
        int itemId;
        
        MenuDAO menuDAO=new MenuDAOImpl();
        if(action==null && cart.getAllItems().isEmpty()) {
        	resp.sendRedirect("emptycart.jsp");
        	return;
        }
        else if(action==null && !(cart.getAllItems().isEmpty())) {
        	resp.sendRedirect("cart.jsp");
        	return;
        }
        //add item to the cart based on action
        else if(action.equals("add")) {
        	itemId=Integer.parseInt(req.getParameter("itemId"));
        	Menu menuItem = menuDAO.fetchSpecific(itemId);
			if(menuItem!=null) {
				int quantity=1;
				CartItem cartItem=new CartItem(menuItem.getMenuId(), menuItem.getRestaurantId(), menuItem.getName(), quantity, menuItem.getPrice(), menuItem.getImagePath());
				cart.addItem(cartItem);
			}
	        session.setAttribute("restaurantId", menuItem.getRestaurantId());
        }
        else if(action.equals("update")) {
        	  itemId=Integer.parseInt(req.getParameter("itemId"));
        	  int newQuantity = Integer.parseInt(req.getParameter("quantity"));
        	  cart.update(itemId, newQuantity);
        }
        else if(action.equals("remove")) {
        	itemId=Integer.parseInt(req.getParameter("itemId"));
        	cart.remove(itemId);
        }
        else if(action.equals("clear")) {
        	cart.clear();
        }
       
        session.setAttribute("cart", cart);
        resp.sendRedirect("cart.jsp");
    }
}


