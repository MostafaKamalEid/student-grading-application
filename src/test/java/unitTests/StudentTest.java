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
    public void test_SetName_ShouldSetStudentName() {
        try (MockedStatic<StudentValidator> mockedValidator = mockStatic(StudentValidator.class)) {
            mockedValidator.when(() -> StudentValidator.validateName(Mockito.anyString())).thenReturn(true);

            Student student = new Student();
            student.setName("John Doe");
            assertEquals("John Doe", student.getName());
        }
    }

    @Test
    public void test_SetName_WithInvalidName_ShouldThrowIllegalArgumentException() {
        try (MockedStatic<StudentValidator> mockedValidator = mockStatic(StudentValidator.class)) {
            mockedValidator.when(() -> StudentValidator.validateName(Mockito.anyString())).thenReturn(false);

            Student student = new Student();
            Exception exception = assertThrows(IllegalArgumentException.class, () -> student.setName("John@Doe"));
            assertEquals("Invalid name", exception.getMessage());
        }
    }

    @Test
    public void test_GetName_ShouldReturnStudentName() {
        Student student = new Student();
        student.setName("John Doe");
        assertEquals("John Doe", student.getName());
    }

    @Test
    public void test_SetNumber_ShouldSetStudentNumber() {
        try (MockedStatic<StudentValidator> mockedValidator = mockStatic(StudentValidator.class)) {
            mockedValidator.when(() -> StudentValidator.validateNumber(Mockito.anyString())).thenReturn(true);

            Student student = new Student();
            student.setNumber("1234567s");
            assertEquals("1234567s", student.getNumber());
        }
    }

    @Test
    public void test_SetNumber_WithInvalidNumber_ShouldThrowIllegalArgumentException() {
        try (MockedStatic<StudentValidator> mockedValidator = mockStatic(StudentValidator.class)) {
            mockedValidator.when(() -> StudentValidator.validateNumber(Mockito.anyString())).thenReturn(false);

            Student student = new Student();
            Exception exception = assertThrows(IllegalArgumentException.class, () -> student.setNumber("1234567890"));
            assertEquals("Invalid number", exception.getMessage());
        }
    }

    @Test
    public void test_GetNumber_ShouldReturnStudentNumber() {
        Student student = new Student();
        student.setNumber("12345678");
        assertEquals("12345678", student.getNumber());
    }

    @Test
    public void test_SetActivityMarks_ShouldSetActivityMarks() {
        try (MockedStatic<StudentValidator> mockedValidator = mockStatic(StudentValidator.class)) {
            mockedValidator.when(() -> StudentValidator.validateActivityMarks(Mockito.anyInt())).thenReturn(true);

            Student student = new Student();
            student.setActivityMarks(10);
            assertEquals(10, student.getActivityMarks());
        }
    }

    @Test
    public void test_SetActivityMarks_WithInvalidValue_ShouldThrowIllegalArgumentException() {
        try (MockedStatic<StudentValidator> mockedValidator = mockStatic(StudentValidator.class)) {
            mockedValidator.when(() -> StudentValidator.validateActivityMarks(Mockito.anyInt())).thenReturn(false);

            Student student = new Student();
            Exception exception = assertThrows(IllegalArgumentException.class, () -> student.setActivityMarks(100));
            assertEquals("Invalid activity marks", exception.getMessage());
        }
    }

    @Test
    public void test_GetActivityMarks_ShouldReturnActivityMarks() {
        Student student = new Student();
        student.setActivityMarks(10);
        assertEquals(10, student.getActivityMarks());
    }

    @Test
    public void test_SetOralMarks_ShouldSetOralMarks() {
        try (MockedStatic<StudentValidator> mocked = Mockito.mockStatic(StudentValidator.class)) {
            mocked.when(() -> StudentValidator.validateOralMarks(Mockito.anyInt())).thenReturn(true);
            Student student = new Student();
            student.setOralMarks(10);
            assertEquals(10, student.getOralMarks());
        }
    }

    @Test
    public void test_SetOralMarks_WithInvalidValue_ShouldThrowIllegalArgumentException() {
        try (MockedStatic<StudentValidator> mockedValidator = mockStatic(StudentValidator.class)) {
            mockedValidator.when(() -> StudentValidator.validateOralMarks(Mockito.anyInt())).thenReturn(false);

            Student student = new Student();
            Exception exception = assertThrows(IllegalArgumentException.class, () -> student.setOralMarks(100));
            assertEquals("Invalid oral marks", exception.getMessage());
        }
    }

    @Test
    public void test_GetOralMarks_ShouldReturnOralMarks() {
        Student student = new Student();
        student.setOralMarks(10);
        assertEquals(10, student.getOralMarks());
    }

    @Test
    public void test_GetMidMarks_ShouldReturnMidMarks() {
        Student student = new Student();
        student.setMidMarks(20);
        assertEquals(20, student.getMidMarks());
    }

    @Test
    public void test_SetMidMarks_ShouldSetMidMarks() {
        try (MockedStatic<StudentValidator> mocked = Mockito.mockStatic(StudentValidator.class)) {
            mocked.when(() -> StudentValidator.validateMidMarks(Mockito.anyInt())).thenReturn(true);
            Student student = new Student();
            student.setMidMarks(20);
            assertEquals(20, student.getMidMarks());
        }
    }

    @Test
    public void test_SetMidMarks_WithInvalidValue_ShouldThrowIllegalArgumentException() {
        try (MockedStatic<StudentValidator> mockedValidator = mockStatic(StudentValidator.class)) {
            mockedValidator.when(() -> StudentValidator.validateMidMarks(Mockito.anyInt())).thenReturn(false);

            Student student = new Student();
            Exception exception = assertThrows(IllegalArgumentException.class, () -> student.setMidMarks(100));
            assertEquals("Invalid mid marks", exception.getMessage());
        }
    }

    @Test
    public void test_SetFinalMarks_ShouldSetFinalMarks() {
        try (MockedStatic<StudentValidator> mocked = Mockito.mockStatic(StudentValidator.class)) {
            mocked.when(() -> StudentValidator.validateFinalMarks(Mockito.anyInt())).thenReturn(true);
            mocked.when(() -> StudentValidator.validateFullMarks(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyInt(),
                    Mockito.anyInt())).thenReturn(true);
            Student student = new Student();
            student.setFinalMarks(60);
            assertEquals(60, student.getFinalMarks());
        }
    }

    @Test
    public void test_SetFinalMarks_WithInvalidValue_ShouldThrowIllegalArgumentException() {
        try (MockedStatic<StudentValidator> mockedValidator = mockStatic(StudentValidator.class)) {
            mockedValidator.when(() -> StudentValidator.validateFinalMarks(Mockito.anyInt())).thenReturn(false);

            Student student = new Student();
            Exception exception = assertThrows(IllegalArgumentException.class, () -> student.setFinalMarks(100));
            assertEquals("Invalid final marks", exception.getMessage());
        }
    }

    @Test
    public void test_SetFinalMarks_WithInvalidFullMarks_ShouldThrowIllegalArgumentException() {
        try (MockedStatic<StudentValidator> mockedValidator = mockStatic(StudentValidator.class)) {
            mockedValidator.when(() -> StudentValidator.validateFinalMarks(Mockito.anyInt())).thenReturn(true);
            mockedValidator
                    .when(() -> StudentValidator.validateFullMarks(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyInt(),
                            Mockito.anyInt()))
                    .thenReturn(false);

            Student student = new Student();
            Exception exception = assertThrows(IllegalArgumentException.class, () -> student.setFinalMarks(60));
            assertEquals("Invalid full marks", exception.getMessage());
        }
    }

    @Test
    public void test_GetFinalMarks_ShouldReturnFinalMarks() {
        Student student = new Student();
        student.setFinalMarks(60);
        assertEquals(60, student.getFinalMarks());
    }

    @Test
    public void test_CalculateGrading_ShouldReturnStudentGrade() {
        Student student = new Student("John Doe", "1234567A", 10, 10, 20, 60);
        assertEquals("A+", student.CalculateGrading());
    }

    @Test
    public void test_CalculateGrading_WithMarkOutofRange_ShouldThrowIllegalArgumentException() {
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
            Exception exception = assertThrows(IllegalArgumentException.class, () -> student.CalculateGrading());
            assertEquals("Invalid mark", exception.getMessage());
        }
    }

    @Test
    public void test_CalculateGPA_ShouldReturnGPA() {
        Student student = new Student("John Doe", "1234567A", 10, 10, 20, 60);
        assertEquals(4.0, student.CalculateGPA());
    }

    @Test
    public void test_ToString() {
        Student student = new Student("John Doe", "1234567A", 10, 10, 20, 60);
        assertEquals(
                "Student{name='John Doe', number='1234567A', activityMarks=10, oralMarks=10, midMarks=20, finalMarks=60}",
                student.toString());
    }
}
