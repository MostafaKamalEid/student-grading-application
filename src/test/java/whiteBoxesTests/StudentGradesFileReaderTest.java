package whiteBoxesTests;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import student.project.models.StudentGradingFile;
import student.project.models.Subject;
import student.project.services.StudentGradesService.StudentGradesFileReader;
import student.project.services.StudentGradesService.StudentGradesFileWriter;

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
    public void testReadSubject_ValidInput()  {
        String subjectLine = "Software Testing,CSE337s,100";
        assertDoesNotThrow(() -> StudentGradesFileReader.readSubject(subjectLine));
    }

    @Test
    public void testReadSubject_InvalidInput() {
        String subjectLine = "Software Testing,CSE337s,invalid";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> StudentGradesFileReader.readSubject(subjectLine));
        assertEquals("Invalid subject data: Software Testing,CSE337s,invalid\nReason: The full mark should be an integer.", exception.getMessage());
    }

    @Test
    public void testReadStudents_ValidInput() throws IOException {
        Path tempFile = Files.createTempFile("temp", ".txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile.toString()));
        writer.write("John Doe,1234567A,10,10,20,57\n");
        writer.close();

        assertDoesNotThrow(() -> StudentGradesFileReader.readStudents(new BufferedReader(new FileReader(tempFile.toString()))));
    }

    @Test
    public void testReadStudents_InvalidInput() throws IOException {
        Path tempFile = Files.createTempFile("temp", ".txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile.toString()));
        writer.write("John Doe,1234567A,10,10,20,invalid\n");
        writer.close();

        Exception exception =assertThrows(IllegalArgumentException.class, () -> StudentGradesFileReader.readStudents(new BufferedReader(new FileReader(tempFile.toString()))));
        assertEquals("Invalid student data: John Doe,1234567A,10,10,20,invalid\nReason: The activity marks, oral marks, midterm marks and final marks should be integers.", exception.getMessage());
    }

    @Test
    public void testReadSubject_NullInput() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> StudentGradesFileReader.readSubject(null));
        assertEquals("Invalid subject data: null\nReason: The line should contain exactly 3 fields separated by commas.", exception.getMessage());
    }

    @Test
    public void testReadSubject_EmptyInput() {
        String subjectLine = "";
        Exception exception =assertThrows(IllegalArgumentException.class, () -> StudentGradesFileReader.readSubject(subjectLine));
        assertEquals("Invalid subject data: \nReason: The line should contain exactly 3 fields separated by commas.", exception.getMessage());
    }

    @Test
    public void testReadSubject_LessThanThreeFields() {
        String subjectLine = "Software Testing,CSE337s";
        Exception exception =assertThrows(IllegalArgumentException.class, () -> StudentGradesFileReader.readSubject(subjectLine));
        assertEquals("Invalid subject data: Software Testing,CSE337s\nReason: The line should contain exactly 3 fields separated by commas.", exception.getMessage());
    }

    @Test
    public void testReadSubject_MoreThanThreeFields() {
        String subjectLine = "Software Testing,CSE337s,100,ExtraField";
        Exception exception =assertThrows(IllegalArgumentException.class, () -> StudentGradesFileReader.readSubject(subjectLine));
        assertEquals("Invalid subject data: Software Testing,CSE337s,100,ExtraField\nReason: The line should contain exactly 3 fields separated by commas.", exception.getMessage());
    }

    @Test
    public void testReadStudents_NullInput()  {
        BufferedReader reader = new BufferedReader(new StringReader("null\n"));
        Exception exception = assertThrows(IllegalArgumentException.class, () -> StudentGradesFileReader.readStudents(reader));
        assertEquals("Invalid student data: null\nReason: The line should contain exactly 6 fields separated by commas.", exception.getMessage());
    }

    @Test
    public void testReadStudents_EmptyInput_NoExceptionThorws_ContinueToNextLine() {
        BufferedReader reader = new BufferedReader(new StringReader("\n"));
        assertDoesNotThrow(() -> StudentGradesFileReader.readStudents(reader));
    }

    @Test
    public void testReadStudents_LessThanSixFields() {
        BufferedReader reader = new BufferedReader(new StringReader("John Doe,1234567A,10,10,20\n"));
        Exception exception = assertThrows(IllegalArgumentException.class, () -> StudentGradesFileReader.readStudents(reader));
        assertEquals("Invalid student data: John Doe,1234567A,10,10,20\nReason: The line should contain exactly 6 fields separated by commas.", exception.getMessage());
    }

    @Test
    public void testReadStudents_MoreThanSixFields()  {
        BufferedReader reader = new BufferedReader(new StringReader("John Doe,1234567A,10,10,20,57,ExtraField\n"));
        Exception exception = assertThrows(IllegalArgumentException.class, () -> StudentGradesFileReader.readStudents(reader));
        assertEquals("Invalid student data: John Doe,1234567A,10,10,20,57,ExtraField\nReason: The line should contain exactly 6 fields separated by commas.", exception.getMessage());
    }
    @Test
    public void testReadStudents_InvalidStudentData_ShouldThrowIllegalArgumentException() throws IOException {
        String studentData = ",1234567A,10,10,20,57"; // Empty student name
        BufferedReader reader = new BufferedReader(new StringReader(studentData));
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> StudentGradesFileReader.readStudents(reader));
        assertTrue(exception.getMessage().contains("Invalid student data:"));
    }
    @Test
    public void testReadSubject_InvalidSubjectData_ShouldThrowIllegalArgumentException() {
        String subjectLine = ",CSE337s,100"; // Empty subject name
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> StudentGradesFileReader.readSubject(subjectLine));
        assertTrue(exception.getMessage().contains("Invalid subject data:"));
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