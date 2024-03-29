package com.security.sample.service;

import com.security.sample.dto.UserProfileDto;
import com.security.sample.entity.User;
import com.security.sample.entity.UserDetails;
import com.security.sample.exception.NotFoundException;
import com.security.sample.repository.UserDetailsRepo;
import com.security.sample.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService {
    @Autowired
    private UserDetailsRepo userDetailsRepo;
    @Autowired
    private UserRepository userRepository;

    public String userProfile(UserDetails userDetails, long id) {
        UserDetails userDetails1 = new UserDetails();

        userDetails1.setAddress(userDetails.getAddress());
        userDetails1.setContact(userDetails.getContact());
        userDetails1.setImage(userDetails.getImage());
        userDetails1.setUserId(id);


        if (!userDetailsRepo.existsById(id)) {

            userDetailsRepo.save(userDetails1);
            return userDetails1.getId() + " saved";
        } else {
            return "program id already exists";
        }
    }

//    public UserProfileDto getUserProfile(int id) {
//  User user=userRepository.findById(id);
//  UserDetails userDetails=userDetailsRepo.findByUserId(id);
//
//    }



}