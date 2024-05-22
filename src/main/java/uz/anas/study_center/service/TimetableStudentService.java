package uz.anas.study_center.service;

import org.springframework.stereotype.Service;
import uz.anas.study_center.entity.Timetable;
import uz.anas.study_center.entity.TimetableStudent;
import uz.anas.study_center.model.response.TimetableStudentResponseDto;

import java.util.List;

@Service
public interface TimetableStudentService {

    List<TimetableStudentResponseDto> findAllByTimetable(Timetable timetable);

    TimetableStudent save(TimetableStudent timetableStudent);
}
