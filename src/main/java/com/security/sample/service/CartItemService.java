package com.security.sample.service;

import com.security.sample.entity.CartItem;
import com.security.sample.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    public List<CartItem> getAllCartItems() {
        return cartItemRepository.findAll();
    }

    public CartItem getCartItemById(Long userID) {
        return cartItemRepository.findById(userID)
                .orElseThrow(() -> new RuntimeException("CartItem not found with id: " + userID));
    }

    public CartItem addCartItem(CartItem cartItem,long userID) {
        cartItem.setUserID(userID);
        return cartItemRepository.save(cartItem);
    }

    public CartItem updateCartItem(Long userID, CartItem cartItem) {

        getCartItemById(userID);


        return cartItemRepository.save(cartItem);
    }

    public void deleteCartItem(Long id) {
        getCartItemById(id);
        cartItemRepository.deleteById(id);
    }
}