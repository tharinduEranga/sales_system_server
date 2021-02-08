package com.icbt.ap.sales.repository.impl;

import com.icbt.ap.sales.entity.Product;
import com.icbt.ap.sales.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * @author Tharindu Eranga
 * @date Mon 08 Feb 2021
 */
@Repository
@RequiredArgsConstructor
@Slf4j
public class ProductRepositoryImpl implements ProductRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Product> findAll() {
        return jdbcTemplate.query("SELECT * FROM product", new ProductRowMapper());
    }

    @Override
    public Optional<Product> findById(String id) {

        String sql = "SELECT * FROM product WHERE id = ?";

        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, (resultSet, rowNum) ->
                        Product.builder()
                                .id(resultSet.getString("id"))
                                .name(resultSet.getString("name"))
                                .build(),
                id));
    }

    @Override
    public void save(Product product) {
        jdbcTemplate.update("INSERT INTO product (id, name) " + "VALUES (?, ?)",
                product.getId(), product.getName());
    }

    @Override
    public void update(Product product) {
        jdbcTemplate.update("UPDATE product " + " SET name = ? " + " WHERE id = ?",
                product.getName(), product.getId());
    }

    @Override
    public void delete(String id) {
        jdbcTemplate.update("DELETE FROM product WHERE id=?", id);
    }

    private static class ProductRowMapper implements RowMapper<Product> {
        @Override
        public Product mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            return Product.builder()
                    .id(resultSet.getString("id"))
                    .name(resultSet.getString("name"))
                    .build();
        }
    }
}