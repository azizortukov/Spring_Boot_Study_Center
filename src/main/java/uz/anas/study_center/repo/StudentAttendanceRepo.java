package uz.anas.study_center.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.anas.study_center.entity.StudentAttendance;

import java.util.UUID;

public interface StudentAttendanceRepo extends JpaRepository<StudentAttendance, UUID> {
}
