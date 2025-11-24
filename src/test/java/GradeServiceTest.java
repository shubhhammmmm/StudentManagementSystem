import model.Grade;
import service.GradeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class GradeServiceTest {
    private GradeService gradeService;
    
    @BeforeEach
    void setUp() {
        gradeService = new GradeService();
    }
    
    @Test
    void testAssignValidGrade() {
        Grade grade = new Grade("G100", "S001", "C001", 85.5, "Spring2024");
        
        boolean result = gradeService.assignGrade(grade);
        assertTrue(result);
    }
    
    @Test
    void testAssignGradeWithInvalidScore() {
        Grade grade = new Grade("G101", "S001", "C001", 150.0, "Spring2024");
        
        assertThrows(IllegalArgumentException.class, () -> {
            gradeService.assignGrade(grade);
        });
    }
    
    @Test
    void testCalculateStudentGPA() {
        double gpa = gradeService.calculateStudentGPA("S001");
        assertTrue(gpa >= 0.0 && gpa <= 4.0);
    }
    
    @Test
    void testGetStudentAcademicStanding() {
        String standing = gradeService.getStudentAcademicStanding("S001");
        assertNotNull(standing);
        assertTrue(standing.equals("Honors") || standing.equals("Good Standing") || standing.equals("Academic Probation"));
    }
}