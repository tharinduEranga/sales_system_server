package com.icbt.ap.sales;

import com.icbt.ap.sales.entity.Product;
import com.icbt.ap.sales.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@Slf4j
@SpringBootTest
class SalesSystemServerApplicationTests {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void contextLoads() {

    }

    @Test
    void testGetProduct() {
        final Optional<Product> optionalProduct = productRepository.findById("1");
        log.info("Searched Product: {}",
                optionalProduct.isPresent() ? optionalProduct.get() : "No product");
    }

    @Test
    void testGetAllProducts() {
        final List<Product> products = productRepository.findAll();
        log.info("Products: {}", products);
    }

}
