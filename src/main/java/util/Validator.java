package util;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Validator {
    
    public static boolean isValidEmail(String email) {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }
    
    public static boolean isValidDate(String dateStr) {
        try {
            LocalDate.parse(dateStr);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
    
    public static boolean isValidScore(double score) {
        return score >= 0 && score <= 100;
    }
    
    public static boolean isValidId(String id) {
        return id != null && !id.trim().isEmpty() && id.matches("^[A-Za-z0-9]+$");
    }
    
    public static boolean isValidName(String name) {
        return name != null && !name.trim().isEmpty() && name.matches("^[A-Za-z\\s]+$");
    }
    
    public static boolean isValidPhone(String phone) {
        return phone != null && phone.matches("^[0-9\\-\\+\\(\\)\\s]+$");
    }
    
    public static boolean isValidSemester(String semester) {
        return semester != null && !semester.trim().isEmpty() && semester.matches("^[A-Za-z0-9]+$");
    }
}