package com.icbt.ap.sales.service.impl;

import com.icbt.ap.sales.entity.Product;
import com.icbt.ap.sales.enums.ProductStatus;
import com.icbt.ap.sales.repository.ProductRepository;
import com.icbt.ap.sales.repository.impl.ProductRepositoryImpl;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;

import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;

/**
 * @author Tharindu Eranga
 * @date Mon 08 Feb 2021
 */

@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class ProductServiceImplTest {

    private ProductServiceImpl productService;

    private final ProductRepository productRepository;

    private ProductServiceImplTest() {
        productRepository = new ProductRepositoryImpl();
        DataSource dataSource = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:test/sales_db_test.sql")
                .build();
        productRepository.setDataSource(dataSource);
        productService = new ProductServiceImpl(productRepository);
    }

    @Test
    void add() {
        String name = "Sugar";
        productService.add(Product.builder().name(name).status(ProductStatus.ACTIVE).build());
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void getById() {
    }

    @Test
    void getAll() {
    }
}