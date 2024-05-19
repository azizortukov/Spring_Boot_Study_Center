package uz.anas.study_center.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.anas.study_center.repo.GroupRepo;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService  {


    private final GroupRepo groupRepo;


}
