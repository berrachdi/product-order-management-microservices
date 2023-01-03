package com.medtech.ordermicroservices.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "t_orders")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String orderNumber;

    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderLineItems> orderLineItemsList = new java.util.ArrayList<>();
}
