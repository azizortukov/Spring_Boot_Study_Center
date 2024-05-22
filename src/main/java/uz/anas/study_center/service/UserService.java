package uz.anas.study_center.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import uz.anas.study_center.entity.User;
import uz.anas.study_center.model.request.UserRequestDto;

import java.util.List;

@Service
public interface UserService {

    User getByPhoneNumber(String phoneNumber);

    boolean confirmPassword(UserRequestDto user);

    void saveUserRequestDto(UserRequestDto userRequestDto);

    List<User> getAllStudents();

    Page<User> getAllStudentsContaining(Integer pageNumber, Integer pageSize, String search);

    User save(User user);
}
