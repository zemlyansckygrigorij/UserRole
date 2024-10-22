package org.example.userrole.services;

import lombok.RequiredArgsConstructor;
import org.example.userrole.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;


/**
 * @author Grigoriy Zemlyanskiy
 * @version 1.0
 * class UserComponentImpl
 */
@RequiredArgsConstructor
@Component
public class UserRoleComponentImpl implements UserRoleComponent{
    @Autowired
    UserRoleRepo repo;

    @Override
    public UserRole commit(long userId ,long roleId) {
        if(!checkUserExist(userId)) {
            throw new RuntimeException("user not found.");
        }
        if(!checkRoleExist(roleId)) {
            throw new RuntimeException("role not found.");
        }
        UserRole userRole = new UserRole();
        userRole.setRoleId(roleId);
        userRole.setUserId(userId);
        return this.repo.save(userRole);
    }

    @Override
    public List<String> findAllRoleByUserId(long id) {
        if(!checkUserExist(id)) {
            throw new RuntimeException("user not found.");
        }
        return this.repo.findRolesByUserId(id).orElseThrow(()->new RuntimeException("roles not found."));
    }

    @Override
    public void deleteUserRoleByIds(long userId ,long roleId){
        this.repo.deleteUserRoleByIds(userId ,roleId);
    }

    private boolean checkUserExist(long userId){
        Optional<List<String>> usernames = this.repo.findUserById(userId);
        if(usernames.isEmpty()||usernames.get().size()==0) {
            return false;
        }
        return true;
    }

    private boolean checkRoleExist(long roleId){
        Optional<List<String>> rolenames = this.repo.findRoleById(roleId);
        if(rolenames.isEmpty()|rolenames.get().size()==0) {
            return false;
        }
        return true;
    }
}
