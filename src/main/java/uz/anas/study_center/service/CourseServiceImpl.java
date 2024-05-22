package uz.anas.study_center.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.anas.study_center.entity.Course;
import uz.anas.study_center.repo.CourseRepo;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepo courseRepo;

    @Override
    public List<Course> findAll() {
        return courseRepo.findAll();
    }

    @Override
    public Course save(Course course) {
        return courseRepo.save(course);
    }
}
