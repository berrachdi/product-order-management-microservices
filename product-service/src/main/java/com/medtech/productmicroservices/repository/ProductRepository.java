package com.medtech.productmicroservices.repository;

import com.medtech.productmicroservices.models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends
        MongoRepository<Product, String> {
}
