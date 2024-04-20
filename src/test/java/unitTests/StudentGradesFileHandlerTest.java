package unitTests;

import org.junit.jupiter.api.Test;
import student.project.services.StudentGradesService.StudentGradesFileHandler;
import static org.junit.jupiter.api.Assertions.*;

public class StudentGradesFileHandlerTest {

    @Test
    public void testHandle_WithNullFilePath_ShouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> {
            StudentGradesFileHandler.Handle(null);
        });
    }
    @Test
    public void testHandle_WithValidFilePath_ShouldNotThrowException_() {
        assertDoesNotThrow(() -> {
            StudentGradesFileHandler.Handle("src/test/java/unitTests/test.txt");
        });
    }
}

