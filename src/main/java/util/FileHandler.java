package util;

import java.io.*;
import java.util.List;

public class FileHandler {
    
    public static boolean saveToFile(String filename, String content) {
        try {
            File file = new File(filename);
            file.getParentFile().mkdirs(); // Create directories if they don't exist
            
            try (PrintWriter writer = new PrintWriter(new FileWriter(file, true))) {
                writer.println(content);
                return true;
            }
        } catch (IOException e) {
            System.err.println("Error saving to file: " + e.getMessage());
            return false;
        }
    }
    
    public static String readFromFile(String filename) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
        return content.toString();
    }
    
    public static boolean exportToCSV(String filename, List<String> data) {
        try {
            File file = new File(filename);
            file.getParentFile().mkdirs();
            
            try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
                for (String line : data) {
                    writer.println(line);
                }
                return true;
            }
        } catch (IOException e) {
            System.err.println("Error exporting to CSV: " + e.getMessage());
            return false;
        }
    }
    
    public static boolean fileExists(String filename) {
        return new File(filename).exists();
    }
}