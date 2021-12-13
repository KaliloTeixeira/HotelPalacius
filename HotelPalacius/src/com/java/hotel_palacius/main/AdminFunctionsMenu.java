package com.java.hotel_palacius.main;

import java.util.List;
import java.util.Scanner;

import com.java.hotel_palacius.dao.MenuItemDao;
import com.java.hotel_palacius.model.MenuItem;
import com.java.hotel_palacius.util.DateUtil;

public class AdminFunctionsMenu {
	public static void showAdminMenu(MenuItemDao menuItemDao) {
		Scanner read = new Scanner(System.in);
		int menuOption;

		long tempUserId, tempMenuItemId;

		String tempName, tempCategory, tempDateOfLaunch;
		float tempPrice;
		boolean tempIsActive, tempFreeDelivery;

		List<MenuItem> tempMenuItemList;

		menuOption = read.nextInt();
		switch (menuOption) {
		case 1:
			// List All Menu Items
			System.out.println("ID \tName \t\t\tPrice \tActive \t\tDate of Launch \t\tCategory \tFree Delivery\n");
			tempMenuItemList = menuItemDao.getMenuItemListAdmin();
			tempMenuItemList.forEach(menuItem -> System.out.println(menuItem.toString()));
			break;

		case 2:
			// List Available Menu Items
			System.out.println("ID \tName \t\t\tPrice \tActive \t\tDate of Launch \t\tCategory \tFree Delivery\n");
			tempMenuItemList = menuItemDao.getMenuItemListCustomer();
			tempMenuItemList.forEach(menuItem -> System.out.println(menuItem.toString()));
			break;

		case 3:
			// Get a specific Menu Item by ID
			System.out.print("Write the Menu Item ID: ");
			tempMenuItemId = read.nextLong();
			System.out.println();
			System.out.println("ID \tName \t\t\tPrice \tActive \t\tDate of Launch \t\tCategory \tFree Delivery\n");
			System.out.println(menuItemDao.getMenuItem(tempMenuItemId).toString());
			break;

		case 4:
			// Add New Menu Item
			System.out.print("Write the Menu Item ID: ");
			tempMenuItemId = read.nextInt();
			read.nextLine();

			System.out.printf("New Name: ");
			tempName = read.nextLine();

			System.out.print("New Category: ");
			tempCategory = read.nextLine();

			System.out.print("New Price: ");
			tempPrice = Float.parseFloat(read.nextLine());

			System.out.print("New Date of Launch: ");
			tempDateOfLaunch = read.nextLine();

			System.out.println("Is Active (true/false): ");
			tempIsActive = read.nextBoolean();

			System.out.println("Is Free Delivery (true/false): ");
			tempFreeDelivery = read.nextBoolean();

			menuItemDao.addMenuItem(new MenuItem(tempMenuItemId, tempName, tempPrice, tempIsActive,
					DateUtil.convertToDate(tempDateOfLaunch), tempCategory, tempFreeDelivery));

			break;

		case 5:
			// Edit a specific Menu Item by ID
			System.out.print("Write the Menu Item ID: ");
			tempMenuItemId = read.nextInt();
			read.nextLine();

			System.out.printf("New Name:");
			tempName = read.nextLine();

			System.out.print("New Category: ");
			tempCategory = read.nextLine();

			System.out.print("New Price: ");
			tempPrice = Float.parseFloat(read.nextLine());

			System.out.print("New Date of Launch: ");
			tempDateOfLaunch = read.nextLine();

			System.out.println("Is Active (true/false): ");
			tempIsActive = read.nextBoolean();

			System.out.println("Is Free Delivery (ture/false): ");
			tempFreeDelivery = read.nextBoolean();

			menuItemDao.modifyMenuItem(new MenuItem(tempMenuItemId, tempName, tempPrice, tempIsActive,
					DateUtil.convertToDate(tempDateOfLaunch), tempCategory, tempFreeDelivery));
			break;

		case 6:
			// Remove menu Item by ID
			System.out.print("Write the Menu Item ID: ");
			tempMenuItemId = read.nextInt();

			menuItemDao.removeMenuItem(tempMenuItemId);
			break;

		default:
			break;
		}
		read.nextLine();
		read.nextLine();
	}
}
