package uz.anas.study_center.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.anas.study_center.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepo extends JpaRepository<User, UUID> {

    User findByPhoneNumber(String phoneNumber);

    @Query(nativeQuery = true, value = """
                    select u.* from users u
                    join users_roles ur on u.id = ur.user_id
                    join roles r on r.id = ur.roles_id
                    where r.name = ?1""")
    List<User> findALLByRoleName(String roleName);

    @Query(nativeQuery = true, value = """
                    select u.* from users u
                    join users_roles ur on u.id = ur.user_id
                    join roles r on r.id = ur.roles_id
                    where r.name = ?1 and u.phone_number ilike %?2%""")
    Page<User> findAllByRoleNameAndPhoneNumber(String roleName, String phoneNumber, Pageable pageable);

    @Query(nativeQuery = true, value = """
                    select u.* from users u
                    join users_roles ur on u.id = ur.user_id
                    join roles r on r.id = ur.roles_id
                    where r.name = ?1""")
    Page<User> findByRoleNamePaginated(String name, PageRequest pageRequest);

    @Query(nativeQuery = true, value = """
            select u.* from users u
            join timetable_student tts on u.id = tts.student_id
            join timetable tt on tt.id = tts.timetable_id
            where tt.id = (
                select t.id from timetable t
                where t.group_id = :groupId
                order by t.name desc
                limit 1)""")
    Optional<List<User>> findAllByGroupId(UUID groupId);

    @Query(nativeQuery = true, value = """
            select u.* from users u
            join timetable_student tts on u.id = tts.student_id
            join timetable tt on tt.id = tts.timetable_id
            join groups g on g.id = tt.group_id
            join groups g2 on g2.course_id = g.course_id
            where g2.id = :groupId and u.id not in (
            select u2.id from users u2
            join timetable_student tts2 on u2.id = tts2.student_id
            join timetable tt2 on tt2.id = tts2.timetable_id
            where tt2.id = (
                select t.id from timetable t
                where t.group_id = :groupId
                order by t.name desc
                limit 1))""")
    Optional<List<User>> findAllExcludingGroupId(UUID groupId);
}
