package com.security.sample.service;


import com.security.sample.entity.Feedback;
import com.security.sample.repository.FeedBackRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeedbackService {

    @Autowired
    private FeedBackRepo feedbackRepository;

    public Feedback addFeedback(long userId, long categoryId, Feedback feedback) {
        feedback.setUserId(userId);
        feedback.setCategoryId(categoryId);
        return feedbackRepository.save(feedback);
    }

    public List<Object[]> getAllFeedbackWithUserName() {
        return feedbackRepository.findAllFeedbackWithUserName();
    }

//    public void deleteFeedback(Long feedbackId) {
//        Optional<Feedback> feedbackOptional = feedbackRepository.findById(feedbackId);
//        if (feedbackOptional.isPresent()) {
//            Feedback feedback = feedbackOptional.get();
//            // Check if the current user is the owner of the feedback
//            String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
//            if (currentUser.equals(feedback.getUserId().getClass().getName())) {
//                feedbackRepository.deleteById(feedbackId);
//            } else {
//                // Handle unauthorized deletion
//                throw new UnauthorizedException("You are not authorized to delete this feedback.");
//            }
//        } else {
//
//            throw new FeedbackNotFoundException("Feedback not found.");
//        }
//    }

    public void deleteFeedback(Long feedbackId) {
        feedbackRepository.deleteById(feedbackId);
    }

//    public Feedback updateFeedback(Long feedbackId, Feedback updatedFeedback) {
//        Optional<Feedback> existingFeedbackOptional = feedbackRepository.findById(feedbackId);
//        if (existingFeedbackOptional.isPresent()) {
//            Feedback existingFeedback = existingFeedbackOptional.get();
//
//            String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
//            if (currentUser.equals(existingFeedback.getUserId().getClass().getName())) {
//                existingFeedback.setStars(updatedFeedback.getStars());
//                existingFeedback.setComment(updatedFeedback.getComment());
//
//                return feedbackRepository.save(existingFeedback);
//            } else {
//
//                throw new UnauthorizedException("You are not authorized to update this feedback.");
//            }
//        } else {
//            throw new FeedbackNotFoundException("Feedback not found.");
//        }
//    }

    public Feedback updateFeedback(Long feedbackId, Feedback updatedFeedback) {
        Optional<Feedback> existingFeedbackOptional = feedbackRepository.findById(feedbackId);
        if (existingFeedbackOptional.isPresent()) {
            Feedback existingFeedback = existingFeedbackOptional.get();
            existingFeedback.setStars(updatedFeedback.getStars());
            existingFeedback.setComment(updatedFeedback.getComment());
            return feedbackRepository.save(existingFeedback);
        } else {
            return null;
        }
    }
}
