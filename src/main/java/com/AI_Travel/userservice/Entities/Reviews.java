package com.AI_Travel.userservice.Entities;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Set;

@Table
@Entity(name = "REVIEWS")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Reviews {
    @Id
    @GeneratedValue
    private long Rid;

    private int rating;

    private String comment;

    private LocalDateTime reviewDateTime;

    @ManyToOne
    @JsonBackReference(value = "Review")
    private Users users;

}
