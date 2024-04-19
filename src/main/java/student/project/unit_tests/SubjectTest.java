package student.project.unit_tests;

import org.junit.Test;
import student.project.models.Subject;

import static org.junit.Assert.assertEquals;



public class SubjectTest {

    @Test
    public void test_getName_Subject_WithValidInput_ItShouldReturnNameOfCourse(){
        Subject subject= new Subject("Software Testing","CSE337s",100);
        String expected= "Testing";
        String actual= subject.getName();
        assertEquals("Testing GetName function",expected,actual );
    }
    @Test
    public void test_getName_Subject_WithInvalidNameStartsWithNumber_ItShouldThrowIllegalArgumentException()
    {


    }
    @Test
    public void TestGetCodeOfCourse_ItShouldReturnCodeOfCourse(){
        Subject subject= new Subject("Testing","CSE441s",100);
        String expected= "CSE441s";
        String actual= subject.getCode();
        assertEquals("Testing GetCode function",expected,actual );
    }

    @Test
    public void TestGetFullMarkOfCourse_ItShouldReturnFullMarkOfCourse(){
        Subject subject= new Subject("Testing","CSE441s",100);
        int expected= 100;
        int actual= subject.getFullMark();
        assertEquals("Testing getFullMark function",expected,actual );
    }
    @Test
    public void TestSetFullMarkOfCourse_ItShouldReturnSetFullMarkOfCourse(){
        Subject subject= new Subject();
        subject.setFullMark(100);
        int actual = subject.getFullMark();
        int expected= 100;
        assertEquals("Testing getFullMark function",expected,actual );
    }
    @Test
    public void TestToString_ItShouldReturnRequiredStringOfCourse(){
        Subject subject= new Subject("Testing","CSE441s",100);
        String actual = subject.toString();
        String expected=  "Subject{" +
                "name='" + "Testing" + '\'' +
                ", code='" + "CSE441s" + '\'' +
                ", fullMark=" + 100 +
                '}' ;
        assertEquals("Testing getFullMark function",expected,actual );
    }








}
