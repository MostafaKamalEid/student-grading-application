
package unitTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.Mock;
import student.project.models.Student;
import student.project.models.StudentGradingFile;
import student.project.models.Subject;
import student.project.services.StudentGradesService.StudentGradesFileWriter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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

    @BeforeEach
    public void init() {
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
    public void testWriteDataWithNullFilePath() {
        // Assert that an IllegalArgumentException is thrown for null file path
        assertThrows(NullPointerException.class,
                () -> StudentGradesFileWriter.writeData(null, studentGradingFileMock));
    }

    @Test
    public void testWriteDataWithEmptyFile() throws IOException {
        // Create a temporary empty file
        Path tempFile = tempDir.resolve("tempFile.txt");
        Files.createFile(tempFile);

        // Initialize the mock objects
        StudentGradingFile studentGradingMock = mock(StudentGradingFile.class);
        when(studentGradingMock.subject()).thenReturn(null);
        when(studentGradingMock.students()).thenReturn(new ArrayList<>());

        // Test writing data to the output file
        assertDoesNotThrow(() -> StudentGradesFileWriter.writeData(tempFile.toString(), studentGradingMock));

        // clean up the file
        Files.delete(tempFile);

    }

    @Test
    public void testWriteDataWithIOException() {
        // Assert that an IllegalArgumentException is thrown for IO exception
        assertThrows(NullPointerException.class,
                () -> StudentGradesFileWriter.writeData("invalidFilePath.txt", studentGradingMock));
    }

    @Test
    public void testWriteSubjectWithNullSubject() {
        assertDoesNotThrow(() -> StudentGradesFileWriter.writeSubject(writerMock, null));
    }

    @Test
    public void testWriteSubjectWithNonNullSubject()  {
        // Initialize the mock objects
        subjectMock = mock(Subject.class);
        when(subjectMock.getFullMark()).thenReturn(100);
        writerMock = mock(BufferedWriter.class);

        // Test the writeSubject method
        assertDoesNotThrow(() -> StudentGradesFileWriter.writeSubject(writerMock, subjectMock));
    }

    @Test
    public void testWriteStudentsWithNullStudents()  {
        assertDoesNotThrow(() -> StudentGradesFileWriter.writeStudents(writerMock, null));
    }

    @Test
    public void testWriteStudentsWithEmptyStudentsList()  {
        List<Student> studentsList = new ArrayList<>();
        assertDoesNotThrow(() -> StudentGradesFileWriter.writeStudents(writerMock, studentsList));
    }

    @Test
    public void testWriteStudentsWithNonEmptyStudentsList()  {
        // Initialize the mock objects
        Student studentMock = mock(Student.class);
        when(studentMock.getName()).thenReturn("John");
        when(studentMock.getNumber()).thenReturn("123456");
        when(studentMock.CalculateGPA()).thenReturn(3.5);
        when(studentMock.CalculateGrading()).thenReturn("A");

        List<Student> studentsList = new ArrayList<>();
        studentsList.add(studentMock);

        writerMock = mock(BufferedWriter.class);

        // Test the writeStudents method
        assertDoesNotThrow(() -> StudentGradesFileWriter.writeStudents(writerMock, studentsList));
    }
}
