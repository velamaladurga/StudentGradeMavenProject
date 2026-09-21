package devops;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StudentGradeTest {

    @Test
    public void testTotalMarks() {

        Student student =
                new Student("Pujitha", 80, 70, 90);

        assertEquals(240, student.getTotal());
    }

    @Test
    public void testAverageMarks() {

        Student student =
                new Student("Pujitha", 80, 70, 90);

        assertEquals(80.0, student.getAverage());
    }

    @Test
    public void testGrade() {

        Student student =
                new Student("Pujitha", 80, 70, 90);

        assertEquals("A", student.getGrade());
    }

    @Test
    public void testAddStudent() {

        StudentGradeApp app = new StudentGradeApp();

        app.addStudent(
                new Student("Pujitha", 80, 70, 90)
        );

        assertEquals(1, app.getStudentCount());
    }
}