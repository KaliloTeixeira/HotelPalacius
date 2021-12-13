package com.java.hotel_palacius.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.java.hotel_palacius.model.MenuItem;
import com.java.hotel_palacius.util.DateUtil;

public class MenuItemDaoSqlImpl implements MenuItemDao {
	private Statement stmt;
	private Connection connection;
	private PreparedStatement prepStmt;
	private ResultSet rs;

	public MenuItemDaoSqlImpl() {
		this.connectToDataBase();
	}

	public void connectToDataBase() {
		String dbUrl = "jdbc:mysql://localhost:3306/hotel_palacius";
		String user = "root";
		String password = "admin";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(dbUrl, user, password);
			this.stmt = connection.createStatement();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("ERROR: Database not connected.");
		}

	}

	public void closeConnectionToDataBase() {
		try {
			stmt.close();
			prepStmt.close();
			connection.close();
		} catch (SQLException e) {
			e.getMessage();
		}
	}

	@Override
	public List<MenuItem> getMenuItemListAdmin() {
		List<MenuItem> menuItemList = new ArrayList<MenuItem>();
		try {
			rs = stmt.executeQuery("SELECT * FROM hotel_palacius.menu_items");

			while (rs.next()) {
				menuItemList.add(new MenuItem(rs.getInt("id"), rs.getString("name"), rs.getFloat("price"),
						rs.getBoolean("active"), DateUtil.convertToDateFromSql(rs.getString("dateOfLaunch")),
						rs.getString("category"), rs.getBoolean("freeDelivery")));
			}

		} catch (SQLException e) {
			e.getMessage();
		}

		return menuItemList;
	}

	@Override
	public List<MenuItem> getMenuItemListCustomer() {
		List<MenuItem> menuItemList = new ArrayList<MenuItem>();
		try {
			rs = stmt.executeQuery(
					"SELECT * FROM hotel_palacius.menu_items " + "WHERE active = true AND dateOfLaunch > NOW()");

			while (rs.next()) {
				menuItemList.add(new MenuItem(rs.getInt("id"), rs.getString("name"), rs.getFloat("price"),
						rs.getBoolean("active"), DateUtil.convertToDateFromSql(rs.getString("dateOfLaunch")),
						rs.getString("category"), rs.getBoolean("freeDelivery")));
			}

		} catch (SQLException e) {
			e.getMessage();
		}

		return menuItemList;
	}

	@Override
	public void addMenuItem(MenuItem menuItem) {
		String preparedQuery = "INSERT INTO menu_items VALUES (?, ?, ?, ?, ?, ?, ?)";

		java.sql.Date sqlDate = new java.sql.Date(menuItem.getDateOfLaunch().getTime());

		try {
			prepStmt = connection.prepareStatement(preparedQuery);
			prepStmt.setLong(1, menuItem.getId());
			prepStmt.setString(2, menuItem.getName());
			prepStmt.setFloat(3, menuItem.getPrice());
			prepStmt.setBoolean(4, menuItem.isActive());
			prepStmt.setDate(5, sqlDate);
			prepStmt.setString(6, menuItem.getCategory());
			prepStmt.setBoolean(7, menuItem.isFreeDelivery());

			if (prepStmt.executeUpdate() == 1)
				System.out.println("Item Added to Cart.");

		} catch (SQLException e) {
			System.out.println("ERROR: Menu Item not inserted, try again.");
		}
	}

	@Override
	public void modifyMenuItem(MenuItem menuItem) {
		String preparedQuery = "UPDATE menu_items SET " + "name = ?, " + "price = ?, " + "active = ?, "
				+ "dateOfLaunch = ?, " + "category = ?, " + "freeDelivery = ? " + "WHERE menu_items.id = ?";

		java.sql.Date sqlDate = new java.sql.Date(menuItem.getDateOfLaunch().getTime());

		try {
			prepStmt = connection.prepareStatement(preparedQuery);
			prepStmt.setString(1, menuItem.getName());
			prepStmt.setFloat(2, menuItem.getPrice());
			prepStmt.setBoolean(3, menuItem.isActive());
			prepStmt.setDate(4, sqlDate);
			prepStmt.setString(5, menuItem.getCategory());
			prepStmt.setBoolean(6, menuItem.isFreeDelivery());
			prepStmt.setLong(7, menuItem.getId());

			if (prepStmt.executeUpdate() == 1)
				System.out.println("Item modified.");

		} catch (SQLException e) {
			System.out.println("ERROR: Menu Item not modified, try again.");
		}

	}

	@Override
	public MenuItem getMenuItem(long menuItemId) {
		MenuItem menuItem = null;
		String preparedQuery = "SELECT * FROM menu_items WHERE menu_items.id = ? ";

		try {
			prepStmt = connection.prepareStatement(preparedQuery);
			prepStmt.setLong(1, menuItemId);
			rs = prepStmt.executeQuery();

			while (rs.next()) {
				menuItem = new MenuItem(rs.getInt("id"), rs.getString("name"), rs.getFloat("price"),
						rs.getBoolean("active"), DateUtil.convertToDateFromSql(rs.getString("dateOfLaunch")),
						rs.getString("category"), rs.getBoolean("freeDelivery"));
			}
		} catch (SQLException e) {
			System.out.println("ERROR: Menu Item doesnt exist.");
		}

		return menuItem;
	}

	@Override
	public void removeMenuItem(long menuItemId) {
		String preparedQuery = "DELETE FROM menu_items WHERE id = ? ";

		try {
			prepStmt = connection.prepareStatement(preparedQuery);
			prepStmt.setLong(1, menuItemId);

			if (prepStmt.executeUpdate() == 1)
				System.out.println("Item deleted.");

		} catch (SQLException e) {
			System.out.println("ERROR: Menu Item not deleted, try again.");
		}

	}

}
