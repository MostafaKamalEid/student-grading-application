package unitTests;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import student.project.models.StudentGradingFile;
import student.project.services.StudentGradesService.StudentGradesFileHandler;
import student.project.services.StudentGradesService.StudentGradesFileReader;
import student.project.services.StudentGradesService.StudentGradesFileWriter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class StudentGradesFileHandlerTest {

    @Test
    public void testHandle_WithValidFilePath_ShouldNotThrowException_() throws IOException {
        // Arrange
        Path tempFilePath = Files.createTempFile("temp", ".txt");
        String filePath = tempFilePath.toString();
        StudentGradingFile mockFile = mock(StudentGradingFile.class);

        // Mock the static methods
        try (MockedStatic<StudentGradesFileReader> mockedStudentGradesFileReader = Mockito.mockStatic(StudentGradesFileReader.class);
             MockedStatic<StudentGradesFileWriter> ignored = Mockito.mockStatic(StudentGradesFileWriter.class)) {

            when(StudentGradesFileReader.readData(filePath)).thenReturn(mockFile);

            // Act and Assert
            assertDoesNotThrow(() -> StudentGradesFileHandler.Handle(filePath));
            mockedStudentGradesFileReader.verify(times(1),() -> StudentGradesFileReader.readData(filePath));

        }

    }
    @Test
    public void testHandle_WithNullFilePath_ShouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> StudentGradesFileHandler.Handle(null));
    }

    @Test
    public void testHandle_WithValidFilePath_ShouldCallWriteDataOnce() throws IOException {
        // Arrange
        Path tempFilePath = Files.createTempFile("temp", ".txt");
        String filePath = tempFilePath.toString();
        StudentGradingFile mockFile = mock(StudentGradingFile.class);

        // Mock the static methods
        try (MockedStatic<StudentGradesFileReader> ignored = Mockito.mockStatic(StudentGradesFileReader.class);
             MockedStatic<StudentGradesFileWriter> mockedStudentGradesFileWriter = Mockito.mockStatic(StudentGradesFileWriter.class)) {
            when(StudentGradesFileReader.readData(filePath)).thenReturn(mockFile);

            // Act
            StudentGradesFileHandler.Handle(filePath);

            // Assert
            mockedStudentGradesFileWriter.verify(times(1),() -> StudentGradesFileWriter.writeData(filePath, mockFile));
        }
    }
}

