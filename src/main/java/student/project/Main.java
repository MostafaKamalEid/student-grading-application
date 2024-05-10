package student.project;
import student.project.services.StudentGradesService.StudentGradesFileHandler;
public class Main {

    public static void main(String[] args) {
        // check if the file path is provided
        if (args.length == 0) {
            throw new IllegalArgumentException("Please provide the file path as an argument.");
        }
        // read the file
        try {
            StudentGradesFileHandler.Handle(args[0]);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}