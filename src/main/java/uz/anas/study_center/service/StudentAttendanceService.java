package uz.anas.study_center.service;

import org.springframework.stereotype.Service;
import uz.anas.study_center.entity.StudentAttendance;

@Service
public interface StudentAttendanceService {

    StudentAttendance save(StudentAttendance studentAttendance);

}
