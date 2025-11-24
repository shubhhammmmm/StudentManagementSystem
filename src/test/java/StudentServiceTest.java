import model.Student;
import service.StudentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

public class StudentServiceTest {
    private StudentService studentService;
    
    @BeforeEach
    void setUp() {
        studentService = new StudentService();
    }
    
    @Test
    void testRegisterValidStudent() {
        Student student = new Student("S100", "Test", "User", 
                "test@university.edu", LocalDate.now(), "Computer Science", "123-456-7890");
        
        boolean result = studentService.registerStudent(student);
        assertTrue(result);
        assertTrue(studentService.getTotalStudents() >= 3); // 2 sample + 1 new
    }
    
    @Test
    void testRegisterStudentWithInvalidEmail() {
        Student student = new Student("S101", "Test", "User", 
                "invalid-email", LocalDate.now(), "Computer Science", "123-456-7890");
        
        assertThrows(IllegalArgumentException.class, () -> {
            studentService.registerStudent(student);
        });
    }
    
    @Test
    void testRegisterStudentWithEmptyId() {
        Student student = new Student("", "Test", "User", 
                "test@university.edu", LocalDate.now(), "Computer Science", "123-456-7890");
        
        assertThrows(IllegalArgumentException.class, () -> {
            studentService.registerStudent(student);
        });
    }
    
    @Test
    void testSearchStudents() {
        var results = studentService.searchStudents("john");
        assertFalse(results.isEmpty());
        assertEquals("John", results.get(0).getFirstName());
    }
    
    @Test
    void testSearchWithEmptyKeyword() {
        assertThrows(IllegalArgumentException.class, () -> {
            studentService.searchStudents("");
        });
    }
    
    @Test
    void testStudentExists() {
        assertTrue(studentService.studentExists("S001"));
        assertFalse(studentService.studentExists("NONEXISTENT"));
    }
}