package uz.anas.study_center.component;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.anas.study_center.entity.*;
import uz.anas.study_center.entity.enums.RoleName;
import uz.anas.study_center.service.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class Runnable implements CommandLineRunner {

    private final RoleServiceImpl roleService;
    private final GroupServiceImpl groupService;
    private final CourseServiceImpl courseService;
    private final UserServiceImpl userService;
    private final TimetableServiceImpl timetableService;
    private final TimetableStudentServiceImpl timetableStudentService;
    private final StudentAttendanceServiceImpl studentAttendanceService;
    private final PasswordEncoder passwordEncoder;

    //Initial data will be created if init.data is true in application properties
    //By default, it's false
    @Value("${init.data}")
    private Boolean initialData;

    @Override
    public void run(String... args) {
        if (initialData) {
            System.out.println("...adding mock data...");
            initData();
        }
    }

    //Creating initial data method "mock data"
    private void initData() {
        Random random = new Random();
        int count = 0;

        //Adding every possible role types to db and one user to every role
        for (RoleName roleName : RoleName.values()) {
            if (!roleService.exists(roleName)) {
                Role role = roleService.save(Role.builder().name(roleName).build());
                userService.save(
                        User.builder()
                                .firstName("John")
                                .phoneNumber(String.valueOf(count).repeat(9))
                                .roles(List.of(role))
                                .password(passwordEncoder.encode("123"))
                                .build());
                count++;
            }
        }

        //Adding courses, its groups, groups' timetables, students to timetable
        if (courseService.findAll().isEmpty() && groupService.findAll().isEmpty()) {
            count = 0;
            for (int i = 1; i <= 10; i++) {
                Course course = courseService.save(Course.builder()
                        .price(random.nextInt(90, 150))
                        .name("Course" + i)
                        .build());
                for (int j = 20; j <= 30; j++) {
                    Group group = groupService.save(Group.builder()
                            .name("Group " + random.nextInt(1, 1000))
                            .course(course)
                            .build());
                    Timetable timetable = timetableService.save(Timetable.builder()
                            .currentLesson(1)
                            .name("Timetable " + count++)
                            .group(group)
                            .build());
                    TimetableStudent timetableStudent = TimetableStudent.builder()
                            .timetable(timetable)
                            .student(userService.save(User.builder()
                                    .phoneNumber(String.valueOf(random.nextInt(194385320, 986676787)))
                                    .password(passwordEncoder.encode("123"))
                                    .roles(List.of(roleService.getByName(RoleName.ROLE_STUDENT)))
                                    .build()))
                            .build();
                    timetableStudent.setAttendances(generateAttendances(timetableStudent));
                    timetableStudentService.save(timetableStudent);
                    count++;
                }
            }
        }

    }

    //Creating a default 12 lesson for a timetable as timetable is one month and 12 lesson would be there
    private List<StudentAttendance> generateAttendances(TimetableStudent timetableStudent) {
        List<StudentAttendance> attendances = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            StudentAttendance studentAttendance = StudentAttendance.builder()
                    .attendance(false)
                    .lessonOrder(i)
                    .timetableStudent(timetableStudent)
                    .build();
            attendances.add(studentAttendance);
        }
        return attendances;
    }
}
