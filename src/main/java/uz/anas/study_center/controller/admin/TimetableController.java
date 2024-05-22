package uz.anas.study_center.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uz.anas.study_center.entity.Timetable;
import uz.anas.study_center.service.GroupServiceImpl;
import uz.anas.study_center.service.StudentAttendanceServiceImpl;
import uz.anas.study_center.service.TimetableServiceImpl;
import uz.anas.study_center.service.TimetableStudentServiceImpl;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/admin/timetable")
@RequiredArgsConstructor
@PreAuthorize(value = "hasRole('ROLE_ADMIN')")
public class TimetableController {


    private final GroupServiceImpl groupService;
    private final TimetableServiceImpl timetableService;
    private final TimetableStudentServiceImpl timetableStudentService;
    private final StudentAttendanceServiceImpl studentAttendanceService;

    @GetMapping
    public String timetable(
            @RequestParam(required = false) UUID groupId,
            @RequestParam(required = false) UUID timetableId,
            @RequestParam(required = false) UUID lessonNumber,
            @RequestParam(required = false) UUID studentAttendanceId,
            Model model){
        model.addAttribute("groups", groupService.findAll());
        List<Timetable> timetables = timetableService.findAll();
        model.addAttribute("timetables", timetables);
        model.addAttribute("timetableStudents", timetableStudentService.findAllByTimetable(timetables.getFirst()));
        model.addAttribute("selectedGroupId", timetables.getFirst().getGroup().getId());
        return "admin/timetable";
    }

}
