package com.icbt.ap.sales.repository;

import com.icbt.ap.sales.entity.Product;
import com.icbt.ap.sales.repository.main.CrudRepository;

import java.util.List;

/**
 * @author Tharindu Eranga
 * @date Mon 08 Feb 2021
 */
public interface ProductRepository extends CrudRepository<String, Product> {

    Product findByName(String name);

    List<Product> findAllByIdsIn(List<String> productIds);
}
