package com.icbt.ap.sales.repository;

import com.icbt.ap.sales.entity.Product;
import com.icbt.ap.sales.repository.main.CrudRepository;

import javax.sql.DataSource;

/**
 * @author Tharindu Eranga
 * @date Mon 08 Feb 2021
 */
public interface ProductRepository extends CrudRepository<String, Product> {
    void setDataSource(DataSource dataSource);

    Product findByName(String name);
}
