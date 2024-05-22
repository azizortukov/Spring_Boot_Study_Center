package uz.anas.study_center.service;

import org.springframework.stereotype.Service;
import uz.anas.study_center.entity.Timetable;

@Service
public interface TimetableService {

    Timetable save(Timetable timetable);
}
