package uz.anas.study_center.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.anas.study_center.entity.Timetable;
import uz.anas.study_center.entity.TimetableStudent;
import uz.anas.study_center.model.response.TimetableStudentResponseDto;
import uz.anas.study_center.repo.TimetableStudentRepo;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TimetableStudentServiceImpl implements TimetableStudentService {

    private final TimetableStudentRepo timetableStudentRepo;

    @Override
    public List<TimetableStudentResponseDto> findAllByTimetable(Timetable timetable) {
        return timetableStudentRepo.findAllByTimetable(timetable.getId());
    }

    @Override
    public TimetableStudent save(TimetableStudent timetableStudent) {
        return timetableStudentRepo.save(timetableStudent);
    }
}
