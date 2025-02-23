package com.foodapp.Controller;

import java.util.HashMap;
import java.util.Map;

import com.foodapp.model.CartItem;						

public class Cart{
	private Map<Integer,CartItem> items;
	public Cart() {
		items = new HashMap<>(); 
	}
	public void addItem(CartItem item) {
		int itemId=item.getCartItemId();
		if(items.containsKey(itemId)) {
			CartItem existingItem = items.get(itemId);
			existingItem.setQuantity(existingItem.getQuantity()+item.getQuantity());
		}
		else {
			items.put(itemId, item);
		}
	}
	public void update(int itemId,int quantity) {
		if(items.containsKey(itemId)) {
			if(quantity<=0) {
				items.remove(itemId);
			}
			else {
				CartItem existingItem = items.get(itemId);
				existingItem.setQuantity(quantity);
			}
		}
	}
	public Map<Integer,CartItem> getAllItems(){
		return items;
	}
	public void remove(int itemId) {
		items.remove(itemId);
	}
	public void clear() {
		items.clear();
	}
	 // Method to get the restaurant ID associated with the cart items
    public int getRestaurantId() {
        if (items.isEmpty()) {
            return 0;
        }
        return items.values().iterator().next().getRestaurantId();
    }
}
