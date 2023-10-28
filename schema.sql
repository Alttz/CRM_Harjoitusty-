-- Disable foreign key checks
SET FOREIGN_KEY_CHECKS = 0;

-- Drop tables if they exist
DROP TABLE IF EXISTS meeting;
DROP TABLE IF EXISTS customer;
DROP TABLE IF EXISTS app_user;
DROP TABLE IF EXISTS employee;
DROP TABLE IF EXISTS meeting_employee;

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
    org_num VARCHAR(10)
);

-- Insert initial data into the Customer table
INSERT INTO customer (customer_name, org_num) VALUES 
    ('Nordea', '1234567-9'),
    ('Bonava', '6787890-1'),
    ('Aktia', '4567890-2');

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
INSERT INTO meeting (meeting_title, customer_id) VALUES 
    ('Neuvottelu', 1),
    ('Yhteistyö workshop', 2),
    ('Palaveri', 3),
    ('Lainaneuvottelu', 1);
    
    -- Create the meeting_employee join table
CREATE TABLE meeting_employee (
    meeting_id INT NOT NULL,
    employee_id BIGINT NOT NULL,
    PRIMARY KEY (meeting_id, employee_id),
    FOREIGN KEY (meeting_id) REFERENCES meeting(meetingid),
    FOREIGN KEY (employee_id) REFERENCES employee(employee_id)
);

-- Assuming meeting_ids are generated sequentially, 
-- so 'Neuvottelu' gets 1, 'Yhteistyö workshop' gets 2, etc.

-- For 'Neuvottelu'
INSERT INTO meeting_employee (meeting_id, employee_id) VALUES 
    (1, 1),
    (1, 2);

-- For 'Yhteistyö workshop'
INSERT INTO meeting_employee (meeting_id, employee_id) VALUES 
    (2, 1);

-- For 'Palaveri'
INSERT INTO meeting_employee (meeting_id, employee_id) VALUES 
    (3, 2);

-- For 'Lainaneuvottelu'
INSERT INTO meeting_employee (meeting_id, employee_id) VALUES 
    (4, 2);
    
-- Drop the foreign key constraint related to employee_id in the meeting table
ALTER TABLE meeting DROP FOREIGN KEY meeting_ibfk_2;

-- Once you have migrated the data, you can remove the 'employee_id' column from the 'meeting' table:
ALTER TABLE meeting DROP COLUMN employee_id;

-- Select all records from the Customer table
SELECT * FROM customer;

-- Select all records from the Meeting table
SELECT * FROM meeting;

-- Select all records from the App-user table
SELECT * FROM app_user;

-- Select all records from the Employee table
SELECT * FROM employee;

-- Select all records from the Meeting_employee table
SELECT * FROM meeting_employee;


-- Enable foreign key checks
SET FOREIGN_KEY_CHECKS = 1;
