package devops;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StudentGradeController {

    private StudentGradeApp app = new StudentGradeApp();

    public StudentGradeController() {

        app.addStudent(new Student("Pujitha", 85, 78, 92));
        app.addStudent(new Student("Ananya", 72, 68, 75));
        app.addStudent(new Student("Rahul", 45, 52, 48));
    }

    @GetMapping("/")
    public String home(Model model) {

        model.addAttribute("students", app.getStudents());

        return "index";
    }

    @PostMapping("/add")
    public String addStudent(
            @RequestParam String name,
            @RequestParam int mark1,
            @RequestParam int mark2,
            @RequestParam int mark3) {

        app.addStudent(
                new Student(name, mark1, mark2, mark3)
        );

        return "redirect:/";
    }
}