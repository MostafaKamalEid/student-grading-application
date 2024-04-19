package unitTests;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import student.project.models.Student;
import student.project.validations.StudentValidator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockStatic;

public class StudentTest {
    @Test
    public void test_SetName_Student_ItShouldReturnExpectedName() {
        try (MockedStatic<StudentValidator> mockedValidator = mockStatic(StudentValidator.class)) {
            mockedValidator.when(() -> StudentValidator.validateName(Mockito.anyString())).thenReturn(true);

            Student student = new Student();
            student.setName("John Doe");
            assertEquals("John Doe", student.getName());
        }
    }

    @Test
    public void test_SetName_Student_WithInvalidName_ItShouldThrowIllegalArgumentException() {
        try (MockedStatic<StudentValidator> mockedValidator = mockStatic(StudentValidator.class)) {
            mockedValidator.when(() -> StudentValidator.validateName(Mockito.anyString())).thenReturn(false);

            Student student = new Student();
            Exception exception = assertThrows(IllegalArgumentException.class, () -> student.setName("John@Doe"));
            assertEquals("Invalid name", exception.getMessage());
        }
    }

    // Add more tests for other methods using similar approach
    @Test
    public void testGetName() {
        Student student = new Student();
        student.setName("John Doe");
        assertEquals("John Doe", student.getName());
    }

    // @Test
    // public void testSetName() {
    // Student student = new Student();
    // student.setName("John Doe");
    // assertEquals("John Doe", student.getName());
    // }

    @Test
    public void testGetNumber() {
        Student student = new Student();
        student.setNumber("1234567890");
        assertEquals("1234567890", student.getNumber());
    }

    @Test
    public void testSetNumber() {
        Student student = new Student();
        student.setNumber("1234567890");
        assertEquals("1234567890", student.getNumber());
    }

    @Test
    public void testGetActivityMarks() {
        Student student = new Student();
        // student.setActivityMarks(100);
        // assertEquals(100, student.getActivityMarks());
        Exception exception = assertThrows(IllegalArgumentException.class, () -> student.setActivityMarks(100));
        assertEquals("Invalid activity marks", exception.getMessage());
    }

    @Test
    public void testSetActivityMarks() {
        Student student = new Student();
        student.setActivityMarks(100);
        assertEquals(100, student.getActivityMarks());
    }

    @Test
    public void testGetOralMarks() {
        Student student = new Student();
        student.setOralMarks(100);
        assertEquals(100, student.getOralMarks());
    }

    @Test
    public void testSetOralMarks() {
        Student student = new Student();
        student.setOralMarks(100);
        assertEquals(100, student.getOralMarks());
    }

    @Test
    public void testGetMidMarks() {
        Student student = new Student();
        student.setMidMarks(100);
        assertEquals(100, student.getMidMarks());
    }

    @Test
    public void testSetMidMarks() {
        Student student = new Student();
        student.setMidMarks(100);
        assertEquals(100, student.getMidMarks());
    }

    @Test
    public void testGetFinalMarks() {
        Student student = new Student();
        student.setFinalMarks(100);
        assertEquals(100, student.getFinalMarks());
    }

    @Test
    public void testSetFinalMarks() {
        Student student = new Student();
        student.setFinalMarks(100);
        assertEquals(100, student.getFinalMarks());
    }

    // @Test
    // public void testCalculateTotalMarks() {
    // Student student = new Student();
    // student.setActivityMarks(100);
    // student.setOralMarks(100);
    // student.setMidMarks(100);
    // student.setFinalMarks(100);
    //
    // }
}
