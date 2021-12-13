package com.java.hotel_palacius.main;

import java.util.List;
import java.util.Scanner;

import com.java.hotel_palacius.dao.CartDao;
import com.java.hotel_palacius.dao.MenuItemDao;
import com.java.hotel_palacius.exceptions.CartEmptyException;
import com.java.hotel_palacius.model.Cart;
import com.java.hotel_palacius.model.MenuItem;

public class CustomerFunctionsMenu {
	public static void showCustomerMenu(MenuItemDao menuItemDao, CartDao cartDao) {
		Scanner read = new Scanner(System.in);
		int menuOption;
		long tempUserId, tempMenuItemId;

		List<MenuItem> tempMenuItemList;
		Cart cart;

		menuOption = read.nextInt();
		switch (menuOption) {
		case 1:
			// List all Menu Items
			System.out.println("ID \tName \t\t\tPrice \tActive \t\tDate of Launch \t\tCategory \tFree Delivery\n");
			tempMenuItemList = menuItemDao.getMenuItemListCustomer();
			tempMenuItemList.forEach(menuItem -> System.out.println(menuItem.toString()));
			break;

		case 2:
			// Check my Cart
			System.out.print("UserID: ");
			tempUserId = read.nextLong();
			System.out.println("\n----------- Cart - User " + tempUserId + " -----------\n");
			try {
				cart = cartDao.getAllCartItems(tempUserId);
				tempMenuItemList = cart.getMenuItemList();
				tempMenuItemList.forEach(menuItem -> System.out.println(menuItem.toString()));
				System.out.println("\nTotal Price: " + cart.getTotal());
			} catch (CartEmptyException e) {
				System.out.println("Cart Empty.");
			}
			break;

		case 3:
			// Add Item to Cart
			System.out.print("User ID: ");
			tempUserId = read.nextLong();
			System.out.print("Menu Item ID: ");
			tempMenuItemId = read.nextLong();

			cartDao.addCartItem(tempUserId, tempMenuItemId);
			break;

		case 4:
			// Remove Item from Cart
			System.out.print("User ID: ");
			tempUserId = read.nextLong();
			System.out.print("Menu Item ID: ");
			tempMenuItemId = read.nextLong();

			cartDao.removeCartItem(tempUserId, tempMenuItemId);
			break;

		default:
			break;
		}

		read.nextLine();
		read.nextLine();
	}
}
