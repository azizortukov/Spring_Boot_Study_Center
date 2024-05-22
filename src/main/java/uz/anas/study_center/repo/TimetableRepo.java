package uz.anas.study_center.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.anas.study_center.entity.Timetable;

import java.util.UUID;

public interface TimetableRepo extends JpaRepository<Timetable, UUID> {
}
