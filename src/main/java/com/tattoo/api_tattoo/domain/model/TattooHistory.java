package com.tattoo.api_tattoo.domain.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
class TattooHistory {
    private LocalDate date;
    private String style;
    private String artist;
    private String location;
}