package uz.anas.study_center.service;

import org.springframework.stereotype.Service;
import uz.anas.study_center.entity.Timetable;

import java.util.List;

@Service
public interface TimetableService {

    Timetable save(Timetable timetable);

    List<Timetable> findAll();
}
