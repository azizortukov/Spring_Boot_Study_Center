package uz.anas.study_center.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.anas.study_center.entity.TimetableStudent;
import uz.anas.study_center.model.response.TimetableStudentResponseDto;

import java.util.List;
import java.util.UUID;

public interface TimetableStudentRepo extends JpaRepository<TimetableStudent, UUID> {


    @Query(nativeQuery = true, value = """
            select u.phone_number as phone_number, array_agg(sa.attendance order by sa.lesson_order) as attendances from timetable_student tts
            join public.timetable tt on tt.id = tts.timetable_id
            join public.student_attendance sa on tts.id = sa.timetable_student_id
            join public.users u on tts.student_id = u.id
            where tt.id = ?1
            group by u.id
            """)
    List<TimetableStudentResponseDto> findAllByTimetable(UUID timetableId);


//    select tts.* from timetable_student tts
//    left join public.student_attendance sa on tts.id = sa.timetable_student_id
//    left join public.timetable t on t.id = tts.timetable_id
//    where timetable_id = :timetableId
//    and t.current_lesson <= sa.lesson_order
//    group by tts.student_id, tts.id
}
