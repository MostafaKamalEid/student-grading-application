package whiteBoxesTests;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import student.project.models.Student;
import student.project.validations.StudentValidator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

public class StudentTest {

    @Test
    public void testSetName_ValidName_ShouldSetTheName() {
        Student student = new Student();
        String validName = "John Doe";
        student.setName(validName);
        assertEquals(validName, student.getName());
    }

    @Test
    public void testSetName_InvalidName_ShouldThrowIllegalArgumentException() {
        Student student = new Student();
        String invalidName = "1234";
        assertThrows(IllegalArgumentException.class, () -> student.setName(invalidName));
    }

    @Test
    public void testSetNumber_ValidNumber_ShouldSetTheNumber() {
        Student student = new Student();
        String validNumber = "1234567A";
        student.setNumber(validNumber);
        assertEquals(validNumber, student.getNumber());
    }

    @Test
    public void testSetNumber_InvalidNumber_ShouldThrowIllegalArgumentException() {
        Student student = new Student();
        String invalidNumber = "1234567890";
        assertThrows(IllegalArgumentException.class, () -> student.setNumber(invalidNumber));
    }

    @Test
    public void testSetActivityMarks_ValidMarks_ShouldSetTheMarks() {
        Student student = new Student();
        int validMarks = 10;
        student.setActivityMarks(validMarks);
        assertEquals(validMarks, student.getActivityMarks());
    }

    @Test
    public void testSetActivityMarks_InvalidMarks_ShouldThrowIllegalArgumentException() {
        Student student = new Student();
        int invalidMarks = 101;
        assertThrows(IllegalArgumentException.class, () -> student.setActivityMarks(invalidMarks));
    }

    @Test
    public void testSetOralMarks_ValidMarks_ShouldSetTheMarks() {
        Student student = new Student();
        int validMarks = 10;
        student.setOralMarks(validMarks);
        assertEquals(validMarks, student.getOralMarks());
    }

    @Test
    public void testSetOralMarks_InvalidMarks_ShouldThrowIllegalArgumentException() {
        Student student = new Student();
        int invalidMarks = 101;
        assertThrows(IllegalArgumentException.class, () -> student.setOralMarks(invalidMarks));
    }

    @Test
    public void testSetMidMarks_ValidMarks_ShouldSetTheMarks() {
        Student student = new Student();
        int validMarks = 20;
        student.setMidMarks(validMarks);
        assertEquals(validMarks, student.getMidMarks());
    }

    @Test
    public void testSetMidMarks_InvalidMarks_ShouldThrowIllegalArgumentException() {
        Student student = new Student();
        int invalidMarks = 101;
        assertThrows(IllegalArgumentException.class, () -> student.setMidMarks(invalidMarks));
    }

    @Test
    public void testSetFinalMarks_ValidMarks_ShouldSetTheMarks() {
        Student student = new Student();
        int validMarks = 60;
        student.setFinalMarks(validMarks);
        assertEquals(validMarks, student.getFinalMarks());
    }

    @Test
    public void testSetFinalMarks_InvalidMarks_ShouldThrowIllegalArgumentException() {
        Student student = new Student();
        int invalidMarks = 101;
        assertThrows(IllegalArgumentException.class, () -> student.setFinalMarks(invalidMarks));
    }

    @Test
    public void testCalculateGrading_ValidMarks_ShouldReturnGrading() {
        Student student = new Student("John Doe", "1234567A", 10, 10, 20, 60);
        assertEquals("A+", student.CalculateGrading());
    }

    @Test
    public void testCalculateGrading_InvalidMarks_ShouldThrowIllegalArgumentException() {
        // use mocks to bypass the validation of the marks and test the CalculateGrading method with invalid marks
        try (MockedStatic<StudentValidator> mockedValidator = mockStatic(StudentValidator.class)) {
            mockedValidator.when(() -> StudentValidator.validateFinalMarks(Mockito.anyInt())).thenReturn(true);
            mockedValidator
                    .when(() -> StudentValidator.validateFullMarks(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyInt(),
                            Mockito.anyInt()))
                    .thenReturn(true);
            mockedValidator.when(() -> StudentValidator.validateActivityMarks(Mockito.anyInt())).thenReturn(true);
            mockedValidator.when(() -> StudentValidator.validateOralMarks(Mockito.anyInt())).thenReturn(true);
            mockedValidator.when(() -> StudentValidator.validateMidMarks(Mockito.anyInt())).thenReturn(true);
            mockedValidator.when(() -> StudentValidator.validateName(Mockito.anyString())).thenReturn(true);
            mockedValidator.when(() -> StudentValidator.validateNumber(Mockito.anyString())).thenReturn(true);

            Student student = new Student("John", "1234567A", 50, 10, 30, 60);
            // calculate grading should throw an exception because the marks are invalid for grading over 100
            Exception exception = assertThrows(IllegalArgumentException.class, student::CalculateGrading);
            assertEquals("Invalid mark", exception.getMessage());
        }
    }

    @Test
    public void testCalculateGPA_ValidMarks_ShouldReturnGPA() {
        Student student = new Student("John Doe", "1234567A", 10, 10, 20, 60);
        assertEquals(4.0, student.CalculateGPA());
    }

    @Test
    public void testCalculateGPA_InvalidMarks_ShouldThrowIllegalArgumentException() {
        // use mocks to bypass the validation of the marks and test the CalculateGPA method with invalid marks
        try (MockedStatic<StudentValidator> mockedValidator = mockStatic(StudentValidator.class)) {
            mockedValidator.when(() -> StudentValidator.validateFinalMarks(Mockito.anyInt())).thenReturn(true);
            mockedValidator
                    .when(() -> StudentValidator.validateFullMarks(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyInt(),
                            Mockito.anyInt()))
                    .thenReturn(true);
            mockedValidator.when(() -> StudentValidator.validateActivityMarks(Mockito.anyInt())).thenReturn(true);
            mockedValidator.when(() -> StudentValidator.validateOralMarks(Mockito.anyInt())).thenReturn(true);
            mockedValidator.when(() -> StudentValidator.validateMidMarks(Mockito.anyInt())).thenReturn(true);
            mockedValidator.when(() -> StudentValidator.validateName(Mockito.anyString())).thenReturn(true);
            mockedValidator.when(() -> StudentValidator.validateNumber(Mockito.anyString())).thenReturn(true);

            Student student = new Student("John", "1234567A", 50, 10, 30, 60);
            // calculate GPA should throw an exception because the marks are invalid for GPA over 100
            Exception exception = assertThrows(IllegalArgumentException.class, student::CalculateGPA);
            assertEquals("Invalid mark", exception.getMessage());
        }
    }

    @Test
    public void testToString_ValidStudent_ShouldReturnStringRepresentation() {
        Student student = new Student("John Doe", "1234567A", 10, 10, 20, 60);
        assertEquals("Student{name='John Doe', number='1234567A', activityMarks=10, oralMarks=10, midMarks=20, finalMarks=60}", student.toString());
    }

    @Test
    public void testGetters() {
        Student student = new Student("John Doe", "1234567A", 10, 10, 20, 60);

        assertEquals("John Doe", student.getName());
        assertEquals("1234567A", student.getNumber());
        assertEquals(10, student.getActivityMarks());
        assertEquals(10, student.getOralMarks());
        assertEquals(20, student.getMidMarks());
        assertEquals(60, student.getFinalMarks());
    }

    @Test
    public void testCalculateGrading() {
        Student student;

        student = new Student("John Doe", "1234567A", 10, 10, 20, 57);
        assertEquals("A+", student.CalculateGrading());

        student = new Student("John Doe", "1234567A", 10, 10, 20, 56);
        assertEquals("A", student.CalculateGrading());

        student = new Student("John Doe", "1234567A", 10, 10, 20, 52);
        assertEquals("A-", student.CalculateGrading());

        student = new Student("John Doe", "1234567A", 10, 10, 20, 48);
        assertEquals("B+", student.CalculateGrading());

        student = new Student("John Doe", "1234567A", 10, 10, 20, 43);
        assertEquals("B", student.CalculateGrading());

        // test the rest
        student = new Student("John Doe", "1234567A", 10, 10, 20, 38);
        assertEquals("B-", student.CalculateGrading());

        student = new Student("John Doe", "1234567A", 10, 10, 20, 33);
        assertEquals("C+", student.CalculateGrading());

        student = new Student("John Doe", "1234567A", 10, 10, 20, 32);
        assertEquals("C", student.CalculateGrading());

        student = new Student("John Doe", "1234567A", 10, 10, 20, 28);
        assertEquals("C-", student.CalculateGrading());

        student = new Student("John Doe", "1234567A", 10, 10, 20, 25);
        assertEquals("D+", student.CalculateGrading());

        student = new Student("John Doe", "1234567A", 10, 10, 20, 22);
        assertEquals("D", student.CalculateGrading());

        student = new Student("John Doe", "1234567A", 10, 10, 20, 19);
        assertEquals("F", student.CalculateGrading());


    }

    @Test
    public void testSetFinalMarks_InvalidFullMarks_ShouldThrowIllegalArgumentException() {
        // use mocks to bypass the validation of the marks and test the setFinalMarks method with invalid marks
        try (MockedStatic<StudentValidator> mockedValidator = mockStatic(StudentValidator.class)) {
            mockedValidator.when(() -> StudentValidator.validateFinalMarks(Mockito.anyInt())).thenReturn(true);
            // use real implementation of the validateFullMarks method
            mockedValidator.when(() -> StudentValidator.validateFullMarks(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyInt(),
                    Mockito.anyInt())).thenCallRealMethod();
            mockedValidator.when(() -> StudentValidator.validateActivityMarks(Mockito.anyInt())).thenReturn(true);
            mockedValidator.when(() -> StudentValidator.validateOralMarks(Mockito.anyInt())).thenReturn(true);
            mockedValidator.when(() -> StudentValidator.validateMidMarks(Mockito.anyInt())).thenReturn(true);
            mockedValidator.when(() -> StudentValidator.validateName(Mockito.anyString())).thenReturn(true);
            mockedValidator.when(() -> StudentValidator.validateNumber(Mockito.anyString())).thenReturn(true);

            Student student = new Student();
            student.setName("John Doe");
            student.setNumber("1234567A");
            student.setActivityMarks(10);
            student.setOralMarks(10);
            student.setMidMarks(20);

            // setFinalMarks should throw an exception because the full marks are invalid
            Exception exception = assertThrows(IllegalArgumentException.class, () -> student.setFinalMarks(61));
            assertEquals("Invalid full marks", exception.getMessage());
        }
    }
}