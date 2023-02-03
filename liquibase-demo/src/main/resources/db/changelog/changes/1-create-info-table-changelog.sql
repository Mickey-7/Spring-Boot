--liquibase formatted sql
--changeset techgeeknext:create-tables
CREATE TABLE applicationInfo(
    id INT PRIMARY KEY AUTO_INCREMENT,
    applicationName VARCHAR(250) NOT NULL,
    applicationVersion VARCHAR(05) NOT NULL
);