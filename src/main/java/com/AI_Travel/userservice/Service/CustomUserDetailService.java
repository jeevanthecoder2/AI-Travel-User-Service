package com.AI_Travel.userservice.Service;

import com.AI_Travel.userservice.Dao.UserRepository;
import com.AI_Travel.userservice.Entities.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        Users user = this.userRepository.findUserByUserName(userName).orElseThrow(()->
                new RuntimeException("User not Found!!!!"));
        if(user==null){
            new RuntimeException("User not Found");

        }
        return user;
    }
}
