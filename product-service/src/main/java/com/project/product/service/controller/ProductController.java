package com.project.product.service.controller;

import com.project.product.service.dto.ProductRequest;
import com.project.product.service.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
private final ProductService productService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String storeProduct(@Valid @RequestBody ProductRequest productRequest){

        productService.storeProduct(productRequest);

        return "store "+productRequest.getName()+"product successfully";
    }

    @GetMapping("/exists/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public Boolean productExists(@PathVariable("productId") String productId){
        // check if exists in database
        return true ;

    }
}
