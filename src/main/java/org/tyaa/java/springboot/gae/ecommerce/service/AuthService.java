/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tyaa.java.springboot.gae.ecommerce.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tyaa.java.springboot.gae.ecommerce.dao.RoleDAO;
import org.tyaa.java.springboot.gae.ecommerce.dao.UserDAO;
import org.tyaa.java.springboot.gae.ecommerce.entity.Role;
import org.tyaa.java.springboot.gae.ecommerce.entity.User;
import org.tyaa.java.springboot.gae.ecommerce.model.JsonHttpResponse;
import org.tyaa.java.springboot.gae.ecommerce.model.RoleModel;
import org.tyaa.java.springboot.gae.ecommerce.model.UserModel;

/**
 *
 * @author yurii
 */
@Service
public class AuthService {
    
    @Autowired
    private RoleDAO roleDAO;
    
    @Autowired
    private UserDAO userDAO;

    public JsonHttpResponse createRole(RoleModel _roleModel) {
        
        Role role = new Role(_roleModel.name);
        roleDAO.create(role);
        return new JsonHttpResponse(
                JsonHttpResponse.createdStatus
                , "Role '" + _roleModel.name + "' created successfully"
        );
    }
    
    // Создание пользователя с ролью по умолчанию
    public JsonHttpResponse createUser(UserModel _userModel) throws Exception {
	
        // Получаем из хранилища объект роли по умолчанию
        Role role = roleDAO.read("user");
        // Создаем пользователя с заданными именем и паролем и
        // со ссылкой на объект роли по умолчанию
        userDAO.create(
            new User(
                    _userModel.name
                    , _userModel.password
                    , role
                )
            );
        return new JsonHttpResponse(
                JsonHttpResponse.createdStatus
                , "User '" + _userModel.name + "' created successfully"
        );
    }

    public JsonHttpResponse<List<RoleModel>> readRole() {
        
        List<Role> roles = roleDAO.read();
        List<RoleModel> roleModels =
            roles.stream()
            .map((r) -> {
                return new RoleModel(r.getId(), r.getName());
            })
            .collect(Collectors.toList());
        return new JsonHttpResponse(
                JsonHttpResponse.fetchedStatus
                , "The roles list fetched successfully"
                , roleModels
        );
    }
    
    public JsonHttpResponse<List<UserModel>> readUser() {
        
        List<User> users = userDAO.read();
        List<UserModel> userModels =
            users.stream()
            .map((u) -> {
                return new UserModel(
                    u.getId()
                    , u.getName()
                    , u.getPassword()
                    , new RoleModel(
                        u.getRole().getId()
                        , u.getRole().getName()
                    )
                );
            })
            .collect(Collectors.toList());
        return new JsonHttpResponse(
                JsonHttpResponse.fetchedStatus
                , "The users list fetched successfully"
                , userModels
        );
    }

    public JsonHttpResponse<RoleModel> readRole(Long _id) throws Exception {
        
        Role role =
                roleDAO.read(_id);
        String status =
                (role != null && role.getId() != null)
                ? JsonHttpResponse.fetchedStatus
                : JsonHttpResponse.warningStatus;
        String message =
                (role != null && role.getId() != null)
                ? "The role fetched successfully"
                : "Not found";
        RoleModel roleModel = new RoleModel(role.getId(), role.getName());
        return new JsonHttpResponse(
                status
                , message
                , roleModel
        );
    }
    
    public JsonHttpResponse<UserModel> readUser(Long _id) throws Exception {
        
        User user =
                userDAO.read(_id);
        String status =
            (user != null && user.getId() != null)
            ? JsonHttpResponse.fetchedStatus
            : JsonHttpResponse.warningStatus;
        String message =
            (user != null && user.getId() != null)
            ? "The user fetched successfully"
            : "Not found";
        UserModel userModel = null;
        if (user != null && user.getId() != null) {
            userModel =
                new UserModel(
                    user.getId()
                    , user.getName()
                    , user.getPassword()
                    , new RoleModel(
                        user.getRole().getId()
                        , user.getRole().getName()
                    )
                );
        }
        return new JsonHttpResponse(
                status
                , message
                , userModel
        );
    }
    
    public JsonHttpResponse<UserModel> readUser(String _name) throws Exception {
        
        User user =
                userDAO.read(_name);
        String status =
            (user != null && user.getId() != null)
            ? JsonHttpResponse.fetchedStatus
            : JsonHttpResponse.warningStatus;
        String message =
            (user != null && user.getId() != null)
            ? "The user fetched successfully"
            : "Not found";
        UserModel userModel = null;
        if (user != null && user.getId() != null) {
            userModel =
                new UserModel(
                    user.getId()
                    , user.getName()
                    , user.getPassword()
                    , new RoleModel(
                        user.getRole().getId()
                        , user.getRole().getName()
                    )
                );
        }
        return new JsonHttpResponse(
                status
                , message
                , userModel
        );
    }

    /* public JsonHttpResponse read(String _name) throws Exception {
        
            Author author =
                    authorDAO.read(_name);
            String status =
                    (author != null && author.getId() != null)
                    ? JsonHttpResponse.fetchedStatus
                    : JsonHttpResponse.warningStatus;
            String message =
                    (author != null && author.getId() != null)
                    ? "The author fetched successfully"
                    : "Not found";
            return new JsonHttpResponse(
                    status
                    , message
                    , author
            );
    } */

    /* public JsonHttpResponse update(Author author) {
        
        authorDAO.update(author);
        return new JsonHttpResponse(
                JsonHttpResponse.updatedStatus
                , "The author updated successfully"
        );
    } */

    public JsonHttpResponse deleteRole(Long _id) {
        
        roleDAO.delete(_id);
        return new JsonHttpResponse(
                JsonHttpResponse.deletedStatus
                , "The role deleted successfully"
        );
    }
    
    public JsonHttpResponse deleteUser(Long _id) {
        
        userDAO.delete(_id);
        return new JsonHttpResponse(
                JsonHttpResponse.deletedStatus
                , "The user deleted successfully"
        );
    }
}
