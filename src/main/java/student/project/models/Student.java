package student.project.models;

public class Student {
    private String name;
    private String number;
    private int activityMarks;
    private int oralMarks;
    private int midMarks;
    private int finalMarks;

    public Student() {
    }

    public Student(String name, String number, int activityMarks, int oralMarks, int midMarks, int finalMarks) {
        this.name = name;
        this.number = number;
        this.activityMarks = activityMarks;
        this.oralMarks = oralMarks;
        this.midMarks = midMarks;
        this.finalMarks = finalMarks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getActivityMarks() {
        return activityMarks;
    }

    public void setActivityMarks(int activityMarks) {
        this.activityMarks = activityMarks;
    }

    public int getOralMarks() {
        return oralMarks;
    }

    public void setOralMarks(int oralMarks) {
        this.oralMarks = oralMarks;
    }

    public int getMidMarks() {
        return midMarks;
    }

    public void setMidMarks(int midMarks) {
        this.midMarks = midMarks;
    }

    public int getFinalMarks() {
        return finalMarks;
    }

    public void setFinalMarks(int finalMarks) {
        this.finalMarks = finalMarks;
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
