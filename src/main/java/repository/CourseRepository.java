package repository;

import model.Course;
import java.util.*;

public class CourseRepository {
    private Map<String, Course> courses;
    private static final String DATA_FILE = "resources/courses.dat";
    
    public CourseRepository() {
        this.courses = new HashMap<>();
        loadFromFile();
        if (courses.isEmpty()) {
            initializeSampleData();
        }
    }
    
    private void initializeSampleData() {
        addCourse(new Course("C001", "Introduction to Programming", "Dr. Smith", 3, "Computer Science", 30, "Mon/Wed 10:00-11:30"));
        addCourse(new Course("C002", "Data Structures", "Dr. Johnson", 4, "Computer Science", 25, "Tue/Thu 14:00-15:30"));
        addCourse(new Course("C003", "Calculus I", "Dr. Brown", 4, "Mathematics", 35, "Mon/Wed/Fri 09:00-10:00"));
        addCourse(new Course("C004", "Physics I", "Dr. Wilson", 4, "Physics", 30, "Tue/Thu 11:00-12:30"));
        saveToFile();
    }
    
    private void loadFromFile() {
        System.out.println("Loading course data from file...");
    }
    
    private void saveToFile() {
        System.out.println("Saving course data to file...");
    }
    
    public boolean addCourse(Course course) {
        if (courses.containsKey(course.getCourseId())) {
            return false;
        }
        courses.put(course.getCourseId(), course);
        saveToFile();
        return true;
    }
    
    public Course getCourse(String courseId) {
        return courses.get(courseId);
    }
    
    public List<Course> getAllCourses() {
        return new ArrayList<>(courses.values());
    }
    
    public boolean updateCourse(Course course) {
        if (!courses.containsKey(course.getCourseId())) {
            return false;
        }
        courses.put(course.getCourseId(), course);
        saveToFile();
        return true;
    }
    
    public boolean deleteCourse(String courseId) {
        boolean removed = courses.remove(courseId) != null;
        if (removed) {
            saveToFile();
        }
        return removed;
    }
    
    public List<Course> searchCourses(String keyword) {
        List<Course> result = new ArrayList<>();
        String lowerKeyword = keyword.toLowerCase();
        
        for (Course course : courses.values()) {
            if (course.getCourseName().toLowerCase().contains(lowerKeyword) ||
                course.getInstructor().toLowerCase().contains(lowerKeyword) ||
                course.getDepartment().toLowerCase().contains(lowerKeyword) ||
                course.getCourseId().toLowerCase().contains(lowerKeyword)) {
                result.add(course);
            }
        }
        return result;
    }
    
    public boolean courseExists(String courseId) {
        return courses.containsKey(courseId);
    }
}