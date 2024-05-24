package uz.anas.study_center.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uz.anas.study_center.entity.Timetable;
import uz.anas.study_center.entity.User;
import uz.anas.study_center.repo.TimetableRepo;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TimetableServiceImpl implements TimetableService {

    private final TimetableRepo timetableRepo;

    @Override
    public Timetable save(Timetable timetable) {
        return timetableRepo.save(timetable);
    }

    @Override
    public List<Timetable> findAll() {
        Sort sort = Sort.by("name");
        return timetableRepo.findAll(sort);
    }

    @Override
    public List<Timetable> findAllByGroupIdAndMentor(UUID groupId, User mentor) {
        return timetableRepo.findAllByGroupIdAndMentor(groupId, mentor);
    }

    @Override
    public boolean checkIdInCollection(List<Timetable> timetables, UUID timetableId) {
        return timetables.stream()
                .anyMatch(timetable -> timetable.getId().equals(timetableId));
    }
}
