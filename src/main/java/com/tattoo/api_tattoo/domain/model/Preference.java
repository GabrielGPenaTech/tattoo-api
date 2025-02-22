package com.tattoo.api_tattoo.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "tb_preference")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Preference {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ElementCollection
    private List<String> favoriteStyles;

    @ElementCollection
    private List<String> favoriteArtists;

    private String preferredLocation;

    @ElementCollection
    private List<TattooHistory> tattooHistory;
}
