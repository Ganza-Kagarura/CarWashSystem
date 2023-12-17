package com.wetechb.CarwashProject.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
//import javax.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "client")
@Getter
@Setter
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, length = 50, name = "clientName")
    private String clientName;
    @Column(nullable = false, unique = true, length = 50, name = "plateNumber")
//    @NotBlank(message = "Plate number is required")
    private String plateNumber;
    @Column(length = 50, name = "carModel")
    private String carModel;
    @Column(nullable = false, unique = true, length = 50,name = "clientEmail")
    private String clientEmail;
    @Column(nullable = false, name = "appointmentDate")
    private LocalDateTime appointmentDate;
    @Column(name = "price")
    private Integer price;
}
