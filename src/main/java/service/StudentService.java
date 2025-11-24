package service;

import model.Student;
import repository.StudentRepository;
import util.Validator;
import java.util.List;

public class StudentService {
    private StudentRepository studentRepository;
    
    public StudentService() {
        this.studentRepository = new StudentRepository();
    }
    
    public boolean registerStudent(Student student) {
        // Validate student data
        if (!Validator.isValidId(student.getStudentId())) {
            throw new IllegalArgumentException("Student ID cannot be empty and must be alphanumeric");
        }
        if (!Validator.isValidName(student.getFirstName())) {
            throw new IllegalArgumentException("First name cannot be empty and must contain only letters");
        }
        if (!Validator.isValidName(student.getLastName())) {
            throw new IllegalArgumentException("Last name cannot be empty and must contain only letters");
        }
        if (!Validator.isValidEmail(student.getEmail())) {
            throw new IllegalArgumentException("Invalid email format");
        }
        if (student.getDepartment() == null || student.getDepartment().trim().isEmpty()) {
            throw new IllegalArgumentException("Department cannot be empty");
        }
        
        return studentRepository.addStudent(student);
    }
    
    public Student getStudent(String studentId) {
        return studentRepository.getStudent(studentId);
    }
    
    public List<Student> getAllStudents() {
        return studentRepository.getAllStudents();
    }
    
    public boolean updateStudent(Student student) {
        if (!studentRepository.studentExists(student.getStudentId())) {
            throw new IllegalArgumentException("Student not found");
        }
        return studentRepository.updateStudent(student);
    }
    
    public boolean deleteStudent(String studentId) {
        if (!studentRepository.studentExists(studentId)) {
            throw new IllegalArgumentException("Student not found");
        }
        return studentRepository.deleteStudent(studentId);
    }
    
    public List<Student> searchStudents(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            throw new IllegalArgumentException("Search keyword cannot be empty");
        }
        return studentRepository.searchStudents(keyword);
    }
    
    public int getTotalStudents() {
        return studentRepository.getAllStudents().size();
    }
    
    public boolean studentExists(String studentId) {
        return studentRepository.studentExists(studentId);
    }
}