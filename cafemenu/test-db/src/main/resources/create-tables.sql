DROP TABLE IF EXISTS item;
CREATE TABLE item (
  item_id INT NOT NULL AUTO_INCREMENT,
  item_name VARCHAR(255) NOT NULL UNIQUE,
  item_price DECIMAL NOT NULL,
  PRIMARY KEY (item_id)
);

DROP TABLE IF EXISTS order_d;
CREATE TABLE order_d (
  order_id INT NOT NULL AUTO_INCREMENT,
  employee_id INT NOT NULL,
  order_date_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
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
  FOREIGN KEY (iio_order_id) REFERENCES order_d(order_id) ON DELETE CASCADE
);


