package unitTests;

import org.junit.jupiter.api.Test;

import student.project.models.Student;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StudentTest {

    @Test
    public void testGetName() {
        Student student = new Student();
        student.setName("John Doe");
        assertEquals("John Doe", student.getName());
    }

    @Test
    public void testSetName() {
        Student student = new Student();
        student.setName("John Doe");
        assertEquals("John Doe", student.getName());
    }

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
        student.setActivityMarks(100);
        assertEquals(100, student.getActivityMarks());
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

    @Test
    public void testCalculateTotalMarks() {
        Student student = new Student();
        student.setActivityMarks(100);
        student.setOralMarks(100);
        student.setMidMarks(100);
        student.setFinalMarks(100);
        assertEquals(400, student.calculateTotalMarks());
    }
}

}
