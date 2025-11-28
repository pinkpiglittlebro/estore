-- Drop tables if exist
DROP TABLE IF EXISTS order_items;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS users;

-- USERS
CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255),
    full_name VARCHAR(255),
    address VARCHAR(255),
    city VARCHAR(255),
    postal VARCHAR(20),
    phone VARCHAR(50),
    credit_card VARCHAR(50),
    province VARCHAR(10),
    role VARCHAR(20) DEFAULT 'USER'
);

-- PRODUCTS
CREATE TABLE products (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    category VARCHAR(100),
    price DECIMAL(10,2) NOT NULL,
    image_url VARCHAR(255),
    description TEXT,
    inventory INT DEFAULT 10
);

-- ORDERS
CREATE TABLE orders (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    total DECIMAL(10,2),
    created_at VARCHAR(50),
    full_name VARCHAR(255),
    address VARCHAR(255),
    city VARCHAR(255),
    postal VARCHAR(20),
    phone VARCHAR(50)
);

-- ORDER ITEMS
CREATE TABLE order_items (
    id INT PRIMARY KEY AUTO_INCREMENT,
    order_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL,
    price DECIMAL(10,2) NOT NULL
);

-- INSERT USERS
INSERT INTO users 
(id, username, password, email, full_name, address, city, postal, phone, credit_card, province, role)
VALUES
(1, 'test', '123', 'test@gmail.com', '123', '', '', '', '', '', '', 'USER'),
(2, 'admin', 'admin123', 'admin@yorku.ca', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ADMIN');

-- INSERT PRODUCTS
INSERT INTO products 
(id, name, category, price, image_url, description, inventory)
VALUES
(1, 'Burnt Cheesecake', 'Cake', 6.50, 'burnt_cheesecake.jpg', 'Creamy, rich baked cheesecake with a caramelized top and silky texture.', 8),
(2, 'Pork Floss Cake', 'Cake', 5.25, 'pork_floss_cake.jpg', 'Soft sponge cake layered with whipped cream and topped with savory pork floss for a sweet-salty fusion.', 0),
(3, 'Earl Grey Chiffon Cake', 'Cake', 6.00, 'earl_grey_chiffon.jpg', 'Light and airy chiffon cake infused with Earl Grey tea aroma and frosted with tea-flavored cream.', 10),
(4, 'Chocolate Cupcake', 'Cupcake', 3.00, 'chocolate_cupcake.jpg', 'Moist chocolate cupcake topped with smooth buttercream frosting and chocolate sprinkles.', 18),
(5, 'Vanilla Butter Cupcake', 'Cupcake', 2.75, 'vanilla_cupcake.jpg', 'Classic vanilla cupcake with soft buttery texture and sweet vanilla frosting swirl.', 20),
(6, 'Matcha Roll Cake', 'Roll', 7.00, 'matcha_roll.jpg', 'Soft matcha sponge rolled with lightly sweetened cream — a perfect balance of bitter and sweet.', 15),
(7, 'Strawberry Swiss Roll', 'Roll', 6.50, 'strawberry_roll.jpg', 'Fluffy sponge rolled with fresh strawberry slices and cream, finished with powdered sugar.', 9),
(8, 'Mango Pudding', 'Pudding', 4.50, 'mango_pudding.jpg', 'Refreshing, smooth mango pudding served chilled with a touch of cream on top.', 14),
(9, 'Strawberry Mousse', 'Mousse', 6.00, 'strawberry_mousse.jpg', 'Light strawberry mousse layered over sponge cake, topped with fresh strawberries.', 10),
(10, 'Lemon Tart', 'Tart', 5.75, 'lemon_tart.jpg', 'Tangy lemon curd in a buttery tart shell — a zesty, refreshing dessert.', 8),
(11, 'Egg Tart (2 pcs)', 'Tart', 4.20, 'egg_tart.jpg', 'Crispy, flaky pastry filled with silky egg custard — two pieces per order.', 15),
(12, 'Tiramisu Cup', 'Cup Dessert', 5.75, 'tiramisu.jpg', 'Classic Italian tiramisu served in a cup, layered with espresso-soaked sponge and mascarpone cream.', 10);

-- INSERT ORDERS
INSERT INTO orders 
(id, user_id, total, created_at, full_name, address, city, postal, phone)
VALUES
(1, 1, 12.00, '2025-11-27T03:57:18.587841', 'Jack', '901-501 St Clair Ave W', 'Toronto', 'M5P 0A2', '7786817807'),
(2, 1, 575.00, '2025-11-27T04:01:27.84929', 'Olive', '111 A St', 'Halifax', 'V1V 2V6', '12345678');

-- INSERT ORDER ITEMS
INSERT INTO order_items 
(id, order_id, product_id, quantity, price)
VALUES
(1, 1, 3, 1, 6.00),
(2, 1, 4, 2, 3.00),
(3, 2, 10, 100, 5.75);
