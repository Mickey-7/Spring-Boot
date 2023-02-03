--liquibase formatted sql
--changeset techgeeknext:create-tables
INSERT INTO applicationInfo(applicationName,applicationVersion)
VALUES
    ('LiquibaseWithSpringBoot','1.0'),
    ('LiquibaseWithSpringBoot','2.0');
