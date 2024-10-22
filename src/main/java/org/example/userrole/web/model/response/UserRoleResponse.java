package org.example.userrole.web.model.response;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.example.userrole.entity.UserRole;

/**
 * @author Grigoriy Zemlyanskiy
 * @version 1.0
 * class UserResponse
 * for send response
 */
@Schema(description = "Данные отношения пользователь-роль")
@Data
@Getter
@AllArgsConstructor
public class UserRoleResponse {
    @Schema(description = "Идентификатор отношения пользователь-роль.")
    @JsonProperty("id")
    private long id;

    @Schema(description = "Идентификатор пользователя.")
    @JsonProperty("user_id")
    private long userId;

    @Schema(description = "Идентификатор роли.")
    @JsonProperty("role_id")
    private long roleId;

    public UserRoleResponse(UserRole userRole) {
        this.id = userRole.getId();
        this.userId = userRole.getUserId();
        this.roleId = userRole.getRoleId();
    }
}

