package integrationTests.level4;
import org.junit.jupiter.api.Test;
import student.project.models.Subject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public  class SubjectGettersTest {

    @Test
    public void test_getName_Subject_ItShouldReturnNameOfCourse(){
        Subject subject= new Subject("Software Testing","CSE337s",100);
        String expected= "Software Testing";
        String actual= subject.getName();
        assertEquals(expected,actual );
    }

    @Test
    public void test_getCode_ShouldReturnCodeOfCourse(){
        Subject subject= new Subject("Software Testing","CSE337s",100);
        String expected= "CSE337s";
        String actual= subject.getCode();
        assertEquals(expected,actual );
    }

    @Test
    public void test_getFullMark_ShouldReturnFullMarkOfCourse(){
        Subject subject= new Subject("Software Testing","CSE337s",100);
        int expected= 100;
        int actual= subject.getFullMark();
        assertEquals(expected,actual );
    }
    @Test
    public void test_setFullMark_WithValidInputOfValue100_ShouldSetFullMarkOfCourse(){
            Subject subject = new Subject();
            subject.setFullMark(100);
            int actual = subject.getFullMark();
            int expected = 100;
            assertEquals(expected, actual);
    }
}

