package com.tattoo.api_tattoo.domain.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "tb_appointment")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDateTime appointmentDate;
    private String appointmentStatus;

    @ElementCollection
    private List<ConsultationHistory> previousConsultations;
}