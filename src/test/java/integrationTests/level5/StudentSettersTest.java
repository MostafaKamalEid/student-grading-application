package integrationTests.level5;

import org.junit.jupiter.api.Test;
import student.project.models.Student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockStatic;

public class StudentSettersTest {
    @Test
    public void test_SetName_ShouldSetStudentName() {
            Student student = new Student();
            student.setName("John Doe");
            assertEquals("John Doe", student.getName());
    }

    @Test
    public void test_SetName_WithInvalidName_ShouldThrowIllegalArgumentException() {
            Student student = new Student();
            Exception exception = assertThrows(IllegalArgumentException.class, () -> student.setName("John@Doe"));
            assertEquals("Invalid name", exception.getMessage());
    }

    @Test
    public void test_SetNumber_ShouldSetStudentNumber() {
            Student student = new Student();
            student.setNumber("1234567s");
            assertEquals("1234567s", student.getNumber());
    }

    @Test
    public void test_SetNumber_WithInvalidNumber_ShouldThrowIllegalArgumentException() {
            Student student = new Student();
            Exception exception = assertThrows(IllegalArgumentException.class, () -> student.setNumber("1234567890"));
            assertEquals("Invalid number", exception.getMessage());
    }

    @Test
    public void test_SetActivityMarks_ShouldSetActivityMarks() {
            Student student = new Student();
            student.setActivityMarks(10);
            assertEquals(10, student.getActivityMarks());
    }

    @Test
    public void test_SetActivityMarks_WithInvalidValue_ShouldThrowIllegalArgumentException() {
            Student student = new Student();
            Exception exception = assertThrows(IllegalArgumentException.class, () -> student.setActivityMarks(100));
            assertEquals("Invalid activity marks", exception.getMessage());
    }

    @Test
    public void test_SetOralMarks_ShouldSetOralMarks() {
            Student student = new Student();
            student.setOralMarks(10);
            assertEquals(10, student.getOralMarks());
    }

    @Test
    public void test_SetOralMarks_WithInvalidValue_ShouldThrowIllegalArgumentException() {
            Student student = new Student();
            Exception exception = assertThrows(IllegalArgumentException.class, () -> student.setOralMarks(100));
            assertEquals("Invalid oral marks", exception.getMessage());
    }


    @Test
    public void test_SetMidMarks_ShouldSetMidMarks() {
            Student student = new Student();
            student.setMidMarks(20);
            assertEquals(20, student.getMidMarks());
    }

    @Test
    public void test_SetMidMarks_WithInvalidValue_ShouldThrowIllegalArgumentException() {
            Student student = new Student();
            Exception exception = assertThrows(IllegalArgumentException.class, () -> student.setMidMarks(100));
            assertEquals("Invalid mid marks", exception.getMessage());
    }

    @Test
    public void test_SetFinalMarks_ShouldSetFinalMarks() {
            Student student = new Student();
            student.setFinalMarks(60);
            assertEquals(60, student.getFinalMarks());
    }

    @Test
    public void test_SetFinalMarks_WithInvalidValue_ShouldThrowIllegalArgumentException() {
            Student student = new Student();
            Exception exception = assertThrows(IllegalArgumentException.class, () -> student.setFinalMarks(100));
            assertEquals("Invalid final marks", exception.getMessage());
    }

    @Test
    public void test_SetFinalMarks_WithInvalidFullMarks_ShouldThrowIllegalArgumentException() {
            Student student = new Student();
            Exception exception = assertThrows(IllegalArgumentException.class, () -> student.setFinalMarks(160));
            assertEquals("Invalid final marks", exception.getMessage());
    }

    @Test
    public void test_ToString() {
        Student student = new Student("John Doe", "1234567A", 10, 10, 20, 60);
        assertEquals(
                "Student{name='John Doe', number='1234567A', activityMarks=10, oralMarks=10, midMarks=20, finalMarks=60}",
                student.toString());
    }
}
