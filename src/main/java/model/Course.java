package model;

public class Course {
    private String courseId;
    private String courseName;
    private String instructor;
    private int credits;
    private String department;
    private int maxStudents;
    private String schedule;
    
    public Course(String courseId, String courseName, String instructor, 
                  int credits, String department, int maxStudents, String schedule) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.instructor = instructor;
        this.credits = credits;
        this.department = department;
        this.maxStudents = maxStudents;
        this.schedule = schedule;
    }
    
    // Getters and Setters
    public String getCourseId() { return courseId; }
    public void setCourseId(String courseId) { this.courseId = courseId; }
    
    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }
    
    public String getInstructor() { return instructor; }
    public void setInstructor(String instructor) { this.instructor = instructor; }
    
    public int getCredits() { return credits; }
    public void setCredits(int credits) { this.credits = credits; }
    
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    
    public int getMaxStudents() { return maxStudents; }
    public void setMaxStudents(int maxStudents) { this.maxStudents = maxStudents; }
    
    public String getSchedule() { return schedule; }
    public void setSchedule(String schedule) { this.schedule = schedule; }
    
    @Override
    public String toString() {
        return String.format("Course{ID: %s, Name: %s, Instructor: %s, Credits: %d, Max Students: %d, Schedule: %s}", 
                courseId, courseName, instructor, credits, maxStudents, schedule);
    }
    
    public String toCSV() {
        return String.format("%s,%s,%s,%d,%s,%d,%s", 
                courseId, courseName, instructor, credits, department, maxStudents, schedule);
    }
}