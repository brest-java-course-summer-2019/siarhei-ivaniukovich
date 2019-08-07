INSERT INTO item (item_id, item_name, item_price) VALUES (1, 'Burger', 5.0);
INSERT INTO item (item_id, item_name, item_price) VALUES (2, 'Nuggets', 3.0);
INSERT INTO item (item_id, item_name, item_price) VALUES (3, 'French fried', 2.5);
INSERT INTO item (item_id, item_name, item_price) VALUES (4, 'Coffee', 1.5);
INSERT INTO item (item_id, item_name, item_price) VALUES (5, 'Tea', 1.0);

INSERT INTO order (order_id, order_item_id, order_item_count) VALUES (1, 1, 1);
INSERT INTO order (order_id, order_item_id, order_item_count) VALUES (1, 2, 3);
INSERT INTO order (order_id, order_item_id, order_item_count) VALUES (1, 4, 1);
INSERT INTO order (order_id, order_item_id, order_item_count) VALUES (2, 4, 2);
INSERT INTO order (order_id, order_item_id, order_item_count) VALUES (2, 5, 1);
INSERT INTO order (order_id, order_item_id, order_item_count) VALUES (3, 1, 1);
INSERT INTO order (order_id, order_item_id, order_item_count) VALUES (3, 3, 2);
INSERT INTO order (order_id, order_item_id, order_item_count) VALUES (3, 2, 1);
INSERT INTO order (order_id, order_item_id, order_item_count) VALUES (3, 4, 4);
INSERT INTO order (order_id, order_item_id, order_item_count) VALUES (4, 4, 10);
INSERT INTO order (order_id, order_item_id, order_item_count) VALUES (4, 5, 12);

INSERT INTO orderslist (ol_order_id, ol_employee_id, ol_order_status) VALUES (1, 5, 2);
INSERT INTO orderslist (ol_order_id, ol_employee_id, ol_order_status) VALUES (2, 7, 1);
INSERT INTO orderslist (ol_order_id, ol_employee_id, ol_order_status) VALUES (3, 12, 1);
INSERT INTO orderslist (ol_order_id, ol_employee_id, ol_order_status) VALUES (4, 11, 0);


