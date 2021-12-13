package com.java.hotel_palacius.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cart {
	private List<MenuItem> menuItemList;
	private double total;

	public Cart() {
		this.menuItemList = new ArrayList<MenuItem>();
		this.total = 0.0;
	}

	public List<MenuItem> getMenuItemList() {
		return menuItemList;
	}

	public void setMenuItemList(List<MenuItem> menuItemList) {
		this.menuItemList = menuItemList;
	}

	public double getTotal() {
		return this.total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	@Override
	public int hashCode() {
		return Objects.hash(menuItemList);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cart other = (Cart) obj;
		return Objects.equals(menuItemList, other.menuItemList);
	}

	@Override
	public String toString() {
		return "ID \tName \t\t\tPrice \tActive \t\tDate of Launch \t\tCategory \tFree Delivery\n" + menuItemList
				+ "\nTotal Price: " + total + "\n]";
	}

}
