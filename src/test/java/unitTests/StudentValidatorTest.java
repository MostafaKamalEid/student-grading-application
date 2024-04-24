package unitTests;

import org.junit.jupiter.api.Test;
import student.project.validations.StudentValidator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StudentValidatorTest {

    @Test
    public void test_validateName_WithValidInputOfSingleWord_ShouldReturnTrue() {
        assertTrue(StudentValidator.validateName("John"));
    }

    @Test
    public void test_validateName_WithValidInputOfMultipleWords_ShouldReturnTrue() {
        assertTrue(StudentValidator.validateName("John Doe"));
    }

    @Test
    public void test_validateName_WithInvalidInputOfEmptyString_ShouldReturnFalse() {
        assertFalse(StudentValidator.validateName(""));
    }

    @Test
    public void test_validateName_WithInvalidInputOfNull_ShouldReturnFalse() {
        assertFalse(StudentValidator.validateName(null));
    }

    @Test
    public void test_validateNumber_WithValidInput_ShouldReturnTrue() {
        assertTrue(StudentValidator.validateNumber("1234567A"));
    }

    @Test
    public void test_validateNumber_WithInvalidInputOfShortLength_ShouldReturnFalse() {
        assertFalse(StudentValidator.validateNumber("123456"));
    }

    @Test
    public void test_validateNumber_WithInvalidInputOfLongLength_ShouldReturnFalse() {
        assertFalse(StudentValidator.validateNumber("123456789"));
    }

    @Test
    public void test_validateActivityMarks_WithValidMarksInRange_ShouldReturnTrue() {
        assertTrue(StudentValidator.validateActivityMarks(8));
    }

    @Test
    public void test_validateActivityMarks_WithInvalidMarksBelowRange_ShouldReturnFalse() {
        assertFalse(StudentValidator.validateActivityMarks(-1));
    }

    @Test
    public void test_validateActivityMarks_WithInvalidMarksAboveRange_ShouldReturnFalse() {
        assertFalse(StudentValidator.validateActivityMarks(20));
    }

    @Test
    public void test_validateOralMarks_WithValidMarksInRange_ShouldReturnTrue() {
        assertTrue(StudentValidator.validateOralMarks(5));
    }

    @Test
    public void test_validateOralMarks_WithInvalidMarksBelowRange_ShouldReturnFalse() {
        assertFalse(StudentValidator.validateOralMarks(-2));
    }

    @Test
    public void test_validateOralMarks_WithInvalidMarksAboveRange_ShouldReturnFalse() {
        assertFalse(StudentValidator.validateOralMarks(11));
    }

    @Test
    public void test_validateMidMarks_WithValidMarksInRange_ShouldReturnTrue() {
        assertTrue(StudentValidator.validateMidMarks(15));
    }

    @Test
    public void test_validateMidMarks_WithInvalidMarksBelowRange_ShouldReturnFalse() {
        assertFalse(StudentValidator.validateMidMarks(-1));
    }

    @Test
    public void test_validateMidMarks_WithInvalidMarksAboveRange_ShouldReturnFalse() {
        assertFalse(StudentValidator.validateMidMarks(25));
    }

    @Test
    public void test_validateFinalMarks_WithValidMarksInRange_ShouldReturnTrue() {
        assertTrue(StudentValidator.validateFinalMarks(50));
    }

    @Test
    public void test_validateFinalMarks_WithInvalidMarksBelowRange_ShouldReturnFalse() {
        assertFalse(StudentValidator.validateFinalMarks(-1));
    }

    @Test
    public void test_validateFinalMarks_WithInvalidMarksAboveRange_ShouldReturnFalse() {
        assertFalse(StudentValidator.validateFinalMarks(61));
    }

    @Test
    public void test_validateFullMarks_WithValidMarksInRange_ShouldReturnTrue() {
        assertTrue(StudentValidator.validateFullMarks(10, 10, 10, 10));
    }

    @Test
    public void test_validateFullMarks_WithValidMarksSumAboveRange_ShouldReturnFalse() {
        assertFalse(StudentValidator.validateFullMarks(40, 40, 40, 40));
    }

    @Test
    public void test_validateFullMarks_WithValidMarksSumBelowRange_ShouldReturnFalse() {
        assertFalse(StudentValidator.validateFullMarks(-40, 40, -50, -20));
    }

}
