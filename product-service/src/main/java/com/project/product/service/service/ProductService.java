package com.project.product.service.service;

import com.project.product.service.dto.ProductRequest;
import com.project.product.service.model.Product;
import com.project.product.service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    public void storeProduct(ProductRequest req){
        Product product  = new Product();
        product.setName(req.getName());

        productRepository.save(product);
    }

}
