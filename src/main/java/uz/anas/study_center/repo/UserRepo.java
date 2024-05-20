package uz.anas.study_center.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.anas.study_center.entity.User;

import java.util.List;
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
}
