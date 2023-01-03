package com.medtech.productmicroservices.services;

import com.medtech.productmicroservices.dto.ProductRequest;
import com.medtech.productmicroservices.dto.ProductResponse;
import com.medtech.productmicroservices.models.Product;
import com.medtech.productmicroservices.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 *  THIS CLASS USED TO IMPLEMENT A BUSINESS LOGIQUE FOR A PRODUCT SERVICES.
 *  USED AS A DEPEDENCY PRODEUCTREPOSITORY INTERFACE TO INTRACT WITH THE MONGODB DATABASE,
 *  THIS DEPENDECY MANAGED BY SPRING IoC.
 */
@RequiredArgsConstructor
@Service
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;

    /**
     * @Description THIS METHODE USED TO CREATE A NEW PRODUCT AND TO ADD IT INTO THE DATABASE
     * @param productRequest
     */
    public void createProduct(ProductRequest productRequest) {
        Product product = this.mapProductRequestToProduct(productRequest);

        productRepository.save(product);
        log.info("Product {} is saved", product.getId());
    }


    /**
     *
     * @return List<ProductResponse>
     */
    public List<ProductResponse> getAllProducts() {
        List<Product> listOfProducts = productRepository.findAll();
        return listOfProducts.stream().map(this::mapProductToProductResponse).collect(Collectors.toList());
    }

    /**
     *
     * @param product
     * @return ProductResponse
     */
    private ProductResponse mapProductToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }

    /**
     *
     * @param productRequest
     * @return Product
     */
    private Product mapProductRequestToProduct(ProductRequest productRequest) {
        return Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();
    }
}
