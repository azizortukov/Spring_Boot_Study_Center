package uz.anas.study_center.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import uz.anas.study_center.entity.Group;

import java.util.List;

@Service
public interface GroupService {


    Page<Group> getAll(Integer pageNumber, Integer pageSize, String search);

    List<Group> findAll();

    Group save(Group group);
}
