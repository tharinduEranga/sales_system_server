package com.icbt.ap.sales.service.impl;

import com.icbt.ap.sales.entity.Product;
import com.icbt.ap.sales.exception.CustomServiceException;
import com.icbt.ap.sales.repository.ProductRepository;
import com.icbt.ap.sales.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Tharindu Eranga
 * @date Mon 08 Feb 2021
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public void add(Product product) {
        /*checks whether the product name already exists*/
        final Product productByName = productRepository.findByName(product.getName());
        if (productByName != null) throw new CustomServiceException(
                "error.validation.product.name.already.exist.code",
                "error.validation.product.name.already.exist.message"
        );

        productRepository.save(product);
    }

    @Override
    public void update(Product product) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Product getById(String id) {
        return productRepository.findById(id).orElseThrow(() -> new CustomServiceException(
                "error.validation.product.not.found.code",
                "error.validation.product.not.found.message"
        ));
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }
}
