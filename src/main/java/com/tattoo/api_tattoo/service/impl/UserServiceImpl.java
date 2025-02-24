package com.tattoo.api_tattoo.service.impl;

import com.tattoo.api_tattoo.controller.dto.UserDTO;
import com.tattoo.api_tattoo.domain.model.User;
import com.tattoo.api_tattoo.domain.repository.UserRepository;
import com.tattoo.api_tattoo.service.UserService;
import com.tattoo.api_tattoo.service.exception.BusinessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User save(UserDTO userDTO) {
        var userEmailExists = userRepository.existsByEmail(userDTO.getEmail());

        if(userEmailExists) {
            throw new BusinessException("Email already in use");
        }

        var usernameExists = userRepository.existsByUsername(userDTO.getUsername());

        if(usernameExists) {
            throw new BusinessException("Username already in use");
        }

        User user = new User();
        var passwordEncoded = passwordEncoder.encode(userDTO.getPassword());

        user.setEmail(userDTO.getEmail());
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoded);

        return userRepository.save(user);
    }

    @Override
    public User findById(String id) {
        return userRepository.findById(id);
    }

    @Override
    public User update(String id, User user) {
        User userModel = userRepository.findById(id);

        if (userModel == null) {
            throw new BusinessException("User not found");
        }

        var existUsername = userRepository.existsByUsername(user.getUsername());

        if(existUsername) {
            throw new BusinessException("Username exists");
        }

        if (user.getPassword() != null) {
            var passwordEncoded = passwordEncoder.encode(user.getPassword());
            user.setPassword(passwordEncoded);
        }

        userModel.setUsername(user.getUsername());
        userModel.setPassword(user.getPassword());
        userModel.setBiography(user.getBiography());
        userModel.setBirthDate(user.getBirthDate());
        userModel.setGender(user.getGender());
        userModel.setProfilePicture(user.getProfilePicture());
        return userRepository.save(userModel);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void delete(String id) {
        User user = userRepository.findById(id);

        if (user == null) {
            throw new BusinessException("User not found");
        }

        userRepository.save(user);
    }
}
