package org.example.userrole.services;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ContextConfiguration
@Transactional
class UserRoleComponentImplTest {
    @Autowired
    private UserRoleComponent component;
    @Autowired
    private DataTestValues testValues;

    @Test
    void commit() {
        component.deleteUserRoleByIds(testValues.userId,testValues.roleId);
        assertEquals(testValues.cuttedSize, component.findAllRoleByUserId(1l).size());
        component.commit(testValues.userId, testValues.roleId);
        assertEquals(testValues.fullSize, component.findAllRoleByUserId(1l).size());
    }

    @Test
    void findAllRoleByUserId() {
        List<String> roles =component.findAllRoleByUserId(testValues.userId);
        assertEquals(testValues.fullSize, roles.size());
    }
    @Test
    void findAllRoleByUserIdThrowException()  {
        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                component.findAllRoleByUserId(3l));
        assertEquals("user not found.", exception.getMessage());
    }

    @Test
    void deleteUserRoleByIds() {
        assertEquals(testValues.fullSize, component.findAllRoleByUserId(testValues.userId).size());
        component.deleteUserRoleByIds(testValues.userId, testValues.roleId);
        assertEquals(testValues.cuttedSize, component.findAllRoleByUserId(testValues.userId).size());
    }

}