package uz.anas.study_center.controller.admin;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uz.anas.study_center.entity.User;
import uz.anas.study_center.service.GroupServiceImpl;
import uz.anas.study_center.service.UserService;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/admin/configure")
@RequiredArgsConstructor
public class ConfigController {

    private final GroupServiceImpl groupService;
    private final UserService userService;
    private final HttpSession httpSession;

    @SuppressWarnings("unchecked")
    @GetMapping
    public String config(Model model,
                         @RequestParam(required = false) UUID selectedGroupId) {
        if (httpSession.getAttribute("selectedGroupId") != null) {
            List<User> timetableStudents = (List<User>)httpSession.getAttribute("timetableStudents");
            List<User> students = (List<User>)httpSession.getAttribute("students");
            model.addAttribute("selectedGroupId", httpSession.getAttribute("selectedGroupId"));
            model.addAttribute("timetableStudents", timetableStudents);
            model.addAttribute("students", students);
        }
        model.addAttribute("configure", true);
        model.addAttribute("groups", groupService.findAll());
        model.addAttribute("selectedGroupId", selectedGroupId);
        return "admin/configure";
    }

    @GetMapping("/groupSelection")
    public String groupSelection(@RequestParam(required = false) UUID selectedGroupId) {
        httpSession.setAttribute("selectedGroupId", selectedGroupId);
        httpSession.setAttribute("timetableStudents", userService.findAllByGroupId(selectedGroupId));
        httpSession.setAttribute("students", userService.findAllExcludingGroupId(selectedGroupId));
        return "redirect:/admin/configure";
    }

    //Add the add student function to the new timetable list of student same thing wit removeStudent
    //TODO reminder: ADD SEARCH AND SELECT FROM ALL STUDENTS, IN CASE NEW TIMETABLE DOESN'T HAVE STUDENTS
    @GetMapping("/addStudent")
    public String addStudent(@RequestParam UUID studentId, Model model) {
        return null;
    }

    @GetMapping("/removeStudent")
    public String removeStudent(@RequestParam UUID studentId, Model model) {
        return null;
    }

}
