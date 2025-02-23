package com.tattoo.api_tattoo.domain.repository;

import com.tattoo.api_tattoo.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
    Boolean existsByEmail(String email);
    void deleteByEmail(String email);
}
