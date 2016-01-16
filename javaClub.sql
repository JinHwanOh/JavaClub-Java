-- Dropt database if exists
DROP DATABASE IF EXISTS dev;

-- Create database
CREATE DATABASE dev;

-- Use database
use dev;

-- Create student table
CREATE TABLE User(
	id VARCHAR(25) NOT NULL,
    password VARCHAR(25) NOT NULL,
    firstName VARCHAR(25) NOT NULL,
    lastName VARCHAR(25) NOT NULL,
	email VARCHAR(50) NOT NULL,
    
    PRIMARY KEY(id)
);