package uz.anas.study_center.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.anas.study_center.entity.StudentAttendance;
import uz.anas.study_center.entity.TimetableStudent;
import uz.anas.study_center.model.response.TimetableStudentResponseDto;
import uz.anas.study_center.repo.TimetableStudentRepo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TimetableStudentServiceImpl implements TimetableStudentService {

    private final TimetableStudentRepo timetableStudentRepo;
    private final StudentAttendanceService studentAttendanceService;

    @Override
    public List<TimetableStudentResponseDto> findAllByTimetableId(UUID timetableId) {
        return timetableStudentRepo.findAllByTimetableId(timetableId);
    }

    @Override
    public TimetableStudent save(TimetableStudent timetableStudent) {
        return timetableStudentRepo.save(timetableStudent);
    }

    @Override
    public void updateAttendanceById(UUID timetableStudentId, int lessonOrder) {
        Optional<TimetableStudent> updateAttendance = timetableStudentRepo.findById(timetableStudentId);
        if (updateAttendance.isPresent()) {
            TimetableStudent timetableStudent = updateAttendance.get();
            // Collect items to update in a separate list to avoid ConcurrentModificationException
            List<StudentAttendance> itemsToUpdate = timetableStudent.getAttendances().stream()
                    .filter(item -> item.getLessonOrder().equals(lessonOrder))
                    .peek(item -> item.setAttendance(!item.getAttendance())) // Toggle the attendance
                    .toList();

            // Now save the updated items
            itemsToUpdate.forEach(studentAttendanceService::save);
        } else {
            System.out.println("not found");
        }
    }
}
