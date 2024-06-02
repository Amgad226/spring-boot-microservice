package com.project.order.service.controller;

import com.project.order.service.dto.OrderRequest;
import com.project.order.service.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
private final OrderService orderService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> storeOrder(@Valid @RequestBody OrderRequest orderRequest)  {


        orderService.storeOrder(orderRequest);

        return new ResponseEntity <>("store " + orderRequest.getName() + "order successfully",HttpStatus.CREATED);
    }
}
