package uz.anas.study_center.component;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import uz.anas.study_center.entity.Role;
import uz.anas.study_center.entity.enums.RoleName;
import uz.anas.study_center.service.RoleServiceImpl;

@Component
@RequiredArgsConstructor
public class Runnable implements CommandLineRunner {

    private final RoleServiceImpl roleService;

    @Override
    public void run(String... args) {
        for (RoleName roleName : RoleName.values()) {
            if (!roleService.exists(roleName)) {
                roleService.save(Role.builder().name(roleName).build());
            }
        }
    }
}
