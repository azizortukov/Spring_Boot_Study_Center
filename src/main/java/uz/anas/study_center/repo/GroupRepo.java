package uz.anas.study_center.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.anas.study_center.entity.Group;

import java.util.List;
import java.util.UUID;

@Repository
public interface GroupRepo extends JpaRepository<Group, UUID> {

    List<Group> findAll();

    Page<Group> findAllByNameContainingIgnoreCase(String name, Pageable pageable);

}
