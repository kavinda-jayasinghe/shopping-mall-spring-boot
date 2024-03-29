package com.security.sample.service;

import com.security.sample.entity.CartItem;
import com.security.sample.repository.CartItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;


    public CartItem getCartItemById(Long userID) {
        return cartItemRepository.findById(userID)
                .orElseThrow(() -> new RuntimeException("CartItem not found with id: " + userID));
    }

    public CartItem addCartItem(CartItem cartItem,long userID) {
        cartItem.setUserID(userID);
        return cartItemRepository.save(cartItem);
    }


}