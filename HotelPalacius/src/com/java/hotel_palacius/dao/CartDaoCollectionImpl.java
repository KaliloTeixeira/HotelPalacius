package com.java.hotel_palacius.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.java.hotel_palacius.exceptions.CartEmptyException;
import com.java.hotel_palacius.model.Cart;
import com.java.hotel_palacius.model.MenuItem;

public class CartDaoCollectionImpl implements CartDao {
	private HashMap<Long, Cart> userCarts;

	public CartDaoCollectionImpl() {
		if (userCarts == null) {
			userCarts = new HashMap<Long, Cart>();
		}
	}

	@Override
	public void addCartItem(long userId, long menuItemId) {
		List<MenuItem> menuItemList;

		MenuItemDao menuItemDao = new MenuItemDaoCollectionImpl();
		MenuItem menuItem = menuItemDao.getMenuItem(menuItemId);

		if (userCarts.containsKey(userId)) {
			menuItemList = userCarts.get(userId).getMenuItemList();
			menuItemList.add(menuItem);
		} else {
			Cart newCart = new Cart();
			menuItemList = new ArrayList<MenuItem>();
			menuItemList.add(menuItem);
			newCart.setMenuItemList(menuItemList);
			userCarts.put(userId, newCart);
		}

		System.out.println("Item Added to Cart.");

	}

	@Override
	public Cart getAllCartItems(long userId) throws CartEmptyException {
		Cart cart = userCarts.get(userId);

		if (cart == null || cart.getMenuItemList().size() == 0) {
			throw new CartEmptyException();
		}

		double total = 0.0;

		for (MenuItem menuItem : cart.getMenuItemList())
			total += menuItem.getPrice();

		userCarts.get(userId).setTotal(total);

		return cart;
	}

	@Override
	public void removeCartItem(long userId, long menuItemId) {
		List<MenuItem> menuItemList = userCarts.get(userId).getMenuItemList();

		for (MenuItem menuItem : menuItemList) {
			if (menuItem.getId() == menuItemId) {
				menuItemList.remove(menuItem);
				System.out.println("Item removed from Cart.");
				return;
			}
		}

	}

}
