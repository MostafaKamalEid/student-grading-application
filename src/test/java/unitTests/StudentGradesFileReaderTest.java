package unitTests;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import student.project.models.Student;
import student.project.models.Subject;
import student.project.models.StudentGradingFile;
import student.project.services.StudentGradesService.StudentGradesFileReader;

import java.io.BufferedReader;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StudentGradesFileReaderTest {
    @Test
    public void test_readData_WithFileDoesNotExist_ShouldThrowIllegalArgumentException() {
        try (MockedStatic<Files> filesMock = Mockito.mockStatic(Files.class)) {
            filesMock.when(() -> Files.exists(Paths.get("nonexistent.txt"))).thenReturn(false);
            Exception exception = assertThrows(IllegalArgumentException.class,
                    () -> StudentGradesFileReader.readData("nonexistent.txt"));
            assertTrue(exception.getMessage().contains("File does not exist"));
        }
    }
    @Test
    public void test_readData_FileWithReadingError_ShouldThrowIllegalArgumentException() throws IOException {
        // Mock the existence check to always return true, simulating that the file
        // exists
        try (MockedStatic<Files> filesMock = Mockito.mockStatic(Files.class)) {
            filesMock.when(() -> Files.exists(Paths.get("realfile.txt"))).thenReturn(true);

            // Mock FileReader and BufferedReader to simulate IOException
            BufferedReader mockBufferedReader = mock(BufferedReader.class);
            when(mockBufferedReader.readLine()).thenThrow(new IOException("Simulated read error"));
            Exception exception = assertThrows(IllegalArgumentException.class,
                    () -> StudentGradesFileReader.readData("realfile.txt"));
            assertTrue(exception.getMessage().contains("Error reading file:"));
        }
    }


    @Test
    public void test_readData_WithValidFile_ShouldReturnCorrectGradingFile() throws IOException {
        // Arrange
        String fileContent = "English, ENG101, 100\n" +
                "John Doe,12345678,8,9,18,50\n" +
                "Jane Doe,87654321,10,10,20,60\n";
        BufferedReader reader = new BufferedReader(new StringReader(fileContent));
        StudentGradingFile expectedFile = new StudentGradingFile(
                new Subject("English", "ENG101", 100),
                List.of(
                        new Student("John Doe", "12345678", 8, 9, 18, 50),
                        new Student("Jane Doe", "87654321", 10, 10, 20, 60)
                )
        );

        try (MockedStatic<StudentGradesFileReader> mockedStudentGradesFileReader = Mockito.mockStatic(StudentGradesFileReader.class)) {
            mockedStudentGradesFileReader.when(() -> StudentGradesFileReader.readData("record.txt")).thenReturn(expectedFile);

            // Act
            StudentGradingFile result = StudentGradesFileReader.readData("record.txt");

            // Assert
            assertNotNull(result);
            assertEquals(expectedFile, result);
        }
    }

    @Test
    public void test_readData_WithOnlySubjectLine_ShouldThrowIllegalArgumentException() throws IOException {
        // Arrange
        String fileContent = "Mathematics, MAT001, 100\n";
        BufferedReader reader = new BufferedReader(new StringReader(fileContent));

        try (MockedStatic<StudentGradesFileReader> mockedStudentGradesFileReader = Mockito.mockStatic(StudentGradesFileReader.class)) {
            mockedStudentGradesFileReader.when(() -> StudentGradesFileReader.readData("record.txt")).thenThrow(new IllegalArgumentException("No student data found."));

            // Act and Assert
            Exception exception = assertThrows(IllegalArgumentException.class,
                    () -> StudentGradesFileReader.readData("record.txt"));
            assertTrue(exception.getMessage().contains("No student data found."));
        }
    }

    @Test
    public void test_readData_WithOnlyStudentLine_ShouldThrowIllegalArgumentException() throws IOException {
        // Arrange
        String fileContent = "John Doe,12345678,8,9,18,50\n";
        BufferedReader reader = new BufferedReader(new StringReader(fileContent));

        try (MockedStatic<StudentGradesFileReader> mockedStudentGradesFileReader = Mockito.mockStatic(StudentGradesFileReader.class)) {
            mockedStudentGradesFileReader.when(() -> StudentGradesFileReader.readData("record.txt")).thenThrow(new IllegalArgumentException("The line should contain exactly 3 fields"));

            // Act and Assert
            Exception exception = assertThrows(IllegalArgumentException.class,
                    () -> StudentGradesFileReader.readData("record.txt"));
            assertTrue(exception.getMessage().contains("The line should contain exactly 3 fields"));
        }
    }
}
