-- Disable foreign key checks
SET FOREIGN_KEY_CHECKS = 0;

-- Drop tables if they exist
DROP TABLE IF EXISTS meeting;
DROP TABLE IF EXISTS customer;
DROP TABLE IF EXISTS app_user;
DROP TABLE IF EXISTS employee;

-- Create the app-user table    
CREATE TABLE app_user (
    user_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_role VARCHAR(100) NOT NULL,
    username VARCHAR(250) NOT NULL,
    password_hash VARCHAR(250) NOT NULL
);

-- Insert initial data into the App-user table
INSERT INTO app_user (username, password_hash, user_role) 
VALUES 
    ('user', '$2a$10$1DTvwpXVBArGFixHBuzVJObjTuXhIOkx5pse6KsYs8/C2ckxnGEou', 'USER'), 
    ('admin', '$2a$10$cDZgyF4xaPMmmoRW3OVcmuf.8o2YSx8.M7CeRKqi.1PVw.t3E8uEC', 'ADMIN');

-- Create the employee table    
CREATE TABLE employee (
    employee_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    employee_position VARCHAR(250) NOT NULL,
    first_name VARCHAR(250) NOT NULL,
    last_name VARCHAR(250) NOT NULL
);
    
    -- Insert initial data into the Employee table
INSERT INTO employee (employee_position, first_name, last_name) 
VALUES 
    ('Account Manager', 'Jim', 'Halpert'), 
	 ('Office Manager', 'Michael', 'Scott'); 
 
-- Create the Customer table
CREATE TABLE customer (
    customer_id INT AUTO_INCREMENT PRIMARY KEY,
    customer_name VARCHAR(255) NOT NULL,
    org_num INT
);

-- Insert initial data into the Customer table
INSERT INTO customer (customer_name, org_num) VALUES 
    ('Nordea', 12345),
    ('Bonava', 67890),
    ('Aktia', 54321);

-- Create the Meeting table
CREATE TABLE meeting (
    meetingid INT AUTO_INCREMENT PRIMARY KEY,
    meeting_title VARCHAR(255) NOT NULL,
    customer_id INT,
    employee_id BIGINT,
    FOREIGN KEY (customer_id) REFERENCES customer(customer_id),
    FOREIGN KEY (employee_id) REFERENCES employee(employee_id)
);

-- Insert initial data into the Customer table
INSERT INTO meeting (meeting_title, customer_id, employee_id) VALUES 
    ('Neuvottelu', 1, 1),
    ('Yhteistyö workshop', 2, 1),
    ('Palaveri', 3, 2),
    ('Lainaneuvottelu', 1, 2);

-- Select all records from the Customer table
SELECT * FROM customer;

-- Select all records from the Meeting table
SELECT * FROM meeting;

-- Select all records from the App-user table
SELECT * FROM app_user;

-- Select all records from the Employee table
SELECT * FROM employee;

-- Enable foreign key checks
SET FOREIGN_KEY_CHECKS = 1;
