package uz.anas.study_center.service;

import org.springframework.stereotype.Service;
import uz.anas.study_center.entity.User;
import uz.anas.study_center.model.request.UserRequestDto;

@Service
public interface UserService {

    User getByPhoneNumber(String phoneNumber);

    boolean confirmPassword(UserRequestDto user);

    void saveUserRequestDto(UserRequestDto userRequestDto);
}
