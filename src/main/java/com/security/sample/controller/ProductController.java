package com.security.sample.controller;
import com.security.sample.entity.Product;
import com.security.sample.service.ProductService;
import com.security.sample.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/get-all-products")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> productList = productService.findAll();
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @PostMapping("/post-product")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product savedProduct = productService.save(product);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    @GetMapping("/get-product-by-name/{productName}")
    public ResponseEntity<Product> getProductByName(@PathVariable String productName) {
        Optional<Product> productOptional = productService.findByProductName(productName);
        return productOptional.map(product -> new ResponseEntity<>(product, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping(path={"/update/{id}"})

    public ResponseEntity<StandardResponse> updateProgram(
            @RequestBody Product product,
            @PathVariable(value = "id") int id){
        String updated=productService.productUpdate(product,id);

        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201,id+"Programm successfully saved",updated),
                HttpStatus.CREATED
        );
    }


    @DeleteMapping(
            path={"/delete-product/{id}"}
    )
    public String deleteProduct( @PathVariable(value = "id") long id) throws ChangeSetPersister.NotFoundException {

        boolean product=productService.productDelete(id);
        return "deleted";
    }
}