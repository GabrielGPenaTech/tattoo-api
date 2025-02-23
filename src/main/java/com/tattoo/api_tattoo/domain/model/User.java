package com.tattoo.api_tattoo.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity(name = "tb_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String username;
    private String password;
    @Column(unique = true)
    private String email;
    private String fullName;
    private LocalDate birthDate;
    private String gender;
    private String biography;
    private String profilePicture;
    private Boolean active;
}
