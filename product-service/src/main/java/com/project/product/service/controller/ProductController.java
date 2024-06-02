package com.project.product.service.controller;

import com.project.product.service.dto.ProductRequest;
import com.project.product.service.service.ProductService;
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
    public String storeProduct(@RequestBody ProductRequest productRequest){

    productService.storeProduct(productRequest);

    return "store "+productRequest.getName()+"product successfully";
}
}
