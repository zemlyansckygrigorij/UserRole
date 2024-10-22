package org.example.userrole.services;

import org.example.userrole.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

/**
 * @author Grigoriy Zemlyanskiy
 * @version 1.0
 * interface UserRepo
 */
@Repository
public interface UserRoleRepo extends JpaRepository<UserRole, Long> {

    @Query(value ="select name from roles where id in (select role_id from user_role where user_id =?1 )",
            nativeQuery = true)
    Optional<List<String>> findRolesByUserId(long userId);

    @Query(value ="select username from users where user_id =?1 ",
            nativeQuery = true)
    Optional<List<String>> findUserById(long userId);

    @Query(value ="select name from roles  where id =?1 ",
            nativeQuery = true)
    Optional<List<String>> findRoleById(long Id);

    @Query(value ="select id from user_role where user_id =?1 and role_id=?2",
            nativeQuery = true)
    Optional<List<Long>> findUserRolesById(long userId ,long roleId);

    @Modifying
    @Query(value ="delete from user_role where user_id =?1 and role_id=?2",
            nativeQuery = true)
    void deleteUserRoleByIds(long userId ,long roleId);
}
