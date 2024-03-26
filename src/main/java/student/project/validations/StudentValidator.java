package student.project.validations;

public class StudentValidator {
    // Student name: It must be Alphabetic characters and Spaces. the name should not
    //start with space.
     public static boolean validateName(String name) {
          if (name == null || name.isEmpty() || name.isBlank()) {
                return false;
          }
          return name.matches("[A-Za-z]+[A-Za-z ]*");
     }

    //. Student number: the length of this field must be Alphanumeric characters of exact
    //length of 8 characters. It should start with numbers and might End with only one
    //Alphabetic character (e.g. 1234567A).
    public static boolean validateNumber(String number) {
        if (number == null || number.isEmpty() || number.isBlank()) {
            return false;
        }
        return number.matches("[0-9]{7}[A-Za-z0-9]");
    }
    //Student Activities mark: It is an integer of a value from 0 up to 10 of the full mark
    //of the activities.
    public static boolean validateActivityMarks(int activityMarks) {
        return activityMarks >= 0 && activityMarks <= 10;
    }
    //Oral/Practical mark: It is an integer of a value from 0 up to 10 of the full mark
    //of the oral/practical.
    public static boolean validateOralMarks(int oralMarks) {
        return oralMarks >= 0 && oralMarks <= 10;
    }
    //Midterm exam mark: It is an integer of a value from 0 up to 20 of the full mark
    //of the midterm exam.
    public static boolean validateMidMarks(int midMarks) {
        return midMarks >= 0 && midMarks <= 20;
    }
    //Final exam mark: It is an integer of a value from 0 up to 60 of the full mark
    //of the final exam.
    public static boolean validateFinalMarks(int finalMarks) {
        return finalMarks >= 0 && finalMarks <= 60;
    }
    // validate full marks of the student (activity, oral, mid, final) must be >= 0 and <= 100
    public static boolean validateFullMarks(int activityMarks, int oralMarks, int midMarks, int finalMarks) {
        int fullMarks = activityMarks + oralMarks + midMarks + finalMarks;
        return fullMarks >= 0 && fullMarks <= 100;
    }
}
