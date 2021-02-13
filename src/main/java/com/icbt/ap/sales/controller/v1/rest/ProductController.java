package com.icbt.ap.sales.controller.v1.rest;

import com.icbt.ap.sales.controller.CommonController;
import com.icbt.ap.sales.controller.v1.model.request.ProductUpdateRequest;
import com.icbt.ap.sales.controller.v1.model.response.ProductResponse;
import com.icbt.ap.sales.controller.v1.model.request.ProductSaveRequest;
import com.icbt.ap.sales.dto.CommonResponseDTO;
import com.icbt.ap.sales.dto.ContentResponseDTO;
import com.icbt.ap.sales.entity.Product;
import com.icbt.ap.sales.enums.ProductStatus;
import com.icbt.ap.sales.exception.CustomServiceException;
import com.icbt.ap.sales.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
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

    private final MessageSource messageSource;

    @GetMapping(path = "")
    public ResponseEntity<ContentResponseDTO<List<ProductResponse>>> getProducts() {
        return getAllProducts();
    }

    @GetMapping(path = "/{productId}")
    public ResponseEntity<ContentResponseDTO<ProductResponse>> getProduct(
            @PathVariable(name = "productId") String productId) {

        return getProductById(productId);
    }

    @PostMapping(path = "")
    public ResponseEntity<CommonResponseDTO> saveProduct(@Valid @RequestBody ProductSaveRequest request) {
        return addNewProduct(request);
    }

    @PutMapping(path = "")
    public ResponseEntity<CommonResponseDTO> updateProduct(@Valid @RequestBody ProductUpdateRequest request) {
        return modifyProduct(request);
    }

    @DeleteMapping(path = "/{productId}")
    public ResponseEntity<CommonResponseDTO> deleteProduct(@PathVariable(name = "productId") String productId) {
        return deleteProductTmp(productId);
    }


    /*Internal functions*/

    private ResponseEntity<ContentResponseDTO<List<ProductResponse>>> getAllProducts() {
        return ResponseEntity.ok(new ContentResponseDTO<>(true,
                getProductResponseList(productService.getAll())));
    }

    private ResponseEntity<ContentResponseDTO<ProductResponse>> getProductById(String productId) {
        return ResponseEntity.ok(new ContentResponseDTO<>(true,
                getProductResponse(productService.getById(productId))));
    }

    private ResponseEntity<CommonResponseDTO> addNewProduct(ProductSaveRequest request) {
        productService.add(getProductSaveEntity(request));
        return new ResponseEntity<>(new CommonResponseDTO(true,
                getCode("success.confirmation.common.added.code"),
                getMessage("success.confirmation.product.added.message")),
                HttpStatus.CREATED);
    }

    private ResponseEntity<CommonResponseDTO> modifyProduct(ProductUpdateRequest request) {
        validateUpdateProductRequest(request);
        productService.update(getProductUpdateEntity(request));
        return new ResponseEntity<>(new CommonResponseDTO(true,
                getCode("success.confirmation.common.updated.code"),
                getMessage("success.confirmation.product.updated.message")),
                HttpStatus.OK);
    }

    private ResponseEntity<CommonResponseDTO> deleteProductTmp(String productId) {
        productService.delete(productId);
        return new ResponseEntity<>(new CommonResponseDTO(true,
                getCode("success.confirmation.common.updated.code"),
                getMessage("success.confirmation.product.deleted.message")),
                HttpStatus.OK);
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

    private Product getProductSaveEntity(ProductSaveRequest request) {
        return Product.builder()
                .name(request.getName())
                .status(ProductStatus.ACTIVE)
                .build();
    }

    private Product getProductUpdateEntity(ProductUpdateRequest request) {
        return Product.builder()
                .id(request.getId())
                .name(request.getName())
                .status(ProductStatus.getById(request.getStatusId()))
                .build();
    }

    private void validateUpdateProductRequest(ProductUpdateRequest request) {
        if ((request.getStatusId() != null) && (ProductStatus.getById(request.getStatusId()) == null))
            throw new CustomServiceException(
                    "error.validation.common.not.found.code",
                    "error.validation.common.status.not.found.message"
            );
    }

    private String getFormattedDateTime(LocalDateTime dateTime) {
        return DATE_TIME_FORMATTER.format(dateTime);
    }

    private String getCode(String key) {
        return messageSource.getMessage(key, new Object[0], Locale.getDefault());
    }

    private String getMessage(String key) {
        return messageSource.getMessage(key, new Object[0], Locale.getDefault());
    }
}
