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
        List<Object[]> ttStudents = timetableStudentRepo.findAllByTimetable(timetable.getId());
        return ttStudents.stream().map(this::mapToDto).toList();
    }

    private TimetableStudentResponseDto mapToDto(Object[] item) {
       return new TimetableStudentResponseDto(
               (String)item[0], (boolean[])item[1]
       );
    }

    @Override
    public TimetableStudent save(TimetableStudent timetableStudent) {
        return timetableStudentRepo.save(timetableStudent);
    }
}
