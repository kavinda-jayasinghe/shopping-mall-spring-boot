package com.security.sample.repository;

import com.security.sample.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedBackRepo extends JpaRepository<Feedback,Long> {

    @Query("SELECT f, u.firstName, u.lastName FROM Feedback f JOIN User u ON f.userId = u.id")
    List<Object[]> findAllFeedbackWithUserName();

}
