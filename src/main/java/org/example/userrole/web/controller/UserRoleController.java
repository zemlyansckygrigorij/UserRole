package org.example.userrole.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.userrole.services.UserRoleComponent;
import org.example.userrole.web.model.response.UserRoleResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @author Grigoriy Zemlyanskiy
 * @version 1.0
 * class UserController
 * for work with /users
 */
@RestController
@Validated
@Tag(name = "API работы с пользователями",
        description = "Api work with user roles")
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserRoleController {
    private final UserRoleComponent component;

    @Operation(
            description = "назначение роли пользователю",
            summary = "insert new  role to user")
    @ApiResponse(responseCode = "200", description = "list of UserRoleResponse")
    @PostMapping("/{userId}/roles/{roleId}")
    public UserRoleResponse commit(@PathVariable(name = "userId") final Long userId,
                                   @PathVariable(name = "roleId") final Long roleId){
        return  new UserRoleResponse(component.commit(userId, roleId));
    }

    @Operation(
            description = "получение списка ролей пользователя",
            summary = "Retrieve all roles")
    @ApiResponse(responseCode = "200", description = "list of UserRoleResponse")
    @GetMapping("/{userId}/roles")
    public List<String> findAll(@PathVariable(name = "userId") final Long userId){
        return component.findAllRoleByUserId(userId);
    }

    @Operation(
            description = "удаление роли у пользователя",
            summary = "delete role from user by id")
    @ApiResponse(responseCode = "200", description = "null")
    @DeleteMapping("/{userId}/roles/{roleId}")
    public void deleteUserById(
            @PathVariable(name = "userId") final Long userId,
            @PathVariable(name = "roleId") final Long roleId) {
        try {
            component.deleteUserRoleByIds( userId, roleId);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

