-- Reset statements --

DROP TABLE Categories;
DROP TABLE Vendors;
DROP TABLE Products;
DROP TABLE UserRole;
DROP TABLE Users;
DROP TABLE Customers;
DROP TABLE Transactions;

DROP SCHEMA ROOT RESTRICT;
DROP SCHEMA StockManagementDB RESTRICT;
CREATE SCHEMA StockManagementDB;
SET SCHEMA StockManagementDB;

-- Create Tables --

CREATE TABLE StockManagementDB.Categories (
    category_id INT,
    category VARCHAR(100),
    PRIMARY KEY (category_id)
);

CREATE TABLE StockManagementDB.Vendors (
    vendor_id INT,
    vendor VARCHAR(100),
    PRIMARY KEY (vendor_id)
);

CREATE TABLE StockManagementDB.Products (
    product_id INT,
    category_id INT,
    vendor_id INT,
    product_name VARCHAR(100),
    product_description VARCHAR(255),
    stock_quantity INT,
    price DOUBLE,
    PRIMARY KEY (product_id),
    FOREIGN KEY (category_id) REFERENCES Categories(category_id),
    FOREIGN KEY (vendor_id) REFERENCES Vendors(vendor_id)
);

CREATE TABLE StockManagementDB.UserRole (
    role_id INT,
    role_type VARCHAR(20),
    PRIMARY KEY (role_id)
);

CREATE TABLE StockManagementDB.Users (
    user_id INT,
    role_id INT,
    username VARCHAR(50),
    password VARCHAR(50),
    status BOOLEAN,
    PRIMARY KEY (user_id),
    FOREIGN KEY (role_id) REFERENCES UserRole(role_id)
);

CREATE TABLE StockManagementDB.Customers (
    customer_id INT,
    customer_name VARCHAR(50),
    customer_lastname VARCHAR(50),
    customer_email VARCHAR(50),
    PRIMARY KEY (customer_id)
);

CREATE TABLE StockManagementDB.Transactions (
    transaction_id INT,
    product_id INT,
    customer_id INT,
    user_id INT,
    transaction_quantity INT,
    transaction_total DOUBLE,
    transaction_date VARCHAR(20),
    PRIMARY KEY (transaction_id),
    FOREIGN KEY (product_id) REFERENCES Products(product_id),
    FOREIGN KEY (customer_id) REFERENCES Customers(customer_id),
    FOREIGN KEY (user_id) REFERENCES Users(user_id)
);

-- Inserts --

INSERT INTO Categories VALUES (1, 'Bread'), (2, 'Milk'), (3, 'Chips');
INSERT INTO Vendors VALUES (1, 'Checkers');
INSERT INTO Products VALUES 
(1, 1, 1, 'Sasko Premium', 'Sasko Bread', 0, 11.99),
(2, 2, 1, 'Milk Premium', 'Cow Milk', 10, 8.99),
(3, 3, 1, 'Nik Naks', 'Cheap Chips', 10, 1.99),
(4, 1, 1, 'Albany Premium', 'Albany Bread', 5, 12.99);
INSERT INTO UserRole VALUES (1, 'ADMIN'), (2, 'USER');
INSERT INTO Users VALUES 
(1, 1, 'root', 'root', 1), 
(2, 2, 'user_1', 'userpwd', 0);
INSERT INTO Customers VALUES (1, 'Joe', 'Biden', 'joe@email.com');
INSERT INTO Transactions VALUES (1, 1, 1, 2, 2, 23.99, '2021-08-61');

-- Updates --

UPDATE Products SET stock_quantity = 15 WHERE product_id=1;
UPDATE Users SET password = 'mypassword' WHERE user_id=2;

-- Selects --

SELECT * FROM Products;
SELECT * FROM Users;
SELECT * FROM Customers;
SELECT * FROM Transactions;

-- Select JOIN --

SELECT 
 category, 
 product_name,
 product_description,
 stock_quantity
FROM Products 
INNER JOIN Categories ON Products.category_id = Categories.category_id
WHERE category = 'Bread' AND stock_quantity > 0;

-- Select Order by --
SELECT product_name FROM Products ORDER BY product_name ASC;
