package com.AI_Travel.userservice.Entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Table
@Entity(name = "BOOKING")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

    @Id
    @GeneratedValue
    private long bookingId;

    private String bookingState;

//    private String cast;

    private String bookingStatus;

    @ManyToOne
    @JsonBackReference(value = "Booking")
    private Users user;

    @ManyToOne
    @JsonBackReference
    private Trip trip;
}
