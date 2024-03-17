package student.project.models;

public class Subject {
    private String name;
    private String code;
    private int fullMark;

    public Subject() {
    }

    public Subject(String name, String code, int fullMark) {
        this.name = name;
        this.code = code;
        this.fullMark = fullMark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getFullMark() {
        return fullMark;
    }

    public void setFullMark(int fullMark) {
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
