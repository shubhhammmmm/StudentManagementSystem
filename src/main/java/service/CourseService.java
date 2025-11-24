package service;

import model.Course;
import repository.CourseRepository;
import util.Validator;
import java.util.List;

public class CourseService {
    private CourseRepository courseRepository;
    
    public CourseService() {
        this.courseRepository = new CourseRepository();
    }
    
    public boolean addCourse(Course course) {
        if (!Validator.isValidId(course.getCourseId())) {
            throw new IllegalArgumentException("Course ID cannot be empty and must be alphanumeric");
        }
        if (course.getCourseName() == null || course.getCourseName().trim().isEmpty()) {
            throw new IllegalArgumentException("Course name cannot be empty");
        }
        if (course.getInstructor() == null || course.getInstructor().trim().isEmpty()) {
            throw new IllegalArgumentException("Instructor name cannot be empty");
        }
        if (course.getCredits() <= 0 || course.getCredits() > 5) {
            throw new IllegalArgumentException("Credits must be between 1 and 5");
        }
        if (course.getMaxStudents() <= 0) {
            throw new IllegalArgumentException("Maximum students must be positive");
        }
        
        return courseRepository.addCourse(course);
    }
    
    public Course getCourse(String courseId) {
        return courseRepository.getCourse(courseId);
    }
    
    public List<Course> getAllCourses() {
        return courseRepository.getAllCourses();
    }
    
    public boolean updateCourse(Course course) {
        if (!courseRepository.courseExists(course.getCourseId())) {
            throw new IllegalArgumentException("Course not found");
        }
        return courseRepository.updateCourse(course);
    }
    
    public boolean deleteCourse(String courseId) {
        if (!courseRepository.courseExists(courseId)) {
            throw new IllegalArgumentException("Course not found");
        }
        return courseRepository.deleteCourse(courseId);
    }
    
    public List<Course> searchCourses(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            throw new IllegalArgumentException("Search keyword cannot be empty");
        }
        return courseRepository.searchCourses(keyword);
    }
    
    public int getTotalCourses() {
        return courseRepository.getAllCourses().size();
    }
    
    public boolean courseExists(String courseId) {
        return courseRepository.courseExists(courseId);
    }
}