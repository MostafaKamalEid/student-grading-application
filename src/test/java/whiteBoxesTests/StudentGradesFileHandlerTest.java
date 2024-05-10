package whiteBoxesTests;

import org.junit.jupiter.api.Test;
import student.project.services.StudentGradesService.StudentGradesFileHandler;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class StudentGradesFileHandlerTest {

    @Test
    public void testHandle_WithNullFilePath_ShouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> {
            StudentGradesFileHandler.Handle(null);
        });
        throw new IllegalArgumentException("File path cannot be null");
    }

    @Test
    public void testHandle_WithValidFilePath_ShouldNotThrowException() throws IOException {
        // Create a temporary file
        Path tempFile = Files.createTempFile("temp", ".txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile.toString()));
        writer.write("Software Testing,CSE337s,100\n");
        writer.write("John Doe,1234567A,10,10,20,57\n");
        writer.close();

        assertDoesNotThrow(() -> {
            StudentGradesFileHandler.Handle(tempFile.toString());
        });
    }

    @Test
    public void testHandle_WhenFilePathPointsToNonExistentFile_ShouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> {
            StudentGradesFileHandler.Handle("non_existent_file.txt");
        });
    }

    @Test
    public void testHandle_WhenFilePathPointsToFileWithInvalidData_ShouldThrowIllegalArgumentException() throws IOException {
        // Create a temporary file with invalid data
        Path tempFile = Files.createTempFile("temp", ".txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile.toString()));
        writer.write("Invalid Data\n");
        writer.close();

        assertThrows(IllegalArgumentException.class, () -> {
            StudentGradesFileHandler.Handle(tempFile.toString());
        });
    }
}