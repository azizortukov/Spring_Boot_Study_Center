package uz.anas.study_center.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.anas.study_center.entity.Role;
import uz.anas.study_center.entity.enums.RoleName;
import uz.anas.study_center.repo.RoleRepo;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService  {


    private final RoleRepo roleRepo;

    @Override
    public boolean exists(RoleName name) {
        return roleRepo.existsByName(name);
    }

    @Override
    public void save(Role role) {
        roleRepo.save(role);
    }

    @Override
    public Role getByName(RoleName roleName) {
        return roleRepo.findByName(roleName);
    }
}
