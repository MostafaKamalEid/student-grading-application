package whiteBoxesTests;

import org.junit.jupiter.api.Test;
import student.project.validations.SubjectValidator;

import static org.junit.jupiter.api.Assertions.*;

public class SubjectValidatorTest {

    @Test
    public void testValidateName_ValidName_ShouldReturnTrue() {
        assertTrue(SubjectValidator.validateName("Mathematics"));
    }

    @Test
    public void testValidateName_EmptyName_ShouldReturnFalse() {
        assertFalse(SubjectValidator.validateName(""));
    }

    @Test
    public void testValidateName_NullName_ShouldReturnFalse() {
        assertFalse(SubjectValidator.validateName(null));
    }

    @Test
    public void testValidateName_InvalidName_ShouldReturnFalse() {
        assertFalse(SubjectValidator.validateName("Math@123"));
    }

    @Test
    public void testValidateCode_ValidCode_ShouldReturnTrue() {
        assertTrue(SubjectValidator.validateCode("ABC123"));
    }

    @Test
    public void testValidateCode_ValidCodeWithOptionalCharacter_ShouldReturnTrue() {
        assertTrue(SubjectValidator.validateCode("ABC123s"));
    }

    @Test
    public void testValidateCode_EmptyCode_ShouldReturnFalse() {
        assertFalse(SubjectValidator.validateCode(""));
    }

    @Test
    public void testValidateCode_NullCode_ShouldReturnFalse() {
        assertFalse(SubjectValidator.validateCode(null));
    }

    @Test
    public void testValidateCode_InvalidCode_ShouldReturnFalse() {
        assertFalse(SubjectValidator.validateCode("123ABC"));
    }

    @Test
    public void testValidateFullMark_ValidFullMark_ShouldReturnTrue() {
        assertTrue(SubjectValidator.validateFullMark(100));
    }

    @Test
    public void testValidateFullMark_InvalidFullMark_ShouldReturnFalse() {
        assertFalse(SubjectValidator.validateFullMark(101));
    }
}