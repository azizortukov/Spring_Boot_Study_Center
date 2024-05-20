package uz.anas.study_center.component;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import uz.anas.study_center.entity.Group;
import uz.anas.study_center.entity.Role;
import uz.anas.study_center.entity.enums.RoleName;
import uz.anas.study_center.service.GroupServiceImpl;
import uz.anas.study_center.service.RoleServiceImpl;

@Component
@RequiredArgsConstructor
public class Runnable implements CommandLineRunner {

    private final RoleServiceImpl roleService;
    private final GroupServiceImpl groupService;

    @Override
    public void run(String... args) {
        for (RoleName roleName : RoleName.values()) {
            if (!roleService.exists(roleName)) {
                roleService.save(Role.builder().name(roleName).build());
            }
        }
        if (groupService.findAll().isEmpty()) {
            for (int i = 0; i < 100; i++) {
                groupService.save(Group.builder().name("Group " + i).build());
            }
        }
    }
}
