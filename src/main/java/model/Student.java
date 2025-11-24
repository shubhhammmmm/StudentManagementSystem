package model;

import java.time.LocalDate;

public class Student {
    private String studentId;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate enrollmentDate;
    private String department;
    private String phoneNumber;
    
    public Student(String studentId, String firstName, String lastName, 
                  String email, LocalDate enrollmentDate, String department, String phoneNumber) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.enrollmentDate = enrollmentDate;
        this.department = department;
        this.phoneNumber = phoneNumber;
    }
    
    // Getters and Setters
    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }
    
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public LocalDate getEnrollmentDate() { return enrollmentDate; }
    public void setEnrollmentDate(LocalDate enrollmentDate) { this.enrollmentDate = enrollmentDate; }
    
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    
    @Override
    public String toString() {
        return String.format("Student{ID: %s, Name: %s %s, Email: %s, Department: %s, Phone: %s}", 
                studentId, firstName, lastName, email, department, phoneNumber);
    }
    
    public String toCSV() {
        return String.format("%s,%s,%s,%s,%s,%s,%s", 
                studentId, firstName, lastName, email, enrollmentDate, department, phoneNumber);
    }
}