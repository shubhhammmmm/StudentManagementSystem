package repository;

import model.Student;
import java.util.*;
import java.time.LocalDate;

public class StudentRepository {
    private Map<String, Student> students;
    private static final String DATA_FILE = "resources/students.dat";
    
    public StudentRepository() {
        this.students = new HashMap<>();
        loadFromFile();
        if (students.isEmpty()) {
            initializeSampleData();
        }
    }
    
    private void initializeSampleData() {
        addStudent(new Student("S001", "John", "Doe", "john.doe@university.edu", 
                LocalDate.of(2023, 9, 1), "Computer Science", "123-456-7890"));
        addStudent(new Student("S002", "Jane", "Smith", "jane.smith@university.edu", 
                LocalDate.of(2023, 9, 1), "Mathematics", "123-456-7891"));
        addStudent(new Student("S003", "Bob", "Johnson", "bob.johnson@university.edu", 
                LocalDate.of(2023, 9, 1), "Physics", "123-456-7892"));
        saveToFile();
    }
    
    private void loadFromFile() {
        // Implementation for file loading
        System.out.println("Loading student data from file...");
    }
    
    private void saveToFile() {
        // Implementation for file saving
        System.out.println("Saving student data to file...");
    }
    
    public boolean addStudent(Student student) {
        if (students.containsKey(student.getStudentId())) {
            return false;
        }
        students.put(student.getStudentId(), student);
        saveToFile();
        return true;
    }
    
    public Student getStudent(String studentId) {
        return students.get(studentId);
    }
    
    public List<Student> getAllStudents() {
        return new ArrayList<>(students.values());
    }
    
    public boolean updateStudent(Student student) {
        if (!students.containsKey(student.getStudentId())) {
            return false;
        }
        students.put(student.getStudentId(), student);
        saveToFile();
        return true;
    }
    
    public boolean deleteStudent(String studentId) {
        boolean removed = students.remove(studentId) != null;
        if (removed) {
            saveToFile();
        }
        return removed;
    }
    
    public List<Student> searchStudents(String keyword) {
        List<Student> result = new ArrayList<>();
        String lowerKeyword = keyword.toLowerCase();
        
        for (Student student : students.values()) {
            if (student.getFirstName().toLowerCase().contains(lowerKeyword) ||
                student.getLastName().toLowerCase().contains(lowerKeyword) ||
                student.getEmail().toLowerCase().contains(lowerKeyword) ||
                student.getDepartment().toLowerCase().contains(lowerKeyword) ||
                student.getStudentId().toLowerCase().contains(lowerKeyword)) {
                result.add(student);
            }
        }
        return result;
    }
    
    public boolean studentExists(String studentId) {
        return students.containsKey(studentId);
    }
}