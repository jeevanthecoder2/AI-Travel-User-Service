package com.AI_Travel.userservice.Dao;
import com.AI_Travel.userservice.Entities.Users;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Optional;

@EnableJpaRepositories
public interface UserRepository extends JpaRepository<Users,Integer> {
    public Optional<Users> findUserByUserName(final String userName);

    Optional<Users> findUserByUserEmail(final String userEmail);

    Users findUsersByUserType(final String UserType);


}
