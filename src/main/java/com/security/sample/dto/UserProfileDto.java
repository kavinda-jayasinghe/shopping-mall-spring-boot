package com.security.sample.dto;

import com.security.sample.entity.User;
import com.security.sample.entity.UserDetails;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserProfileDto {

    private User user;
    private UserDetails userDetails;

}
