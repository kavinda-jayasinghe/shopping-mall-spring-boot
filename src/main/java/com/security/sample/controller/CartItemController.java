package com.security.sample.controller;

import com.security.sample.entity.CartItem;
import com.security.sample.entity.Product;
import com.security.sample.service.CartItemService;
import com.security.sample.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cart-items")
public class CartItemController {

    @Autowired
    private CartItemService cartItemService;
    @Autowired
    private ProductService productService;


    //ADMIN CUSTOMER THEATER_OWNER
    @PostMapping("/add/{userID}")
    public CartItem addCartItem(@RequestBody CartItem cartItem, @PathVariable long userID) {
        return cartItemService.addCartItem(cartItem, userID);
    }

    //get Product from cart

    @GetMapping("/userCart/{userId}")
    public ResponseEntity<List<Product>> getProductsInUserCart(@PathVariable long userId) {
        List<Product> products = productService.getProductsInUserCart(userId);
        return ResponseEntity.ok(products);
    }

    //CUSTOMER
    @PutMapping("/update/{userID}")
    public CartItem updateCartItem(@PathVariable Long userID, @RequestBody CartItem cartItem) {
        return cartItemService.updateCartItem(userID, cartItem);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCartItem(@PathVariable Long id) {
        cartItemService.deleteCartItem(id);
    }


}