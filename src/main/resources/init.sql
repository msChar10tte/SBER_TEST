CREATE SCHEMA IF NOT EXISTS test;
USE  test;

CREATE TABLE employees ( id BIGINT PRIMARY KEY AUTO_INCREMENT, name_employees VARCHAR(80), phone_employees BIGINT, status_employees BOOLEAN NOT NULL, date_ DATE);

INSERT INTO employees(name_employees, phone_employees, status_employees, date_) VALUES ('Nastya','1234567845', FALSE, '2024-05-25');
INSERT INTO employees(name_employees, phone_employees, status_employees, date_) VALUES ('Denis','3748594837', TRUE, '2022-02-25');
INSERT INTO employees(name_employees, phone_employees, status_employees, date_) VALUES ('Anna','4839745362', TRUE, '2023-05-25');