package com.AI_Travel.userservice.Entities;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Set;

@Table
@Entity(name = "NOTIFICATION")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Notification {

    @Id
    @GeneratedValue
    private long Nid;

    private String Message;

    private LocalDateTime notificationDateTime;

    private String status;

    @ManyToOne
    @JsonBackReference(value = "Notification")
    private Users users;
}
