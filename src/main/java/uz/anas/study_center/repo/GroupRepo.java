package uz.anas.study_center.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.anas.study_center.entity.Group;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface GroupRepo extends JpaRepository<Group, UUID> {

}
