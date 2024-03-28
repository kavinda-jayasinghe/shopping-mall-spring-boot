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

    public CartItem getCartItemById(Long id) {
        return cartItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CartItem not found with id: " + id));
    }

    public CartItem addCartItem(CartItem cartItem) {
        // You may add some validation or business logic here before saving
        return cartItemRepository.save(cartItem);
    }

    public CartItem updateCartItem(Long id, CartItem cartItem) {
        // Check if cartItem with given id exists
        getCartItemById(id);
        // You may add some validation or business logic here before updating
        cartItem.setId(id);
        return cartItemRepository.save(cartItem);
    }

    public void deleteCartItem(Long id) {
        // Check if cartItem with given id exists
        getCartItemById(id);
        cartItemRepository.deleteById(id);
    }
}