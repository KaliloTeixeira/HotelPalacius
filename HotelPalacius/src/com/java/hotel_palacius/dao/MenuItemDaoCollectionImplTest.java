package com.java.hotel_palacius.dao;

import java.util.List;

import com.java.hotel_palacius.model.MenuItem;
import com.java.hotel_palacius.util.DateUtil;

public class MenuItemDaoCollectionImplTest {
	public static void main(String[] args) {
		testGetMenuItemListAdmin();
		System.out.println();
		testGetMenuItemListCustomer();
		System.out.println();
		testModifyMenuItem();
	}

	public static void testGetMenuItemListAdmin() {
		MenuItemDao menuItemDao = new MenuItemDaoCollectionImpl();

		List<MenuItem> menuItemList = menuItemDao.getMenuItemListAdmin();

		for (MenuItem menuItem : menuItemList) {
			System.out.println(menuItem.toString());
		}

	}

	public static void testGetMenuItemListCustomer() {
		MenuItemDao menuItemDao = new MenuItemDaoCollectionImpl();

		List<MenuItem> menuItemList = menuItemDao.getMenuItemListCustomer();

		for (MenuItem menuItem : menuItemList) {
			System.out.println(menuItem.toString());
		}
	}

	private static void testModifyMenuItem() {
		MenuItem testMenuItem = new MenuItem(005, "Coconout Brownie", 32.00f, true,
				DateUtil.convertToDate("02/11/2022"), "Dessert", true);

		MenuItemDao menuItemDao = new MenuItemDaoCollectionImpl();
		try {
			menuItemDao.modifyMenuItem(testMenuItem);
		} catch (Exception e) {
			e.getMessage();
		}
		System.out.println(menuItemDao.getMenuItem(5));

	}

}
