-- Disable foreign key checks
SET FOREIGN_KEY_CHECKS = 0;

-- Drop tables if they exist
DROP TABLE IF EXISTS meeting;
DROP TABLE IF EXISTS customer;

-- Enable foreign key checks
SET FOREIGN_KEY_CHECKS = 1;

-- Create the Customer table
CREATE TABLE customer (
    customer_id INT AUTO_INCREMENT PRIMARY KEY,
    customer_name VARCHAR(255) NOT NULL,
    org_num INT
);

-- Insert initial data into the Customer table
INSERT INTO customer (customer_name, org_num) VALUES
    ('Customer 1', 12345),
    ('Customer 2', 67890),
    ('Customer 3', 54321);

-- Create the Meeting table
CREATE TABLE meeting (
    meetingid INT AUTO_INCREMENT PRIMARY KEY,
    meeting_title VARCHAR(255) NOT NULL,
    customer_id INT,
    FOREIGN KEY (customer_id) REFERENCES customer(customer_id)
);

-- Insert initial data into the Meeting table
INSERT INTO meeting (meeting_title, customer_id) VALUES
    ('Meeting 1', 1),
    ('Meeting 2', 2),
    ('Meeting 3', 3);
    
-- Select all records from the Customer table
SELECT * FROM customer;

-- Select all records from the Meeting table
SELECT * FROM meeting;