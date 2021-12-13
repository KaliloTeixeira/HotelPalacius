# Create Hotel Database
CREATE DATABASE hotel_palacius;
USE hotel_palacius;

# Create Table MenuItem
CREATE TABLE menu_items (
	id INTEGER NOT NULL,
    name VARCHAR(50) NOT NULL,
    price DECIMAL NOT NULL,
    active BOOLEAN NOT NULL,
    dateOfLaunch DATE NOT NULL,
    category VARCHAR(50) NOT NULL,
    freeDelivery BOOLEAN NOT NULL,
    PRIMARY KEY(id)
);

# Create Table cart_items
CREATE TABLE cart_items (
	cart_items_id INT NOT NULL AUTO_INCREMENT,
    user_id INT NOT NULL,
    menu_item_id INT NOT NULL,
    PRIMARY KEY(cart_items_id)
);