# List all items from an user
SELECT user_id, menu_item_id, name, price, active, dateOfLaunch, category, freeDelivery 
FROM cart_items 
LEFT JOIN menu_items 
ON menu_item_id = menu_items.id 
WHERE user_id = '2'; 

# List all menu items
SELECT * FROM menu_items;

# List available menu items
SELECT * FROM menu_items 
WHERE active = true AND dateOfLaunch > NOW(); 

# Get Item
SELECT * FROM menu_items 
WHERE menu_items.id = '1';

# Remove Item
DELETE FROM menu_items 
WHERE menu_items.id = '1';