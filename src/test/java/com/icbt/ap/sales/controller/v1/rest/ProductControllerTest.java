package com.icbt.ap.sales.controller.v1.rest;

import com.icbt.ap.sales.entity.Product;
import com.icbt.ap.sales.enums.ProductStatus;
import com.icbt.ap.sales.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.Local;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.icbt.ap.sales.controller.v1.util.ApiConstant.DATE_TIME_FORMATTER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


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

    @Autowired
    private MessageSource messageSource;

    private static final String PRODUCT_PATH = "/v1/product";


    @Test
    void getProducts() throws Exception {
        final LocalDateTime now = LocalDateTime.now();
        List<Product> products = new ArrayList<>();
        products.add(Product.builder()
                .id("64db6f2b-6ab1-11eb-888a-a81e849e9ba4")
                .name("Apple")
                .status(ProductStatus.ACTIVE)
                .createdAt(now)
                .build());
        products.add(Product.builder()
                .id("64db6f2b-6ab1-11eb-888a-a4324234324")
                .name("Orange")
                .status(ProductStatus.ACTIVE)
                .createdAt(now)
                .build());

        when(productService.getAll())
                .thenReturn(products);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(PRODUCT_PATH)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder)
                .andReturn();

        log.info("Response: {}", result.getResponse().getContentAsString());
        String expected = "{'success':true,'data':[{'id':'64db6f2b-6ab1-11eb-888a-a81e849e9ba4'," +
                "'name':'Apple','status':'Active','createdAt':'"
                + DATE_TIME_FORMATTER.format(now) + "'}," +
                "{'id':'64db6f2b-6ab1-11eb-888a-a4324234324'," +
                "'name':'Orange','status':'Active','createdAt':'"
                + DATE_TIME_FORMATTER.format(now) + "'}]}";

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    void getProduct() throws Exception {
        Product mockProduct = Product.builder()
                .id("64db6f2b-6ab1-11eb-888a-a81e849e9ba4")
                .name("Apple")
                .status(ProductStatus.ACTIVE)
                .createdAt(LocalDateTime.now())
                .build();

        when(productService.getById(Mockito.anyString()))
                .thenReturn(mockProduct);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(PRODUCT_PATH + "/121212")
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
    void saveProduct() throws Exception {

        // Send course as body to /students/Student1/courses
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(PRODUCT_PATH)
                .accept(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"name\": \"Apple\"\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }

    @Test
    void updateProduct() {
    }

    @Test
    void deleteProduct() {
    }
}