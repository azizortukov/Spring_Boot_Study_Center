package uz.anas.study_center.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.anas.study_center.entity.Course;

import java.util.UUID;

public interface CourseRepo extends JpaRepository<Course, UUID> {
}
