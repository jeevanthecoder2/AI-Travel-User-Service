package com.AI_Travel.userservice.Service;

import com.AI_Travel.userservice.Dao.UserRepository;
import com.AI_Travel.userservice.Entities.Users;
import com.AI_Travel.userservice.Util.EmailUtil;
import com.AI_Travel.userservice.Util.OTPutil;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;


@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OTPutil otPutil;

    @Autowired
    private EmailUtil emailUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Users createUser(Users user) throws MessagingException {
        String OTP = otPutil.generateOtp();
        emailUtil.sendotpEmail(user.getUserEmail(), OTP);
        user.setUserPassword(passwordEncoder.encode(user.getPassword()));
//        user.setUserASeller(false);
        user.setOTP(OTP);
        user.setOtpGeneratedTime(LocalDateTime.now());
        return userRepository.save(user);
    }
    public void ConfirmLogin(String userName){

        Users user = this.userRepository.findUserByUserName(userName).orElseThrow(()->
                new RuntimeException("User Not Found!!!!"));
        user.setLoggedIn(true);
        user.setLoggedInTime(LocalDateTime.now());
        this.userRepository.save(user);

    }
    public void Logout(String userName){
        Users user = this.userRepository.findUserByUserName(userName).orElseThrow(()->
                new RuntimeException("User Not Found!!!!"));
        user.setLoggedIn(false);
        user.setLoggedInTime(null);
        this.userRepository.save(user);
    }

    public String verifyAccount(String email, String otp) {
        Users user = userRepository.findUserByUserEmail(email).orElseThrow(()
                -> new RuntimeException("User not found with this email : "+email));

        if(user.getOTP().equals(otp) && Duration.between(user.getOtpGeneratedTime(),LocalDateTime.now()).getSeconds() < (1*60)){
            user.setVerified(true);
            userRepository.save(user);
            return "OTP verified you can login";
        }

        return "Please regenerate OTP and try again";

    }

    public String regenerateOTP(String email) {
        Users user = userRepository.findUserByUserEmail(email).orElseThrow(()
                -> new RuntimeException("User not found with this email : "+email));

        String OTP = otPutil.generateOtp();
        try{
            emailUtil.sendotpEmail(email,OTP);

        }catch (MessagingException ex){
            throw new RuntimeException("Unable to send OTP please try again");
        }

        user.setOTP(OTP);
        user.setOtpGeneratedTime(LocalDateTime.now());
        userRepository.save(user);
        return "Email sent...Please verify account within 1 minute";


    }

}
