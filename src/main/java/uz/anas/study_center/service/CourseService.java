package uz.anas.study_center.service;

import org.springframework.stereotype.Service;
import uz.anas.study_center.entity.Course;

import java.util.List;

@Service
public interface CourseService {

    List<Course> findAll();

    Course save(Course course);
}
