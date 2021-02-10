package com.icbt.ap.sales.controller.v1.rest;

import com.icbt.ap.sales.entity.Product;
import com.icbt.ap.sales.enums.ProductStatus;
import com.icbt.ap.sales.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;

import static com.icbt.ap.sales.controller.v1.util.ApiConstant.DATE_TIME_FORMATTER;


/**
 * @author Tharindu Eranga
 * @date Wed 10 Feb 2021
 */
@Slf4j
@ExtendWith(SpringExtension.class)
@WebMvcTest(value = ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    private final Product mockProduct = Product.builder()
            .id("64db6f2b-6ab1-11eb-888a-a81e849e9ba4")
            .name("Apple")
            .status(ProductStatus.ACTIVE)
            .createdAt(LocalDateTime.now())
            .build();

    @Test
    void getProducts() throws Exception {

        Mockito.when(productService.getById(Mockito.anyString()))
                .thenReturn(mockProduct);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/v1/product/121212")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder)
                .andReturn();

        log.info("Response: {}", result.getResponse().getContentAsString());
        String expected = "{'success':true,'data':{'id':'64db6f2b-6ab1-11eb-888a-a81e849e9ba4'," +
                "'name':'Apple','status':'Active','createdAt':'"
                + DATE_TIME_FORMATTER.format(mockProduct.getCreatedAt()) + "'}}";

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    void getProduct() {
    }

    @Test
    void saveProduct() {
    }

    @Test
    void updateProduct() {
    }

    @Test
    void deleteProduct() {
    }
}