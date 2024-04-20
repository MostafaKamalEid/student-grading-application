
package unitTests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.File;
import java.nio.file.Path;
import org.junit.jupiter.api.io.TempDir;
import java.nio.file.Files;
import student.project.models.StudentGradingFile; 
import student.project.services.StudentGradesService.StudentGradesFileWriter;
import student.project.models.Student;
import student.project.models.Subject;
import java.io.IOException;
import java.util.List;
import java.nio.file.Paths;
import java.util.ArrayList;

public class StudentGradesFileWriterTest {
    
    @TempDir
    Path tempDir;
    @Mock
    StudentGradingFile studentGradingFileMock;

    @Test
    public void testWriteDataSuccess() throws IOException {
        // Create a temporary input file
        Path filePath = tempDir.resolve("inputFile.txt");
        try(BufferedWriter inputWriter = Files.newBufferedWriter(filePath)){
        inputWriter.write("Subject\nStudent1\nStudent2\nStudent3");
        inputWriter.close();
        }
        // Create a mock StudentGradingFile with subject and students
        // Mocking the Subject and Student classes
        Subject subjectMock = mock(Subject.class);
        when(subjectMock.getName()).thenReturn("Math");

        Student student1Mock = mock(Student.class);
        when(student1Mock.getName()).thenReturn("Student1");
        Student student2Mock = mock(Student.class);
        when(student2Mock.getName()).thenReturn("Student2");
        Student student3Mock = mock(Student.class);
        when(student3Mock.getName()).thenReturn("Student3");
        List<Student> studentsList = new ArrayList<>();
        studentsList.add(student1Mock);
        studentsList.add(student2Mock);
        studentsList.add(student3Mock);

        // Mocking the behavior of StudentGradingFile
        StudentGradingFile studentGradingFileMock = mock(StudentGradingFile.class);
        when(studentGradingFileMock.subject()).thenReturn(subjectMock);
        when(studentGradingFileMock.students()).thenReturn(studentsList);

        // Test writing data to the output file
        String inputFilePath = filePath.toString();
        StudentGradesFileWriter.writeData(inputFilePath, studentGradingFileMock);

        // Check if the output file is created and contains the expected data
        String outputFilePath = filePath.toString().replace(".txt", "_output.txt");
        Path outputPath = Paths.get(outputFilePath);
        File outputFile = new File(outputFilePath);
        assertTrue(outputFile.exists());
        BufferedReader reader = Files.newBufferedReader(outputPath);
        assertEquals("Math", reader.readLine()); // Check subject
        assertEquals("Student1", reader.readLine()); // Check student 1
        assertEquals("Student2", reader.readLine()); // Check student 2
        assertEquals("Student3", reader.readLine()); // Check student 3
        assertNull(reader.readLine()); // Check end of file
        reader.close();

        // Clean up: Delete the output file after the test
        outputFile.delete();
    }

