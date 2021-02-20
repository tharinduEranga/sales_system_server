package com.icbt.ap.sales.service;

import com.icbt.ap.sales.entity.Product;
import com.icbt.ap.sales.service.main.CrudService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Tharindu Eranga
 * @date Mon 08 Feb 2021
 */
@Service
public interface ProductService extends CrudService<String, Product> {

    List<Product> validateAndGetProductsByIds(List<String> productIds);
}
