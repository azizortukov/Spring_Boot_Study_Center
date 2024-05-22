package uz.anas.study_center.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.anas.study_center.repo.TimetableStudentRepo;

@Service
@RequiredArgsConstructor
public class TimetableStudentServiceImpl implements TimetableStudentService {

    private final TimetableStudentRepo timetableStudentRepo;

}
