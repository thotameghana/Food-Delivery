package com.foodapp.model;

public class CartItem {
	private int cartItemId;
	private int restaurantId;
	private String name;
	private int quantity;
	private int price;
	private String imagePath;
	public CartItem() {
	}
	public CartItem(int cartItemId, int restaurantId, String name, int quantity, int price, String imagePath) {
		super();
		this.cartItemId = cartItemId;
		this.restaurantId = restaurantId;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
		this.imagePath=imagePath;
	}
	public int getCartItemId() {
		return cartItemId;
	}
	public void setCartItemId(int cartItemId) {
		this.cartItemId = cartItemId;
	}
	public int getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	@Override
	public String toString() {
		return cartItemId+"         "+restaurantId+"       "+name+"         "+quantity+"      "+price;
	}
	
}
