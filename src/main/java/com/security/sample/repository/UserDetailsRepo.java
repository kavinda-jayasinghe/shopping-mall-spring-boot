package com.security.sample.repository;

import com.security.sample.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDetailsRepo extends JpaRepository<UserDetails, Long> {


//    @Query("SELECT u.email, u.firstName, u.lastName, ud.userId, ud.contact, ud.image, ud.address " +
//            "FROM User u " +
//            "JOIN UserDetails ud ON u.id = ud.userId " +
//            "WHERE ud.userId = :userId")
//    UserDetails findByUserId(int userId);
}
