package org.example.userrole.services;


import org.example.userrole.entity.UserRole;

import java.util.List;

/**
 * @author Grigoriy Zemlyanskiy
 * @version 1.0
 * service for work with table user_role
 */
public interface UserRoleComponent {

    /**
     * Сохраняет отношение пользователь-роль.
     *
     * @param userId  идентификатор пользователя.
     * @param roleId  идентификатор роли.
     * @return сохраненное отношение.
     */
    UserRole commit(long userId ,long roleId);

    /**
     * Находит всех отношения пользователь-роль.
     *
     * @return список отношений.
     */
    List<String> findAllRoleByUserId(long id);

    /**
     * Удалить отношение пользователь-роль по идентификатору.
     *
     * @param userId  идентификатор пользователя.
     * @param roleId  идентификатор роли.
     */
    void deleteUserRoleByIds(long userId ,long roleId);

}
