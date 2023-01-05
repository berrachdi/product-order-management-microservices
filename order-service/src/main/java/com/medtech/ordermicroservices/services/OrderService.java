package com.medtech.ordermicroservices.services;

import com.medtech.ordermicroservices.dto.InventoryResponse;
import com.medtech.ordermicroservices.dto.OrderLineItemsDto;
import com.medtech.ordermicroservices.dto.OrderRequest;
import com.medtech.ordermicroservices.models.Order;
import com.medtech.ordermicroservices.models.OrderLineItems;
import com.medtech.ordermicroservices.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final WebClient webClient;

    /**
     * @description This methode to place an order into the database
     * @param orderRequest
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

        List<String> skuCodesList = order.getOrderLineItemsList().stream()
                .map(OrderLineItems::getSkuCode)
                .collect(Collectors.toList());

        // CALL A INVENTORY SERVICE, AND PLACE ORDER IF PRODUCT IS IN STOCK
        InventoryResponse[] inventoryResponseArray = webClient.get()
                                            .uri("http://localhost:8082/api/inventory", uriBuilder ->
                                                    uriBuilder.queryParam("skuCode",skuCodesList).build())
                                            .retrieve()
                                            .bodyToMono(InventoryResponse[].class)
                                            .block();


        Boolean isAllProductsInStock = Arrays.stream(inventoryResponseArray)
                .allMatch(InventoryResponse::isInStock);
        if(isAllProductsInStock){
            this.orderRepository.save(order);
        }else{
            throw new IllegalArgumentException("Product is not in stock, please try again");
        }


    }


    /**
     * @description This methode used to mapped between a order item dto and order item
     * @param orderLineItemsDto
     * @return
     */
    private OrderLineItems mapOrderLineItemsDtoToNormal(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        return orderLineItems;
    }
}
