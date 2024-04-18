package student.project.services.StudentGradesService;

import student.project.models.StudentGradingFile;
import student.project.models.Student;
import student.project.models.Subject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class StudentGradesFileWriter {
    public static void writeData(String originalFilePath, StudentGradingFile studentGradingFile) {

        // create a file path for the output file by appending "_output" to the original file path
        // get the file name without the extension and append "_output" to it
        String fileName = originalFilePath.substring(0, originalFilePath.lastIndexOf('.'));
        String filePath = fileName + "_output.txt";

        // open the file to write
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            // write the subject
            writeSubject(writer, studentGradingFile.subject());
            // write the students
            writeStudents(writer, studentGradingFile.students());
            writer.close();
        }
        catch (IOException e) {
            System.out.println("Error writing file: " + filePath);
        }
    }
    public static void writeSubject(BufferedWriter writer, Subject subject) throws IOException {
        if (subject == null) {
            return;
        }
        writer.write("Subject Name: " + subject.getName());
        writer.newLine();
        writer.write("Max Mark: " + subject.getFullMark());
        writer.newLine();
    }
    public static void writeStudents(BufferedWriter writer, List<Student> students) throws IOException {
        if (students == null || students.isEmpty()) {
            return;
        }
        writer.write("name  number  GPA  Grade");
        writer.newLine();
        for (Student student : students) {
            writer.write(student.getName() + " " + student.getNumber() + " " + student.CalculateGPA() + " " + student.CalculateGrading());
            writer.newLine();
        }
    }
}
