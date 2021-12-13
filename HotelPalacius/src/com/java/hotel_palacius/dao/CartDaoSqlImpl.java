package com.java.hotel_palacius.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.java.hotel_palacius.exceptions.CartEmptyException;
import com.java.hotel_palacius.model.Cart;
import com.java.hotel_palacius.model.MenuItem;
import com.java.hotel_palacius.util.DateUtil;

public class CartDaoSqlImpl implements CartDao {
	private Statement stmt;
	private Connection connection;
	private PreparedStatement prepStmt;
	private ResultSet rs;

	public CartDaoSqlImpl() {
		connectToDataBase();
	}

	public void connectToDataBase() {
		String dbUrl = "jdbc:mysql://localhost:3306/hotel_palacius";
		String user = "root";
		String password = "admin";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(dbUrl, user, password);
			this.stmt = connection.createStatement();
			System.out.println("Database connected.");
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
	public void addCartItem(long userId, long menuItemId) {
		String preparedString = "INSERT INTO cart_items (user_id, menu_item_id) VALUES (?, ?)";
		try {
			prepStmt = connection.prepareStatement(preparedString);
			prepStmt.setLong(1, userId);
			prepStmt.setLong(2, menuItemId);

			if (prepStmt.executeUpdate() == 1)
				System.out.println("Item Added to Cart.");

		} catch (SQLException e) {
			System.out.println("ERROR: Item not inserted, try again.");
		}

	}

	@Override
	public Cart getAllCartItems(long userId) throws CartEmptyException {
		Cart cart = new Cart();
		List<MenuItem> menuItemList = new ArrayList<MenuItem>();

		String preparedString = "SELECT menu_item_id, name, price, active, dateOfLaunch, category, freeDelivery "
				+ "FROM cart_items " + "LEFT JOIN menu_items " + "ON menu_item_id = menu_items.id "
				+ "WHERE user_id = ?";

		try {
			prepStmt = connection.prepareStatement(preparedString);
			prepStmt.setLong(1, userId);

			rs = prepStmt.executeQuery();

			while (rs.next()) {
				menuItemList.add(new MenuItem(rs.getInt("menu_item_id"), rs.getString("name"), rs.getFloat("price"),
						rs.getBoolean("active"), DateUtil.convertToDateFromSql(rs.getString("dateOfLaunch")),
						rs.getString("category"), rs.getBoolean("freeDelivery")));
			}
			cart.setMenuItemList(menuItemList);

			double total = 0.0;
			for (MenuItem menuItem : cart.getMenuItemList())
				total += menuItem.getPrice();

			cart.setTotal(total);

		} catch (SQLException e) {
			e.getMessage();
		}

		return cart;
	}

	@Override
	public void removeCartItem(long userId, long menuItemId) {
		String preparedQuery = "DELETE FROM cart_items WHERE user_id = ? AND menu_item_id = ? LIMIT 1";

		try {
			prepStmt = connection.prepareStatement(preparedQuery);
			prepStmt.setLong(1, userId);
			prepStmt.setLong(2, menuItemId);

			if (prepStmt.executeUpdate() == 1)
				System.out.println("Item deleted.");

		} catch (SQLException e) {
			System.out.println("ERROR: Menu Item not deleted, try again.");
		}

	}

}
