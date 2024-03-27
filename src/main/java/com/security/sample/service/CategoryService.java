package com.security.sample.service;

import com.security.sample.entity.Category;
import com.security.sample.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Optional<Category> getCategoryById(long id) {
        return categoryRepository.findById(id);
    }

    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category updateCategory(long id, Category updatedCategory) {
        if (categoryRepository.existsById(id)) {
            updatedCategory.setCategoryId(id);
            return categoryRepository.save(updatedCategory);
        } else {
            return null; // or throw an exception
        }
    }

    public boolean deleteCategory(long id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
            return true;
        } else {
            return false; // or throw an exception
        }
    }
}