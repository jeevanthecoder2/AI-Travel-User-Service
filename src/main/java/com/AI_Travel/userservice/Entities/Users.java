package com.AI_Travel.userservice.Entities;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import jakarta.validation.constraints.Pattern;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

@Table
@Entity(name = "USERS")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Users implements UserDetails{

    @Id
    @GeneratedValue
    private long userId;

//    private long userId;

    @Column(unique=true)
    private String userName;
    private String userPassword;

    @Column(unique = true)
    @Pattern(regexp = "[A-Za-z0-9._]+@[A-Za-z0-9]+\\.[A-Z|a-z]{2,}")
    private String userEmail;

    @Column(unique = true)
    @Pattern(regexp = "\\+\\{12}")
    private String userPhone;

    private String userPreferences;

    private String userType;

    private String OTP;
    private LocalDateTime otpGeneratedTime;
    private boolean isVerified=false;
    private boolean isLoggedIn=false;
    private LocalDateTime loggedInTime;


    //    private Date userJoinDate;
    @OneToMany()
    @JsonManagedReference(value = "Trip")
    private Set<Trip> trips;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonManagedReference(value = "Booking")
    private Set<Booking> bookings;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonManagedReference(value = "Review")
    private Set<Reviews> reviews;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonManagedReference(value = "SearchHistory")
    private Set<SearchHistory> searchHistories;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonManagedReference(value = "Feedback")
    private Set<Feedback> feedbacks;

    @OneToMany()
    @JsonManagedReference(value = "Notification")
    private Set<Notification> notifications;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.userPassword;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.isVerified();
    }
}
