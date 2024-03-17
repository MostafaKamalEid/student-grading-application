package student.project;

import student.project.models.Student;
import student.project.models.Subject;
import java.util.List;

public  record StudentGradingFile(Subject subject, List<Student> students) {
}
