package com.java.hotel_palacius.dao;

import com.java.hotel_palacius.exceptions.CartEmptyException;
import com.java.hotel_palacius.model.Cart;

public class CartDaoCollectionImplTest {
	public static void main(String[] args) {
		testAddCartItem();
		System.out.println();
		testRemoveCartItem();
	}

	private static void testAddCartItem() {
		CartDao cartDao = new CartDaoSqlImpl();
		Cart cart;

		cartDao.addCartItem(1, 2);
		cartDao.addCartItem(1, 3);
		cartDao.addCartItem(1, 5);

		try {
			cart = cartDao.getAllCartItems(1);
			System.out.println(cart);
		} catch (CartEmptyException e) {
			e.printStackTrace();
		}
	}

	private static void testRemoveCartItem() {
		CartDao cartDao = new CartDaoCollectionImpl();
		Cart cart;

		cartDao.addCartItem(1, 2);
		cartDao.addCartItem(1, 3);
		cartDao.addCartItem(1, 5);

		cartDao.removeCartItem(1, 3);

		try {
			cart = cartDao.getAllCartItems(1);
			System.out.println(cart);
		} catch (CartEmptyException e) {
			e.printStackTrace();
		}

	}

}