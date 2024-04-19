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
    public void test_getName_ItShouldReturnNameOfCourse(){
        Subject subject= new Subject("Software Testing","CSE337s",100);
        String expected= "Software Testing";
        String actual= subject.getName();
        assertEquals(expected,actual );
    }
    @Test
    public void test_setName_WithInvalidNameStartsWithNumber_ShouldThrowIllegalArgumentException()
    {
        // Act - calling setName with invalid input should throw IllegalArgumentException

        try (MockedStatic<SubjectValidator> subjectValidatorMock = Mockito.mockStatic(SubjectValidator.class)) {
            subjectValidatorMock.when(() -> SubjectValidator.validateName(Mockito.anyString())).thenReturn(false);
            Subject subject= new Subject();
            String input="3 Machine learning";
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                    subject.setName(input));
            assertEquals("Invalid name", exception.getMessage());

        }
    }
    @Test
    public void test_setName_WithInvalidNameEndsWithNumber_ShouldThrowIllegalArgumentException()
    {
        // Act - calling setName with invalid input should throw IllegalArgumentException

        try (MockedStatic<SubjectValidator> subjectValidatorMock = Mockito.mockStatic(SubjectValidator.class)) {
            subjectValidatorMock.when(() -> SubjectValidator.validateName(Mockito.anyString())).thenReturn(false);
            String input="Machine learning 3";
            Subject subject= new Subject();
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                    subject.setName(input));
            assertEquals("Invalid name", exception.getMessage());

        }
    }
    @Test
    public void test_setName_WithInvalidNameContainsNumber_ShouldThrowIllegalArgumentException()
    {
        // Act - calling setName with invalid input should throw IllegalArgumentException

        try (MockedStatic<SubjectValidator> subjectValidatorMock = Mockito.mockStatic(SubjectValidator.class)) {
            subjectValidatorMock.when(() -> SubjectValidator.validateName(Mockito.anyString())).thenReturn(false);
            String input="Machine 3 learning";
            Subject subject= new Subject();
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                    subject.setName(input));
            assertEquals("Invalid name", exception.getMessage());

        }
    }
    @Test
    public void test_setName_WithInvalidNameAllNumbers_ShouldThrowIllegalArgumentException()
    {
        // Act - calling setName with invalid input should throw IllegalArgumentException
        try (MockedStatic<SubjectValidator> subjectValidatorMock = Mockito.mockStatic(SubjectValidator.class)) {
            subjectValidatorMock.when(() -> SubjectValidator.validateName(Mockito.anyString())).thenReturn(false);
            String input="20 4 2001";
            Subject subject= new Subject();
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                    subject.setName(input));
            assertEquals("Invalid name", exception.getMessage());

        }
    }
    @Test
    public void test_setName_WithInvalidNameStartsWithNumberAndNoSpaces_ShouldThrowIllegalArgumentException()
    {
        // Act - calling setName with invalid input should throw IllegalArgumentException
        try (MockedStatic<SubjectValidator> subjectValidatorMock = Mockito.mockStatic(SubjectValidator.class)) {
            subjectValidatorMock.when(() -> SubjectValidator.validateName(Mockito.anyString())).thenReturn(false);
            String input="3Machine";
            Subject subject= new Subject();
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                    subject.setName(input));
            assertEquals("Invalid name", exception.getMessage());

        }
    }
    @Test
    public void test_setName_WithInvalidNameEndsWithNumberAndNoSpaces_ShouldThrowIllegalArgumentException()
    {
        // Act - calling setName with invalid input should throw IllegalArgumentException
        try (MockedStatic<SubjectValidator> subjectValidatorMock = Mockito.mockStatic(SubjectValidator.class)) {
            subjectValidatorMock.when(() -> SubjectValidator.validateName(Mockito.anyString())).thenReturn(false);
            String input="Machine3";
            Subject subject= new Subject();
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                    subject.setName(input));
            assertEquals("Invalid name", exception.getMessage());

        }
    }
    @Test
    public void test_setName_WithInvalidNameContainsWithNumberAndNoSpaces_ShouldThrowIllegalArgumentException()
    {
        // Act - calling setName with invalid input should throw IllegalArgumentException
        try (MockedStatic<SubjectValidator> subjectValidatorMock = Mockito.mockStatic(SubjectValidator.class)) {
            subjectValidatorMock.when(() -> SubjectValidator.validateName(Mockito.anyString())).thenReturn(false);
            String input="Mach3ine";
            Subject subject= new Subject();
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                    subject.setName(input));
            assertEquals("Invalid name", exception.getMessage());
        }
    }

    @Test
    public void test_setName_WithInvalidNameStartingWithSpace_ShouldThrowIllegalArgumentException()
    {
        // Act - calling setName with invalid input should throw IllegalArgumentException
        try (MockedStatic<SubjectValidator> subjectValidatorMock = Mockito.mockStatic(SubjectValidator.class)) {
            subjectValidatorMock.when(() -> SubjectValidator.validateName(Mockito.anyString())).thenReturn(false);
            String input=" Software Testing";
            Subject subject= new Subject();
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                    subject.setName(input));
            assertEquals("Invalid name", exception.getMessage());

        }
    }
    @Test
    public void test_setName_WithInvalidNameAllNumbersAndNoSpaces_ShouldThrowIllegalArgumentException()
    {
        // Act - calling setName with invalid input should throw IllegalArgumentException
        try (MockedStatic<SubjectValidator> subjectValidatorMock = Mockito.mockStatic(SubjectValidator.class)) {
            subjectValidatorMock.when(() -> SubjectValidator.validateName(Mockito.anyString())).thenReturn(false);
            String input="2042001";
            Subject subject= new Subject();
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                    subject.setName(input));
            assertEquals("Invalid name", exception.getMessage());

        }
    }
    @Test
    public void test_setName_WithInvalidNameContainsSpecialCharacter_ShouldThrowIllegalArgumentException()
    {
        // Act - calling setName with invalid input should throw IllegalArgumentException
        try (MockedStatic<SubjectValidator> subjectValidatorMock = Mockito.mockStatic(SubjectValidator.class)) {
            subjectValidatorMock.when(() -> SubjectValidator.validateName(Mockito.anyString())).thenReturn(false);
            Subject subject = new Subject();
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                    subject.setName("Ma@/.chine"));
            assertEquals("Invalid name", exception.getMessage());
        }
    }
    @Test
    public void test_setName_WithValidInputContainsSpaces_ShouldSetNameOfCourse(){
        try (MockedStatic<SubjectValidator> subjectValidatorMock = Mockito.mockStatic(SubjectValidator.class)) {
            subjectValidatorMock.when(() -> SubjectValidator.validateName(Mockito.anyString())).thenReturn(true);
            Subject subject = new Subject();
            String expected = "Software Testing";
            subject.setName("Software Testing");
            String actual = subject.getName();
            assertEquals(expected, actual);
        }
    }
    @Test
    public void test_setName_WithValidInputDoesntContainSpaces_ShouldSetNameOfCourse(){
        try (MockedStatic<SubjectValidator> subjectValidatorMock = Mockito.mockStatic(SubjectValidator.class)) {
            subjectValidatorMock.when(() -> SubjectValidator.validateName(Mockito.anyString())).thenReturn(true);
            Subject subject = new Subject();
            String expected = "ML";
            subject.setName("ML");
            String actual = subject.getName();
            assertEquals(expected, actual);
        }
    }

    @Test
    public void test_getCode_ShouldReturnCodeOfCourse(){
        Subject subject= new Subject("Software Testing","CSE337s",100);
        String expected= "CSE337s";
        String actual= subject.getCode();
        assertEquals(expected,actual );
    }
    @Test
    public void test_setCode_WithValidInputOf6AlphanumericCharacters_ShouldSetCodeOfCourse(){
        try (MockedStatic<SubjectValidator> subjectValidatorMock = Mockito.mockStatic(SubjectValidator.class)) {
            subjectValidatorMock.when(() -> SubjectValidator.validateCode(Mockito.anyString())).thenReturn(true);
            Subject subject = new Subject();
            String expected = "CSE337";
            subject.setCode("CSE337");
            String actual = subject.getCode();
            assertEquals(expected, actual);
        }
    }
    @Test
    public void test_setCode_WithValidInputOf7AlphanumericCharacters_ShouldSetCodeOfCourse(){
        try (MockedStatic<SubjectValidator> subjectValidatorMock = Mockito.mockStatic(SubjectValidator.class)) {
            subjectValidatorMock.when(() -> SubjectValidator.validateCode(Mockito.anyString())).thenReturn(true);
            Subject subject = new Subject();
            String expected = "CSE337s";
            subject.setCode("CSE337s");
            String actual = subject.getCode();
            assertEquals(expected, actual);
        }
    }

    @Test
    public void test_setCode_WithInvalidCodeContains5Characters_ShouldThrowIllegalArgumentException(){
        // Act - calling setCode with invalid input should throw IllegalArgumentException
        try (MockedStatic<SubjectValidator> subjectValidatorMock = Mockito.mockStatic(SubjectValidator.class)) {
            subjectValidatorMock.when(() -> SubjectValidator.validateCode(Mockito.anyString())).thenReturn(false);
            Subject subject = new Subject();
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                    subject.setCode("CSE27"));
            assertEquals("Invalid code", exception.getMessage());
        }
    }
    @Test
    public void test_setCode_WithInvalidCodeContains8Characters_ShouldThrowIllegalArgumentException(){
        // Act - calling setCode with invalid input should throw IllegalArgumentException
        try (MockedStatic<SubjectValidator> subjectValidatorMock = Mockito.mockStatic(SubjectValidator.class)) {
            subjectValidatorMock.when(() -> SubjectValidator.validateCode(Mockito.anyString())).thenReturn(false);
            Subject subject = new Subject();
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                    subject.setCode("CSE27890"));
            assertEquals("Invalid code", exception.getMessage());
        }
    }
    @Test
    public void test_setCode_WithInvalidCodeContainsANumberIn7thCharacter_ShouldThrowIllegalArgumentException(){
        // Act - calling setCode with invalid input should throw IllegalArgumentException
        try (MockedStatic<SubjectValidator> subjectValidatorMock = Mockito.mockStatic(SubjectValidator.class)) {
            subjectValidatorMock.when(() -> SubjectValidator.validateCode(Mockito.anyString())).thenReturn(false);
            Subject subject = new Subject();
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                    subject.setCode("CSE2759"));
            assertEquals("Invalid code", exception.getMessage());
        }
    }
    @Test
    public void test_setCode_WithInvalidCodeStartsWithNumber_ShouldThrowIllegalArgumentException(){
        // Act - calling setCode with invalid input should throw IllegalArgumentException
        try (MockedStatic<SubjectValidator> subjectValidatorMock = Mockito.mockStatic(SubjectValidator.class)) {
            subjectValidatorMock.when(() -> SubjectValidator.validateCode(Mockito.anyString())).thenReturn(false);
            Subject subject = new Subject();
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                    subject.setCode("2CSE275"));
            assertEquals("Invalid code", exception.getMessage());
        }
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
        try (MockedStatic<SubjectValidator> subjectValidatorMock = Mockito.mockStatic(SubjectValidator.class)) {
            subjectValidatorMock.when(() -> SubjectValidator.validateFullMark(Mockito.anyInt())).thenReturn(true);
            Subject subject = new Subject();
            subject.setFullMark(100);
            int actual = subject.getFullMark();
            int expected = 100;
            assertEquals(expected, actual);
        }
    }
    @Test
    public void test_setFullMark_WithInvalidInputOfValueMoreThan100_ShouldThrowIllegalArgumentException(){

        // Act - calling setFullMark with invalid input should throw IllegalArgumentException
        try (MockedStatic<SubjectValidator> subjectValidatorMock = Mockito.mockStatic(SubjectValidator.class)) {
            subjectValidatorMock.when(() -> SubjectValidator.validateFullMark(Mockito.anyInt())).thenReturn(false);
            Subject subject = new Subject();
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                    subject.setFullMark(150));
            assertEquals("Invalid full mark", exception.getMessage());
        }
    }
    @Test
    public void test_setFullMark_WithInvalidInputOfValueLessThan100_ShouldThrowIllegalArgumentException(){

        // Act - calling setFullMark with invalid input should throw IllegalArgumentException
        try (MockedStatic<SubjectValidator> subjectValidatorMock = Mockito.mockStatic(SubjectValidator.class)) {
            subjectValidatorMock.when(() -> SubjectValidator.validateFullMark(Mockito.anyInt())).thenReturn(false);
            Subject subject = new Subject();
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                    subject.setFullMark(50));
            assertEquals("Invalid full mark", exception.getMessage());
        }
    }
        @Test
    public void test_toString_ShouldReturnRequiredStringOfCourse(){
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

