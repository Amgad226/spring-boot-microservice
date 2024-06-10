package com.project.order.service.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRequest {

//    @Size(min = 2, message = "name should have at least 2 characters")
//    private String name;
    @NotEmpty
    private List<OrderItemsRequest> orderItemsList;

    @NotEmpty
    private String userId;

}
