package repository;

import model.Grade;
import java.util.*;

public class GradeRepository {
    private Map<String, Grade> grades;
    private static final String DATA_FILE = "resources/grades.dat";
    
    public GradeRepository() {
        this.grades = new HashMap<>();
        loadFromFile();
        if (grades.isEmpty()) {
            initializeSampleData();
        }
    }
    
    private void initializeSampleData() {
        addGrade(new Grade("G001", "S001", "C001", 85.5, "Fall2023"));
        addGrade(new Grade("G002", "S001", "C002", 92.0, "Fall2023"));
        addGrade(new Grade("G003", "S002", "C001", 78.5, "Fall2023"));
        addGrade(new Grade("G004", "S002", "C003", 88.0, "Fall2023"));
        addGrade(new Grade("G005", "S003", "C004", 91.5, "Fall2023"));
        saveToFile();
    }
    
    private void loadFromFile() {
        System.out.println("Loading grade data from file...");
    }
    
    private void saveToFile() {
        System.out.println("Saving grade data to file...");
    }
    
    public boolean addGrade(Grade grade) {
        if (grades.containsKey(grade.getGradeId())) {
            return false;
        }
        grades.put(grade.getGradeId(), grade);
        saveToFile();
        return true;
    }
    
    public Grade getGrade(String gradeId) {
        return grades.get(gradeId);
    }
    
    public List<Grade> getAllGrades() {
        return new ArrayList<>(grades.values());
    }
    
    public List<Grade> getGradesByStudent(String studentId) {
        List<Grade> studentGrades = new ArrayList<>();
        for (Grade grade : grades.values()) {
            if (grade.getStudentId().equals(studentId)) {
                studentGrades.add(grade);
            }
        }
        return studentGrades;
    }
    
    public List<Grade> getGradesByCourse(String courseId) {
        List<Grade> courseGrades = new ArrayList<>();
        for (Grade grade : grades.values()) {
            if (grade.getCourseId().equals(courseId)) {
                courseGrades.add(grade);
            }
        }
        return courseGrades;
    }
    
    public boolean updateGrade(Grade grade) {
        if (!grades.containsKey(grade.getGradeId())) {
            return false;
        }
        grades.put(grade.getGradeId(), grade);
        saveToFile();
        return true;
    }
    
    public boolean deleteGrade(String gradeId) {
        boolean removed = grades.remove(gradeId) != null;
        if (removed) {
            saveToFile();
        }
        return removed;
    }
    
    public boolean gradeExists(String gradeId) {
        return grades.containsKey(gradeId);
    }
    
    public boolean isGradeAssigned(String studentId, String courseId, String semester) {
        for (Grade grade : grades.values()) {
            if (grade.getStudentId().equals(studentId) && 
                grade.getCourseId().equals(courseId) && 
                grade.getSemester().equals(semester)) {
                return true;
            }
        }
        return false;
    }
}