CREATE DATABASE IF NOT EXISTS Bookstore;

USE Bookstore;

DROP TABLE IF EXISTS Orders;
DROP TABLE IF EXISTS Carts;
DROP TABLE IF EXISTS Books;
DROP TABLE IF EXISTS Users;

CREATE TABLE IF NOT EXISTS Books
(
    book_id integer(8) AUTO_INCREMENT PRIMARY KEY,
    book_name varchar(80) NOT NULL,
    book_author varchar(80) NOT NULL,
    book_cover varchar(100) NULL,
    book_price float(2) NOT NULL,
    book_rate enum('1','2','3','4','5') NULL,
    book_stocks integer NOT NULL,
    book_description varchar(800) NULL
);

CREATE TABLE IF NOT EXISTS Users
(
    user_id integer(8) AUTO_INCREMENT PRIMARY KEY,
    user_name varchar(16) NOT NULL,
    user_password varchar(32) NOT NULL
);

CREATE TABLE IF NOT EXISTS Orders
(
    order_id integer(8) AUTO_INCREMENT PRIMARY KEY,
    user_id integer(8),
    book_id integer(8),
    order_status enum('Ordered','Processing','Completed','Canceled') NOT NULL,
    FOREIGN KEY (user_id) REFERENCES Users(user_id),
    FOREIGN KEY (book_id) REFERENCES Books(book_id)
);

CREATE TABLE IF NOT EXISTS Carts
(
    cart_id integer(8) AUTO_INCREMENT PRIMARY KEY,
    user_id integer(8),
    book_id integer(8),
    quantity integer NOT NULL,
    FOREIGN KEY (user_id) REFERENCES Users(user_id),
    FOREIGN KEY (book_id) REFERENCES Books(book_id)
);
