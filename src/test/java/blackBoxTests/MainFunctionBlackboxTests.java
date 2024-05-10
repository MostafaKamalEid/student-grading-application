package blackBoxTests;

import static org.junit.jupiter.api.Assertions.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;



import org.junit.jupiter.api.Test;

import student.project.Main;

class MainFunctionBlackboxTests {

	
	@Test
	void testMain_withValidData_studentCodeWitoutS_ShouldNotThrowException() throws IOException {
		String filePath = "ValidData.txt"; // Change this to the desired file path

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
        Path path1 = Paths.get("ValidData_expected.txt");
        Path path2 = Paths.get("ValidData_output.txt");
        long mismatch = Files.mismatch(path1, path2);
        assertEquals(-1, mismatch);
	}
	@Test
	void testMain_withValidData_studentCodeWihS_ShouldNotThrowException() throws IOException {
		String filePath = "ValidData_studentCodeWithS.txt"; // Change this to the desired file path

        String[] arguments = new String[] {filePath};
        Main.main(arguments);
        Path path1 = Paths.get("ValidData_studentCodeWithS_expected.txt");
        Path path2 = Paths.get("ValidData_studentCodeWithS_output.txt");
        long mismatch = Files.mismatch(path1, path2);
        assertEquals(-1, mismatch);
	}
	
	@Test
	void testMain_invalidSubjectNameAndCode_containSpecialCharacter_ShouldThrowException() throws IOException {
		String filePath = "invalidSubjectNameAndCode.txt"; // Change this to the desired file path

        String[] arguments = new String[] {filePath};
        String expectedOutput = "Invalid subject data: Mathematics!,MTH12@,100\nReason: Invalid name\n";

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

        // Print or assert the captured output as needed
        assertEquals(expectedOutput,capturedOutput);
        
        
	}

}
