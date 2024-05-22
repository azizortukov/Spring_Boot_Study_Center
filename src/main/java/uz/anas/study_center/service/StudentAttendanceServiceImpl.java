package uz.anas.study_center.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.anas.study_center.entity.StudentAttendance;
import uz.anas.study_center.repo.StudentAttendanceRepo;

@Service
@RequiredArgsConstructor
public class StudentAttendanceServiceImpl implements StudentAttendanceService {

    private final StudentAttendanceRepo studentAttendanceRepo;

    @Override
    public StudentAttendance save(StudentAttendance studentAttendance) {
        return studentAttendanceRepo.save(studentAttendance);
    }

}
