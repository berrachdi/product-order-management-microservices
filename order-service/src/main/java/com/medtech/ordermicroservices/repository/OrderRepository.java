package com.medtech.ordermicroservices.repository;

import com.medtech.ordermicroservices.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
