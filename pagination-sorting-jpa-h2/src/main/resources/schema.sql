DROP TABLE IF EXISTS employee;

CREATE TABLE employee (
  id bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  email varchar(255) DEFAULT NULL,
  first_name varchar(255) DEFAULT NULL,
  last_name varchar(255) DEFAULT NULL,
  city varchar(50) DEFAULT NULL
);