/* This file is meant for MySQL, and was tested with MySQL version 5.7! Proper functionality with other types of SQL or older versions of MySQL can't be guaranteed!*/

DROP DATABASE IF EXISTS UniversityDB;

CREATE DATABASE IF NOT EXISTS UniversityDB;

DROP USER IF EXISTS 'jdbc-program'@'localhost';
CREATE USER IF NOT EXISTS 'jdbc-program'@'localhost' IDENTIFIED BY 'password';

GRANT ALL PRIVILEGES ON UniversityDB.* TO 'jdbc-program'@'localhost' WITH GRANT OPTION;

USE UniversityDB;

CREATE TABLE IF NOT EXISTS Administrators(
    admin_ssn CHAR(9) PRIMARY KEY,
    name CHAR(64));

CREATE TABLE IF NOT EXISTS Professors(
    prof_ssn CHAR(9) PRIMARY KEY,
    name CHAR(64),
    age INTEGER,
    specialty CHAR(64));

CREATE TABLE IF NOT EXISTS Departments(
    dept_num INTEGER PRIMARY KEY,
    dept_name CHAR(64),
    office CHAR(10));

/*Enforcement of the max_enrollment value will take place in the Java program.*/
CREATE TABLE IF NOT EXISTS Courses(
    course_id CHAR(10),
    prof_ssn CHAR(10),
    dept_num INTEGER,
    current_enrollment INTEGER,
    max_enrollment INTEGER,
    PRIMARY KEY (course_id, prof_ssn),
    FOREIGN KEY (prof_ssn) REFERENCES Professors(prof_ssn),
    FOREIGN KEY (dept_num) REFERENCES Departments(dept_num));

CREATE TABLE IF NOT EXISTS Students(
    stud_ssn CHAR(9) PRIMARY KEY,
    name CHAR(64),
    age INTEGER,
    deg_prog CHAR(32),
    major_dept INTEGER,
    FOREIGN KEY (major_dept) REFERENCES Departments(dept_num));

CREATE TABLE IF NOT EXISTS Enrollment(
    course_id CHAR(10),
    stud_ssn CHAR(10),
    PRIMARY KEY(course_id, stud_ssn),
    FOREIGN KEY (course_id) REFERENCES Courses(course_id),
    FOREIGN KEY (stud_ssn) REFERENCES Students(stud_ssn));
