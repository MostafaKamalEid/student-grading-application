package whiteBoxesTests;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import student.project.models.Subject;
import student.project.validations.SubjectValidator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public  class SubjectTest {
    @Test
    public void testGetName() {
        Subject subject = new Subject("Software Testing", "CSE337s", 100);
        assertEquals("Software Testing", subject.getName());
    }

    @Test
    public void testSetName() {
        Subject subject = new Subject();
        subject.setName("Software Testing");
        assertEquals("Software Testing", subject.getName());
    }

    @Test
    public void testSetName_InvalidName() {
        Subject subject = new Subject();
        assertThrows(IllegalArgumentException.class, () -> subject.setName("3Software Testing"));
    }

    @Test
    public void testGetCode() {
        Subject subject = new Subject("Software Testing", "CSE337s", 100);
        assertEquals("CSE337s", subject.getCode());
    }

    @Test
    public void testSetCode() {
        Subject subject = new Subject();
        subject.setCode("CSE337s");
        assertEquals("CSE337s", subject.getCode());
    }

    @Test
    public void testSetCode_InvalidCode() {
        Subject subject = new Subject();
        assertThrows(IllegalArgumentException.class, () -> subject.setCode("CSE337s9"));
    }

    @Test
    public void testGetFullMark() {
        Subject subject = new Subject("Software Testing", "CSE337s", 100);
        assertEquals(100, subject.getFullMark());
    }

    @Test
    public void testSetFullMark() {
        Subject subject = new Subject();
        subject.setFullMark(100);
        assertEquals(100, subject.getFullMark());
    }

    @Test
    public void testSetFullMark_InvalidFullMark() {
        Subject subject = new Subject();
        assertThrows(IllegalArgumentException.class, () -> subject.setFullMark(150));
    }

    @Test
    public void testToString() {
        Subject subject = new Subject("Software Testing", "CSE337s", 100);
        String expected = "Subject{name='Software Testing', code='CSE337s', fullMark=100}";
        assertEquals(expected, subject.toString());
    }

}

