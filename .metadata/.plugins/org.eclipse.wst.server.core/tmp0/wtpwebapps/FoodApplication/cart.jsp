<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.foodapp.Controller.Cart,com.foodapp.model.CartItem" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">    
<title>Shopping Cart</title>
<link rel="stylesheet" href="./styles/cart.css">
</head>
<body>
    <div class="container">
        <h1>Shopping Cart</h1>
        <div class="cart-items">
            <%               
                Cart cart = (Cart) session.getAttribute("cart");
                if (cart == null || cart.getAllItems().isEmpty()) {
            %>
            <div class="empty-cart">
                <p>Nothing is there in the cart</p>
                <p><a href="MenuServlet?restaurantId=<%= session.getAttribute("restaurantId") %>" style="text-decoration:none">Click here</a> to surf into the menu</p>
            </div>
            <%
                } else {
                    long totalAmount = 0;
                    for (CartItem cartItem : cart.getAllItems().values()) {
                    	int itemTotal = cartItem.getQuantity() * cartItem.getPrice();
                        totalAmount += itemTotal;
            %>
            <div class="cart-item">
                <img src="<%=cartItem.getImagePath()%>" alt="<%= cartItem.getName() %>" class="item-image">
                <div class="item-details">
                    <div class="item-name"><%= cartItem.getName() %></div>
                    <div class="item-price">Price: <%= cartItem.getPrice() %></div>
                    <div class="item-total">Total: <%=itemTotal%></div>
                </div>
                <div class="item-quantity">
                    <form action="CartServlet" method="post">
                        <input type="hidden" name="action" value="update">
                        <input type="hidden" name="itemId" value="<%= cartItem.getCartItemId() %>">
                        <button type="submit" name="quantity" value="<%= cartItem.getQuantity() - 1 %>" class="quantity-btn">-</button>
                        <span><%= cartItem.getQuantity() %></span>
                        <button type="submit" name="quantity" value="<%= cartItem.getQuantity() + 1 %>" class="quantity-btn">+</button>
                    </form>
                </div>
                <form action="CartServlet" method="post">
                    <input type="hidden" name="action" value="remove">
                    <input type="hidden" name="itemId" value="<%= cartItem.getCartItemId() %>">
                    <button type="submit" class="remove-btn" aria-label="Remove item">Remove</button>
                </form>
            </div>
            <%
                    }
            %>
            <div class="total-price">
                <h3>Total Amount: <%= totalAmount %></h3>
            </div>
            <%
                }
            %>
        </div>
        <div class="cart-summary">
            <div class="cart-buttons">
                <form action="CartServlet" method="post">
                    <input type="hidden" name="action" value="clear">
                    <button type="submit" class="cart-btn clear-cart-btn">Clear Cart</button>
                </form>
                <a href="MenuServlet?restaurantId=<%= session.getAttribute("restaurantId") %>" style="text-decoration:none" class="cart-btn add-items-btn">Add More Items</a>
                <a href="checkout.jsp"><button class="cart-btn checkout-btn">Proceed to Checkout</button></a>
            </div>
        </div>
    </div>
</body>
</html>
