package uz.anas.study_center.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import uz.anas.study_center.entity.Group;

import java.util.List;
import java.util.UUID;

@Service
public interface GroupService {


    Page<Group> getAll(Integer pageNumber, Integer pageSize, String search);

    List<Group> findAllByMentorId(UUID mentorId);

    Group save(Group group);

    List<Group> findAll();

}
