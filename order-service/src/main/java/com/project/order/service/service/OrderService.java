package com.project.order.service.service;

import com.project.order.service.dto.OrderRequest;
import com.project.order.service.model.Order;
import com.project.order.service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    public void storeOrder(OrderRequest req){
        Order order  = new Order();
        order.setName(req.getName());

        orderRepository.save(order);
    }

}
