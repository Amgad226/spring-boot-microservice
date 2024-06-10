package com.project.order.service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id ;
    private String name ;
    private String userId;
    private LocalDateTime createdAt;
    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderItems> orderItemsList;

}
