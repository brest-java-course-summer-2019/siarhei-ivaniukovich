INSERT INTO item (item_name, item_price) VALUES ('Burger', 5.00);
INSERT INTO item (item_name, item_price) VALUES ('Nuggets', 3.25);
INSERT INTO item (item_name, item_price) VALUES ('French fried', 2.50);
INSERT INTO item (item_name, item_price) VALUES ('Coffee', 1.55);
INSERT INTO item (item_name, item_price) VALUES ('Tea', 1.10);
INSERT INTO item (item_name, item_price) VALUES ('ChickenBurger', 3.50);
INSERT INTO item (item_name, item_price) VALUES ('GrillBurger', 4.00);
INSERT INTO item (item_name, item_price) VALUES ('DoubleTrouble Burger', 7.00);
INSERT INTO item (item_name, item_price) VALUES ('Cola 0,5', 1.50);
INSERT INTO item (item_name, item_price) VALUES ('Sprite 0,5', 1.50);
INSERT INTO item (item_name, item_price) VALUES ('Latte', 2.50);

INSERT INTO order_d (employee_id, order_date_time) VALUES (21, '2019-08-15 9:05:05');
INSERT INTO order_d (employee_id, order_date_time) VALUES (21, '2019-08-15 9:08:24');
INSERT INTO order_d (employee_id, order_date_time) VALUES (15, '2019-08-15 10:12:22');
INSERT INTO order_d (employee_id) VALUES (12);
INSERT INTO order_d (employee_id) VALUES (77);

INSERT INTO item_in_order (iio_order_id, iio_item_id, iio_item_name, iio_item_price, iio_item_count) VALUES (1, 1, 'Burger', 5.00, 1);
INSERT INTO item_in_order (iio_order_id, iio_item_id, iio_item_name, iio_item_price, iio_item_count) VALUES (1, 4, 'Coffee', 1.55, 1);
INSERT INTO item_in_order (iio_order_id, iio_item_id, iio_item_name, iio_item_price, iio_item_count) VALUES (1, 5, 'Tea', 1.10, 2);
INSERT INTO item_in_order (iio_order_id, iio_item_id, iio_item_name, iio_item_price, iio_item_count) VALUES (2, 2, 'Nuggets', 3.25, 2);
INSERT INTO item_in_order (iio_order_id, iio_item_id, iio_item_name, iio_item_price, iio_item_count) VALUES (2, 3, 'French fried', 2.50, 2);
INSERT INTO item_in_order (iio_order_id, iio_item_id, iio_item_name, iio_item_price, iio_item_count) VALUES (3, 4, 'Coffee', 1.55, 2);
INSERT INTO item_in_order (iio_order_id, iio_item_id, iio_item_name, iio_item_price, iio_item_count) VALUES (3, 5, 'Tea', 1.10, 3);
INSERT INTO item_in_order (iio_order_id, iio_item_id, iio_item_name, iio_item_price, iio_item_count) VALUES (4, 2, 'Nuggets', 3.25, 2);
INSERT INTO item_in_order (iio_order_id, iio_item_id, iio_item_name, iio_item_price, iio_item_count) VALUES (4, 3, 'French fried', 2.50, 3);
INSERT INTO item_in_order (iio_order_id, iio_item_id, iio_item_name, iio_item_price, iio_item_count) VALUES (4, 4, 'Coffee', 1.55, 2);
INSERT INTO item_in_order (iio_order_id, iio_item_id, iio_item_name, iio_item_price, iio_item_count) VALUES (4, 5, 'Tea', 1.10, 1);
INSERT INTO item_in_order (iio_order_id, iio_item_id, iio_item_name, iio_item_price, iio_item_count) VALUES (5, 2, 'Nuggets', 3.25, 2);
INSERT INTO item_in_order (iio_order_id, iio_item_id, iio_item_name, iio_item_price, iio_item_count) VALUES (5, 3, 'French fried', 2.50, 3);
INSERT INTO item_in_order (iio_order_id, iio_item_id, iio_item_name, iio_item_price, iio_item_count) VALUES (5, 4, 'Coffee', 1.55, 2);
INSERT INTO item_in_order (iio_order_id, iio_item_id, iio_item_name, iio_item_price, iio_item_count) VALUES (5, 5, 'Burger', 5.00, 8);
INSERT INTO item_in_order (iio_order_id, iio_item_id, iio_item_name, iio_item_price, iio_item_count) VALUES (5, 6, 'ChickenBurger', 3.50, 2);
INSERT INTO item_in_order (iio_order_id, iio_item_id, iio_item_name, iio_item_price, iio_item_count) VALUES (5, 7, 'GrillBurger', 4.00, 2);
INSERT INTO item_in_order (iio_order_id, iio_item_id, iio_item_name, iio_item_price, iio_item_count) VALUES (5, 9, 'Cola 0,5', 1.50, 5);
INSERT INTO item_in_order (iio_order_id, iio_item_id, iio_item_name, iio_item_price, iio_item_count) VALUES (5, 10, 'Sprite 0,5', 1.50, 12);

