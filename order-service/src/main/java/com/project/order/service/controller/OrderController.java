package com.project.order.service.controller;

import com.project.order.service.dto.OrderRequest;
import com.project.order.service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
private final OrderService orderService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String storeOrder(@RequestBody OrderRequest orderRequest){

        orderService.storeOrder(orderRequest);

    return "store "+orderRequest.getName()+"order successfully";
}
}
