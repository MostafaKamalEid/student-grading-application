package student.project.models;

import student.project.validations.SubjectValidator;

public class Subject {
    private String name;
    private String code;
    private int fullMark;

    public Subject() {
    }

    public Subject(String name, String code, int fullMark) {
        // use setters to set the values of the fields
        setName(name);
        setCode(code);
        setFullMark(fullMark);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        // validate the name using SubjectValidator
        if (!SubjectValidator.validateName(name)) {
            throw new IllegalArgumentException("Invalid name");
        }
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        // validate the code using SubjectValidator
        if (!SubjectValidator.validateCode(code)) {
            throw new IllegalArgumentException("Invalid code");
        }
        this.code = code;
    }

    public int getFullMark() {
        return fullMark;
    }

    public void setFullMark(int fullMark) {
        // validate the full mark using SubjectValidator
        if (!SubjectValidator.validateFullMark(fullMark)) {
            throw new IllegalArgumentException("Invalid full mark");
        }
        this.fullMark = fullMark;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", fullMark=" + fullMark +
                '}';
    }
}
