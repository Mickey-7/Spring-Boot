-- liquibase formatter sql
-- changeset vivek:5
ALTER TABLE applicationInfo
ADD applicationDescription VARCHAR(100) NOT NULL;