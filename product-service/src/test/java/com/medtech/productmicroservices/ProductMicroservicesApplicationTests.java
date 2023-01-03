package com.medtech.productmicroservices;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.medtech.productmicroservices.dto.ProductRequest;
import com.medtech.productmicroservices.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;
import java.util.logging.Logger;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class ProductMicroservicesApplicationTests {

  /*  @Container
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.2");

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private ObjectMapper objectMapper;


    @Autowired
    private ProductRepository productRepository;

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry){
        dynamicPropertyRegistry.add("spring.data.mongodb",
                mongoDBContainer::getReplicaSetUrl);

    }

    @Test
    void shouldCreateProduct() throws Exception {

        ProductRequest productRequest = getProductRequest();
        String productRequestString = objectMapper.writeValueAsString(productRequest);

            mockMvc.perform( MockMvcRequestBuilders.post("/api/products")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(productRequestString)

            ).andExpect(status().isCreated());


        Assertions.assertEquals(7, productRepository.findAll().size());

    }

    @Test
    void shouldReturnAllProduct() throws Exception {



        mockMvc.perform( MockMvcRequestBuilders.get("/api/products")


        ).andExpect(status().isOk());




    }

    private ProductRequest getProductRequest() {
        return ProductRequest.builder()
                .name("Samsung s21")
                .description("This is samsung s21")
                .price(BigDecimal.valueOf(1400))
                .build();
    }*/

}
