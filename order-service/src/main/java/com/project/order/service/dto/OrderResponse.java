package com.project.order.service.dto;
import com.project.order.service.model.OrderItems;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private long id;
    private String name;
    private LocalDateTime createdAt;
    private List<OrderItems> orderItemsList;
}
