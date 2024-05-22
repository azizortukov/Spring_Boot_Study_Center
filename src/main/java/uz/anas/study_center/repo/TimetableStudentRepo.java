package uz.anas.study_center.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.anas.study_center.entity.TimetableStudent;

import java.util.UUID;

public interface TimetableStudentRepo extends JpaRepository<TimetableStudent, UUID> {
}
