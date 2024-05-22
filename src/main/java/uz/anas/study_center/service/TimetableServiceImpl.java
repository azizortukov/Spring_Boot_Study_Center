package uz.anas.study_center.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.anas.study_center.entity.Timetable;
import uz.anas.study_center.repo.TimetableRepo;

@Service
@RequiredArgsConstructor
public class TimetableServiceImpl implements TimetableService {

    private final TimetableRepo timetableRepo;

    @Override
    public Timetable save(Timetable timetable) {
        return timetableRepo.save(timetable);
    }
}
