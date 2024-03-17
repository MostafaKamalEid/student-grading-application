package student.project.models;

import student.project.validations.StudentValidator;

import java.util.Arrays;
import java.util.List;

public class Student {
    private String name;
    private String number;
    private int activityMarks;
    private int oralMarks;
    private int midMarks;
    private int finalMarks;
    // using the following weights for the marks of the student (activity, oral, mid, final)
    private static final List<GradeRange> GRADE_RANGES = Arrays.asList(
            new GradeRange(97, 100, 4, "A+"),
            new GradeRange(93, 96, 4, "A"),
            new GradeRange(89, 92, 3.7, "A-"),
            new GradeRange(84, 88, 3.3, "B+"),
            new GradeRange(80, 83, 3, "B"),
            new GradeRange(76, 79, 2.7, "B-"),
            new GradeRange(73, 75, 2.3, "C+"),
            new GradeRange(70, 72, 2, "C"),
            new GradeRange(67, 69, 1.7, "C-"),
            new GradeRange(64, 66, 1.3, "D+"),
            new GradeRange(60, 63, 1, "D"),
            new GradeRange(0, 59, 0, "F")
    );

    public Student() {
    }

    public Student(String name, String number, int activityMarks, int oralMarks, int midMarks, int finalMarks) {
        // use setters to set the values of the fields
        setName(name);
        setNumber(number);
        setActivityMarks(activityMarks);
        setOralMarks(oralMarks);
        setMidMarks(midMarks);
        setFinalMarks(finalMarks);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        // validate the name using StudentValidator
        if (!StudentValidator.validateName(name)) {
            throw new IllegalArgumentException("Invalid name");
        }
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        // validate the number using StudentValidator
        if (!StudentValidator.validateNumber(number)) {
            throw new IllegalArgumentException("Invalid number");
        }

        this.number = number;
    }

    public int getActivityMarks() {
        return activityMarks;
    }

    public void setActivityMarks(int activityMarks) {

        if (!StudentValidator.validateActivityMarks(activityMarks)) {
            throw new IllegalArgumentException("Invalid activity marks");
        }
        this.activityMarks = activityMarks;
    }

    public int getOralMarks() {
        return oralMarks;
    }

    public void setOralMarks(int oralMarks) {
        if (!StudentValidator.validateOralMarks(oralMarks)) {
            throw new IllegalArgumentException("Invalid oral marks");
        }

        this.oralMarks = oralMarks;
    }

    public int getMidMarks() {
        return midMarks;
    }

    public void setMidMarks(int midMarks) {
        if (!StudentValidator.validateMidMarks(midMarks)) {
            throw new IllegalArgumentException("Invalid mid marks");
        }
        this.midMarks = midMarks;
    }

    public int getFinalMarks() {
        return finalMarks;
    }

    public void setFinalMarks(int finalMarks) {
        if (!StudentValidator.validateFinalMarks(finalMarks)) {
            throw new IllegalArgumentException("Invalid final marks");
        }

        this.finalMarks = finalMarks;
    }
    public  String CalculateGrading(){
        for (GradeRange gradeRange : GRADE_RANGES) {
            int fullMarks = activityMarks + oralMarks + midMarks + finalMarks;

            if (fullMarks >= gradeRange.lowerBound() && fullMarks <= gradeRange.upperBound()) {
                return gradeRange.grade();
            }
        }
        return "Invalid mark";
    }
    public double CalculateGPA(){
        // use the full marks of the student (activity, oral, mid, final) to calculate the GPA
        int fullMarks = activityMarks + oralMarks + midMarks + finalMarks;
        // get representation of the full marks in terms of GPA
        for (GradeRange gradeRange : GRADE_RANGES) {
            if (fullMarks >= gradeRange.lowerBound() && fullMarks <= gradeRange.upperBound()) {
                return gradeRange.gpa();
            }
        }
        return 0;
    }



    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", activityMarks=" + activityMarks +
                ", oralMarks=" + oralMarks +
                ", midMarks=" + midMarks +
                ", finalMarks=" + finalMarks +
                '}';
    }

}
