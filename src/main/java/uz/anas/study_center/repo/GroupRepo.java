package uz.anas.study_center.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.anas.study_center.entity.Group;

import java.util.List;
import java.util.UUID;

@Repository
public interface GroupRepo extends JpaRepository<Group, UUID> {

    @Query(nativeQuery = true, value = """
                select g.* from groups g
                join timetable t on g.id = t.group_id
                where t.mentor_id = :mentorId
                group by g.id""")
    List<Group> findAllByMentorId(UUID mentorId);

    Page<Group> findAllByNameContainingIgnoreCase(String name, Pageable pageable);

}
