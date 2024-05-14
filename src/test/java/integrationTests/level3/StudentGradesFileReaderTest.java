package integrationTests.level3;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import student.project.models.StudentGradingFile;
import student.project.services.StudentGradesService.StudentGradesFileReader;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

public class StudentGradesFileReaderTest {

    @Test
    public void testReadData_FileDoesNotExist() {
        assertThrows(IllegalArgumentException.class, () -> StudentGradesFileReader.readData("nonexistentfile.txt"));
    }

    @Test
    public void testReadData_FileExists_ValidData() throws IOException {
        Path tempFile = Files.createTempFile("temp", ".txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile.toString()));
        writer.write("Software Testing,CSE337s,100\n");
        writer.write("John Doe,1234567A,10,10,20,57\n");
        writer.close();

        assertDoesNotThrow(() -> StudentGradesFileReader.readData(tempFile.toString()));
        StudentGradingFile studentGradingFile = StudentGradesFileReader.readData(tempFile.toString());

        assertEquals("Software Testing", studentGradingFile.subject().getName());
        assertEquals("CSE337s", studentGradingFile.subject().getCode());
        assertEquals(100, studentGradingFile.subject().getFullMark());

        assertEquals(1, studentGradingFile.students().size());
        assertEquals("John Doe", studentGradingFile.students().get(0).getName());
        assertEquals("1234567A", studentGradingFile.students().get(0).getNumber());
    }

    @Test
    public void testReadData_FileExists_InvalidData() throws IOException {
        Path tempFile = Files.createTempFile("temp", ".txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile.toString()));
        writer.write("Software Testing,CSE337s,invalid\n");
        writer.write("John Doe,1234567A,10,10,20,57\n");
        writer.close();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> StudentGradesFileReader.readData(tempFile.toString()));
        assertEquals("Invalid subject data: Software Testing,CSE337s,invalid\nReason: The full mark should be an integer.", exception.getMessage());
    }

       @Test
    public void testReadData_IOExceptionThrown_ShouldThrowIllegalArgumentException() throws IOException {
        // Create a temporary file
        Path tempFile = Files.createTempFile("temp", ".txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile.toString()));
        writer.write("Software Testing,CSE337s,100\n");
        writer.write("John Doe,1234567A,10,10,20,57\n");
        writer.close();

        try (MockedStatic<StudentGradesFileReader> mockedStudentGradesFileReader= mockStatic(StudentGradesFileReader.class)) {
            mockedStudentGradesFileReader.when(() -> StudentGradesFileReader.readStudents(Mockito.any())).thenThrow(new IOException());
            mockedStudentGradesFileReader.when(() -> StudentGradesFileReader.readData(Mockito.anyString())).thenCallRealMethod();

            // Call the method with the path of the temporary file
            Exception exception = assertThrows(IllegalArgumentException.class, () -> StudentGradesFileReader.readData(tempFile.toString()));

            // Check that the correct error message was thrown
            assertTrue(exception.getMessage().contains("Error reading file: " + tempFile.toString()));
        }
    }
}