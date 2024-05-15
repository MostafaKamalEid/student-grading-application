package student.project.services.StudentGradesService;

import student.project.models.StudentGradingFile;
import student.project.models.Student;
import student.project.models.Subject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class StudentGradesFileReader {
    public  static StudentGradingFile readData(String filePath) {
        // check if the file exists
        if (!Files.exists(Paths.get(filePath))) {
            throw  new IllegalArgumentException("File does not exist: " + filePath);
        }
        // open the file to read
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            // read the first line to get the subject
            String subjectLine = reader.readLine();
            // read the subject
            Subject subject = readSubject(subjectLine);
            // read the students
            List<Student> students = readStudents(reader);
            return new StudentGradingFile(subject, students);

            // use reader to read the file
        }
        catch (IOException e) {
            throw new IllegalArgumentException("Error reading file: " + filePath);
        }
    }
    public static Subject readSubject(String subjectLine) {
        if (subjectLine == null) {
            throw new IllegalArgumentException("Invalid subject data: null\nReason: The line should contain exactly 3 fields separated by commas.");
        }
        String[] subjectData = subjectLine.split(",");
        // check if the line has the correct number of fields
        if (subjectData.length != 3) {
            throw new IllegalArgumentException("Invalid subject data: " + subjectLine + "\nReason: The line should contain exactly 3 fields separated by commas.");
        }
        String name;
        String code;
        int fullMark;
        try {
            name = subjectData[0].trim();
            code = subjectData[1].trim();
            fullMark = Integer.parseInt(subjectData[2].trim());
        }catch (Exception e) {
            throw new IllegalArgumentException("Invalid subject data: " + subjectLine + "\nReason: The full mark should be an integer.");
        }
        try{
            return new Subject(name, code, fullMark);
        }
        catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid subject data: " + subjectLine + "\nReason: " + e.getMessage());
        }
    }
    public static List<Student> readStudents(BufferedReader reader) throws IOException {
        List<Student> students = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            String[] studentData = line.split(",");
            // check if the line is empty
            if (line.trim().isEmpty()) {
                continue;
            }

            // check if the line has the correct number of fields
            if (studentData.length != 6) {
                throw new IllegalArgumentException("Invalid student data: " + line + "\nReason: The line should contain exactly 6 fields separated by commas.");
            }
            String name;
            String number;
            int activityMarks;
            int oralMarks;
            int midMarks;
            int finalMarks;
            try{
                name = studentData[0];
                number = studentData[1].trim();
                activityMarks = Integer.parseInt(studentData[2].trim());
                oralMarks = Integer.parseInt(studentData[3].trim());
                midMarks = Integer.parseInt(studentData[4].trim());
                finalMarks = Integer.parseInt(studentData[5].trim());
            }
            catch (Exception e) {
                throw new IllegalArgumentException("Invalid student data: " + line + "\nReason: The activity marks, oral marks, midterm marks and final marks should be integers.");
            }

            try {
                Student student = new Student(name, number, activityMarks, oralMarks, midMarks, finalMarks);
                students.add(student);
            }
            catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid student data: " + line + "\nReason: " + e.getMessage());
            }
        }
        // check unique student numbers
        // two for loops to compare each student with the rest of the students in the list to check for duplicates choose the first one and remove the rest of the duplicates
        // print the duplicate with toString method
        for (int i = 0; i < students.size(); i++) {
            for (int j = i + 1; j < students.size(); j++) {
                if (students.get(i).getNumber().equals(students.get(j).getNumber())) {
                    throw new IllegalArgumentException("Duplicate student number: " + students.get(j).toString());
                }
            }
        }

        // check if the student list is empty
        if (students.isEmpty()) {
            throw new IllegalArgumentException("No student data found.");
        }
        return students;
    }
}
