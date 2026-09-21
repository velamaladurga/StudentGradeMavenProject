package devops;

import java.util.ArrayList;
import java.util.List;

public class StudentGradeApp {

    private List<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
        students.add(student);
    }

    public int getStudentCount() {
        return students.size();
    }

    public void displayStudents() {

        System.out.println("STUDENT GRADE MANAGEMENT SYSTEM");
        System.out.println("--------------------------------");

        for (Student student : students) {

            System.out.println("Student: " + student.getName());
            System.out.println("Total: " + student.getTotal());
            System.out.println("Average: " + student.getAverage());
            System.out.println("Grade: " + student.getGrade());
            System.out.println("--------------------------------");
        }
    }

    public static void main(String[] args) {

        StudentGradeApp app = new StudentGradeApp();

        app.addStudent(new Student("Pujitha", 85, 78, 92));
        app.addStudent(new Student("Ananya", 72, 68, 75));
        app.addStudent(new Student("Rahul", 45, 52, 48));

        app.displayStudents();
    }
}