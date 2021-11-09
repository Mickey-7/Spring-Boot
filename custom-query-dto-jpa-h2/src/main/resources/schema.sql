CREATE TABLE customer(
    id INT,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    mobile VARCHAR(100),
    country VARCHAR(100)
);

CREATE TABLE customer_order(
    name VARCHAR(100),
    description VARCHAR(100),
    order_status VARCHAR(100),
    customer_id INT
);