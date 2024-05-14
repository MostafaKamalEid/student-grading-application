package integrationTests.level6;

import org.junit.jupiter.api.Test;
import student.project.validations.SubjectValidator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SubjectValidatorTest {

    //////////////////////////
    //Testing Validate Name//
    ////////////////////////
    @Test
    public void test_validateName_WithValidInputOfSingleWord_ShouldReturnTrue() {
        assertTrue(SubjectValidator.validateName("Mathematics"), "Single word should be valid");
    }

    @Test
    public void test_validateName_WithValidInputOfMultipleWords_ShouldReturnTrue() {
        assertTrue(SubjectValidator.validateName("Physical Education"), "Multiple words should be valid");
    }

    @Test
    public void test_validateName_WithLeadingSpace_ShouldReturnFalse() {
        assertFalse(SubjectValidator.validateName(" History"), "Leading space should make the name invalid");
    }

    @Test
    public void test_validateName_WithEmptyString_ShouldReturnFalse() {
        assertFalse(SubjectValidator.validateName(""), "Empty string should be invalid");
    }

    @Test
    public void test_validateName_WithNullInput_ShouldReturnFalse() {
        assertFalse(SubjectValidator.validateName(null), "Null input should be invalid");
    }

    @Test
    public void test_validateName_WithSpacesInBetween_ShouldReturnTrue() {
        assertTrue(SubjectValidator.validateName("Art History"), "Spaces between words should be valid");
    }

    @Test
    public void test_validateName_WithNumbersInInput_ShouldReturnFalse() {
        assertFalse(SubjectValidator.validateName("Math123"), "Names with numbers should be invalid");
    }

    @Test
    public void test_validateName_WithSpecialCharsInInput_ShouldReturnFalse() {
        assertFalse(SubjectValidator.validateName("Science!"), "Names with special characters should be invalid");
    }
    //////////////////////////
    //Testing Validate Code//
    ////////////////////////
    @Test
    public void test_validateCode_WithValidCodeOf6Characters_ShouldReturnTrue() {
        assertTrue(SubjectValidator.validateCode("CSE441"), "Valid 6-character code should return true");
    }

    @Test
    public void test_validateCode_WithValidCodeOf7Characters_ShouldReturnTrue() {
        assertTrue(SubjectValidator.validateCode("CSE441s"), "Valid 7-character code should return true");
    }

    @Test
    public void test_validateCode_WithInvalidCodeOf5Characters_ShouldReturnFalse() {
        assertFalse(SubjectValidator.validateCode("CSE44"), "Invalid 5-character code should return false");
    }

    @Test
    public void test_validateCode_WithInvalidCodeOf8Characters_ShouldReturnFalse() {
        assertFalse(SubjectValidator.validateCode("CSE441ss"), "Invalid 8-character code should return false");
    }

    @Test
    public void test_validateCode_WithNumbersFirst_ShouldReturnFalse() {
        assertFalse(SubjectValidator.validateCode("123CSEs"), "Code starting with numbers should return false");
    }

    @Test
    public void test_validateCode_WithMissingNumbers_ShouldReturnFalse() {
        assertFalse(SubjectValidator.validateCode("ABCDef"), "Code without required numbers should return false");
    }

    @Test
    public void test_validateCode_WithLowerCaseLetters_ShouldReturnTrue() {
        assertTrue(SubjectValidator.validateCode("abc123"), "Code with lowercase letters should return true");
    }

    @Test
    public void test_validateCode_WithUpperCaseLetters_ShouldReturnTrue() {
        assertTrue(SubjectValidator.validateCode("XYZ789"), "Code with uppercase letters should return true");
    }

    @Test
    public void test_validateCode_WithMixedCaseLetters_ShouldReturnTrue() {
        assertTrue(SubjectValidator.validateCode("XyZ123"), "Code with mixed-case letters should return true");
    }

    @Test
    public void test_validateCode_WithSpecialCharacters_ShouldReturnFalse() {
        assertFalse(SubjectValidator.validateCode("CSE#12"), "Code with special characters should return false");
    }

    @Test
    public void test_validateCode_WithInvalidCharacterAtEndOtherThanS_ShouldReturnFalse() {
        assertFalse(SubjectValidator.validateCode("CSE123x"), "Code with invalid character at the end should return false");
    }

    @Test
    public void test_validateCode_WithNullInput_ShouldReturnFalse() {
        assertFalse(SubjectValidator.validateCode(null), "Null input should be invalid");
    }

    @Test
    public void test_validateCode_WithEmptyString_ShouldReturnFalse() {
        assertFalse(SubjectValidator.validateCode(""), "Empty string should be invalid");
    }

    @Test
    public void test_validateCode_WithOnlySpaces_ShouldReturnFalse() {
        assertFalse(SubjectValidator.validateCode("       "), "String of only spaces should be invalid");
    }
    ///////////////////////////////
    //Testing Validate full mark//
    /////////////////////////////

    @Test
    public void test_validateFullMark_WithFullMarkOf100_ShouldReturnTrue() {
        assertTrue(SubjectValidator.validateFullMark(100), "Full mark of 100 should return true");
    }

    @Test
    public void test_validateFullMark_WithFullMarkNotEqualTo100_ShouldReturnFalse() {
        assertFalse(SubjectValidator.validateFullMark(80), "Full mark not equal to 100 should return false");
    }

    @Test
    public void test_validateFullMark_WithNegativeFullMark_ShouldReturnFalse() {
        assertFalse(SubjectValidator.validateFullMark(-100), "Negative full mark should return false");
    }

    @Test
    public void test_validateFullMark_WithZeroFullMark_ShouldReturnFalse() {
        assertFalse(SubjectValidator.validateFullMark(0), "Zero full mark should return false");
    }

    @Test
    public void test_validateFullMark_WithValidFullMarkBoundaryValue_ShouldReturnTrue() {
        assertTrue(SubjectValidator.validateFullMark(100), "Boundary value of 100 should return true");
    }

    @Test
    public void test_validateFullMark_WithInvalidFullMarkBoundaryValue_ShouldReturnFalse() {
        assertFalse(SubjectValidator.validateFullMark(99), "Boundary value not equal to 100 should return false");
    }

    @Test
    public void test_validateFullMark_WithMaxIntFullMark_ShouldReturnFalse() {
        assertFalse(SubjectValidator.validateFullMark(Integer.MAX_VALUE), "Max int full mark should return false");
    }

}

