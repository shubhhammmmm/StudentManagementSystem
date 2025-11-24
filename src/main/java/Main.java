import model.Student;
import model.Course;
import model.Grade;
import service.StudentService;
import service.CourseService;
import service.GradeService;
import util.Validator;

import java.util.Scanner;
import java.time.LocalDate;
import java.util.List;

public class Main {
    private static StudentService studentService = new StudentService();
    private static CourseService courseService = new CourseService();
    private static GradeService gradeService = new GradeService();
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("=== Student Management System ===");
        System.out.println("Version 1.0 - Developed for VITyarthi");
        showMainMenu();
    }
    
    private static void showMainMenu() {
        while (true) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Student Management");
            System.out.println("2. Course Management");
            System.out.println("3. Grade Management");
            System.out.println("4. Reports & Analytics");
            System.out.println("5. System Information");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            
            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                
                switch (choice) {
                    case 1:
                        showStudentMenu();
                        break;
                    case 2:
                        showCourseMenu();
                        break;
                    case 3:
                        showGradeMenu();
                        break;
                    case 4:
                        showReportsMenu();
                        break;
                    case 5:
                        showSystemInfo();
                        break;
                    case 6:
                        System.out.println("Thank you for using Student Management System!");
                        System.out.println("Goodbye!");
                        return;
                    default:
                        System.out.println("Invalid option! Please choose 1-6.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.nextLine(); // Clear invalid input
            }
        }
    }
    
    private static void showStudentMenu() {
        while (true) {
            System.out.println("\n--- Student Management ---");
            System.out.println("1. Add New Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. View Student Details");
            System.out.println("7. Back to Main Menu");
            System.out.print("Choose an option: ");
            
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();
                
                switch (choice) {
                    case 1:
                        addStudent();
                        break;
                    case 2:
                        viewAllStudents();
                        break;
                    case 3:
                        searchStudent();
                        break;
                    case 4:
                        updateStudent();
                        break;
                    case 5:
                        deleteStudent();
                        break;
                    case 6:
                        viewStudentDetails();
                        break;
                    case 7:
                        return;
                    default:
                        System.out.println("Invalid option! Please choose 1-7.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.nextLine();
            }
        }
    }
    
    private static void addStudent() {
        System.out.println("\n--- Add New Student ---");
        System.out.print("Enter Student ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Department: ");
        String department = scanner.nextLine();
        System.out.print("Enter Phone Number: ");
        String phone = scanner.nextLine();
        
        Student student = new Student(id, firstName, lastName, email, LocalDate.now(), department, phone);
        
        try {
            if (studentService.registerStudent(student)) {
                System.out.println("✅ Student added successfully!");
            } else {
                System.out.println("❌ Failed to add student. Student ID might already exist.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
    
    private static void viewAllStudents() {
        System.out.println("\n--- All Students ---");
        List<Student> students = studentService.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            System.out.println("Total Students: " + students.size());
            System.out.println("------------------------------------------------------------");
            for (int i = 0; i < students.size(); i++) {
                System.out.println((i + 1) + ". " + students.get(i));
            }
        }
    }
    
    private static void searchStudent() {
        System.out.print("Enter search keyword: ");
        String keyword = scanner.nextLine();
        
        try {
            List<Student> results = studentService.searchStudents(keyword);
            if (results.isEmpty()) {
                System.out.println("No students found matching your search.");
            } else {
                System.out.println("Search Results (" + results.size() + " found):");
                System.out.println("------------------------------------------------------------");
                for (int i = 0; i < results.size(); i++) {
                    System.out.println((i + 1) + ". " + results.get(i));
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
    
    private static void updateStudent() {
        System.out.print("Enter Student ID to update: ");
        String id = scanner.nextLine();
        
        Student student = studentService.getStudent(id);
        if (student == null) {
            System.out.println("❌ Student not found!");
            return;
        }
        
        System.out.println("Current details: " + student);
        System.out.println("\nEnter new details (press enter to keep current value):");
        
        System.out.print("First Name [" + student.getFirstName() + "]: ");
        String firstName = scanner.nextLine();
        if (!firstName.isEmpty()) student.setFirstName(firstName);
        
        System.out.print("Last Name [" + student.getLastName() + "]: ");
        String lastName = scanner.nextLine();
        if (!lastName.isEmpty()) student.setLastName(lastName);
        
        System.out.print("Email [" + student.getEmail() + "]: ");
        String email = scanner.nextLine();
        if (!email.isEmpty()) student.setEmail(email);
        
        System.out.print("Department [" + student.getDepartment() + "]: ");
        String department = scanner.nextLine();
        if (!department.isEmpty()) student.setDepartment(department);
        
        System.out.print("Phone Number [" + student.getPhoneNumber() + "]: ");
        String phone = scanner.nextLine();
        if (!phone.isEmpty()) student.setPhoneNumber(phone);
        
        try {
            if (studentService.updateStudent(student)) {
                System.out.println("✅ Student updated successfully!");
            } else {
                System.out.println("❌ Failed to update student.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
    
    private static void deleteStudent() {
        System.out.print("Enter Student ID to delete: ");
        String id = scanner.nextLine();
        
        Student student = studentService.getStudent(id);
        if (student == null) {
            System.out.println("❌ Student not found!");
            return;
        }
        
        System.out.println("Student to delete: " + student);
        System.out.print("Are you sure you want to delete this student? (y/n): ");
        String confirmation = scanner.nextLine();
        
        if (confirmation.equalsIgnoreCase("y")) {
            try {
                if (studentService.deleteStudent(id)) {
                    System.out.println("✅ Student deleted successfully!");
                } else {
                    System.out.println("❌ Student not found or deletion failed.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("❌ Error: " + e.getMessage());
            }
        } else {
            System.out.println("Deletion cancelled.");
        }
    }
    
    private static void viewStudentDetails() {
        System.out.print("Enter Student ID: ");
        String id = scanner.nextLine();
        
        Student student = studentService.getStudent(id);
        if (student == null) {
            System.out.println("❌ Student not found!");
            return;
        }
        
        System.out.println("\n--- Student Details ---");
        System.out.println(student);
        
        // Show student's grades and GPA
        try {
            List<Grade> grades = gradeService.getStudentGrades(id);
            if (!grades.isEmpty()) {
                System.out.println("\n--- Academic Record ---");
                for (Grade grade : grades) {
                    System.out.println("  - " + grade);
                }
                double gpa = gradeService.calculateStudentGPA(id);
                String standing = gradeService.getStudentAcademicStanding(id);
                System.out.printf("\nGPA: %.2f | Academic Standing: %s\n", gpa, standing);
            } else {
                System.out.println("\nNo grades recorded for this student.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
    
    private static void showCourseMenu() {
        while (true) {
            System.out.println("\n--- Course Management ---");
            System.out.println("1. Add New Course");
            System.out.println("2. View All Courses");
            System.out.println("3. Search Course");
            System.out.println("4. Update Course");
            System.out.println("5. Delete Course");
            System.out.println("6. View Course Details");
            System.out.println("7. Back to Main Menu");
            System.out.print("Choose an option: ");
            
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();
                
                switch (choice) {
                    case 1:
                        addCourse();
                        break;
                    case 2:
                        viewAllCourses();
                        break;
                    case 3:
                        searchCourse();
                        break;
                    case 4:
                        updateCourse();
                        break;
                    case 5:
                        deleteCourse();
                        break;
                    case 6:
                        viewCourseDetails();
                        break;
                    case 7:
                        return;
                    default:
                        System.out.println("Invalid option! Please choose 1-7.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.nextLine();
            }
        }
    }
    
    private static void addCourse() {
        System.out.println("\n--- Add New Course ---");
        System.out.print("Enter Course ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Course Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Instructor: ");
        String instructor = scanner.nextLine();
        System.out.print("Enter Credits: ");
        int credits = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Department: ");
        String department = scanner.nextLine();
        System.out.print("Enter Maximum Students: ");
        int maxStudents = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Schedule: ");
        String schedule = scanner.nextLine();
        
        Course course = new Course(id, name, instructor, credits, department, maxStudents, schedule);
        
        try {
            if (courseService.addCourse(course)) {
                System.out.println("✅ Course added successfully!");
            } else {
                System.out.println("❌ Failed to add course. Course ID might already exist.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
    
    private static void viewAllCourses() {
        System.out.println("\n--- All Courses ---");
        List<Course> courses = courseService.getAllCourses();
        if (courses.isEmpty()) {
            System.out.println("No courses found.");
        } else {
            System.out.println("Total Courses: " + courses.size());
            System.out.println("------------------------------------------------------------");
            for (int i = 0; i < courses.size(); i++) {
                System.out.println((i + 1) + ". " + courses.get(i));
            }
        }
    }
    
    private static void searchCourse() {
        System.out.print("Enter search keyword: ");
        String keyword = scanner.nextLine();
        
        try {
            List<Course> results = courseService.searchCourses(keyword);
            if (results.isEmpty()) {
                System.out.println("No courses found matching your search.");
            } else {
                System.out.println("Search Results (" + results.size() + " found):");
                System.out.println("------------------------------------------------------------");
                for (int i = 0; i < results.size(); i++) {
                    System.out.println((i + 1) + ". " + results.get(i));
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
    
    private static void updateCourse() {
        System.out.print("Enter Course ID to update: ");
        String id = scanner.nextLine();
        
        Course course = courseService.getCourse(id);
        if (course == null) {
            System.out.println("❌ Course not found!");
            return;
        }
        
        System.out.println("Current details: " + course);
        System.out.println("\nEnter new details (press enter to keep current value):");
        
        System.out.print("Course Name [" + course.getCourseName() + "]: ");
        String name = scanner.nextLine();
        if (!name.isEmpty()) course.setCourseName(name);
        
        System.out.print("Instructor [" + course.getInstructor() + "]: ");
        String instructor = scanner.nextLine();
        if (!instructor.isEmpty()) course.setInstructor(instructor);
        
        System.out.print("Credits [" + course.getCredits() + "]: ");
        String creditsStr = scanner.nextLine();
        if (!creditsStr.isEmpty()) {
            try {
                course.setCredits(Integer.parseInt(creditsStr));
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid credits value. Keeping current value.");
            }
        }
        
        System.out.print("Department [" + course.getDepartment() + "]: ");
        String department = scanner.nextLine();
        if (!department.isEmpty()) course.setDepartment(department);
        
        System.out.print("Max Students [" + course.getMaxStudents() + "]: ");
        String maxStr = scanner.nextLine();
        if (!maxStr.isEmpty()) {
            try {
                course.setMaxStudents(Integer.parseInt(maxStr));
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid max students value. Keeping current value.");
            }
        }
        
        System.out.print("Schedule [" + course.getSchedule() + "]: ");
        String schedule = scanner.nextLine();
        if (!schedule.isEmpty()) course.setSchedule(schedule);
        
        try {
            if (courseService.updateCourse(course)) {
                System.out.println("✅ Course updated successfully!");
            } else {
                System.out.println("❌ Failed to update course.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
    
    private static void deleteCourse() {
        System.out.print("Enter Course ID to delete: ");
        String id = scanner.nextLine();
        
        Course course = courseService.getCourse(id);
        if (course == null) {
            System.out.println("❌ Course not found!");
            return;
        }
        
        System.out.println("Course to delete: " + course);
        System.out.print("Are you sure you want to delete this course? (y/n): ");
        String confirmation = scanner.nextLine();
        
        if (confirmation.equalsIgnoreCase("y")) {
            try {
                if (courseService.deleteCourse(id)) {
                    System.out.println("✅ Course deleted successfully!");
                } else {
                    System.out.println("❌ Course not found or deletion failed.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("❌ Error: " + e.getMessage());
            }
        } else {
            System.out.println("Deletion cancelled.");
        }
    }
    
private static void viewCourseDetails() {
        System.out.print("Enter Course ID: ");
        String id = scanner.nextLine();
        
        Course course = courseService.getCourse(id);
        if (course == null) {
            System.out.println("❌ Course not found!");
            return;
        }
        
        System.out.println("\n--- Course Details ---");
        System.out.println(course);
        
        // Show course grades and statistics
        try {
            List<Grade> grades = gradeService.getCourseGrades(id);
            if (!grades.isEmpty()) {
                System.out.println("\n--- Course Grades ---");
                for (Grade grade : grades) {
                    System.out.println("  - " + grade);
                }
                double average = gradeService.calculateCourseAverage(id);
                System.out.printf("\nCourse Average: %.2f\n", average);
                System.out.println("Total Students Enrolled: " + grades.size());
            } else {
                System.out.println("\nNo grades recorded for this course.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
    
    private static void showGradeMenu() {
        while (true) {
            System.out.println("\n--- Grade Management ---");
            System.out.println("1. Assign Grade");
            System.out.println("2. View All Grades");
            System.out.println("3. Update Grade");
            System.out.println("4. Delete Grade");
            System.out.println("5. View Student Grades");
            System.out.println("6. View Course Grades");
            System.out.println("7. Back to Main Menu");
            System.out.print("Choose an option: ");
            
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();
                
                switch (choice) {
                    case 1:
                        assignGrade();
                        break;
                    case 2:
                        viewAllGrades();
                        break;
                    case 3:
                        updateGrade();
                        break;
                    case 4:
                        deleteGrade();
                        break;
                    case 5:
                        viewStudentGrades();
                        break;
                    case 6:
                        viewCourseGrades();
                        break;
                    case 7:
                        return;
                    default:
                        System.out.println("Invalid option! Please choose 1-7.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.nextLine();
            }
        }
    }
    
    private static void assignGrade() {
        System.out.println("\n--- Assign Grade ---");
        System.out.print("Enter Grade ID: ");
        String gradeId = scanner.nextLine();
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();
        System.out.print("Enter Course ID: ");
        String courseId = scanner.nextLine();
        System.out.print("Enter Score (0-100): ");
        double score = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Enter Semester: ");
        String semester = scanner.nextLine();
        
        Grade grade = new Grade(gradeId, studentId, courseId, score, semester);
        
        try {
            if (gradeService.assignGrade(grade)) {
                System.out.println("✅ Grade assigned successfully!");
            } else {
                System.out.println("❌ Failed to assign grade. Grade ID might already exist.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
    
    private static void viewAllGrades() {
        System.out.println("\n--- All Grades ---");
        List<Grade> grades = gradeService.getAllGrades();
        if (grades.isEmpty()) {
            System.out.println("No grades found.");
        } else {
            System.out.println("Total Grades: " + grades.size());
            System.out.println("------------------------------------------------------------");
            for (int i = 0; i < grades.size(); i++) {
                System.out.println((i + 1) + ". " + grades.get(i));
            }
        }
    }
    
    private static void updateGrade() {
        System.out.print("Enter Grade ID to update: ");
        String gradeId = scanner.nextLine();
        
        Grade grade = gradeService.getGrade(gradeId);
        if (grade == null) {
            System.out.println("❌ Grade not found!");
            return;
        }
        
        System.out.println("Current grade: " + grade);
        System.out.print("Enter new score (0-100): ");
        double newScore = scanner.nextDouble();
        scanner.nextLine();
        
        grade.setScore(newScore);
        
        try {
            if (gradeService.updateGrade(grade)) {
                System.out.println("✅ Grade updated successfully!");
            } else {
                System.out.println("❌ Failed to update grade.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
    
    private static void deleteGrade() {
        System.out.print("Enter Grade ID to delete: ");
        String gradeId = scanner.nextLine();
        
        Grade grade = gradeService.getGrade(gradeId);
        if (grade == null) {
            System.out.println("❌ Grade not found!");
            return;
        }
        
        System.out.println("Grade to delete: " + grade);
        System.out.print("Are you sure you want to delete this grade? (y/n): ");
        String confirmation = scanner.nextLine();
        
        if (confirmation.equalsIgnoreCase("y")) {
            try {
                if (gradeService.deleteGrade(gradeId)) {
                    System.out.println("✅ Grade deleted successfully!");
                } else {
                    System.out.println("❌ Grade not found or deletion failed.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("❌ Error: " + e.getMessage());
            }
        } else {
            System.out.println("Deletion cancelled.");
        }
    }
    
    private static void viewStudentGrades() {
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();
        
        try {
            List<Grade> grades = gradeService.getStudentGrades(studentId);
            if (grades.isEmpty()) {
                System.out.println("No grades found for this student.");
            } else {
                System.out.println("\n--- Grades for Student " + studentId + " ---");
                for (Grade grade : grades) {
                    System.out.println("  - " + grade);
                }
                double gpa = gradeService.calculateStudentGPA(studentId);
                String standing = gradeService.getStudentAcademicStanding(studentId);
                System.out.printf("\nGPA: %.2f | Academic Standing: %s\n", gpa, standing);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
    
    private static void viewCourseGrades() {
        System.out.print("Enter Course ID: ");
        String courseId = scanner.nextLine();
        
        try {
            List<Grade> grades = gradeService.getCourseGrades(courseId);
            if (grades.isEmpty()) {
                System.out.println("No grades found for this course.");
            } else {
                System.out.println("\n--- Grades for Course " + courseId + " ---");
                for (Grade grade : grades) {
                    System.out.println("  - " + grade);
                }
                double average = gradeService.calculateCourseAverage(courseId);
                System.out.printf("\nCourse Average: %.2f\n", average);
                System.out.println("Total Students: " + grades.size());
            }
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
    
    private static void showReportsMenu() {
        while (true) {
            System.out.println("\n--- Reports & Analytics ---");
            System.out.println("1. System Statistics");
            System.out.println("2. Student Academic Report");
            System.out.println("3. Course Performance Report");
            System.out.println("4. Department Overview");
            System.out.println("5. Back to Main Menu");
            System.out.print("Choose an option: ");
            
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();
                
                switch (choice) {
                    case 1:
                        showSystemStatistics();
                        break;
                    case 2:
                        generateStudentReport();
                        break;
                    case 3:
                        generateCourseReport();
                        break;
                    case 4:
                        showDepartmentOverview();
                        break;
                    case 5:
                        return;
                    default:
                        System.out.println("Invalid option! Please choose 1-5.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.nextLine();
            }
        }
    }
    
    private static void showSystemStatistics() {
        System.out.println("\n--- System Statistics ---");
        System.out.println("Total Students: " + studentService.getTotalStudents());
        System.out.println("Total Courses: " + courseService.getTotalCourses());
        System.out.println("Total Grades Recorded: " + gradeService.getAllGrades().size());
        
        // Additional statistics
        List<Student> students = studentService.getAllStudents();
        if (!students.isEmpty()) {
            System.out.println("\n--- Department Distribution ---");
            // This would be more sophisticated in a real system
            System.out.println("Sample data loaded for demonstration");
        }
    }
private static void generateStudentReport() {
        System.out.print("Enter Student ID for report: ");
        String studentId = scanner.nextLine();
        
        Student student = studentService.getStudent(studentId);
        if (student == null) {
            System.out.println("❌ Student not found!");
            return;
        }
        
        System.out.println("\n--- Academic Report for " + student.getFirstName() + " " + student.getLastName() + " ---");
        System.out.println("Student ID: " + student.getStudentId());
        System.out.println("Department: " + student.getDepartment());
        System.out.println("Enrollment Date: " + student.getEnrollmentDate());
        
        try {
            List<Grade> grades = gradeService.getStudentGrades(studentId);
            if (!grades.isEmpty()) {
                System.out.println("\n--- Course Grades ---");
                System.out.println("Course ID\tScore\tGrade\tSemester");
                System.out.println("---------\t-----\t-----\t--------");
                for (Grade grade : grades) {
                    System.out.printf("%s\t\t%.2f\t%s\t%s\n", 
                            grade.getCourseId(), grade.getScore(), grade.getLetterGrade(), grade.getSemester());
                }
                
                double gpa = gradeService.calculateStudentGPA(studentId);
                String standing = gradeService.getStudentAcademicStanding(studentId);
                System.out.printf("\nOverall GPA: %.2f\n", gpa);
                System.out.println("Academic Standing: " + standing);
            } else {
                System.out.println("\nNo grades recorded for this student.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
    
    private static void generateCourseReport() {
        System.out.print("Enter Course ID for report: ");
        String courseId = scanner.nextLine();
        
        Course course = courseService.getCourse(courseId);
        if (course == null) {
            System.out.println("❌ Course not found!");
            return;
        }
        
        System.out.println("\n--- Course Performance Report ---");
        System.out.println("Course: " + course.getCourseName() + " (" + courseId + ")");
        System.out.println("Instructor: " + course.getInstructor());
        System.out.println("Credits: " + course.getCredits());
        
        try {
            List<Grade> grades = gradeService.getCourseGrades(courseId);
            if (!grades.isEmpty()) {
                System.out.println("\n--- Student Grades ---");
                System.out.println("Student ID\tScore\tGrade\tSemester");
                System.out.println("----------\t-----\t-----\t--------");
                for (Grade grade : grades) {
                    System.out.printf("%s\t\t%.2f\t%s\t%s\n", 
                            grade.getStudentId(), grade.getScore(), grade.getLetterGrade(), grade.getSemester());
                }
                
                double average = gradeService.calculateCourseAverage(courseId);
                System.out.printf("\nCourse Average: %.2f\n", average);
                System.out.println("Total Students: " + grades.size());
                
                // Grade distribution
                int aCount = 0, bCount = 0, cCount = 0, dCount = 0, fCount = 0;
                for (Grade grade : grades) {
                    switch (grade.getLetterGrade()) {
                        case "A": aCount++; break;
                        case "B": bCount++; break;
                        case "C": cCount++; break;
                        case "D": dCount++; break;
                        case "F": fCount++; break;
                    }
                }
                
                System.out.println("\n--- Grade Distribution ---");
                System.out.println("A: " + aCount + " students");
                System.out.println("B: " + bCount + " students");
                System.out.println("C: " + cCount + " students");
                System.out.println("D: " + dCount + " students");
                System.out.println("F: " + fCount + " students");
            } else {
                System.out.println("\nNo grades recorded for this course.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
    
    private static void showDepartmentOverview() {
        System.out.println("\n--- Department Overview ---");
        System.out.println("This feature would show statistics by department");
        System.out.println("in a more comprehensive implementation.");
        System.out.println("\nCurrent departments with data:");
        System.out.println("- Computer Science");
        System.out.println("- Mathematics"); 
        System.out.println("- Physics");
    }
    
    private static void showSystemInfo() {
        System.out.println("\n--- System Information ---");
        System.out.println("Student Management System v1.0");
        System.out.println("Developed for VITyarthi Platform");
        System.out.println("\nFeatures:");
        System.out.println("✅ Student Management (CRUD operations)");
        System.out.println("✅ Course Management (CRUD operations)");
        System.out.println("✅ Grade Management and Assignment");
        System.out.println("✅ GPA Calculation");
        System.out.println("✅ Academic Standing Evaluation");
        System.out.println("✅ Search and Reporting");
        System.out.println("✅ Data Validation and Error Handling");
        System.out.println("\nTechnical Stack:");
        System.out.println("- Java 11+");
        System.out.println("- Object-Oriented Design");
        System.out.println("- Layered Architecture (Model-Repository-Service)");
        System.out.println("- File-based Persistence");
        System.out.println("- Input Validation");
    }
}