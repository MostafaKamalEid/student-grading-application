
package unitTests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mock;
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
    @Mock
    BufferedWriter writerMock;
    @Mock
    StudentGradingFile studentGradingMock;
    
    Subject subjectMock;
    Student student1Mock;
    Student student2Mock;
    Student student3Mock;
    
    public StudentGradesFileWriterTest(){
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
        
        Subject subject2Mock = mock(Subject.class);
        when(subject2Mock.getName()).thenReturn(" ");

        Student student1Mock1 = mock(Student.class);
        when(student1Mock1.getName()).thenReturn(" ");
        Student student2Mock2 = mock(Student.class);
        when(student2Mock2.getName()).thenReturn(" ");
        Student student3Mock3 = mock(Student.class);
        when(student3Mock3.getName()).thenReturn(" ");
        
        List<Student> studentList = new ArrayList<>();
        studentsList.add(student1Mock1);
        studentsList.add(student2Mock2);
        studentsList.add(student3Mock3);
        
        // Mocking the behavior of StudentGradingFile
        StudentGradingFile studentGradingMock = mock(StudentGradingFile.class);
        when(studentGradingMock.subject()).thenReturn(subject2Mock);
        when(studentGradingMock.students()).thenReturn(studentList);
    }

    @Test
    public void testWriteDataSuccess() throws IOException {
        // Create a temporary input file
        Path filePath = tempDir.resolve("inputFile.txt");
        try(BufferedWriter inputWriter = Files.newBufferedWriter(filePath)){
        inputWriter.write("Subject\nStudent1\nStudent2\nStudent3");
        inputWriter.close();
        }
        
        List<Student> studentsList = new ArrayList<>();
        studentsList.add(student1Mock);
        studentsList.add(student2Mock);
        studentsList.add(student3Mock);

        // Test writing data to the output file
        String inputFilePath = filePath.toString();
        StudentGradesFileWriter.writeData(inputFilePath, studentGradingFileMock);

        // Check if the output file is created and contains the expected data
        String outputFilePath = filePath.toString().replace(".txt", "_output.txt");
        Path outputPath = Paths.get(outputFilePath);
        File outputFile = new File(outputFilePath);
        assertTrue(outputFile.exists());
        BufferedReader reader = Files.newBufferedReader(outputPath);
        assertEquals("Subject Name: Math", reader.readLine()); // Check subject
        assertEquals("Student1", reader.readLine()); // Check student 1
        assertEquals("Student2", reader.readLine()); // Check student 2
        assertEquals("Student3", reader.readLine()); // Check student 3
        assertNull(reader.readLine()); // Check end of file
        reader.close();

        // Clean up: Delete the output file after the test
        outputFile.delete();
    }
    @Test
    public void testWriteDataNoSubject() throws IOException {
        // Create a temporary input file with no subject
        String originalFilePath = "inputFile.txt";
        Path filePath = tempDir.resolve(originalFilePath);
        BufferedWriter inputWriter = Files.newBufferedWriter(filePath);
        inputWriter.write("Student1\nStudent2\nStudent3");
        inputWriter.close();

        String inputFilePath = filePath.toString();
        // Test writing data to the output file
        assertThrows(IllegalArgumentException.class, () -> StudentGradesFileWriter.writeData(inputFilePath, studentGradingFileMock));
    }
    @Test
    public void testWriteDataWithNullFilePath() {
        // Assert that an IllegalArgumentException is thrown for null file path
        assertThrows(IllegalArgumentException.class, () -> StudentGradesFileWriter.writeData(null, studentGradingFileMock));
    }
    @Test
    public void testWriteDataWithEmptyFile() throws IOException {
        // Create a temporary empty file
        Path tempFile = tempDir.resolve("tempFile.txt");
        Files.createFile(tempFile);
        // Assert that an IllegalArgumentException is thrown for empty file
        assertThrows(IllegalArgumentException.class, () -> StudentGradesFileWriter.writeData(tempFile.toString(), studentGradingMock));
    }

    @Test
    public void testWriteDataWithIOException() {
        // Assert that an IllegalArgumentException is thrown for IO exception
        assertThrows(IllegalArgumentException.class, () -> StudentGradesFileWriter.writeData("invalidFilePath.txt", studentGradingMock));
    }
     @Test
    public void testWriteSubjectWithNullSubject() throws IOException {
        assertDoesNotThrow(() -> StudentGradesFileWriter.writeSubject(writerMock, null));
    }

    @Test
    public void testWriteSubjectWithNonNullSubject() throws IOException {
        when(subjectMock.getFullMark()).thenReturn(100);

        assertDoesNotThrow(() -> StudentGradesFileWriter.writeSubject(writerMock, subjectMock));
    }

    @Test
    public void testWriteStudentsWithNullStudents() throws IOException {
        assertDoesNotThrow(() -> StudentGradesFileWriter.writeStudents(writerMock, null));
    }

    @Test
    public void testWriteStudentsWithEmptyStudentsList() throws IOException {
        List<Student> studentsList = new ArrayList<>();
        assertDoesNotThrow(() -> StudentGradesFileWriter.writeStudents(writerMock, studentsList));
    }

    @Test
    public void testWriteStudentsWithNonEmptyStudentsList() throws IOException {
        List<Student> studentsList = new ArrayList<>();
        Student studentMock = mock(Student.class);
        when(studentMock.getName()).thenReturn("John");
        when(studentMock.getNumber()).thenReturn("123456");
        when(studentMock.CalculateGPA()).thenReturn(3.5);
        when(studentMock.CalculateGrading()).thenReturn("A");
        studentsList.add(studentMock);

        assertDoesNotThrow(() -> StudentGradesFileWriter.writeStudents(writerMock, studentsList));
    }
}

