package blackBoxTests;

import static org.junit.jupiter.api.Assertions.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;


import org.junit.jupiter.api.Test;

import student.project.Main;

class MainFunctionBlackboxTests {


	@Test
	void testMain_withValidData_studentCodeWitoutS_ShouldNotThrowException() throws IOException {
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
        Main.main(arguments);
        Path path1 = Paths.get("src/test/java/blackBoxTests/ValidData_expected.txt");
        Path path2 = Paths.get("src/test/java/blackBoxTests/ValidData_output.txt");
        long mismatch = Files.mismatch(path1, path2);
        assertEquals(-1, mismatch);
	}
	@Test
	void testMain_withValidData_studentCodeWihS_ShouldNotThrowException() throws IOException {
		String filePath = "src/test/java/blackBoxTests/ValidData_studentCodeWithS.txt"; // Change this to the desired file path

        String[] arguments = new String[] {filePath};
        Main.main(arguments);
        Path path1 = Paths.get("src/test/java/blackBoxTests/ValidData_studentCodeWithS_expected.txt");
        Path path2 = Paths.get("src/test/java/blackBoxTests/ValidData_studentCodeWithS_output.txt");
        long mismatch = Files.mismatch(path1, path2);
        assertEquals(-1, mismatch);
	}

    @Test
    void testMain_invalidSubjectNameAndCode_containSpecialCharacter() throws IOException {
        String filePath = "src/test/java/blackBoxTests/invalidSubjectNameAndCode.txt"; // Change this to the desired file path

        String[] arguments = new String[] {filePath};
        // expected output list of lines
        ArrayList<String> expectedOutputList =  new ArrayList<String>();
        expectedOutputList.add("Invalid subject data: Mathematics!,MTH12@,100");
        expectedOutputList.add("Reason: Invalid name");
        // Create a ByteArrayOutputStream to capture the output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);

        // Redirect System.out to the ByteArrayOutputStream
        PrintStream originalOut = System.out;
        System.setOut(printStream);
        // Call the main method with the arguments
        Main.main(arguments);

        // Reset System.out to the original PrintStream
        System.setOut(originalOut);

        // Get the captured output as a string
        String capturedOutput = outputStream.toString();
        // Check if the expected output List is contained in the captured output string
        for (String line : expectedOutputList) {
            assertTrue(capturedOutput.contains(line));
        }
        
    }
    @Test
    void testMain_invalidSubjectCode_continsCapitalS() throws IOException {
        String filePath = "src/test/java/blackBoxTests/invalidStudentCode_withCapitalS.txt"; // Change this to the desired file path

        String[] arguments = new String[] {filePath};
        // expected output list of lines
        ArrayList<String> expectedOutputList =  new ArrayList<String>();
        expectedOutputList.add("Invalid subject data: Mathematics,MTH123S,100");
        expectedOutputList.add("Reason: Invalid code");
        // Create a ByteArrayOutputStream to capture the output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);

        // Redirect System.out to the ByteArrayOutputStream
        PrintStream originalOut = System.out;
        System.setOut(printStream);
        // Call the main method with the arguments
        Main.main(arguments);

        // Reset System.out to the original PrintStream
        System.setOut(originalOut);

        // Get the captured output as a string
        String capturedOutput = outputStream.toString();
        // Check if the expected output List is contained in the captured output string
        for (String line : expectedOutputList) {
            assertTrue(capturedOutput.contains(line));
        }
    }
    @Test
    void testMain_invalidSubjectCode_withMoreThan7Char() throws IOException {
        String filePath = "src/test/java/blackBoxTests/invalidSubjectCode_withMoreThan7Char.txt"; // Change this to the desired file path

        String[] arguments = new String[] {filePath};
        // expected output list of lines
        ArrayList<String> expectedOutputList =  new ArrayList<String>();
        expectedOutputList.add("Invalid subject data: Mathematics,MTH1234s,100");
        expectedOutputList.add("Reason: Invalid code");
        // Create a ByteArrayOutputStream to capture the output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);

        // Redirect System.out to the ByteArrayOutputStream
        PrintStream originalOut = System.out;
        System.setOut(printStream);
        // Call the main method with the arguments
        Main.main(arguments);

        // Reset System.out to the original PrintStream
        System.setOut(originalOut);

        // Get the captured output as a string
        String capturedOutput = outputStream.toString();
        // Check if the expected output List is contained in the captured output string
        for (String line : expectedOutputList) {
            assertTrue(capturedOutput.contains(line));
        }
    }
    @Test
    void testMain_invalidSubjectCode_withLessThan7Char() throws IOException {
        String filePath = "src/test/java/blackBoxTests/invalidSubjectCode_withLessThan7Char.txt"; // Change this to the desired file path

        String[] arguments = new String[] {filePath};
        // expected output list of lines
        ArrayList<String> expectedOutputList =  new ArrayList<String>();
        expectedOutputList.add("Invalid subject data: Mathematics,MTH12,100");
        expectedOutputList.add("Reason: Invalid code");
        // Create a ByteArrayOutputStream to capture the output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);

        // Redirect System.out to the ByteArrayOutputStream
        PrintStream originalOut = System.out;
        System.setOut(printStream);
        // Call the main method with the arguments
        Main.main(arguments);

        // Reset System.out to the original PrintStream
        System.setOut(originalOut);

        // Get the captured output as a string
        String capturedOutput = outputStream.toString();
        // Check if the expected output List is contained in the captured output string
        for (String line : expectedOutputList) {
            assertTrue(capturedOutput.contains(line));
        }
    }
    @Test
    void testMain_invalidSubjectCode_withAlphabeticChar() throws IOException {
        String filePath = "src/test/java/blackBoxTests/invalidSubjectCode_withAlphabeticChar"; // Change this to the desired file path

        String[] arguments = new String[] {filePath};
        // expected output list of lines
        ArrayList<String> expectedOutputList =  new ArrayList<String>();
        expectedOutputList.add("Invalid subject data: Mathematics,MTHFFF,100");
        expectedOutputList.add("Reason: Invalid code");
        // Create a ByteArrayOutputStream to capture the output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);

        // Redirect System.out to the ByteArrayOutputStream
        PrintStream originalOut = System.out;
        System.setOut(printStream);
        // Call the main method with the arguments
        Main.main(arguments);

        // Reset System.out to the original PrintStream
        System.setOut(originalOut);

        // Get the captured output as a string
        String capturedOutput = outputStream.toString();
        // Check if the expected output List is contained in the captured output string
        for (String line : expectedOutputList) {
            assertTrue(capturedOutput.contains(line));
        }
    }
    @Test
    void testMain_invalidSubjectCode_withNumbers() throws IOException {
        String filePath = "src/test/java/blackBoxTests/invalidSubjectCode_withNumbers.txt"; // Change this to the desired file path

        String[] arguments = new String[] {filePath};
        // expected output list of lines
        ArrayList<String> expectedOutputList =  new ArrayList<String>();
        expectedOutputList.add("Invalid subject data: Mathematics,123456,100");
        expectedOutputList.add("Reason: Invalid code");
        // Create a ByteArrayOutputStream to capture the output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);

        // Redirect System.out to the ByteArrayOutputStream
        PrintStream originalOut = System.out;
        System.setOut(printStream);
        // Call the main method with the arguments
        Main.main(arguments);

        // Reset System.out to the original PrintStream
        System.setOut(originalOut);

        // Get the captured output as a string
        String capturedOutput = outputStream.toString();
        // Check if the expected output List is contained in the captured output string
        for (String line : expectedOutputList) {
            assertTrue(capturedOutput.contains(line));
        }
    }
    @Test
    void testMain_invalidSubjectCode_withSpecialChar() throws IOException {
        String filePath = "src/test/java/blackBoxTests/invalidSubjectCode_withSpecialChar.txt"; // Change this to the desired file path

        String[] arguments = new String[] {filePath};
        // expected output list of lines
        ArrayList<String> expectedOutputList =  new ArrayList<String>();
        expectedOutputList.add("Invalid subject data: Mathematics,MTH12@,100");
        expectedOutputList.add("Reason: Invalid code");
        // Create a ByteArrayOutputStream to capture the output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);

        // Redirect System.out to the ByteArrayOutputStream
        PrintStream originalOut = System.out;
        System.setOut(printStream);
        // Call the main method with the arguments
        Main.main(arguments);

        // Reset System.out to the original PrintStream
        System.setOut(originalOut);

        // Get the captured output as a string
        String capturedOutput = outputStream.toString();
        // Check if the expected output List is contained in the captured output string
        for (String line : expectedOutputList) {
            assertTrue(capturedOutput.contains(line));
        }
    }
    @Test
    void testMain_invalidFullMark_wihNumberOtherThan100() throws IOException {
        String filePath = "src/test/java/blackBoxTests/invalidFullMark_wihNumberOtherThan100.txt"; // Change this to the desired file path

        String[] arguments = new String[] {filePath};
        // expected output list of lines
        ArrayList<String> expectedOutputList =  new ArrayList<String>();
        expectedOutputList.add("Invalid subject data: Mathematics,MTH123,60");
        expectedOutputList.add("Reason: Invalid full mark");
        // Create a ByteArrayOutputStream to capture the output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);

        // Redirect System.out to the ByteArrayOutputStream
        PrintStream originalOut = System.out;
        System.setOut(printStream);
        // Call the main method with the arguments
        Main.main(arguments);

        // Reset System.out to the original PrintStream
        System.setOut(originalOut);

        // Get the captured output as a string
        String capturedOutput = outputStream.toString();
        // Check if the expected output List is contained in the captured output string
        for (String line : expectedOutputList) {
            assertTrue(capturedOutput.contains(line));
        }
    }
    @Test
    void testMain_invalidFullMark_wihAlphabeticChar() throws IOException {
        String filePath = "src/test/java/blackBoxTests/invalidFullMark_wihAlphabeticChar.txt"; // Change this to the desired file path

        String[] arguments = new String[] {filePath};
        // expected output list of lines
        ArrayList<String> expectedOutputList =  new ArrayList<String>();
        expectedOutputList.add("Invalid subject data: Mathematics,MTH123,Hundred");
        expectedOutputList.add("Reason: The full mark should be an integer.");
        // Create a ByteArrayOutputStream to capture the output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);

        // Redirect System.out to the ByteArrayOutputStream
        PrintStream originalOut = System.out;
        System.setOut(printStream);
        // Call the main method with the arguments
        Main.main(arguments);

        //Reset System.out to the original PrintStream
        System.setOut(originalOut);

        // Get the captured output as a string
        String capturedOutput = outputStream.toString();
        // Check if the expected output List is contained in the captured output string
        for (String line : expectedOutputList) {
            assertTrue(capturedOutput.contains(line));
        }
    }
    @Test
    void testMain_invalidStudentName_wihSpecialChar() throws IOException {
        String filePath = "src/test/java/blackBoxTests/invalidStudentName_wihSpecialChar.txt"; // Change this to the desired file path

        String[] arguments = new String[] {filePath};
        // expected output list of lines
        ArrayList<String> expectedOutputList =  new ArrayList<String>();
        expectedOutputList.add("Invalid student data: John$ Doe,1234567,8,9,18,50");
        expectedOutputList.add("Reason: Invalid name");
        // Create a ByteArrayOutputStream to capture the output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);

        // Redirect System.out to the ByteArrayOutputStream
        PrintStream originalOut = System.out;
        System.setOut(printStream);
        // Call the main method with the arguments
        Main.main(arguments);

        //Reset System.out to the original PrintStream
        System.setOut(originalOut);

        // Get the captured output as a string
        String capturedOutput = outputStream.toString();
        // Check if the expected output List is contained in the captured output string
        for (String line : expectedOutputList) {
            assertTrue(capturedOutput.contains(line));
        }
    }
    @Test
    void testMain_invalidStudentName_wihSomeSpaces() throws IOException {
        String filePath = "src/test/java/blackBoxTests/invalidStudentName_wihSomeSpaces.txt"; // Change this to the desired file path

        String[] arguments = new String[] {filePath};
        // expected output list of lines
        ArrayList<String> expectedOutputList =  new ArrayList<String>();
        expectedOutputList.add("Invalid student data:    n Doe,1234567,8,9,18,50");
        expectedOutputList.add("Reason: Invalid name");
        // Create a ByteArrayOutputStream to capture the output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);

        // Redirect System.out to the ByteArrayOutputStream
        PrintStream originalOut = System.out;
        System.setOut(printStream);
        // Call the main method with the arguments
        Main.main(arguments);

        //Reset System.out to the original PrintStream
        System.setOut(originalOut);

        // Get the captured output as a string
        String capturedOutput = outputStream.toString();
        // Check if the expected output List is contained in the captured output string
        for (String line : expectedOutputList) {
            assertTrue(capturedOutput.contains(line));
        }
    }
    @Test
    void testMain_invalidStudentNumber_wihMoreThan8Num() throws IOException {
        String filePath = "src/test/java/blackBoxTests/invalidStudentNumber_wihMoreThan8Num.txt"; // Change this to the desired file path

        String[] arguments = new String[] {filePath};
        // expected output list of lines
        ArrayList<String> expectedOutputList =  new ArrayList<String>();
        expectedOutputList.add("Invalid student data: John Doe,123456789,8,9,18,50");
        expectedOutputList.add("Reason: Invalid number");
        // Create a ByteArrayOutputStream to capture the output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);

        // Redirect System.out to the ByteArrayOutputStream
        PrintStream originalOut = System.out;
        System.setOut(printStream);
        // Call the main method with the arguments
        Main.main(arguments);

        //Reset System.out to the original PrintStream
        System.setOut(originalOut);

        // Get the captured output as a string
        String capturedOutput = outputStream.toString();
        // Check if the expected output List is contained in the captured output string
        for (String line : expectedOutputList) {
            assertTrue(capturedOutput.contains(line));
        }
    }
    @Test
    void testMain_invalidStudentNumber_withLessThan8Num() throws IOException {
        String filePath = "src/test/java/blackBoxTests/invalidStudentNumber_withLessThan8Num.txt"; // Change this to the desired file path

        String[] arguments = new String[] {filePath};
        // expected output list of lines
        ArrayList<String> expectedOutputList =  new ArrayList<String>();
        expectedOutputList.add("Invalid student data: John Doe,1234567,8,9,18,50");
        expectedOutputList.add("Reason: Invalid number");
        // Create a ByteArrayOutputStream to capture the output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);

        // Redirect System.out to the ByteArrayOutputStream
        PrintStream originalOut = System.out;
        System.setOut(printStream);
        // Call the main method with the arguments
        Main.main(arguments);

        //Reset System.out to the original PrintStream
        System.setOut(originalOut);

        // Get the captured output as a string
        String capturedOutput = outputStream.toString();
        // Check if the expected output List is contained in the captured output string
        for (String line : expectedOutputList) {
            assertTrue(capturedOutput.contains(line));
        }
    }
    @Test
    void testMain_invalidStudentNumber_NoneAlphanumeric() throws IOException {
        String filePath = "src/test/java/blackBoxTests/invalidStudentNumber_NoneAlphanumeric.txt"; // Change this to the desired file path

        String[] arguments = new String[] {filePath};
        // Call the main method with the arguments
        Main.main(arguments);

        Path path1 = Paths.get("src/test/java/blackBoxTests/invalidStudentNumber_NoneAlphanumeric_expected.txt");
        Path path2 = Paths.get("src/test/java/blackBoxTests/invalidStudentNumber_NoneAlphanumeric_output.txt");
        long mismatch = Files.mismatch(path1, path2);
        assertEquals(-1, mismatch);
    }
    @Test
    void testMain_invalidStudentNumber_containingSpecialNonAlphanumericChar() throws IOException {
        String filePath = "src/test/java/blackBoxTests/invalidStudentNumber_containingSpecialNonAlphanumericChar.txt"; // Change this to the desired file path

        String[] arguments = new String[] {filePath};
        // expected output list of lines
        ArrayList<String> expectedOutputList =  new ArrayList<String>();
        expectedOutputList.add("Invalid student data: John Doe,1234567@,8,9,18,50");
        expectedOutputList.add("Reason: Invalid number");
        // Create a ByteArrayOutputStream to capture the output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);

        // Redirect System.out to the ByteArrayOutputStream
        PrintStream originalOut = System.out;
        System.setOut(printStream);
        // Call the main method with the arguments
        Main.main(arguments);

        //Reset System.out to the original PrintStream
        System.setOut(originalOut);

        // Get the captured output as a string
        String capturedOutput = outputStream.toString();
        // Check if the expected output List is contained in the captured output string
        for (String line : expectedOutputList) {
            assertTrue(capturedOutput.contains(line));
        }}
    @Test
    void testMain_invalidActivityMark_containsSpecialChar() throws IOException {
        String filePath = "src/test/java/blackBoxTests/invalidActivityMark_containsSpecialChar.txt"; // Change this to the desired file path

        String[] arguments = new String[] {filePath};
        // expected output list of lines
        ArrayList<String> expectedOutputList =  new ArrayList<String>();
        expectedOutputList.add("Invalid student data: John Doe,12345678,!,9,18,50");
        expectedOutputList.add("Reason: The activity marks, oral marks, midterm marks and final marks should be integers.");
        // Create a ByteArrayOutputStream to capture the output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);

        // Redirect System.out to the ByteArrayOutputStream
        PrintStream originalOut = System.out;
        System.setOut(printStream);
        // Call the main method with the arguments
        Main.main(arguments);

        //Reset System.out to the original PrintStream
        System.setOut(originalOut);

        // Get the captured output as a string
        String capturedOutput = outputStream.toString();
        // Check if the expected output List is contained in the captured output string
        for (String line : expectedOutputList) {
            assertTrue(capturedOutput.contains(line));
      }
    }
    @Test
    void testMain_invalidOralMark_containsAlphabeticChar() throws IOException {
        String filePath = "src/test/java/blackBoxTests/invalidOralMark_containsAlphabeticChar.txt"; // Change this to the desired file path

        String[] arguments = new String[] {filePath};
        // expected output list of lines
        ArrayList<String> expectedOutputList =  new ArrayList<String>();
        expectedOutputList.add("Invalid student data: John Doe,12345678,9,A,18,50");
        expectedOutputList.add("Reason: The activity marks, oral marks, midterm marks and final marks should be integers.");
        // Create a ByteArrayOutputStream to capture the output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);

        // Redirect System.out to the ByteArrayOutputStream
        PrintStream originalOut = System.out;
        System.setOut(printStream);
        // Call the main method with the arguments
        Main.main(arguments);

        //Reset System.out to the original PrintStream
        System.setOut(originalOut);

        // Get the captured output as a string
        String capturedOutput = outputStream.toString();
        // Check if the expected output List is contained in the captured output string
        for (String line : expectedOutputList) {
            assertTrue(capturedOutput.contains(line));
        }}
    @Test
    void testMain_invalidPracticalMark_containsNegativeNum() throws IOException {
        String filePath = "src/test/java/blackBoxTests/invalidPracticalMark_containsNegativeNum.txt"; // Change this to the desired file path

        String[] arguments = new String[] {filePath};
        // expected output list of lines
        ArrayList<String> expectedOutputList =  new ArrayList<String>();
        expectedOutputList.add("Invalid student data: John Doe,12345678,9,-9,18,50");
        expectedOutputList.add("Reason: Invalid oral marks");
        // Create a ByteArrayOutputStream to capture the output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);

        // Redirect System.out to the ByteArrayOutputStream
        PrintStream originalOut = System.out;
        System.setOut(printStream);
        // Call the main method with the arguments
        Main.main(arguments);

        //Reset System.out to the original PrintStream
        System.setOut(originalOut);

        // Get the captured output as a string
        String capturedOutput = outputStream.toString();
        // Check if the expected output List is contained in the captured output string
        for (String line : expectedOutputList) {
            assertTrue(capturedOutput.contains(line));
        }
    }
    @Test
    void testMain_invalidMidtermMark_containingHigherThanMax() throws IOException {
        String filePath = "src/test/java/blackBoxTests/invalidMidtermMark_containingHigherThanMax.txt"; // Change this to the desired file path

        String[] arguments = new String[] {filePath};
        // expected output list of lines
        ArrayList<String> expectedOutputList =  new ArrayList<String>();
        expectedOutputList.add("Invalid student data: John Doe,12345678,9,9,21,50");
        expectedOutputList.add("Reason: Invalid mid marks");
        // Create a ByteArrayOutputStream to capture the output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);

        // Redirect System.out to the ByteArrayOutputStream
        PrintStream originalOut = System.out;
        System.setOut(printStream);
        // Call the main method with the arguments
        Main.main(arguments);

        //Reset System.out to the original PrintStream
        System.setOut(originalOut);

        // Get the captured output as a string
        String capturedOutput = outputStream.toString();
        // Check if the expected output List is contained in the captured output string
        for (String line : expectedOutputList) {
            assertTrue(capturedOutput.contains(line));
        }
        }
    @Test
    void testMain_missingFinalExamMark() throws IOException {
        String filePath = "src/test/java/blackBoxTests/missingFinalExamMark.txt"; // Change this to the desired file path

        String[] arguments = new String[] {filePath};
        // expected output list of lines
        ArrayList<String> expectedOutputList =  new ArrayList<String>();
        expectedOutputList.add("Invalid student data: John Doe,12345678,9,9,18,");
        expectedOutputList.add("Reason: The activity marks, oral marks, midterm marks and final marks should be integers.");
        // Create a ByteArrayOutputStream to capture the output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);

        // Redirect System.out to the ByteArrayOutputStream
        PrintStream originalOut = System.out;
        System.setOut(printStream);
        // Call the main method with the arguments
        Main.main(arguments);

        //Reset System.out to the original PrintStream
        System.setOut(originalOut);

        // Get the captured output as a string
        String capturedOutput = outputStream.toString();
        // Check if the expected output List is contained in the captured output string
        for (String line : expectedOutputList) {
            assertTrue(capturedOutput.contains(line));
        }
        }
    @Test
    void testMain_invalidFileFormate() throws IOException {
        String filePath = "src/test/java/blackBoxTests/invalidFileFormate.json"; // Change this to the desired file path

        String[] arguments = new String[] {filePath};
        // expected output list of lines
        ArrayList<String> expectedOutputList =  new ArrayList<String>();
        expectedOutputList.add("Invalid subject data: null");
        expectedOutputList.add("Reason: The line should contain exactly 3 fields separated by commas.");
        // Create a ByteArrayOutputStream to capture the output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);

        // Redirect System.out to the ByteArrayOutputStream
        PrintStream originalOut = System.out;
        System.setOut(printStream);
        // Call the main method with the arguments
        Main.main(arguments);

        //Reset System.out to the original PrintStream
        System.setOut(originalOut);

        // Get the captured output as a string
        String capturedOutput = outputStream.toString();
        // Check if the expected output List is contained in the captured output string
        for (String line : expectedOutputList) {
            assertTrue(capturedOutput.contains(line));
        }
        }
    @Test
    void testMain_emptyFile() throws IOException {
        String filePath = "src/test/java/blackBoxTests/emptyFile.txt"; // Change this to the desired file path

        String[] arguments = new String[] {filePath};
        // expected output list of lines
        ArrayList<String> expectedOutputList =  new ArrayList<String>();
        expectedOutputList.add("Invalid subject data: null");
        expectedOutputList.add("Reason: The line should contain exactly 3 fields separated by commas.");
        // Create a ByteArrayOutputStream to capture the output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);

        // Redirect System.out to the ByteArrayOutputStream
        PrintStream originalOut = System.out;
        System.setOut(printStream);
        // Call the main method with the arguments
        Main.main(arguments);

        //Reset System.out to the original PrintStream
        System.setOut(originalOut);

        // Get the captured output as a string
        String capturedOutput = outputStream.toString();
        // Check if the expected output List is contained in the captured output string
        for (String line : expectedOutputList) {
            assertTrue(capturedOutput.contains(line));
        }
        }
    @Test
    void testMain_recordsSeparatedBySemicolons() throws IOException {
        String filePath = "src/test/java/blackBoxTests/recordsSeparatedBySemicolons.txt"; // Change this to the desired file path

        String[] arguments = new String[] {filePath};
        // expected output list of lines
        ArrayList<String> expectedOutputList =  new ArrayList<String>();
        expectedOutputList.add("Invalid subject data: Mathematics;MTH123;100");
        expectedOutputList.add("Reason: The line should contain exactly 3 fields separated by commas.");
        // Create a ByteArrayOutputStream to capture the output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);

        // Redirect System.out to the ByteArrayOutputStream
        PrintStream originalOut = System.out;
        System.setOut(printStream);
        // Call the main method with the arguments
        Main.main(arguments);

        //Reset System.out to the original PrintStream
        System.setOut(originalOut);

        // Get the captured output as a string
        String capturedOutput = outputStream.toString();
        // Check if the expected output List is contained in the captured output string
        for (String line : expectedOutputList) {
            assertTrue(capturedOutput.contains(line));
        }
        }

}
