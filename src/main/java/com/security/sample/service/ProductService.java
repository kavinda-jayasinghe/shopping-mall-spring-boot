package com.security.sample.service;


import com.security.sample.entity.Product;
import com.security.sample.exception.NotFoundException;
import com.security.sample.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public Optional<Product> findByProductName(String productName) {
        return productRepository.findByProductName(productName);
    }



    public String productUpdate(Product product, long id) {
        if (productRepository.existsById(id)) {
            productRepository.updateProgram(
                    product.getProductName(),
                    product.getPrice(),
                    product.getDescription(),
                    product.getImageUrl(),
                    id);
            return "Program with ID: " + id + " has been updated.";
        } else {
            return "Program with ID: " + id + " does not exist.";
        }
    }


    public boolean productDelete(long id) {
        if(productRepository.existsById(id)){
            productRepository.deleteById(id);
            return true;
        }else{
            throw new NotFoundException("not found customer for this id");
        }
    }
}