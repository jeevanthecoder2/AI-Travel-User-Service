package com.AI_Travel.userservice.Entities;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Set;

@Table
@Entity(name = "FEEDBACK")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Feedback {

    @Id
    @GeneratedValue
    private long Fid;

    private String feedbackText;
    private LocalDateTime feedbackDateTime;
    private String response;

    @ManyToOne
    @JsonBackReference(value = "Feedback")
    private Users users;
}
