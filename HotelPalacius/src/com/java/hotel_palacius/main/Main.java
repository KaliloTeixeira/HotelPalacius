package com.java.hotel_palacius.main;

import java.util.Scanner;

import com.java.hotel_palacius.dao.CartDao;
import com.java.hotel_palacius.dao.CartDaoSqlImpl;
import com.java.hotel_palacius.dao.MenuItemDao;
import com.java.hotel_palacius.dao.MenuItemDaoSqlImpl;

public class Main {
	public static void main(String[] args) {
		Scanner read = new Scanner(System.in);

		// Using MySql Implementation
		MenuItemDao menuItemDao = new MenuItemDaoSqlImpl();
		CartDao cartDao = new CartDaoSqlImpl();

		// Using Collection Implementation
		// MenuItemDao menuItemDao = new MenuItemDaoCollectionImpl();
		// CartDao cartDao = new CartDaoCollectionImpl();

		int menuOption;

		do {
			Menu.menu(0);
			menuOption = read.nextInt();

			switch (menuOption) {
			case 1:
				Menu.menu(menuOption);
				AdminFunctionsMenu.showAdminMenu(menuItemDao);
				break;
			case 2:
				Menu.menu(menuOption);
				CustomerFunctionsMenu.showCustomerMenu(menuItemDao, cartDao);
				break;

			default:
				Menu.menu(Integer.MAX_VALUE);
				break;
			}

		} while (menuOption != 0);

	}
}
