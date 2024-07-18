package com.AI_Travel.userservice.Entities;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Set;

@Table
@Entity(name = "SEARCHHISTORY")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SearchHistory {

    @Id
    @GeneratedValue
    private long Sid;

    private String searchQuery;

    private LocalDateTime searchDateTime;

    private String resultsReturned;

    @ManyToOne
    @JsonBackReference(value = "SearchHistory")
    private Users users;
}
