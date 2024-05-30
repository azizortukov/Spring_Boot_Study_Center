package uz.anas.study_center.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.anas.study_center.entity.TimetableStudent;
import uz.anas.study_center.model.response.TimetableStudentResponseDto;

import java.util.List;
import java.util.UUID;

public interface TimetableStudentRepo extends JpaRepository<TimetableStudent, UUID> {


    @Query(nativeQuery = true, value = """
            select tts.id as timetableStudentId, u.phone_number as phoneNumber,
            array_agg(sa.attendance order by sa.lesson_order) as attendances ,
            array_agg(sa.lesson_date order by sa.lesson_order) as lessonDates
            from timetable_student tts
            join public.timetable tt on tt.id = tts.timetable_id
            join public.student_attendance sa on tts.id = sa.timetable_student_id
            join public.users u on tts.student_id = u.id
            where tt.id = ?1
            group by u.id, tts.id
            """)
    List<TimetableStudentResponseDto> findAllByTimetableId(UUID timetableId);


}
