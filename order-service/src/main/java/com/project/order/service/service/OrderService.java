package com.project.order.service.service;

import com.project.order.service.dto.OrderRequest;
import com.project.order.service.model.Order;
import com.project.order.service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;
    public void storeOrder(OrderRequest req){
        Order order  = new Order();
        order.setName(req.getName());
        order.setProductId(req.getProductId());

        // call product service to check if the product id is exists or throw an error
       Boolean result=  webClientBuilder.build()
                .get()
                .uri("http://product.service/api/products/exists/"+req.getProductId())
                .retrieve()
                .bodyToMono(Boolean.class)// castomize the response interface
                .block();
       if(!result){
           throw new IllegalArgumentException("product id is not valid");
       }
        orderRepository.save(order);
    }

}
