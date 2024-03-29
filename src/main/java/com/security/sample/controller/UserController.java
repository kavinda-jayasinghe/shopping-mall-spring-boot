package com.security.sample.controller;

import com.security.sample.dto.UserProfileDto;
import com.security.sample.entity.UserDetails;
import com.security.sample.service.UserDetailsService;
import com.security.sample.util.StandardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
@Autowired
private UserDetailsService userDetailsService;

    @GetMapping("get-user-details")
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("Hi user");
    }

    //Add USER details to profile
    @PostMapping(path = "/user-profile/{id}")
    public ResponseEntity<StandardResponse> userSave(@RequestBody UserDetails userDetails,@PathVariable long id){
        String userId= userDetailsService.userProfile(userDetails,id);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201,id+" user details successfully saved ",id),
                HttpStatus.CREATED
        );
    }


}
