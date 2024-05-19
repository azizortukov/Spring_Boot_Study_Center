package uz.anas.study_center.service;

import org.springframework.stereotype.Service;
import uz.anas.study_center.entity.User;

@Service
public interface UserService {

    User getByPhoneNumber(String phoneNumber);

}
