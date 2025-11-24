import model.Course;
import service.CourseService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class CourseServiceTest {
    private CourseService courseService;
    
    @BeforeEach
    void setUp() {
        courseService = new CourseService();
    }
    
    @Test
    void testAddValidCourse() {
        Course course = new Course("C100", "Test Course", "Dr. Test", 
                3, "Computer Science", 30, "Mon/Wed 10:00-11:30");
        
        boolean result = courseService.addCourse(course);
        assertTrue(result);
    }
    
    @Test
    void testAddCourseWithInvalidCredits() {
        Course course = new Course("C101", "Test Course", "Dr. Test", 
                0, "Computer Science", 30, "Mon/Wed 10:00-11:30");
        
        assertThrows(IllegalArgumentException.class, () -> {
            courseService.addCourse(course);
        });
    }
    
    @Test
    void testCourseExists() {
        assertTrue(courseService.courseExists("C001"));
        assertFalse(courseService.courseExists("NONEXISTENT"));
    }
}