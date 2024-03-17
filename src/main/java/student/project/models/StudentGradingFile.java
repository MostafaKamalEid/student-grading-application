package student.project.models;

import java.util.List;

public  record StudentGradingFile(Subject subject, List<Student> students) {
}
