package integrationTests.level2;

import org.junit.jupiter.api.Test;
import student.project.Main;
import student.project.services.StudentGradesService.StudentGradesFileHandler;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class StudentGradesFileHandlerTest {

    @Test
    void testMain_noArguments_shouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> StudentGradesFileHandler.Handle(null));
    }
    @Test
    void testMain_duplicateStudentNumbers_shouldThrowException() {
        String filePath = "src/test/java/blackBoxTests/invalidDuplicateStudents.txt"; // Change this to the desired file path
        // Call the StudentGradesFileHandler.Handle
       Exception exception= assertThrows(IllegalArgumentException.class, () -> StudentGradesFileHandler.Handle(filePath));

        // it throws IllegalArgumentException with the expected output
        assertTrue(exception.getMessage().contains("Duplicate student number:"));



    }
	@Test
	void testMain_withValidData_studentCodeWithoutS_ShouldNotThrowException() throws IOException {
		String filePath = "src/test/java/blackBoxTests/ValidData.txt"; // Change this to the desired file path

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));

            // Write subject information
            writer.write("Mathematics,MTH123," + 100 + "\n");

            // Write student information
            writer.write("John Doe,12345678,8,9,18,50\n");
            writer.write("Jane Smith,23456789,10,8,17,52\n");

            writer.close();
            System.out.println("File generated successfully.");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
        String[] arguments = new String[] {filePath};
        StudentGradesFileHandler.Handle(arguments[0]);
        Path path1 = Paths.get("src/test/java/blackBoxTests/ValidData_expected.txt");
        Path path2 = Paths.get("src/test/java/blackBoxTests/ValidData_output.txt");
        long mismatch = Files.mismatch(path1, path2);
        assertEquals(-1, mismatch);
	}
	@Test
	void testMain_withValidData_studentCodeWihS_ShouldNotThrowException() throws IOException {
		String filePath = "src/test/java/blackBoxTests/ValidData_studentCodeWithS.txt"; // Change this to the desired file path

        String[] arguments = new String[] {filePath};
        StudentGradesFileHandler.Handle(arguments[0]);
        Path path1 = Paths.get("src/test/java/blackBoxTests/ValidData_studentCodeWithS_expected.txt");
        Path path2 = Paths.get("src/test/java/blackBoxTests/ValidData_studentCodeWithS_output.txt");
        long mismatch = Files.mismatch(path1, path2);
        assertEquals(-1, mismatch);
	}

    @Test
    void testMain_invalidSubjectNameAndCode_containSpecialCharacter()  {
        String filePath = "src/test/java/blackBoxTests/invalidSubjectNameAndCode.txt"; // Change this to the desired file path
        // Call the StudentGradesFileHandler.Handle
        Exception exception= assertThrows(IllegalArgumentException.class, () -> StudentGradesFileHandler.Handle(filePath));

        // it throws IllegalArgumentException with the expected output
        assertTrue(exception.getMessage().contains("Invalid subject data: Mathematics!,MTH12@,100"));
        assertTrue(exception.getMessage().contains("Reason: Invalid name"));
        
    }
    @Test
    void testMain_invalidSubjectCode_ContinsCapitalS()  {
        String filePath = "src/test/java/blackBoxTests/invalidStudentCode_withCapitalS.txt"; // Change this to the desired file path
        // Call the StudentGradesFileHandler.Handle
        Exception exception= assertThrows(IllegalArgumentException.class, () -> StudentGradesFileHandler.Handle(filePath));

        // it throws IllegalArgumentException with the expected output
        assertTrue(exception.getMessage().contains("Invalid subject data: Mathematics,MTH123S,100"));
        assertTrue(exception.getMessage().contains("Reason: Invalid code"));

    }
    @Test
    void testMain_invalidSubjectCode_withMoreThan7Char()  {
        String filePath = "src/test/java/blackBoxTests/invalidSubjectCode_withMoreThan7Char.txt"; // Change this to the desired file path

        Exception exception= assertThrows(IllegalArgumentException.class, () -> StudentGradesFileHandler.Handle(filePath));

        // it throws IllegalArgumentException with the expected output
        assertTrue(exception.getMessage().contains("Invalid subject data: Mathematics,MTH1234s,100"));
        assertTrue(exception.getMessage().contains("Reason: Invalid code"));
    }
    @Test
    void testMain_invalidSubjectCode_withLessThan7Char()  {
        String filePath = "src/test/java/blackBoxTests/invalidSubjectCode_withUnder7Char.txt"; // Change this to the desired file path

        Exception exception= assertThrows(IllegalArgumentException.class, () -> StudentGradesFileHandler.Handle(filePath));

        // it throws IllegalArgumentException with the expected output
        assertTrue(exception.getMessage().contains("Invalid subject data: Mathematics,MTH12,100"));
        assertTrue(exception.getMessage().contains("Reason: Invalid code"));


    }
    @Test
    void testMain_invalidSubjectCode_withAlphabeticChar()  {
        String filePath = "src/test/java/blackBoxTests/invalidSubjectCode_withAlphabeticChar"; // Change this to the desired file path

        Exception exception= assertThrows(IllegalArgumentException.class, () -> StudentGradesFileHandler.Handle(filePath));

        // it throws IllegalArgumentException with the expected output
        assertTrue(exception.getMessage().contains("Invalid subject data: Mathematics,MTHFFF,100"));
        assertTrue(exception.getMessage().contains("Reason: Invalid code"));
    }
    @Test
    void testMain_invalidSubjectCode_withNumbers()  {
        String filePath = "src/test/java/blackBoxTests/invalidSubjectCode_withNumbers.txt"; // Change this to the desired file path

        Exception exception= assertThrows(IllegalArgumentException.class, () -> StudentGradesFileHandler.Handle(filePath));

        // it throws IllegalArgumentException with the expected output
        assertTrue(exception.getMessage().contains("Invalid subject data: Mathematics,123456,100"));
        assertTrue(exception.getMessage().contains("Reason: Invalid code"));

    }
    @Test
    void testMain_invalidSubjectCode_withSpecialChar()  {
        String filePath = "src/test/java/blackBoxTests/invalidSubjectCode_withSpecialChar.txt"; // Change this to the desired file path

        Exception exception= assertThrows(IllegalArgumentException.class, () -> StudentGradesFileHandler.Handle(filePath));

        // it throws IllegalArgumentException with the expected output
        assertTrue(exception.getMessage().contains("Invalid subject data: Mathematics,MTH12@,100"));
        assertTrue(exception.getMessage().contains("Reason: Invalid code"));
    }
    @Test
    void testMain_invalidFullMark_wihNumberOtherThan100()  {
        String filePath = "src/test/java/blackBoxTests/invalidFullMark_wihNumberOtherThan100.txt"; // Change this to the desired file path

        String[] arguments = new String[] {filePath};
        Exception exception= assertThrows(IllegalArgumentException.class, () -> StudentGradesFileHandler.Handle(filePath));

        // it throws IllegalArgumentException with the expected output
        assertTrue(exception.getMessage().contains("Invalid subject data: Mathematics,MTH123,60"));
        assertTrue(exception.getMessage().contains("Reason: Invalid full mark"));
    }
    @Test
    void testMain_invalidFullMark_wihAlphabeticChar()  {
        String filePath = "src/test/java/blackBoxTests/invalidFullMark_wihAlphabeticChar.txt"; // Change this to the desired file path
        // Create a ByteArrayOutputStream to capture the output
        Exception exception= assertThrows(IllegalArgumentException.class, () -> StudentGradesFileHandler.Handle(filePath));

        // it throws IllegalArgumentException with the expected output
        assertTrue(exception.getMessage().contains("Invalid subject data: Mathematics,MTH123,Hundred"));
        assertTrue(exception.getMessage().contains("Reason: The full mark should be an integer."));
    }
    @Test
    void testMain_invalidStudentName_wihSpecialChar()  {
        String filePath = "src/test/java/blackBoxTests/invalidStudentName_wihSpecialChar.txt"; // Change this to the desired file path

        Exception exception= assertThrows(IllegalArgumentException.class, () -> StudentGradesFileHandler.Handle(filePath));

        // it throws IllegalArgumentException with the expected output
        assertTrue(exception.getMessage().contains("Invalid student data: John$ Doe,1234567,8,9,18,50"));
        assertTrue(exception.getMessage().contains("Reason: Invalid name"));

    }
    @Test
    void testMain_invalidStudentName_wihSomeSpaces() {
        String filePath = "src/test/java/blackBoxTests/invalidStudentName_wihSomeSpaces.txt"; // Change this to the desired file path

        Exception exception= assertThrows(IllegalArgumentException.class, () -> StudentGradesFileHandler.Handle(filePath));

        // it throws IllegalArgumentException with the expected output
        assertTrue(exception.getMessage().contains("Invalid student data:    n Doe,1234567,8,9,18,50"));
        assertTrue(exception.getMessage().contains("Reason: Invalid name"));

    }
    @Test
    void testMain_invalidStudentNumber_wihMoreThan8Num() {
        String filePath = "src/test/java/blackBoxTests/invalidStudentNumber_wihMoreThan8Num.txt"; // Change this to the desired file path

        Exception exception= assertThrows(IllegalArgumentException.class, () -> StudentGradesFileHandler.Handle(filePath));

        // it throws IllegalArgumentException with the expected output
        assertTrue(exception.getMessage().contains("Invalid student data: John Doe,123456789,8,9,18,50"));
        assertTrue(exception.getMessage().contains("Reason: Invalid number"));

    }
    @Test
    void testMain_invalidStudentNumber_withLessThan8Num() {
        String filePath = "src/test/java/blackBoxTests/invalidStudentNumber_withLessThan8Num.txt"; // Change this to the desired file path

        Exception exception= assertThrows(IllegalArgumentException.class, () -> StudentGradesFileHandler.Handle(filePath));

        // it throws IllegalArgumentException with the expected output
        assertTrue(exception.getMessage().contains("Invalid student data: John Doe,1234567,8,9,18,50"));
        assertTrue(exception.getMessage().contains("Reason: Invalid number"));
    }
    @Test
    void testMain_invalidStudentNumber_NoneAlphanumeric() throws IOException {
        String filePath = "src/test/java/blackBoxTests/invalidStudentNumber_NoneAlphanumeric.txt"; // Change this to the desired file path

        String[] arguments = new String[] {filePath};
        // Call the main method with the arguments
        StudentGradesFileHandler.Handle(arguments[0]);

        Path path1 = Paths.get("src/test/java/blackBoxTests/invalidStudentNumber_NoneAlphanumeric_expected.txt");
        Path path2 = Paths.get("src/test/java/blackBoxTests/invalidStudentNumber_NoneAlphanumeric_output.txt");
        long mismatch = Files.mismatch(path1, path2);
        assertEquals(-1, mismatch);
    }
    @Test
    void testMain_invalidStudentNumber_containingSpecialNonAlphanumericChar() {
        String filePath = "src/test/java/blackBoxTests/invalidStudentNumber_containingSpecialNonAlphanumericChar.txt"; // Change this to the desired file path

        Exception exception= assertThrows(IllegalArgumentException.class, () -> StudentGradesFileHandler.Handle(filePath));

        // it throws IllegalArgumentException with the expected output
        assertTrue(exception.getMessage().contains("Invalid student data: John Doe,1234567@,8,9,18,50"));
        assertTrue(exception.getMessage().contains("Reason: Invalid number"));

    }
    @Test
    void testMain_invalidActivityMark_containsSpecialChar() {
        String filePath = "src/test/java/blackBoxTests/invalidActivityMark_containsSpecialChar.txt"; // Change this to the desired file path

        Exception exception= assertThrows(IllegalArgumentException.class, () -> StudentGradesFileHandler.Handle(filePath));

        // it throws IllegalArgumentException with the expected output
        assertTrue(exception.getMessage().contains("Invalid student data: John Doe,12345678,!,9,18,50"));
        assertTrue(exception.getMessage().contains("Reason: The activity marks, oral marks, midterm marks and final marks should be integers."));
    }
    @Test
    void testMain_invalidOralMark_containsAlphabeticChar() {
        String filePath = "src/test/java/blackBoxTests/invalidOralMark_containsAlphabeticChar.txt"; // Change this to the desired file path

        Exception exception= assertThrows(IllegalArgumentException.class, () -> StudentGradesFileHandler.Handle(filePath));

        // it throws IllegalArgumentException with the expected output
        assertTrue(exception.getMessage().contains("Invalid student data: John Doe,12345678,9,A,18,50"));
        assertTrue(exception.getMessage().contains("Reason: The activity marks, oral marks, midterm marks and final marks should be integers."));
    }
    @Test
    void testMain_invalidPracticalMark_containsNegativeNum() {
        String filePath = "src/test/java/blackBoxTests/invalidPracticalMark_containsNegativeNum.txt"; // Change this to the desired file path


        Exception exception= assertThrows(IllegalArgumentException.class, () -> StudentGradesFileHandler.Handle(filePath));

        // it throws IllegalArgumentException with the expected output
        assertTrue(exception.getMessage().contains("Invalid student data: John Doe,12345678,9,-9,18,50"));
        assertTrue(exception.getMessage().contains("Reason: Invalid oral marks"));
    }
    @Test
    void testMain_invalidMidtermMark_containingHigherThanMax() {
        String filePath = "src/test/java/blackBoxTests/invalidMidtermMark_containingHigherThanMax.txt"; // Change this to the desired file path

        Exception exception= assertThrows(IllegalArgumentException.class, () -> StudentGradesFileHandler.Handle(filePath));

        // it throws IllegalArgumentException with the expected output
        assertTrue(exception.getMessage().contains("Invalid student data: John Doe,12345678,9,9,21,50"));
        assertTrue(exception.getMessage().contains("Reason: Invalid mid marks"));
    }
    @Test
    void testMain_missingFinalExamMark() {
        String filePath = "src/test/java/blackBoxTests/missingFinalExamMark.txt"; // Change this to the desired file path

        Exception exception= assertThrows(IllegalArgumentException.class, () -> StudentGradesFileHandler.Handle(filePath));

        // it throws IllegalArgumentException with the expected output
        assertTrue(exception.getMessage().contains("Invalid student data: John Doe,12345678,9,9,18,"));
        assertTrue(exception.getMessage().contains("Reason: The activity marks, oral marks, midterm marks and final marks should be integers."));


        }
    @Test
    void testMain_invalidFileFormat() {
        String filePath = "src/test/java/blackBoxTests/invalidFileFormat.json"; // Change this to the desired file path

        Exception exception= assertThrows(IllegalArgumentException.class, () -> StudentGradesFileHandler.Handle(filePath));

        // it throws IllegalArgumentException with the expected output
        assertTrue(exception.getMessage().contains("Invalid subject data: null"));
        assertTrue(exception.getMessage().contains("Reason: The line should contain exactly 3 fields separated by commas."));

        }
    @Test
    void testMain_emptyFile() {
        String filePath = "src/test/java/blackBoxTests/emptyFile.txt"; // Change this to the desired file path

        Exception exception= assertThrows(IllegalArgumentException.class, () -> StudentGradesFileHandler.Handle(filePath));

        // it throws IllegalArgumentException with the expected output
        assertTrue(exception.getMessage().contains("Invalid subject data: null"));
        assertTrue(exception.getMessage().contains("Reason: The line should contain exactly 3 fields separated by commas."));
    }
    @Test
    void testMain_recordsSeparatedBySemicolons() {
        String filePath = "src/test/java/blackBoxTests/recordsSeparatedBySemicolons.txt"; // Change this to the desired file path

        // Create a ByteArrayOutputStream to capture the output
        Exception exception= assertThrows(IllegalArgumentException.class, () -> StudentGradesFileHandler.Handle(filePath));

        // it throws IllegalArgumentException with the expected output
        assertTrue(exception.getMessage().contains("Invalid subject data: Mathematics;MTH123;100"));
        assertTrue(exception.getMessage().contains("Reason: The line should contain exactly 3 fields separated by commas."));
    }
    @Test
    void testMain_emptySubjectInfo_separatedByCommas() {
        String filePath = "src/test/java/blackBoxTests/emptySubjectInfo_separatedByCommas.txt"; // Change this to the desired file path

        Exception exception= assertThrows(IllegalArgumentException.class, () -> StudentGradesFileHandler.Handle(filePath));

        // it throws IllegalArgumentException with the expected output
        assertTrue(exception.getMessage().contains("Invalid subject data: ,,"));
        assertTrue(exception.getMessage().contains("Reason: The line should contain exactly 3 fields separated by commas."));
        }
    @Test
    void testMain_emptyLinesOnStudentRecords() {
        String filePath = "src/test/java/blackBoxTests/emptyLinesOnStudentRecords.txt"; // Change this to the desired file path

        Exception exception= assertThrows(IllegalArgumentException.class, () -> StudentGradesFileHandler.Handle(filePath));
        // it throws IllegalArgumentException with the expected output
        assertTrue(exception.getMessage().contains("No student data found."));

    }
    @Test
    void testMain_multipleStudentRecordsOnTheSameLine() {
        String filePath = "src/test/java/blackBoxTests/multipleStudentRecordsOnTheSameLine.txt"; // Change this to the desired file path

        Exception exception= assertThrows(IllegalArgumentException.class, () -> StudentGradesFileHandler.Handle(filePath));

        // it throws IllegalArgumentException with the expected output
        assertTrue(exception.getMessage().contains("Invalid student data: John Doe,1234567,8,9,18,50 John Doe,1234567,8,9,18,50"));
        assertTrue(exception.getMessage().contains("Reason: The line should contain exactly 6 fields separated by commas."));
    }
    @Test
    void testMain_spaceBeforeActivitiesMark() {
        String filePath = "src/test/java/blackBoxTests/spaceBeforeActivitiesMark.txt"; // Change this to the desired file path

        Exception exception= assertThrows(IllegalArgumentException.class, () -> StudentGradesFileHandler.Handle(filePath));

        // it throws IllegalArgumentException with the expected output
        assertTrue(exception.getMessage().contains("Invalid student data: John Doe,1234567, 8,9,18,50"));
        assertTrue(exception.getMessage().contains("Reason: Invalid number"));
    }
    @Test
    void testMain_spaceAfterMidtermMark() {
        String filePath = "src/test/java/blackBoxTests/spaceAfterMidtermMark.txt"; // Change this to the desired file path


        Exception exception= assertThrows(IllegalArgumentException.class, () -> StudentGradesFileHandler.Handle(filePath));

        // it throws IllegalArgumentException with the expected output
        assertTrue(exception.getMessage().contains("Invalid student data: John Doe,1234567,8,9,18 ,50"));
        assertTrue(exception.getMessage().contains("Reason: Invalid number"));
    }



}
