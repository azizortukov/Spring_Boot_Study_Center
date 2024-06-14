package uz.anas.study_center.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import uz.anas.study_center.entity.Group;
import uz.anas.study_center.repo.GroupRepo;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService  {

    private final GroupRepo groupRepo;

    @Override
    public Page<Group> getAll(Integer pageNumber, Integer pageSize, String search) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        if (search != null && !search.isEmpty()) {
            return groupRepo.findAllByNameContainingIgnoreCase(search, pageRequest);
        }
        return groupRepo.findAll(pageRequest);
    }

    @Override
    public List<Group> findAllByMentorId(UUID mentorId) {
        return groupRepo.findAllByMentorId(mentorId);
    }

    @Override
    public List<Group> findAll() {
        return groupRepo.findAll();
    }

    @Override
    public Group findById(UUID groupId) {
        return groupRepo.findById(groupId).orElse(null);
    }

    @Override
    public Group save(Group group) {
        return groupRepo.save(group);
    }
}
