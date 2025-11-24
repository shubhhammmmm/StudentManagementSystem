# Student Management System

## 📚 Overview
A comprehensive Java-based console application for managing student records, courses, and grades in educational institutions. Developed as part of the VITyarthi Build Your Own Project assignment.

## ✨ Features
- *Student Management*: Complete CRUD operations for student records
- *Course Management*: Manage course catalog with scheduling
- *Grade Management*: Assign, update, and track student grades
- *Academic Analytics*: GPA calculation, academic standing, performance reports
- *Search & Reporting*: Advanced search and comprehensive reporting
- *Data Validation*: Robust input validation and error handling
- *File Persistence*: Data persistence across sessions

## 🏗 System Architecture
The application follows a layered architecture:
- *Model Layer*: Domain entities (Student, Course, Grade)
- *Repository Layer*: Data access and persistence
- *Service Layer*: Business logic and validation
- *Presentation Layer*: Console-based user interface

## 🛠 Technologies Used
- Java 11+
- Object-Oriented Programming Principles
- File I/O Operations
- JUnit 5 for Testing
- Maven for Build Management

## 📥 Installation & Setup

### Prerequisites
- Java 11 or higher
- Maven 3.6+

### Build Instructions
```bash
# Clone the repository
git clone https://github.com/yourusername/student-management-system.git
cd student-management-system

# Compile the project
mvn compile

# Run the application
mvn exec:java -Dexec.mainClass="Main"

# Run tests
mvn test