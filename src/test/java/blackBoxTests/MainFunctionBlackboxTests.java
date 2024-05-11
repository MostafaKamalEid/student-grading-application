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
}
