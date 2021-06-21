--/**
-- * CREATE Script for init of DB
-- */

DROP TABLE IF EXISTS orders;

---- Create DB for pizza order (size, variety of pizza, toppings) from customers
CREATE TABLE orders (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  date_created TIMESTAMP, 
  deleted BOOLEAN, 
  size VARCHAR(20),
  variety VARCHAR(20), 
  topping VARCHAR(20), 
  price DOUBLE, 
  order_status VARCHAR(20), 
  username VARCHAR(50) NOT NULL
);

insert into orders (id, date_created, deleted, size, variety, topping, price, order_status, username) values (1, now(), false, 'regular', 'cheese', 'onion', 0, 'ORDERED', 'user1');

--insert into orders (id, date_created, deleted, size, variety, topping, price, order_status, username) values (2, now(), false, 'medium', 'chicken', 'tomato', 0, 'ORDERED', 'user2');
--
--insert into orders (id, date_created, deleted, size, variety, topping, price, order_status, username) values (3, now(), false, 'large', 'beef', 'spinach', 0, 'DELIVERED', 'user3');


