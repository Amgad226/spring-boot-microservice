package com.project.order.service.controller;

import com.project.order.service.dto.OrderRequest;
import com.project.order.service.dto.OrderResponse;
import com.project.order.service.service.OrderService;
import com.project.user.service.dto.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
private final OrderService orderService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<OrderResponse> storeOrder(@Valid @RequestBody OrderRequest orderRequest)  {


        OrderResponse order = orderService.storeOrder(orderRequest);

        return new ResponseEntity <>(order,HttpStatus.CREATED);
    }
}
