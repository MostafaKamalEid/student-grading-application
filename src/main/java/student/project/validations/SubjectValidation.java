package student.project.validations;

public class SubjectValidation {
    // Subject name: It must be Alphabetic characters and Spaces. the name should not
    //start with space.
    public static boolean validateName(String name) {
        if (name == null || name.isEmpty() || name.isBlank()) {
            return false;
        }
        return name.matches("[A-Za-z]+[A-Za-z ]*");
    }

    // Subject code:It must be 6-7 Alphanumeric characters. The first 3 are Alphabetic
    //followed by 3 numeric characters. The sevens should be s if exists.
    // for example, ABC123s or ABC123
    public static boolean validateCode(String code) {
        if (code == null || code.isEmpty() || code.isBlank()) {
            return false;
        }
        return code.matches("[A-Za-z]{3}[0-9]{3}s?");
    }


    // Subject full mark: it should be an integer equal 100
    public static boolean validateFullMark(int fullMark) {
        return fullMark == 100;
    }
}
