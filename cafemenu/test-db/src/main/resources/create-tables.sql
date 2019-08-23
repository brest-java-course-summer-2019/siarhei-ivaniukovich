DROP TABLE IF EXISTS item_table;
CREATE TABLE item_table (
  item_id INT NOT NULL AUTO_INCREMENT,
  item_name VARCHAR(255) NOT NULL UNIQUE,
  item_price DECIMAL NOT NULL,
  PRIMARY KEY (item_id)
);

DROP TABLE IF EXISTS order_table;
CREATE TABLE order_table (
  order_id INT NOT NULL AUTO_INCREMENT,
  order_employee_id INT NOT NULL,
  order_datetime DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  order_status INT NOT NULL,
  PRIMARY KEY (order_id)
);

DROP TABLE IF EXISTS item_in_order;
CREATE TABLE item_in_order (
  iio_order_id DECIMAL NOT NULL,
  iio_item_id INT NOT NULL,
  iio_item_name VARCHAR(255) NOT NULL,
  iio_item_price DECIMAL NOT NULL,
  iio_item_count INT NOT NULL,
  PRIMARY KEY (iio_order_id, iio_item_id),
  FOREIGN KEY (iio_order_id) REFERENCES order_table(order_id)
);
