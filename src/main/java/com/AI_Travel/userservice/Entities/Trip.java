package com.AI_Travel.userservice.Entities;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.Set;

@Table
@Entity(name = "TRIP")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Trip {

    @Id
    @GeneratedValue
    private long tripId;

    private String tripName;

    private Date startDate;

    private Date endDate;

    private long budget;

    private String tripPreferences;

    private String tripStatus;


    @ManyToOne
    @JsonBackReference(value = "Trip")
    private Users user;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Booking> bookings;


}
