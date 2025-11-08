CREATE DATABASE IF NOT EXISTS estore CHARACTER SET utf8mb4;
USE estore;

CREATE TABLE IF NOT EXISTS products (
                                        id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                        name VARCHAR(200) NOT NULL,
    category VARCHAR(100),
    price DECIMAL(10,2) NOT NULL,
    image_url VARCHAR(500)
    );

INSERT INTO products (name, category, price, image_url) VALUES
                                                            ('Burnt Cheesecake', 'Cake', 6.50, '/images/burnt_cheesecake.jpg'),
                                                            ('Pork Floss Cake', 'Cake', 5.25, '/images/pork_floss_cake.jpg'),
                                                            ('Earl Grey Chiffon Cake', 'Cake', 6.00, '/images/earl_grey_chiffon.jpg'),

                                                            ('Chocolate Cupcake', 'Cupcake', 3.00, '/images/chocolate_cupcake.jpg'),
                                                            ('Vanilla Butter Cupcake', 'Cupcake', 2.75, '/images/vanilla_cupcake.jpg'),

                                                            ('Matcha Roll Cake', 'Roll', 7.00, '/images/matcha_roll.jpg'),
                                                            ('Strawberry Swiss Roll', 'Roll', 6.50, '/images/strawberry_roll.jpg'),


                                                            ('Mango Pudding', 'Pudding', 4.50, '/images/mango_pudding.jpg'),
                                                            ('Strawberry Mousse', 'Mousse', 6.00, '/images/strawberry_mousse.jpg'),

                                                            ('Lemon Tart', 'Tart', 5.75, '/images/lemon_tart.jpg'),
                                                            ('Egg Tart (2 pcs)', 'Tart', 4.20, '/images/egg_tart.jpg'),

                                                            ('Tiramisu Cup', 'Cup Dessert', 5.75, '/images/tiramisu.jpg');

ALTER TABLE products ADD COLUMN inventory INT DEFAULT 10;


ALTER TABLE products ADD COLUMN description TEXT AFTER image_url;


UPDATE products SET
                    description = 'Creamy, rich baked cheesecake with a caramelized top and silky texture.',
                    inventory = 8
WHERE name = 'Burnt Cheesecake';

UPDATE products SET
                    description = 'Soft sponge cake layered with whipped cream and topped with savory pork floss for a sweet-salty fusion.',
                    inventory = 6
WHERE name = 'Pork Floss Cake';

UPDATE products SET
                    description = 'Light and airy chiffon cake infused with Earl Grey tea aroma and frosted with tea-flavored cream.',
                    inventory = 10
WHERE name = 'Earl Grey Chiffon Cake';

UPDATE products SET
                    description = 'Moist chocolate cupcake topped with smooth buttercream frosting and chocolate sprinkles.',
                    inventory = 18
WHERE name = 'Chocolate Cupcake';

UPDATE products SET
                    description = 'Classic vanilla cupcake with soft buttery texture and sweet vanilla frosting swirl.',
                    inventory = 20
WHERE name = 'Vanilla Butter Cupcake';

UPDATE products SET
                    description = 'Soft matcha sponge rolled with lightly sweetened cream — a perfect balance of bitter and sweet.',
                    inventory = 12
WHERE name = 'Matcha Roll Cake';

UPDATE products SET
                    description = 'Fluffy sponge rolled with fresh strawberry slices and cream, finished with powdered sugar.',
                    inventory = 9
WHERE name = 'Strawberry Swiss Roll';

UPDATE products SET
                    description = 'Refreshing, smooth mango pudding served chilled with a touch of cream on top.',
                    inventory = 14
WHERE name = 'Mango Pudding';

UPDATE products SET
                    description = 'Light strawberry mousse layered over sponge cake, topped with fresh strawberries.',
                    inventory = 10
WHERE name = 'Strawberry Mousse';

UPDATE products SET
                    description = 'Tangy lemon curd in a buttery tart shell — a zesty, refreshing dessert.',
                    inventory = 8
WHERE name = 'Lemon Tart';

UPDATE products SET
                    description = 'Crispy, flaky pastry filled with silky egg custard — two pieces per order.',
                    inventory = 15
WHERE name = 'Egg Tart (2 pcs)';

UPDATE products SET
                    description = 'Classic Italian tiramisu served in a cup, layered with espresso-soaked sponge and mascarpone cream.',
                    inventory = 10
WHERE name = 'Tiramisu Cup';



