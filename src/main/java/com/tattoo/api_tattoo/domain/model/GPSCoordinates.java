package com.tattoo.api_tattoo.domain.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
class GPSCoordinates {
    private float latitude;
    private float longitude;
}
