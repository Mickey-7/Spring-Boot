CREATE TABLE product (
	id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
	name varchar(100) NOT NULL,
	price double NOT NULL,
	sale_price double  NOT NULL,
	sales_count int NOT NULL,
	sale_date VARCHAR(20) NOT NULL
);