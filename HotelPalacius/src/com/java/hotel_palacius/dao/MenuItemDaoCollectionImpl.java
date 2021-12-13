package com.java.hotel_palacius.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.java.hotel_palacius.model.MenuItem;
import com.java.hotel_palacius.util.DateUtil;

public class MenuItemDaoCollectionImpl implements MenuItemDao {
	private List<MenuItem> menuItemList;

	public MenuItemDaoCollectionImpl() {
		if (menuItemList == null) {
			menuItemList = iniciateMenuItems();
		}
	}

	private List<MenuItem> iniciateMenuItems() {
		menuItemList = new ArrayList<MenuItem>();
		menuItemList.add(
				new MenuItem(001, "Sandwich", 99.00f, true, DateUtil.convertToDate("15/03/2018"), "Main Course", true));

		menuItemList.add(new MenuItem(002, "Burguer", 129.00f, true, DateUtil.convertToDate("23/12/2022"),
				"Main Course", false));

		menuItemList.add(
				new MenuItem(003, "Pizza", 149.00f, true, DateUtil.convertToDate("21/08/2023"), "Main Course", false));

		menuItemList.add(new MenuItem(004, "French Fries", 57.00f, false, DateUtil.convertToDate("02/07/2017"),
				"Starters", false));

		menuItemList.add(new MenuItem(005, "Chocolate Brownie", 32.00f, true, DateUtil.convertToDate("02/11/2022"),
				"Dessert", true));

		return menuItemList;
	}

	@Override
	public List<MenuItem> getMenuItemListAdmin() {
		return menuItemList;
	}

	@Override
	public List<MenuItem> getMenuItemListCustomer() {
		List<MenuItem> menuItemListCustomer = new ArrayList<MenuItem>();

		for (MenuItem menuItem : this.menuItemList) {
			if (menuItem.getDateOfLaunch().after(new Date()) && menuItem.isActive()) {
				menuItemListCustomer.add(menuItem);
			}
		}

		return menuItemListCustomer;
	}

	@Override
	public void addMenuItem(MenuItem menuItem) {
		if (menuItemList.contains(menuItem)) {
			System.out.println("ERROR: Menu Item already exists. Try another ID.");
			return;
		}

		menuItemList.add(menuItem);
		System.out.println("Menu Item Added.");
	}

	@Override
	public void modifyMenuItem(MenuItem menuItem) {
		boolean isModified = false;

		for (MenuItem tempMenuItem : menuItemList) {
			if (tempMenuItem.equals(menuItem)) {
				tempMenuItem.setName(menuItem.getName());
				tempMenuItem.setPrice(menuItem.getPrice());
				tempMenuItem.setActive(menuItem.isActive());
				tempMenuItem.setDateOfLaunch(menuItem.getDateOfLaunch());
				tempMenuItem.setCategory(menuItem.getCategory());
				tempMenuItem.setFreeDelivery(menuItem.isFreeDelivery());
				isModified = true;
				System.out.println("Menu Item Modified.");
				break;
			}
		}

		if (!isModified)
			System.out.println("ERROR: We can't modify this Item. Try Again.");
	}

	@Override
	public MenuItem getMenuItem(long menuItemId) {
		MenuItem menuItem = null;

		for (MenuItem tempMenuItem : menuItemList) {
			if (tempMenuItem.getId() == menuItemId)
				menuItem = tempMenuItem;
		}

		return menuItem;
	}

	@Override
	public void removeMenuItem(long menuItemId) {
		MenuItem menuItem = this.getMenuItem(menuItemId);

		if (menuItem == null) {
			System.out.println("ERROR: Menu Item nof found.");
			return;
		}

		menuItemList.remove(menuItem);
		System.out.println("Menu Item Deleted.");
	}

}
