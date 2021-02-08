package com.icbt.ap.sales.repository.impl;

import com.icbt.ap.sales.entity.Product;
import com.icbt.ap.sales.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
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

    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Product> findAll() {
        return jdbcTemplate.query("SELECT * FROM product", new ProductRowMapper());
    }

    @Override
    public Optional<Product> findById(String id) {

        String sql = "SELECT * FROM product WHERE id = ?";

        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new ProductRowMapper(), id));
        } catch (EmptyResultDataAccessException ex) {
            return Optional.empty();
        }
    }

    @Override
    public void save(Product product) {
        jdbcTemplate.update("INSERT INTO product (id, name) " + "VALUES (UUID(), ?)",
                product.getName());
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

    @Override
    public Product findByName(String name) {

        String sql = "SELECT * FROM product WHERE name = ?";

        try {
            return jdbcTemplate.queryForObject(sql, new ProductRowMapper(), name);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    private static class ProductRowMapper implements RowMapper<Product> {
        @Override
        public Product mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            return Product.builder()
                    .id(resultSet.getString("id"))
                    .name(resultSet.getString("name"))
                    .createdAt(resultSet.getTimestamp("created_at").toLocalDateTime())
                    .build();
        }
    }

    @Override
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

}
