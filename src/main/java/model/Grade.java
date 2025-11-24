package model;

public class Grade {
    private String gradeId;
    private String studentId;
    private String courseId;
    private double score;
    private String letterGrade;
    private String semester;
    
    public Grade(String gradeId, String studentId, String courseId, double score, String semester) {
        this.gradeId = gradeId;
        this.studentId = studentId;
        this.courseId = courseId;
        this.score = score;
        this.semester = semester;
        this.letterGrade = calculateLetterGrade(score);
    }
    
    private String calculateLetterGrade(double score) {
        if (score >= 90) return "A";
        else if (score >= 80) return "B";
        else if (score >= 70) return "C";
        else if (score >= 60) return "D";
        else return "F";
    }
    
    // Getters and Setters
    public String getGradeId() { return gradeId; }
    public void setGradeId(String gradeId) { this.gradeId = gradeId; }
    
    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }
    
    public String getCourseId() { return courseId; }
    public void setCourseId(String courseId) { this.courseId = courseId; }
    
    public double getScore() { return score; }
    public void setScore(double score) { 
        this.score = score;
        this.letterGrade = calculateLetterGrade(score);
    }
    
    public String getLetterGrade() { return letterGrade; }
    
    public String getSemester() { return semester; }
    public void setSemester(String semester) { this.semester = semester; }
    
    @Override
    public String toString() {
        return String.format("Grade{ID: %s, Student: %s, Course: %s, Score: %.2f, Grade: %s, Semester: %s}", 
                gradeId, studentId, courseId, score, letterGrade, semester);
    }
    
    public String toCSV() {
        return String.format("%s,%s,%s,%.2f,%s,%s", 
                gradeId, studentId, courseId, score, semester, letterGrade);
    }
}