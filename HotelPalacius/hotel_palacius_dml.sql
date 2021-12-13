use hotel_palacius;

# Insert Data Into Menu Items
INSERT INTO menu_items VALUES
	('001', 'Sandwich', '99.00', true, '2018-03-15', 'Main Course', true),
	('002', 'Burguer', '129.00', true, '2022-12-23', 'Main Course', false),
	('003', 'Pizza', '149.00', true, '2023-08-21', 'Main Course', false),
	('004', 'French Fries', '57.00', false, '2017-07-02', 'Starters', false),
	('005', 'Chocolate Brownie', '32.00', true, '2022-11-02', 'Dessert', true)
;

# Insert Data Into Cart
INSERT INTO cart_items (user_id, menu_item_id) VALUES
	('1','2'),
    ('1','3'),
	('2','3'),
    ('2','4'),
    ('2','5')
;
