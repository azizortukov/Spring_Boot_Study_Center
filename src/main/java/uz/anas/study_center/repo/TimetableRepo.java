package uz.anas.study_center.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.anas.study_center.entity.Timetable;
import uz.anas.study_center.entity.User;

import java.util.List;
import java.util.UUID;

public interface TimetableRepo extends JpaRepository<Timetable, UUID> {


    List<Timetable> findAllByGroupIdAndMentor(UUID groupId, User mentor);

    @Query(nativeQuery = true, value = """
            select * from timetable
            where group_id =:groupId
            order by name desc
            limit 1""")
    Timetable findByGroupIdLatest(UUID groupId);
}
