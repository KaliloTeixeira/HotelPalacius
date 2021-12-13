package com.java.hotel_palacius.dao;

import java.util.List;

import com.java.hotel_palacius.model.MenuItem;

public interface MenuItemDao {

	public List<MenuItem> getMenuItemListAdmin();

	public List<MenuItem> getMenuItemListCustomer();

	public void addMenuItem(MenuItem menuItem);

	public void modifyMenuItem(MenuItem menuItem);

	public MenuItem getMenuItem(long menuItemId);

	public void removeMenuItem(long menuItemId);
}
