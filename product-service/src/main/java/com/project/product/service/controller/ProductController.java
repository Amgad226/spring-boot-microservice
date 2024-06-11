package com.project.product.service.controller;

import com.project.product.service.dto.ProductRequest;
import com.project.product.service.dto.ProductResponse;
import com.project.product.service.model.Product;
import com.project.product.service.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
private final ProductService productService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Product> createProduct(@RequestBody ProductRequest req) {
        try {
            Product createdProduct = productService.storeProduct(req);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
        } catch (IllegalArgumentException e) {
            // Handle the exception, e.g., return a 400 Bad Request
            return ResponseEntity.badRequest().body(null);
        }
    }
    @GetMapping("/exists")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> productExists(@RequestParam List<String> productId){
        return productService.productExists(productId) ;
    }

    @Autowired
    Environment environment;


    @GetMapping("/port")
    @ResponseStatus(HttpStatus.OK)
    public String getPort (){
        String port = environment.getProperty("server.port");
        return port;
    }


}
