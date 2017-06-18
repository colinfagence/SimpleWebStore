DROP TABLE IF EXISTS categories;
CREATE TABLE categories ( id INTEGER PRIMARY KEY AUTO_INCREMENT, name varchar(50) );

DROP TABLE IF EXISTS products;
CREATE TABLE products   ( id INTEGER PRIMARY KEY AUTO_INCREMENT, name varchar(50), description varchar(300), price decimal( 6, 2 ), category_id INTEGER, FOREIGN KEY (category_id) REFERENCES categories(id) );