package uz.anas.study_center.service;

import org.springframework.stereotype.Service;
import uz.anas.study_center.entity.Timetable;
import uz.anas.study_center.entity.User;

import java.util.List;
import java.util.UUID;

@Service
public interface TimetableService {

    Timetable save(Timetable timetable);

    List<Timetable> findAll();

    List<Timetable> findAllByGroupIdAndMentor(UUID group, User mentor);

    boolean checkIdInCollection(List<Timetable> timetables, UUID timetableId);

    Timetable findById(UUID timetableId);

    Timetable findLatestByGroupId(UUID selectedGroupId);
}
