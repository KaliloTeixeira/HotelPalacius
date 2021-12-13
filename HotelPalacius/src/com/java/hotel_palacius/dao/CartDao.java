package com.java.hotel_palacius.dao;

import com.java.hotel_palacius.exceptions.CartEmptyException;
import com.java.hotel_palacius.model.Cart;

public interface CartDao {

	public void addCartItem(long userId, long menuItemId);

	public Cart getAllCartItems(long userId) throws CartEmptyException;

	public void removeCartItem(long userId, long menuItemId);

}
