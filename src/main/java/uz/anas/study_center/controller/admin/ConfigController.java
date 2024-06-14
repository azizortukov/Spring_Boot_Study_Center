package uz.anas.study_center.controller.admin;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uz.anas.study_center.component.Runnable;
import uz.anas.study_center.entity.Timetable;
import uz.anas.study_center.entity.TimetableStudent;
import uz.anas.study_center.entity.User;
import uz.anas.study_center.entity.enums.RoleName;
import uz.anas.study_center.service.GroupService;
import uz.anas.study_center.service.TimetableService;
import uz.anas.study_center.service.TimetableStudentService;
import uz.anas.study_center.service.UserService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/admin/configure")
@RequiredArgsConstructor
public class ConfigController {

    private final GroupService groupService;
    private final UserService userService;
    private final HttpSession httpSession;
    private final TimetableService timetableService;
    private final Runnable runnable;
    private final TimetableStudentService timetableStudentService;

    @GetMapping
    public String config(Model model,
                         @RequestParam(required = false) UUID selectedGroupId) {
        if (httpSession.getAttribute("selectedGroupId") != null) {
            selectedGroupId = (UUID) httpSession.getAttribute("selectedGroupId");
            model.addAttribute("timetableStudents", httpSession.getAttribute("timetableStudents"));
            model.addAttribute("students", httpSession.getAttribute("students"));
        }
        model.addAttribute("configure", true);
        model.addAttribute("groups", groupService.findAll());
        model.addAttribute("mentors", userService.getAllByRoleName(RoleName.ROLE_MENTOR));
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
    @SuppressWarnings("unchecked")
    @GetMapping("/addStudent")
    public String addStudent(@RequestParam UUID studentId) {
        List<User> timetableStudents = (List<User>)httpSession.getAttribute("timetableStudents");
        List<User> students = (List<User>)httpSession.getAttribute("students");
        Optional<User> studentById = students.stream().filter(item -> item.getId().equals(studentId)).findFirst();
        if (studentById.isPresent()) {
            timetableStudents.add(studentById.get());
            students.remove(studentById.get());
        }
        httpSession.setAttribute("timetableStudents", timetableStudents);
        httpSession.setAttribute("students", students);
        return "redirect:/admin/configure";
    }

    @SuppressWarnings("unchecked")
    @GetMapping("/removeStudent")
    public String removeStudent(@RequestParam UUID studentId, Model model) {
        List<User> timetableStudents = (List<User>)httpSession.getAttribute("timetableStudents");
        List<User> students = (List<User>)httpSession.getAttribute("students");
        Optional<User> studentById = timetableStudents.stream().filter(item -> item.getId().equals(studentId)).findFirst();
        if (studentById.isPresent()) {
            timetableStudents.remove(studentById.get());
            students.add(studentById.get());
        }
        httpSession.setAttribute("timetableStudents", timetableStudents);
        httpSession.setAttribute("students", students);
        return "redirect:/admin/configure";
    }

    @SuppressWarnings("unchecked")
    @PostMapping("/addTimetable")
    public String addTimetable(@RequestParam LocalDate startDate, @RequestParam UUID mentorId) {
        if (startDate == null || startDate.isBefore(LocalDate.now())) {
            System.out.println("startDate: " + startDate);
            return "redirect:/admin/configure";
        }
        Timetable timetableByGroupId = timetableService.findLatestByGroupId((UUID) httpSession.getAttribute("selectedGroupId"));
        int newTimetableIndex = Integer.parseInt(timetableByGroupId.getName().substring(10)) + 1;
        Timetable timetable = timetableService.save(Timetable.builder()
                .currentLesson(1)
                .mentor(userService.findById(mentorId))
                .name("Timetable " + newTimetableIndex)
                .group(groupService.findById((UUID) httpSession.getAttribute("selectedGroupId")))
                .build());
        for (User timetableStudents : (List<User>) httpSession.getAttribute("timetableStudents")) {
            TimetableStudent timetableStudent = timetableStudentService.save(TimetableStudent.builder()
                    .student(timetableStudents)
                    .timetable(timetable)
                    .build());
            runnable.generateAttendances(timetableStudent, startDate);
        }
        httpSession.removeAttribute("timetableStudents");
        httpSession.removeAttribute("students");
        httpSession.removeAttribute("selectedGroupId");
        return "redirect:/admin/configure";
    }

}
