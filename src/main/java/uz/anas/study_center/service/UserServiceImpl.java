package uz.anas.study_center.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.anas.study_center.entity.User;
import uz.anas.study_center.repo.UserRepo;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService  {

    private final UserRepo userRepo;

    @Override
    public User getByPhoneNumber(String phoneNumber) {
        return userRepo.findByPhoneNumber(phoneNumber);
    }
}
