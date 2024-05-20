package uz.anas.study_center.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.anas.study_center.entity.User;
import uz.anas.study_center.entity.enums.RoleName;
import uz.anas.study_center.model.request.UserRequestDto;
import uz.anas.study_center.repo.UserRepo;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService  {

    private final UserRepo userRepo;
    private final RoleServiceImpl roleService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User getByPhoneNumber(String phoneNumber) {
        return userRepo.findByPhoneNumber(phoneNumber);
    }

    @Override
    public boolean confirmPassword(UserRequestDto user) {
        return user.getPassword().equals(user.getConfirmPassword());
    }

    @Override
    public void saveUserRequestDto(UserRequestDto user) {
        userRepo.save(User.builder()
                        .firstName(user.getFirstName())
                        .lastName(user.getLastName())
                        .roles(List.of(roleService.getByName(RoleName.ROLE_USER)))
                        .phoneNumber(user.getPhoneNumber())
                        .password(passwordEncoder.encode(user.getPassword()))
                .build());
    }

    @Override
    public List<User> getAllStudents() {
        return userRepo.findALLByRoleName(RoleName.ROLE_STUDENT.name());
    }

    @Override
    public Page<User> getAllStudentsContaining(Integer pageNumber, Integer pageSize, String search) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        if (search != null && !search.isEmpty()) {
            return userRepo.findAllByRoleNameAndPhoneNumber(RoleName.ROLE_STUDENT.name(), search, pageRequest);
        }else {
            return userRepo.findByRoleNamePaginated(RoleName.ROLE_STUDENT.name(), pageRequest);
        }
    }
}
