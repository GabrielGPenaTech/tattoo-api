package com.tattoo.api_tattoo.service;

import com.tattoo.api_tattoo.controller.dto.UserDTO;
import com.tattoo.api_tattoo.domain.model.User;

import java.util.List;

public interface UserService {

    User save(UserDTO userDTO);
    User findById(String id);
    User update(String id, User user);
    List<User> findAll();
    void delete(String id);

}
