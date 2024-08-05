Download MySQL connector jar:- https://repo1.maven.org/maven2/com/mysql/mysql-connector-j/8.0.31/
Steps to add JAR file to project:
1. Create Java Project.
2. Right click on Projct.
3. Click on Build Path.
4. Click on Configure Build Path.
5. Select Library, Select Classpath.
6. Click on Add External JAR's.
7. Choose the path where downloaded JAR is present.
8. Click on Apply & Close.
   
**JDBC Student Management System**

Introduction

This project implements a Java application to manage student information using JDBC (Java Database Connectivity). It allows users to perform various operations on student data stored in a MySQL database.

Project Structure

The project is organized into several packages:

student.dao: This package contains the StudentDao class, which interacts with the database to perform CRUD (Create, Read, Update, Delete) operations on student data.
student.dto: This package contains the Student class, which represents a student object with attributes like ID, name, marks, phone number, and address.
student.requirements: This package defines an interface, StudentRequirements, that specifies the functionalities required for managing students.
student.services: This package contains the StudentServices class, which serves as the driver class. It interacts with the StudentDao to perform various operations based on user input.
Features

Add Student: Saves a new student record to the database.
Display All Students: Retrieves and displays all student information from the database.
Display Student by ID: Searches for a student by ID and displays the complete information.
Display Student by Name: Searches for students by name and displays all matching records.
Display Student by Phone: Searches for a student by phone number and displays the information.
Display Passed Students: Finds students with marks above a specified threshold and displays their details. Calculates and displays total and passed student counts.
Update Student's Name: Updates the name of a specific student based on their ID.
Update Student's Phone: Updates the phone number of a specific student based on their ID.
Update Student's Marks: Updates the marks of a specific student based on their ID.
Update Student's Address: Updates the address of a specific student based on their ID.
Update Complete Student: Updates all details (name, marks, phone, address) of a specific student.
Delete Student: Removes a student record from the database based on their ID.
Running the Application

Ensure you have a MySQL database set up and configured.\
Create the necessary tables and schema for storing student information. (These might be provided separately)\
Configure the database connection details in the StudentRequirements class (e.g., username, password, URL).\
Compile the Java source files using a Java compiler.\
Run the StudentServices class using the java command.\
\
\
**Option	Description**
1.	Save Student
2.	Display All Students
3.	Display Student by Id
4.	Display Student by Name
5.	Display Student by Phone
6.	Display Passed Students
7.	Update Student's Name
8.	Update Student's Phone
9.	Update Student's Marks
10.	Update Student's Address
11.	Update Complete Student
12.	Delete Student by ID
13.	Exit
## Authors

- [@deshmukhrahulrmd](https://www.github.com/deshmukhrahulrmd)


## Badges

Add badges from: [shields.io](https://shields.io/)

[![MIT License](https://img.shields.io/badge/License-MIT-green.svg)](https://choosealicense.com/licenses/mit/)
[![GPLv3 License](https://img.shields.io/badge/License-GPL%20v3-yellow.svg)](https://opensource.org/licenses/)
[![AGPL License](https://img.shields.io/badge/license-AGPL-blue.svg)](http://www.gnu.org/licenses/agpl-3.0)

