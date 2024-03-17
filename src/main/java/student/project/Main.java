package student.project;


import student.project.models.Student;
import student.project.models.Subject;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public  static StudentGradingFile readData(String filePath) {
        // check if the file exists
        if (!Files.exists(Paths.get(filePath))) {
            System.out.println("File does not exist: " + filePath);
            return null;
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
            System.out.println("Error reading file: " + filePath);
        }
        return null;
    }
    public static Subject readSubject(String subjectLine) {
        String[] subjectData = subjectLine.split(",");
        // check if the line has the correct number of fields
        if (subjectData.length != 3) {
            System.out.println("Invalid subject data: " + subjectLine);
            System.out.println("Reason: The line should contain exactly 3 fields separated by commas.");
            return null;
        }
        String name;
        String code;
        int fullMark;
        try {
             name = subjectData[0].trim();
             code = subjectData[1].trim();
             fullMark = Integer.parseInt(subjectData[2].trim());
        }catch (Exception e) {
            System.out.println("Invalid subject data: " + subjectLine);
            System.out.println("Reason: The full mark should be an integer.");
            return null;
        }
        try{
            return new Subject(name, code, fullMark);
        }
        catch (IllegalArgumentException e) {
            System.out.println("Invalid subject data: " + subjectLine);
            System.out.println(e.getMessage());
            return null;
        }
    }
    public static List<Student> readStudents(BufferedReader reader) throws IOException {
        List<Student> students = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            String[] studentData = line.split(",");
            // check if the line has the correct number of fields
            if (studentData.length != 6) {
                System.out.println("Invalid student data: " + line);
                System.out.println("Reason: The line should contain exactly 6 fields separated by commas.");

                continue;
            }
            String name;
            String number;
            int activityMarks;
            int oralMarks;
            int midMarks;
            int finalMarks;
            try{
                 name = studentData[0].trim();
                 number = studentData[1].trim();
                 activityMarks = Integer.parseInt(studentData[2].trim());
                 oralMarks = Integer.parseInt(studentData[3].trim());
                 midMarks = Integer.parseInt(studentData[4].trim());
                 finalMarks = Integer.parseInt(studentData[5].trim());
            }
            catch (Exception e) {
                System.out.println("Invalid student data: " + line);
                System.out.println("Reason: The activity marks, oral marks, midterm marks and final marks should be integers.");
                continue;
            }

            try {
                Student student = new Student(name, number, activityMarks, oralMarks, midMarks, finalMarks);
                students.add(student);
            }
            catch (IllegalArgumentException e) {
                System.out.println("Invalid student data: " + line);
                System.out.println(e.getMessage());
            }
        }
        // check unique student numbers
        // remove students with has duplicate student numbers from the list only choose the first one
        // create a new list to store the unique students
        List<Student> uniqueStudents = new ArrayList<>();
        // two for loops to compare each student with the rest of the students in the list to check for duplicates choose the first one and remove the rest of the duplicates
        // print the duplicate with toString method
        for (int i = 0; i < students.size(); i++) {
            for (int j = i + 1; j < students.size(); j++) {
                if (students.get(i).getNumber().equals(students.get(j).getNumber())) {
                    System.out.println("Duplicate student number: " + students.get(j).toString());
                    students.remove(j);
                    j--;
                }
            }
        }



        return students;
    }
    public static void main(String[] args) {
        // check if the file path is provided
        if (args.length == 0) {
            System.out.println("Please provide the file path as an argument.");
            return;
        }
        // read the file
        StudentGradingFile studentGradingFile = readData(args[0]);
        // check if the file was read successfully
        if (studentGradingFile == null) {
            return;
        }
        // write the file
        writeData(args[0], studentGradingFile);

    }
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