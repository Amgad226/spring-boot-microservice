package com.project.product.service.service;

import com.project.product.service.dto.ProductRequest;
import com.project.product.service.dto.ProductResponse;
import com.project.product.service.model.Product;
import com.project.product.service.repository.ProductRepository;
import com.project.user.service.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final WebClient.Builder webClientBuilder;

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);
    public Product storeProduct(ProductRequest req){
        Product product  = new Product();
        product.setName(req.getName());

        product.setPrice(req.getPrice());
        product.setUserId(req.getUserId());
        product.setQuantity(req.getQuantity());
        product.setCreatedAt(LocalDateTime.now());
        String userId = req.getUserId();
        UserResponse result = webClientBuilder.build()
                .get()
                .uri("http://user-service/api/users/" + userId)
                .retrieve()
                .bodyToMono(UserResponse.class)
                .block();

        if (result == null || result.getRole() == null || result.getRole().isEmpty()) {
            System.out.println("not found user in user service ");
            throw new IllegalArgumentException("User ID is not valid or user does not have a role.");
        }

        product.setByRole(result.getRole());
        return productRepository.save(product);
    }
    public List<ProductResponse> productExists(List<String> productIds) {
        logger.info("Checking existence for product IDs: {}", productIds);
        return productIds.stream()
                .map(this::findProductById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(product -> ProductResponse.builder()
                        .productId(product.getId().toString())
                        .isFound(product.getQuantity() > 0)
                        .build())
                .collect(Collectors.toList());
    }
    private Optional<Product> findProductById(String id) {
        logger.info("Finding product by ID: {}", id);
        Optional<Product> product = productRepository.findById(Long.parseLong(id));
        if (product.isPresent()) {
            logger.info("Product found: {}", product.get());
        } else {
            logger.warn("Product not found for ID: {}", id);
            throw new IllegalArgumentException("Product not found.");
        }
        return product;
    }

}
