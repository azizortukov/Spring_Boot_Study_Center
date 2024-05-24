package uz.anas.study_center.service;

import org.springframework.stereotype.Service;
import uz.anas.study_center.entity.TimetableStudent;
import uz.anas.study_center.model.response.TimetableStudentResponseDto;

import java.util.List;
import java.util.UUID;

@Service
public interface TimetableStudentService {

    List<TimetableStudentResponseDto> findAllByTimetableId(UUID timetableId);

    TimetableStudent save(TimetableStudent timetableStudent);

    void updateAttendanceById(UUID timetableStudentId, int lessonOrder);
}
