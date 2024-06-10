package com.project.order.service.service;

import com.project.order.service.dto.OrderItemsRequest;
import com.project.order.service.dto.OrderRequest;
import com.project.order.service.dto.OrderResponse;
import com.project.order.service.model.Order;
import com.project.order.service.model.OrderItems;
import com.project.order.service.repository.OrderRepository;
import com.project.product.service.dto.ProductResponse;
import com.project.user.service.dto.UserResponse;
import com.project.user.service.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;
    public OrderResponse storeOrder(OrderRequest req){
        Order order = new Order();
        order.setCreatedAt(LocalDateTime.now());
        String userId = req.getUserId();
        UserResponse user = webClientBuilder.build()
                .get()
                .uri("http://user-service/api/users/" + userId) // Correct URI construction
                .retrieve()
                .bodyToMono(UserResponse.class) // Correctly mapping to UserResponse
                .block();

        if (user == null || user.getRole() == null || user.getRole().isEmpty()) {
            throw new IllegalArgumentException("User ID is not valid or user does not have a role.");
        }
        order.setUserId(req.getUserId());
        order.setName(user.getRole());

        List<OrderItems> orderItems = req.getOrderItemsList()
                .stream()
                .map(this::mapToDto)
                .toList();

        order.setOrderItemsList(orderItems);

        List<String> productIds = order.getOrderItemsList().stream()
                .map(OrderItems::getProductId)
                .toList();
        ProductResponse[] productResponses = webClientBuilder.build().get()
                .uri("http://product-service/api/products/exists",
                        uriBuilder -> uriBuilder.queryParam("productId",productIds).build())
                .retrieve()
                .bodyToMono(ProductResponse[].class)
                .block();
        boolean allProductFounded= Arrays.stream(productResponses)
                .allMatch(ProductResponse::isFound);
        if(allProductFounded){
            orderRepository.save(order);

        }else{
            throw new IllegalArgumentException("Product is not found, please try again later");
        }
        return mapToOrderResponse(order);
    }
    private OrderItems mapToDto(OrderItemsRequest orderItemsRequest) {
        OrderItems orderItems = new OrderItems();
        orderItems.setProductId(orderItemsRequest.getProductId());
        orderItems.setQuantity(orderItemsRequest.getQuantity());
        return orderItems;
    }
    private OrderResponse mapToOrderResponse(Order order) {
        return OrderResponse.builder()
                .id(order.getId())
                .name(order.getName())
                .createdAt(order.getCreatedAt())
                .orderItemsList(order.getOrderItemsList())
                .build();
    }

}
