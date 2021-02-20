package com.icbt.ap.sales.repository.impl;

import com.icbt.ap.sales.entity.Product;
import com.icbt.ap.sales.enums.ProductStatus;
import com.icbt.ap.sales.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
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
        return jdbcTemplate.query("SELECT * FROM product WHERE status <> ?",
                new ProductRowMapper(), ProductStatus.DELETED.getId());
    }

    @Override
    public Optional<Product> findById(String id) {

        String sql = "SELECT * FROM product WHERE status <> ? AND id = ?";

        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new ProductRowMapper(),
                    ProductStatus.DELETED.getId(), id));
        } catch (EmptyResultDataAccessException ex) {
            return Optional.empty();
        }
    }

    @Override
    public void save(Product product) {
        jdbcTemplate.update("INSERT INTO product (id, name, status) " + "VALUES (UUID(), ?, ?)",
                product.getName(), product.getStatus().getId());
    }

    @Override
    public void update(Product product) {
        jdbcTemplate.update("UPDATE product " + " SET name = ?, status = ? " + " WHERE id = ?",
                product.getName(), product.getStatus().getId(), product.getId());
    }

    @Override
    public void delete(String id) {
        jdbcTemplate.update("UPDATE product " + " SET status = ? " + " WHERE id = ?",
                ProductStatus.DELETED.getId(), id);
    }

    @Override
    public Product findByName(String name) {

        String sql = "SELECT * FROM product WHERE status <> ? AND name = ?";

        try {
            return jdbcTemplate.queryForObject(sql, new ProductRowMapper(),
                    ProductStatus.DELETED.getId(), name);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Product> findAllByIdsIn(List<String> productIds) {
        String sql = "SELECT * FROM product WHERE id IN (%s)";
        String inSql = String.join(",", Collections.nCopies(productIds.size(), "?"));

        return jdbcTemplate.query(String.format(sql, inSql), new ProductRowMapper(), productIds.toArray());
    }

    private static class ProductRowMapper implements RowMapper<Product> {
        @Override
        public Product mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            return Product.builder()
                    .id(resultSet.getString("id"))
                    .name(resultSet.getString("name"))
                    .status(ProductStatus.getById(resultSet.getInt("status")))
                    .createdAt(resultSet.getTimestamp("created_at").toLocalDateTime())
                    .build();
        }
    }

}
