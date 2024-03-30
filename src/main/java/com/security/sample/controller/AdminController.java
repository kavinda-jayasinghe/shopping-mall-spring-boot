package com.security.sample.controller;

import com.security.sample.entity.Category;
import com.security.sample.entity.User;
import com.security.sample.service.CategoryService;


import com.security.sample.service.serviceImpl.UserServiceImpl;
import com.security.sample.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/admin")
public class AdminController {


//    @GetMapping({"/forAdmin"})
//    @PreAuthorize("hasRole('Admin')")
//    public String forAdmin() {
//
//        return "This URL is only accessible to the admin";
//    }


    //CATEGORY
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/get-all-category")
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable("id") long id) {
        Optional<Category> category = categoryService.getCategoryById(id);
        return category.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/addCategory")
    public ResponseEntity<Category> addCategory(@RequestBody Category category) {
        Category addedCategory = categoryService.addCategory(category);
        return new ResponseEntity<>(addedCategory, HttpStatus.CREATED);
    }

    @PutMapping("/update-category/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable("id") long id, @RequestBody Category updatedCategory) {
        Category category = categoryService.updateCategory(id, updatedCategory);
        if (category != null) {
            return new ResponseEntity<>(category, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete-category/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable("id") long id) {
        boolean deleted = categoryService.deleteCategory(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //get all
    @GetMapping("/get-all-users")
    public ResponseEntity<StandardResponse> getAllUsers(){
        List<User> userList = userService.getAllUsers();
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"success",userList),
                HttpStatus.OK
        );
    }

    //delete user
    @DeleteMapping("/users/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable int userId) {
        try {
            userService.deleteUserById(userId);
            return new ResponseEntity<>("User with ID " + userId + " has been deleted", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            // Handle case where user with given ID is not found
            return new ResponseEntity<>("User with ID " + userId + " not found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            // Handle other exceptions
            return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
}
}

