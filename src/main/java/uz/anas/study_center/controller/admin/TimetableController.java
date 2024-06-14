package uz.anas.study_center.controller.admin;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uz.anas.study_center.entity.Timetable;
import uz.anas.study_center.entity.User;
import uz.anas.study_center.model.response.TimetableStudentResponseDto;
import uz.anas.study_center.service.GroupServiceImpl;
import uz.anas.study_center.service.StudentAttendanceServiceImpl;
import uz.anas.study_center.service.TimetableServiceImpl;
import uz.anas.study_center.service.TimetableStudentServiceImpl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/admin/timetable")
@RequiredArgsConstructor
@PreAuthorize(value = "hasAnyRole('ROLE_ADMIN', 'ROLE_MENTOR')")
public class TimetableController {


    private final GroupServiceImpl groupService;
    private final TimetableServiceImpl timetableService;
    private final TimetableStudentServiceImpl timetableStudentService;
    private final StudentAttendanceServiceImpl studentAttendanceService;

    //timetableId always comes as it's select box and something is always selected
    @GetMapping
    public String timetable(
            @RequestParam(required = false) UUID groupId,
            @RequestParam(required = false) UUID timetableId,
            @RequestParam(required = false) String studentAttendanceId,
            @RequestParam(required = false) Boolean started,
            Authentication authentication,
            HttpSession session,
            Model model){
        User mentor = (User) authentication.getPrincipal();
        //Updating student attendance
        if (studentAttendanceId != null){
            String[] split = studentAttendanceId.split("/");
            UUID timetableStudentId = UUID.fromString(split[0]);
            int lessonOrder = Integer.parseInt(split[1]);
            timetableStudentService.updateAttendanceById(timetableStudentId, lessonOrder);
        }

        //Setting group to session
        //TODO if mentor doesn't login or mentor doesn't have group, it throws no such element exception, pls handle it
        UUID chosenGroupId = groupService.findAllByMentorId(mentor.getId()).getFirst().getId();
        if(groupId != null){
            chosenGroupId = groupId;
        }else if (session.getAttribute("selectedGroupId") != null) {
            chosenGroupId = (UUID) session.getAttribute("selectedGroupId");
        }
        session.setAttribute("selectedGroupId", chosenGroupId);
        model.addAttribute("groups", groupService.findAllByMentorId(mentor.getId()));
        model.addAttribute("selectedGroupId", chosenGroupId);
        //Getting id from request param, because timetable id always comes
        List<Timetable> timetables = timetableService.findAllByGroupIdAndMentor(chosenGroupId, mentor);
        model.addAttribute("timetables", timetables);
        UUID chosenTimetableId = timetables.getFirst().getId();
        //check if timetableId is included in findAllGroupId
        if(timetableId != null && timetableService.checkIdInCollection(timetables, timetableId)){
            chosenTimetableId = timetableId;
        }
        Timetable chosenTimetable = timetableService.findById(chosenTimetableId);
        model.addAttribute("chosenTimetable", chosenTimetable);
        model.addAttribute("today", Date.valueOf(LocalDate.now()));
        List<TimetableStudentResponseDto> studentResponseDto = timetableStudentService.findAllByTimetableId(chosenTimetableId);
        model.addAttribute("timetableStudents", studentResponseDto);
        if (started == null) {
            started = (Boolean) session.getAttribute("started");
        }else if (started){
            session.setAttribute("started", started);
        }else {
            chosenTimetable.setCurrentLesson(chosenTimetable.getCurrentLesson() + 1);
            timetableService.save(chosenTimetable);
            session.removeAttribute("started");
        }

        model.addAttribute("started", started);
        return "admin/timetable";
    }

}
