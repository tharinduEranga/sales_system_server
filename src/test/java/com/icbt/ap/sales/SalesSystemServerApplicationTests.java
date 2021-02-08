package com.icbt.ap.sales;

import com.icbt.ap.sales.entity.Product;
import com.icbt.ap.sales.repository.ProductRepository;
import com.icbt.ap.sales.repository.impl.ProductRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class SalesSystemServerApplicationTests {

    private final ProductRepository productRepository;

    private SalesSystemServerApplicationTests() {
        productRepository = new ProductRepositoryImpl();
        DataSource dataSource = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:test/sales_db_test.sql")
                .build();
        productRepository.setDataSource(dataSource);
    }

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

    @Test
    void addProduct() {
        final Product product = Product.builder().name("Pineapple").build();
        productRepository.save(product);
        assertEquals("Pineapple", product.getName());
    }

}
