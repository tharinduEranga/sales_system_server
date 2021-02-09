package com.icbt.ap.sales.controller.v1.rest;

import com.icbt.ap.sales.controller.CommonController;
import com.icbt.ap.sales.controller.v1.model.ProductResponse;
import com.icbt.ap.sales.dto.ContentResponseDTO;
import com.icbt.ap.sales.entity.Product;
import com.icbt.ap.sales.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.icbt.ap.sales.controller.v1.util.ApiConstant.DATE_TIME_FORMATTER;
import static com.icbt.ap.sales.controller.v1.util.ApiConstant.VERSION;

/**
 * @author Tharindu Eranga
 * @date Tue 09 Feb 2021
 */
@RestController
@RequestMapping(value = VERSION + "/product")
@Slf4j
@RequiredArgsConstructor
public class ProductController implements CommonController {

    private final ProductService productService;

    @GetMapping(path = "")
    public ResponseEntity<ContentResponseDTO<List<ProductResponse>>> getProducts() {
        return getAllProducts();
    }

    @GetMapping(path = "{productId}")
    public ResponseEntity<ContentResponseDTO<ProductResponse>> getProduct(
            @PathVariable(name = "productId") String productId) {

        return getProductById(productId);
    }


    private ResponseEntity<ContentResponseDTO<List<ProductResponse>>> getAllProducts() {
        return ResponseEntity.ok(new ContentResponseDTO<>(true,
                getProductResponseList(productService.getAll())));
    }

    private ResponseEntity<ContentResponseDTO<ProductResponse>> getProductById(String productId) {
        return ResponseEntity.ok(new ContentResponseDTO<>(true,
                getProductResponse(productService.getById(productId))));
    }

    private List<ProductResponse> getProductResponseList(List<Product> products) {
        return products
                .stream()
                .map(this::getProductResponse)
                .collect(Collectors.toList());
    }

    private ProductResponse getProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .status(product.getStatus().getDescription())
                .createdAt(getFormattedDateTime(product.getCreatedAt()))
                .build();
    }

    private String getFormattedDateTime(LocalDateTime dateTime) {
        return DATE_TIME_FORMATTER.format(dateTime);
    }
}
