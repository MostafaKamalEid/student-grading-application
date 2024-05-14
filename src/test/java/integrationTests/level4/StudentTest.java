package integrationTests.level4;

import org.junit.jupiter.api.Test;
import student.project.models.Student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockStatic;

public class StudentTest {

    @Test
    public void test_GetName_ShouldReturnStudentName() {
        Student student = new Student();
        student.setName("John Doe");
        assertEquals("John Doe", student.getName());
    }


    @Test
    public void test_GetNumber_ShouldReturnStudentNumber() {
        Student student = new Student();
        student.setNumber("12345678");
        assertEquals("12345678", student.getNumber());
    }

    @Test
    public void test_GetActivityMarks_ShouldReturnActivityMarks() {
        Student student = new Student();
        student.setActivityMarks(10);
        assertEquals(10, student.getActivityMarks());
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
    public void test_CalculateGPA_ShouldReturnGPA() {
        Student student = new Student("John Doe", "1234567A", 10, 10, 20, 60);
        assertEquals(4.0, student.CalculateGPA());
    }

   }
