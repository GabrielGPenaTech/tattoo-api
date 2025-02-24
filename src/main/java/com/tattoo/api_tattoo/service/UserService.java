package com.tattoo.api_tattoo.service;

import com.tattoo.api_tattoo.controller.dto.UserDTO;
import com.tattoo.api_tattoo.domain.model.User;

import java.util.List;

public interface UserService {

    UserDTO save(UserDTO userDTO);
    UserDTO findById(String id);
    UserDTO update(String id, UserDTO userDTO);
    List<UserDTO> findAll();
    void delete(String id);

}
