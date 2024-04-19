package unitTests;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import student.project.models.Subject;
import student.project.validations.SubjectValidator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public  class SubjectTest {

    @Test
    public void test_getName_Subject_ItShouldReturnNameOfCourse(){
        Subject subject= new Subject("Software Testing","CSE337s",100);
        String expected= "Software Testing";
        String actual= subject.getName();
        assertEquals(expected,actual );
    }
    @Test
    public void test_setName_Subject_WithInvalidNameStartsWithNumber_ItShouldThrowIllegalArgumentException()
    {
        // Act - calling setName with invalid input should throw IllegalArgumentException

        try (MockedStatic<SubjectValidator> subjectValidatorMock = Mockito.mockStatic(SubjectValidator.class)) {
            String input="3 Machine learning";
            subjectValidatorMock.when(() -> SubjectValidator.validateName(input)).thenReturn(false);
            Subject subject= new Subject();
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                    subject.setName(input));
            assertEquals("Invalid name", exception.getMessage());

        }
    }
    @Test
    public void test_setName_Subject_WithInvalidNameEndsWithNumber_ItShouldThrowIllegalArgumentException()
    {
        // Act - calling setName with invalid input should throw IllegalArgumentException

        try (MockedStatic<SubjectValidator> subjectValidatorMock = Mockito.mockStatic(SubjectValidator.class)) {
            String input="Machine learning 3";
            subjectValidatorMock.when(() -> SubjectValidator.validateName(input)).thenReturn(false);
            Subject subject= new Subject();
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                    subject.setName(input));
            assertEquals("Invalid name", exception.getMessage());

        }
    }
    @Test
    public void test_setName_Subject_WithInvalidNameContainsNumber_ItShouldThrowIllegalArgumentException()
    {
        // Act - calling setName with invalid input should throw IllegalArgumentException

        try (MockedStatic<SubjectValidator> subjectValidatorMock = Mockito.mockStatic(SubjectValidator.class)) {
            String input="Machine 3 learning";
            subjectValidatorMock.when(() -> SubjectValidator.validateName(input)).thenReturn(false);
            Subject subject= new Subject();
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                    subject.setName(input));
            assertEquals("Invalid name", exception.getMessage());

        }
    }
    @Test
    public void test_setName_Subject_WithInvalidNameAllNumbers_ItShouldThrowIllegalArgumentException()
    {
        // Act - calling setName with invalid input should throw IllegalArgumentException
        try (MockedStatic<SubjectValidator> subjectValidatorMock = Mockito.mockStatic(SubjectValidator.class)) {
            String input="20 4 2001";
            subjectValidatorMock.when(() -> SubjectValidator.validateName(input)).thenReturn(false);
            Subject subject= new Subject();
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                    subject.setName(input));
            assertEquals("Invalid name", exception.getMessage());

        }
    }
    @Test
    public void test_setName_Subject_WithInvalidNameStartsWithNumberAndNoSpaces_ItShouldThrowIllegalArgumentException()
    {
        // Act - calling setName with invalid input should throw IllegalArgumentException
        try (MockedStatic<SubjectValidator> subjectValidatorMock = Mockito.mockStatic(SubjectValidator.class)) {
            String input="3Machine";
            subjectValidatorMock.when(() -> SubjectValidator.validateName(input)).thenReturn(false);
            Subject subject= new Subject();
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                    subject.setName(input));
            assertEquals("Invalid name", exception.getMessage());

        }
    }
    @Test
    public void test_setName_Subject_WithInvalidNameEndsWithNumberAndNoSpaces_ItShouldThrowIllegalArgumentException()
    {
        // Act - calling setName with invalid input should throw IllegalArgumentException
        try (MockedStatic<SubjectValidator> subjectValidatorMock = Mockito.mockStatic(SubjectValidator.class)) {
            String input="Machine3";
            subjectValidatorMock.when(() -> SubjectValidator.validateName(input)).thenReturn(false);
            Subject subject= new Subject();
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                    subject.setName(input));
            assertEquals("Invalid name", exception.getMessage());

        }
    }
    @Test
    public void test_setName_Subject_WithInvalidNameContainsWithNumberAndNoSpaces_ItShouldThrowIllegalArgumentException()
    {
        // Act - calling setName with invalid input should throw IllegalArgumentException
        try (MockedStatic<SubjectValidator> subjectValidatorMock = Mockito.mockStatic(SubjectValidator.class)) {
            String input="Mach3ine";
            subjectValidatorMock.when(() -> SubjectValidator.validateName(input)).thenReturn(false);
            Subject subject= new Subject();
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                    subject.setName(input));
            assertEquals("Invalid name", exception.getMessage());
        }
    }

    @Test
    public void test_setName_Subject_WithInvalidNameStartingWithSpace_ItShouldThrowIllegalArgumentException()
    {
        // Act - calling setName with invalid input should throw IllegalArgumentException
        try (MockedStatic<SubjectValidator> subjectValidatorMock = Mockito.mockStatic(SubjectValidator.class)) {
            String input=" Software Testing";
            subjectValidatorMock.when(() -> SubjectValidator.validateName(input)).thenReturn(false);
            Subject subject= new Subject();
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                    subject.setName(input));
            assertEquals("Invalid name", exception.getMessage());

        }
    }
    @Test
    public void test_setName_Subject_WithInvalidNameAllNumbersAndNoSpaces_ItShouldThrowIllegalArgumentException()
    {
        // Act - calling setName with invalid input should throw IllegalArgumentException
        try (MockedStatic<SubjectValidator> subjectValidatorMock = Mockito.mockStatic(SubjectValidator.class)) {
            String input="2042001";
            subjectValidatorMock.when(() -> SubjectValidator.validateName(input)).thenReturn(false);
            Subject subject= new Subject();
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                    subject.setName(input));
            assertEquals("Invalid name", exception.getMessage());

        }
    }
    @Test
    public void test_setName_Subject_WithInvalidNameContainsSpecialCharacter_ItShouldThrowIllegalArgumentException()
    {
        // Act - calling setName with invalid input should throw IllegalArgumentException
        Subject subject= new Subject();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                subject.setName("Ma@/.chine"));
        assertEquals("Invalid name", exception.getMessage());
    }
    @Test
    public void test_setName_Subject_WithValidInputContainsSpaces_ItShouldSetNameOfCourse(){
        Subject subject= new Subject();
        String expected= "Software Testing";
        subject.setName("Software Testing");
        String actual= subject.getName();
        assertEquals(expected,actual );
    }
    @Test
    public void test_setName_Subject_WithValidInputDoesntContainSpaces_ItShouldSetNameOfCourse(){
        Subject subject= new Subject();
        String expected= "ML";
        subject.setName("ML");
        String actual= subject.getName();
        assertEquals(expected,actual );
    }

    @Test
    public void test_getCode_Subject_ItShouldReturnCodeOfCourse(){
        Subject subject= new Subject("Software Testing","CSE337s",100);
        String expected= "CSE337s";
        String actual= subject.getCode();
        assertEquals(expected,actual );
    }
    @Test
    public void test_setCode_Subject_WithValidInputOf6AlphanumericCharacters_ItShouldSetCodeOfCourse(){
        Subject subject= new Subject();
        String expected= "CSE337";
        subject.setCode("CSE337");
        String actual= subject.getCode();
        assertEquals(expected,actual );
    }
    @Test
    public void test_setCode_Subject_WithValidInputOf7AlphanumericCharacters_ItShouldSetCodeOfCourse(){
        Subject subject= new Subject();
        String expected= "CSE337s";
        subject.setCode("CSE337s");
        String actual= subject.getCode();
        assertEquals(expected,actual );
    }

    @Test
    public void test_setCode_Subject_WithInvalidCodeContains5Characters_ItShouldThrowIllegalArgumentException(){
        // Act - calling setCode with invalid input should throw IllegalArgumentException
        Subject subject= new Subject();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                subject.setCode("CSE27"));
        assertEquals("Invalid code", exception.getMessage());
    }
    @Test
    public void test_setCode_Subject_WithInvalidCodeContains8Characters_ItShouldThrowIllegalArgumentException(){
        // Act - calling setCode with invalid input should throw IllegalArgumentException
        Subject subject= new Subject();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                subject.setCode("CSE27890"));
        assertEquals("Invalid code", exception.getMessage());
    }
    @Test
    public void test_setCode_Subject_WithInvalidCodeContainsANumberIn7thCharacter_ItShouldThrowIllegalArgumentException(){
        // Act - calling setCode with invalid input should throw IllegalArgumentException
        Subject subject= new Subject();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                subject.setCode("CSE2759"));
        assertEquals("Invalid code", exception.getMessage());
    }
    @Test
    public void test_setCode_Subject_WithInvalidCodeStartsWithNumber_ItShouldThrowIllegalArgumentException(){
        // Act - calling setCode with invalid input should throw IllegalArgumentException
        Subject subject= new Subject();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                subject.setCode("2CSE275"));
        assertEquals("Invalid code", exception.getMessage());
    }


    @Test
    public void test_getFullMark_Subject_ItShouldReturnFullMarkOfCourse(){
        Subject subject= new Subject("Software Testing","CSE337s",100);
        int expected= 100;
        int actual= subject.getFullMark();
        assertEquals(expected,actual );
    }
    @Test
    public void test_setFullMark_Subject_WithValidInputOfValue100_ItShouldSetFullMarkOfCourse(){
        Subject subject= new Subject();
        subject.setFullMark(100);
        int actual = subject.getFullMark();
        int expected= 100;
        assertEquals(expected,actual );
    }
    @Test
    public void test_setFullMark_Subject_WithInvalidInputOfValueMoreThan100_ItShouldThrowIllegalArgumentException(){

        // Act - calling setFullMark with invalid input should throw IllegalArgumentException
        Subject subject= new Subject();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                subject.setFullMark(150));
        assertEquals("Invalid full mark", exception.getMessage());
    }
    @Test
    public void test_setFullMark_Subject_WithInvalidInputOfValueLessThan100_ItShouldThrowIllegalArgumentException(){

        // Act - calling setFullMark with invalid input should throw IllegalArgumentException
        Subject subject= new Subject();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                subject.setFullMark(50));
        assertEquals("Invalid full mark", exception.getMessage());
    }
        @Test
    public void test_toString_Subject_ItShouldReturnRequiredStringOfCourse(){
        Subject subject= new Subject("Testing","CSE441s",100);
        String actual = subject.toString();
        String expected=  "Subject{" +
                "name='" + "Testing" + '\'' +
                ", code='" + "CSE441s" + '\'' +
                ", fullMark=" + 100 +
                '}' ;
        assertEquals(expected,actual );
    }
}

