DROP TABLE IF EXISTS item;
CREATE TABLE item (
  item_id INT NOT NULL AUTO_INCREMENT,
  item_name VARCHAR(255) NOT NULL UNIQUE,
  item_price DECIMAL NOT NULL,
  PRIMARY KEY (item_id)
);

DROP TABLE IF EXISTS order;
CREATE TABLE order (
  order_id INT NOT NULL AUTO_INCREMENT,
  order_item_id INT NOT NULL,
  order_item_count INT NOT NULL,
  PRIMARY KEY (order_id)
);

DROP TABLE IF EXISTS orderslist;
CREATE TABLE orderslist (
  order_id INT NOT NULL AUTO_INCREMENT,
  employee_id INT NOT NULL,
  order_status INT NOT NULL,
  PRIMARY KEY (order_id)
  FOREIGN KEY (order_id) REFERENCES order(order_id)
);

