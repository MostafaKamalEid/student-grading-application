package integrationTests.level3;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import student.project.models.Student;
import student.project.models.StudentGradingFile;
import student.project.models.Subject;
import student.project.services.StudentGradesService.StudentGradesFileWriter;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mockStatic;

public class StudentGradesFileWriterTest {

    @Test
    public void testWriteData_NoExceptionThrown() throws IOException {
        Path tempFile = Files.createTempFile("temp", ".txt");
        Subject subject = new Subject("Software Testing", "CSE337s", 100);
        Student student = new Student("John Doe", "1234567A", 10, 10, 20, 57);
        StudentGradingFile studentGradingFile = new StudentGradingFile(subject, Collections.singletonList(student));

        assertDoesNotThrow(() -> StudentGradesFileWriter.writeData(tempFile.toString(), studentGradingFile));
    }
    @Test
    public void testWriteData_ItShouldThrowsIOException() throws IOException {
        // Create a stream to hold the output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Create a mock writeSubject static method to test IOException
        try (MockedStatic<StudentGradesFileWriter> mockedStudentGradesFileWriter= mockStatic(StudentGradesFileWriter.class)) {
            mockedStudentGradesFileWriter.when(() -> StudentGradesFileWriter.writeSubject(Mockito.any(BufferedWriter.class), Mockito.any(Subject.class))).thenThrow(new IOException());
            mockedStudentGradesFileWriter.when(() -> StudentGradesFileWriter.writeStudents(Mockito.any(BufferedWriter.class), Mockito.anyList())).thenThrow(new IOException());
            mockedStudentGradesFileWriter.when(() -> StudentGradesFileWriter.writeData(Mockito.anyString(), Mockito.any(StudentGradingFile.class))).thenCallRealMethod();

            // Create a StudentGradingFile
            Path tempFile = Files.createTempFile("temp", ".txt");

            Subject subject = new Subject("Software Testing", "CSE337s", 100);
            Student student = new Student("John Doe", "1234567A", 10, 10, 20, 57);
            StudentGradingFile studentGradingFile = new StudentGradingFile(subject, Collections.singletonList(student));
            StudentGradesFileWriter.writeData(tempFile.toString(), studentGradingFile);

            // Check that the error message was printed
            // get file name without extension
            String fileNameWithoutExtension = tempFile.getFileName().toString().replaceFirst("[.][^.]+$", "");
            String pathOfFileAfterAddOutput = tempFile.getParent() + File.separator + fileNameWithoutExtension + "_output.txt";

            String expectedOutput = "Error writing file: "+ pathOfFileAfterAddOutput + System.lineSeparator();
            assertEquals(expectedOutput, outContent.toString());
        }
    }
    @Test
    public void testWriteSubject_NoExceptionThrown() throws IOException {
        // Create Writer
        Path tempFile = Files.createTempFile("temp", ".txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile.toString()));

        // Create a mock subject
        Subject subject = new Subject("Software Testing", "CSE337s", 100);

        // Call the writeSubject method
        StudentGradesFileWriter.writeSubject(writer, subject);

        // Close the writer
        writer.close();

        // Read the content of the file
        String content = Files.readString(tempFile);
        // Check the content of the file
        String expectedContent = "Subject Name: Software Testing" + System.lineSeparator() + "Max Mark: 100" + System.lineSeparator();
        assertEquals(expectedContent, content);
    }

    @Test
    public void testWriteStudents_NoExceptionThrown() throws IOException {
        // Create Writer
        Path tempFile = Files.createTempFile("temp", ".txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile.toString()));

        // Create a mock student
        Student student = new Student("John Doe", "1234567A", 10, 10, 20, 57);

        // Call the writeStudents method
        StudentGradesFileWriter.writeStudents(writer, Collections.singletonList(student));

        // Close the writer
        writer.close();

        // Read the content of the file
        String content = Files.readString(tempFile);
        // Check the content of the file
        assertEquals("name  number  GPA  Grade"+ System.lineSeparator() +"John Doe 1234567A 4.0 A+"+ System.lineSeparator(), content);
    }

    @Test
    public void testWriteSubject_WithNullSubject_ShouldNotThrowException() throws IOException {
        // Create a temporary file
        Path tempFile = Files.createTempFile("temp", ".txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile.toString()));

        // Call the writeSubject method with null subject
        StudentGradesFileWriter.writeSubject(writer, null);

        // Close the writer
        writer.close();

        // Read the content of the file
        String content = Files.readString(tempFile);

        // Check the content of the file
        assertEquals("", content);
    }

    @Test
    public void testWriteStudents_WithNullStudents_ShouldNotThrowException() throws IOException {
        // Create a temporary file
        Path tempFile = Files.createTempFile("temp", ".txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile.toString()));

        // Call the writeStudents method with null students
        StudentGradesFileWriter.writeStudents(writer, null);

        // Close the writer
        writer.close();

        // Read the content of the file
        String content = Files.readString(tempFile);

        // Check the content of the file
        assertEquals("", content);
    }

    @Test
    public void testWriteStudents_WithEmptyStudents_ShouldNotThrowException() throws IOException {
        // Create a temporary file
        Path tempFile = Files.createTempFile("temp", ".txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile.toString()));

        // Call the writeStudents method with empty students list
        StudentGradesFileWriter.writeStudents(writer, Collections.emptyList());

        // Close the writer
        writer.close();

        // Read the content of the file
        String content = Files.readString(tempFile);

        // Check the content of the file
        assertEquals("", content);
    }
}