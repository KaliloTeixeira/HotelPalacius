package com.java.hotel_palacius.dao;

import java.util.List;

import com.java.hotel_palacius.model.MenuItem;

public class MenuItemDaoSqlImplTest {
	public static void main(String[] args) {
		testGetMenuItemListAdmin();
	}

	public static void testGetMenuItemListAdmin() {
		MenuItemDao menuItemDao = new MenuItemDaoSqlImpl();

		List<MenuItem> menuItemList = menuItemDao.getMenuItemListAdmin();

		for (MenuItem menuItem : menuItemList) {
			System.out.println(menuItem.toString());
		}

	}
}
