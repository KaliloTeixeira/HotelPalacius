package com.java.hotel_palacius.main;

import com.java.hotel_palacius.util.ConsoleUtil;

public final class Menu {

	public static void menu(int menuOption) {
		switch (menuOption) {
		case 0:
			ConsoleUtil.clearConsole();
			System.out.println("============ Hotel Palacius ============");
			System.out.println("1 - Login as Admin");
			System.out.println("2 - Login as Consumer");
			System.out.println("0 - Exit\n");
			System.out.print("Select an option: ");
			break;

		case 1:
			ConsoleUtil.clearConsole();
			System.out.println("============ Hotel Palacius ============");
			System.out.println("============ Loged as Admin ============");
			System.out.println("1 - List All Menu Items");
			System.out.println("2 - List Available Menu Items");
			System.out.println("3 - Get Menu Item");
			System.out.println("4 - Add Menu Item");
			System.out.println("5 - Modify Menu Item");
			System.out.println("6 - Remove Menu Item");
			System.out.println("0 - Exit\n");
			System.out.print("Select an option: ");
			break;

		case 2:
			ConsoleUtil.clearConsole();
			System.out.println("============ Hotel Palacius ============");
			System.out.println("============ Loged as Consumer ============");
			System.out.println("1 - List All Menu Items");
			System.out.println("2 - Check My Cart");
			System.out.println("3 - Add to Cart");
			System.out.println("4 - Remove from Cart");
			System.out.println("0 - Exit\n");
			System.out.print("Select an option: ");
			break;

		default:
			System.out.println("Bye!");
			break;
		}

	}
}
