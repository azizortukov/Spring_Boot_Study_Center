package uz.anas.study_center.component;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.anas.study_center.entity.*;
import uz.anas.study_center.entity.enums.RoleName;
import uz.anas.study_center.service.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class Runnable implements CommandLineRunner {

    private final RoleService roleService;
    private final GroupService groupService;
    private final CourseService courseService;
    private final UserService userService;
    private final TimetableService timetableService;
    private final TimetableStudentService timetableStudentService;
    private final StudentAttendanceService studentAttendanceService;
    private final PasswordEncoder passwordEncoder;

    Random random = new Random();

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
                Group group = groupService.save(Group.builder()
                        .name("Group " + i)
                        .course(course)
                        .build());
                for (int j = 20; j <= 22; j++) {
                    Timetable timetable = timetableService.save(Timetable.builder()
                            .currentLesson(1)
                            .mentor(userService.save(User.builder()
                                    .roles(List.of(roleService.getByName(RoleName.ROLE_MENTOR)))
                                    .firstName("Teressa")
                                    .lastName("Johnson")
                                    .phoneNumber(String.valueOf(random.nextInt(100000000, 999999999)))
                                    .password(passwordEncoder.encode("123"))
                                    .build()))
                            .name("Timetable " + count++)
                            .group(group)
                            .build());
                    generateTimeTableStudents(timetable);
                    count++;
                }
            }
        }

    }

    private void generateTimeTableStudents(Timetable timetable) {
        for (int i = 1; i <= 12; i++) {
            TimetableStudent timetableStudent = TimetableStudent.builder()
                    .student(userService.save(User.builder()
                            .firstName("Student")
                            .lastName("Studentov")
                            .phoneNumber(String.valueOf(random.nextInt(100000000, 999999999)))
                            .password(passwordEncoder.encode("123"))
                            .roles(List.of(roleService.getByName(RoleName.ROLE_STUDENT)))
                            .build()))
                    .timetable(timetable)
                    .build();
            timetableStudentService.save(timetableStudent);
            generateAttendances(timetableStudent);
        }
    }

    //Creating a default 12 lesson for a timetable as timetable is one month and 12 lesson would be there
    private void generateAttendances(TimetableStudent timetableStudent) {
        for (int i = 0; i < 12; i++) {
            StudentAttendance studentAttendance = StudentAttendance.builder()
                    .attendance(false)
                    .lessonOrder(i)
                    .timetableStudent(timetableStudent)
                    .lessonDate(LocalDate.now().plusDays(i * 2))
                    .build();
            StudentAttendance saved = studentAttendanceService.save(studentAttendance);
        }
    }
}
