package com.tattoo.api_tattoo.service;

import com.tattoo.api_tattoo.domain.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    User save(User user);
    User findByEmail(String email);
    User update(User user);
    List<User> findAll();
    Boolean existsByEmail(String email);
    void deleteByEmail(String email);

}
