package service;

import model.Grade;
import repository.GradeRepository;
import repository.StudentRepository;
import repository.CourseRepository;
import util.Validator;
import java.util.List;

public class GradeService {
    private GradeRepository gradeRepository;
    private StudentRepository studentRepository;
    private CourseRepository courseRepository;
    
    public GradeService() {
        this.gradeRepository = new GradeRepository();
        this.studentRepository = new StudentRepository();
        this.courseRepository = new CourseRepository();
    }
    
    public boolean assignGrade(Grade grade) {
        // Validate grade data
        if (!Validator.isValidId(grade.getGradeId())) {
            throw new IllegalArgumentException("Grade ID cannot be empty and must be alphanumeric");
        }
        if (!Validator.isValidScore(grade.getScore())) {
            throw new IllegalArgumentException("Score must be between 0 and 100");
        }
        if (grade.getSemester() == null || grade.getSemester().trim().isEmpty()) {
            throw new IllegalArgumentException("Semester cannot be empty");
        }
        
        // Check if student exists
        if (!studentRepository.studentExists(grade.getStudentId())) {
            throw new IllegalArgumentException("Student not found");
        }
        
        // Check if course exists
        if (!courseRepository.courseExists(grade.getCourseId())) {
            throw new IllegalArgumentException("Course not found");
        }
        
        // Check if grade already assigned for this student/course/semester
        if (gradeRepository.isGradeAssigned(grade.getStudentId(), grade.getCourseId(), grade.getSemester())) {
            throw new IllegalArgumentException("Grade already assigned for this student, course, and semester");
        }
        
        return gradeRepository.addGrade(grade);
    }
    
    public Grade getGrade(String gradeId) {
        return gradeRepository.getGrade(gradeId);
    }
    
    public List<Grade> getAllGrades() {
        return gradeRepository.getAllGrades();
    }
    
    public List<Grade> getStudentGrades(String studentId) {
        if (!studentRepository.studentExists(studentId)) {
            throw new IllegalArgumentException("Student not found");
        }
        return gradeRepository.getGradesByStudent(studentId);
    }
    
    public List<Grade> getCourseGrades(String courseId) {
        if (!courseRepository.courseExists(courseId)) {
            throw new IllegalArgumentException("Course not found");
        }
        return gradeRepository.getGradesByCourse(courseId);
    }
    
    public boolean updateGrade(Grade grade) {
        if (!gradeRepository.gradeExists(grade.getGradeId())) {
            throw new IllegalArgumentException("Grade not found");
        }
        if (!Validator.isValidScore(grade.getScore())) {
            throw new IllegalArgumentException("Score must be between 0 and 100");
        }
        return gradeRepository.updateGrade(grade);
    }
    
    public boolean deleteGrade(String gradeId) {
        if (!gradeRepository.gradeExists(gradeId)) {
            throw new IllegalArgumentException("Grade not found");
        }
        return gradeRepository.deleteGrade(gradeId);
    }
    
    public double calculateStudentGPA(String studentId) {
        if (!studentRepository.studentExists(studentId)) {
            throw new IllegalArgumentException("Student not found");
        }
        
        List<Grade> studentGrades = gradeRepository.getGradesByStudent(studentId);
        if (studentGrades.isEmpty()) {
            return 0.0;
        }
        
        double totalPoints = 0;
        int totalCredits = 0;
        
        for (Grade grade : studentGrades) {
            double gradePoints = convertGradeToPoints(grade.getLetterGrade());
            // In a real system, we'd get actual credits from the course
            int credits = 3; // Default credits per course
            totalPoints += gradePoints * credits;
            totalCredits += credits;
        }
        
        return totalCredits > 0 ? (totalPoints / totalCredits) : 0.0;
    }
    
    public String getStudentAcademicStanding(String studentId) {
        double gpa = calculateStudentGPA(studentId);
        if (gpa >= 3.5) return "Honors";
        else if (gpa >= 2.0) return "Good Standing";
        else return "Academic Probation";
    }
    
    public double calculateCourseAverage(String courseId) {
        if (!courseRepository.courseExists(courseId)) {
            throw new IllegalArgumentException("Course not found");
        }
        
        List<Grade> courseGrades = gradeRepository.getGradesByCourse(courseId);
        if (courseGrades.isEmpty()) {
            return 0.0;
        }
        
        double total = 0;
        for (Grade grade : courseGrades) {
            total += grade.getScore();
        }
        
        return total / courseGrades.size();
    }
    
    private double convertGradeToPoints(String letterGrade) {
        switch (letterGrade) {
            case "A": return 4.0;
            case "B": return 3.0;
            case "C": return 2.0;
            case "D": return 1.0;
            case "F": return 0.0;
            default: return 0.0;
        }
    }
}