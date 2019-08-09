DROP TABLE IF EXISTS item_table;
CREATE TABLE item_table (
  item_id INT NOT NULL AUTO_INCREMENT,
  item_name VARCHAR(255) NOT NULL UNIQUE,
  item_price DECIMAL NOT NULL,
  PRIMARY KEY (item_id)
);

DROP TABLE IF EXISTS order_table;
CREATE TABLE order_table (
  order_id INT NOT NULL,
  order_item_id INT NOT NULL,
  order_item_count INT NOT NULL,
  FOREIGN KEY (order_item_id) REFERENCES item_table(item_id),
  PRIMARY KEY (order_id, order_item_id)
);

DROP TABLE IF EXISTS orderslist_table;
CREATE TABLE orderslist_table (
  ol_order_id INT NOT NULL AUTO_INCREMENT,
  ol_employee_id INT NOT NULL,
  ol_order_status INT NOT NULL,
  FOREIGN KEY (ol_order_id) REFERENCES order_table(order_id),
  PRIMARY KEY (ol_order_id)
);

