package whiteBoxesTests;

import org.junit.jupiter.api.Test;
import student.project.validations.StudentValidator;

import static org.junit.jupiter.api.Assertions.*;
/// @brief Class for testing the StudentValidator class
/// The StudentValidator class is tested using white-box testing method.
/// The tests are written to cover all the possible paths of the class.
/// The tests are written to cover all the branches of the class.
public class StudentValidatorTest {

    @Test
    public void testValidateName_ValidName_ShouldReturnTrue() {
        assertTrue(StudentValidator.validateName("John Doe"));
    }

    @Test
    public void testValidateName_InvalidName_ShouldReturnFalse() {
        assertFalse(StudentValidator.validateName("John@Doe"));
    }

    @Test
    public void testValidateNumber_ValidNumber_ShouldReturnTrue() {
        assertTrue(StudentValidator.validateNumber("1234567A"));
    }

    @Test
    public void testValidateNumber_InvalidNumber_ShouldReturnFalse() {
        assertFalse(StudentValidator.validateNumber("1234567890"));
    }

    @Test
    public void testValidateActivityMarks_ValidMarks_ShouldReturnTrue() {
        assertTrue(StudentValidator.validateActivityMarks(10));
    }

    @Test
    public void testValidateActivityMarks_InvalidMarks_ShouldReturnFalse() {
        assertFalse(StudentValidator.validateActivityMarks(100));
    }

    @Test
    public void testValidateOralMarks_ValidMarks_ShouldReturnTrue() {
        assertTrue(StudentValidator.validateOralMarks(10));
    }

    @Test
    public void testValidateOralMarks_InvalidMarks_ShouldReturnFalse() {
        assertFalse(StudentValidator.validateOralMarks(100));
    }

    @Test
    public void testValidateMidMarks_ValidMarks_ShouldReturnTrue() {
        assertTrue(StudentValidator.validateMidMarks(20));
    }

    @Test
    public void testValidateMidMarks_InvalidMarks_ShouldReturnFalse() {
        assertFalse(StudentValidator.validateMidMarks(100));
    }

    @Test
    public void testValidateFinalMarks_ValidMarks_ShouldReturnTrue() {
        assertTrue(StudentValidator.validateFinalMarks(60));
    }

    @Test
    public void testValidateFinalMarks_InvalidMarks_ShouldReturnFalse() {
        assertFalse(StudentValidator.validateFinalMarks(100));
    }

    @Test
    public void testValidateFullMarks_ValidMarks_ShouldReturnTrue() {
        assertTrue(StudentValidator.validateFullMarks(10, 10, 20, 60));
    }

    @Test
    public void testValidateFullMarks_InvalidMarks_ShouldReturnFalse() {
        assertFalse(StudentValidator.validateFullMarks(50, 10, 30, 60));
    }
}