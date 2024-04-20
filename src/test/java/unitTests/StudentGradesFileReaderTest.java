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
    public void test_readData_WithValidFile_ShouldReturnCorrectGradingFile()  {

        // Assume StudentGradesFileReader has a method to set the BufferedReader for
        // testing

        // Call the actual method
        StudentGradingFile result = StudentGradesFileReader.readData("record.txt");

        // Asserts
        assertNotNull(result);
        assertNotNull(result.subject());
        assertEquals("English", result.subject().getName());
        assertEquals(7, result.students().size());
    }
    @Test
    public void test_readData_WithOnlySubjectLine_ShouldReturnSubjectDataWithEmptyStudentData() throws Exception {
        // Create a temporary file
        File tempFile = File.createTempFile("testFileWithOnlySubjectLine", ".txt");
        // Request file deletion when the JVM exits, added as a precaution
        tempFile.deleteOnExit();
        // Write to the file
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write("Mathematics, MAT001, 100");
        }

        // Read from the file
        StudentGradingFile result;
        result = StudentGradesFileReader.readData(tempFile.getPath()); // This method needs to accept a BufferedReader

        // Assertions to check the function worked as expected
        assertNotNull(result);
        assertTrue(result.students().isEmpty());
        assertEquals("Mathematics", result.subject().getName());
    }

    @Test
    public void test_readData_WithOnlyStudentLine_ShouldThrowIllegalArgumentException() throws Exception {
        // Create a temporary file
        File tempFile = File.createTempFile("testFileWithOnlySubjectLine", ".txt");
        // Request file deletion when the JVM exits, added as a precaution
        tempFile.deleteOnExit();
        // Write to the file
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write("John Doe,12345678,8,9,18,50");
        }

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> StudentGradesFileReader.readData(tempFile.getPath()));
        assertTrue(exception.getMessage().contains("The line should contain exactly 3 fields"));

    }
    @Test
    public void test_readSubject_WithValidInput_ShouldReturnValidSubjectData() {
        String subjectLine = "Mathematics, MAT101, 100";
        Subject subject = StudentGradesFileReader.readSubject(subjectLine);
        assertEquals("Mathematics", subject.getName());
        assertEquals("MAT101", subject.getCode());
        assertEquals(100, subject.getFullMark());
    }

    @Test
    public void test_readSubject_WithLessNumberOfFields_ShouldThrowIllegalArgumentException() {

        String subjectLine = "Mathematics, MAT101"; //
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> StudentGradesFileReader.readSubject(subjectLine));
        assertTrue(exception.getMessage().contains("The line should contain exactly 3 fields"));
    }

    @Test
    public void test_readSubject_WithMoreNumberOfFields_ShouldThrowIllegalArgumentException() {

        String subjectLine = "Mathematics, MATH101,100,101"; //
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> StudentGradesFileReader.readSubject(subjectLine));
        assertTrue(exception.getMessage().contains("The line should contain exactly 3 fields"));
    }
    @Test
    public void test_readSubject_WithInvalidFullMarkType_ShouldThrowIllegalArgumentException() {
        String subjectLine = "Mathematics, MAT101, one hundred";
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> StudentGradesFileReader.readSubject(subjectLine));
        assertTrue(exception.getMessage().contains("The full mark should be an integer"));

    }

    @Test
    public void test_readSubject_WithvalidNumberOfFieldsWithIncorrectDataTypes_ShouldThrowIllegalArgumentException() {
        String subjectLine = "Mathematics, MATH101, 100";
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> StudentGradesFileReader.readSubject(subjectLine));
        assertTrue(exception.getMessage().contains("Invalid subject data:"));
    }

    @Test
    public void test_readStudents_WithValidData_ShouldReturnValidStudentData() throws IOException {
        String studentData = "John Doe,12345678,8,9,18,50 \nBob Johnson,34567890,9,10,20,55";
        BufferedReader reader = new BufferedReader(new StringReader(studentData));
        List<Student> students = StudentGradesFileReader.readStudents(reader);
        assertEquals(2, students.size());
        assertEquals("John Doe", students.getFirst().getName());
        assertEquals("12345678", students.getFirst().getNumber());
        assertEquals(8, students.getFirst().getActivityMarks());
    }

    @Test
    public void test_readStudents_WithInvalidMarksType_ShouldThrowIllegalArgumentException() {
        String studentData = "John Doe, 001, 25, twenty-five, 25, 25"; // Non-integer mark
        BufferedReader reader = new BufferedReader(new StringReader(studentData));
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> StudentGradesFileReader.readStudents(reader));
        assertTrue(exception.getMessage().contains("should be integers"));
    }
    @Test
    public void test_readStudents_WithInvalidNumberOfFields_ShouldThrowIllegalArgumentException() {
        String studentData = "John Doe, 001, 25,25, 25"; // Non-integer mark
        BufferedReader reader = new BufferedReader(new StringReader(studentData));
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> StudentGradesFileReader.readStudents(reader));
        assertTrue(exception.getMessage().contains("The line should contain exactly 6 fields"));
    }

    @Test
    public void test_readStudents_WithvalidNumberOfFieldsWithIncorrectDataTypes_ShouldThrowIllegalArgumentException()
             {
        String studentData = "John Doe,A14678,8,9,18,50";
        BufferedReader reader = new BufferedReader(new StringReader(studentData));
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> StudentGradesFileReader.readStudents(reader));
        assertTrue(exception.getMessage().contains("Invalid student data:"));
    }

    @Test
    public void test_readStudents_WithDuplicateStudentData_ShouldThrowIlleglArgumentException() {
        String studentData = "John Doe,12345678,8,9,18,50\n" +
                "John Doe,12345678,8,9,18,50"; // Duplicate student number
        BufferedReader reader = new BufferedReader(new StringReader(studentData));
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> StudentGradesFileReader.readStudents(reader));
        assertTrue(exception.getMessage().contains("Duplicate student number"));
    }

    @Test
    public void test_readStudents_WithExcessiveSpaces_ShouldReturnCorrectTrimmedStudentData() throws Exception {
        String studentData = "  John Doe  ,  12345678  ,  8  ,  9  ,  18  ,  50  ";
        BufferedReader reader = new BufferedReader(new StringReader(studentData));

        List<Student> students = StudentGradesFileReader.readStudents(reader);
        assertEquals(1, students.size());
        assertEquals("John Doe", students.getFirst().getName());
        assertEquals("12345678", students.getFirst().getNumber());
        assertEquals(8, students.getFirst().getActivityMarks());
    }

    @Test
    public void test_readStudents_WithEmptyLines_ShouldReturnStudentDataWithSpaceRemoved() throws IOException {
        String studentData = "\n\nJohn Doe,12345678,8,9,18,50\n\nBob Johnsos,34567890,9,10,20,55\n";
        BufferedReader reader = new BufferedReader(new StringReader(studentData));

        List<Student> students = StudentGradesFileReader.readStudents(reader);
        assertEquals(2, students.size());
        assertEquals("John Doe", students.get(0).getName());
        assertEquals("Bob Johnsos", students.get(1).getName());
    }

}
