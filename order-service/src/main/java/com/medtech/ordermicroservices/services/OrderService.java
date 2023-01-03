package com.medtech.ordermicroservices.services;

import com.medtech.ordermicroservices.dto.OrderLineItemsDto;
import com.medtech.ordermicroservices.dto.OrderRequest;
import com.medtech.ordermicroservices.models.Order;
import com.medtech.ordermicroservices.models.OrderLineItems;
import com.medtech.ordermicroservices.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    /**
     *  THIS SERVICE METHODE CREATE A ORDER.
     */

    @Transactional
    public void placeOrder(OrderRequest orderRequest){
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(this::mapOrderLineItemsDtoToNormal)
                .collect(Collectors.toList());

        order.setOrderLineItemsList(orderLineItems);

        this.orderRepository.save(order);

    }

    private OrderLineItems mapOrderLineItemsDtoToNormal(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        return orderLineItems;
    }
}
