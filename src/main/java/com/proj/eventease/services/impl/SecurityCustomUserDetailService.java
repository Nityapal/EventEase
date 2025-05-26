package com.proj.eventease.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.proj.eventease.repositories.UserRepo;

@Service
public class SecurityCustomUserDetailService implements UserDetailsService{

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        
        //loading user
        return userRepo.findByemail(email).orElseThrow(()-> new UsernameNotFoundException("User not found with email: "+email));
    }


}
