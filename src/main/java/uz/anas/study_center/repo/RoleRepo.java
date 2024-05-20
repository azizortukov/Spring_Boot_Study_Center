package uz.anas.study_center.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.anas.study_center.entity.Role;
import uz.anas.study_center.entity.enums.RoleName;

import java.util.UUID;

@Repository
public interface RoleRepo extends JpaRepository<Role, UUID> {

    boolean existsByName(RoleName name);

    Role findByName(RoleName name);

}
