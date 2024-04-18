package student.project.services.StudentGradesService;

import student.project.models.StudentGradingFile;

public class StudentGradesFileHandler {
    public static void Handle(String filePath) {
        // check if the file path is provided
        if (filePath == null) {
           throw new IllegalArgumentException("Please provide the file path as an argument.");
        }


        // read the file
        StudentGradingFile studentGradingFile = StudentGradesFileReader.readData(filePath);
        // write the file
        StudentGradesFileWriter.writeData(filePath, studentGradingFile);

    }
}
