package uz.anas.study_center.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.anas.study_center.entity.Timetable;
import uz.anas.study_center.entity.User;

import java.util.List;
import java.util.UUID;

public interface TimetableRepo extends JpaRepository<Timetable, UUID> {


    List<Timetable> findAllByGroupIdAndMentor(UUID groupId, User mentor);
}
