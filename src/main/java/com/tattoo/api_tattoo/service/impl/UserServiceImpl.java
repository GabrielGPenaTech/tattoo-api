package com.tattoo.api_tattoo.service.impl;

import com.tattoo.api_tattoo.controller.dto.UserDTO;
import com.tattoo.api_tattoo.domain.model.User;
import com.tattoo.api_tattoo.domain.repository.UserRepository;
import com.tattoo.api_tattoo.service.UserService;
import com.tattoo.api_tattoo.service.exception.BusinessException;
import com.tattoo.api_tattoo.service.mapper.UserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder,
                           UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    @Override
    public UserDTO save(UserDTO userDTO) {
        var userEmailExists = userRepository.existsByEmail(userDTO.getEmail());

        if(userEmailExists) {
            throw new BusinessException("Email already in use");
        }

        var usernameExists = userRepository.existsByUsername(userDTO.getUsername());

        if(usernameExists) {
            throw new BusinessException("Username already in use");
        }

        var passwordEncoded = passwordEncoder.encode(userDTO.getPassword());

        User user = userMapper.toEntity(userDTO);
        user.setPassword(passwordEncoded);
        userRepository.save(user);

        return userMapper.toResponseDTO(user);
    }

    @Override
    public UserDTO findById(String id) {
        User user = userRepository.findById(id);

        if (user == null) {
            throw new BusinessException("User not found");
        }

        return userMapper.toResponseDTO(user);
    }

    @Override
    public UserDTO update(String id, UserDTO userDTO) {
        User userModel = userRepository.findById(id);

        if (userModel == null) {
            throw new BusinessException("User not found");
        }

        if (userDTO.getUsername() != null && !userModel.getUsername().equals(userDTO.getUsername())) {
            var existsByUsername = userRepository.existsByUsername(userDTO.getUsername());

            if (existsByUsername) {
                throw new BusinessException("Username already exists");
            }
        }


        if (userDTO.getPassword() != null) {
            var passwordEncoded = passwordEncoder.encode(userDTO.getPassword());
            userDTO.setPassword(passwordEncoded);
        }

        User user = userMapper.toEntity(userDTO);
        userRepository.save(user);

        return userMapper.toResponseDTO(user);
    }

    @Override
    public List<UserDTO> findAll() {
        List<User> users = userRepository.findAll();
        return users.stream().map(userMapper::toResponseDTO).toList();
    }

    @Override
    public void delete(String id) {
        User user = userRepository.findById(id);

        if (user == null) {
            throw new BusinessException("User not found");
        }

        userRepository.delete(user);
    }
}
