package com.icbt.ap.sales.repository.impl;

import com.icbt.ap.sales.entity.Stock;
import com.icbt.ap.sales.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * @author Tharindu Eranga
 * @date Wed 17 Feb 2021
 */
@Repository
@RequiredArgsConstructor
@Slf4j
public class StockRepositoryImpl implements StockRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Stock> findAll() {
        return jdbcTemplate.query("SELECT * FROM stock", new StockRowMapper());
    }

    @Override
    public Optional<Stock> findById(String id) {

        String sql = "SELECT * FROM stock WHERE id = ? ";

        try {
            return Optional.ofNullable(jdbcTemplate
                    .queryForObject(sql, new StockRowMapper(), id));
        } catch (EmptyResultDataAccessException ex) {
            return Optional.empty();
        }
    }

    @Override
    public void save(Stock stock) {
        jdbcTemplate.update("INSERT INTO stock (`id`, `description`, `qty`, `price`, `branch_id`, `product_id`) "
                        + "VALUES (UUID(), ?, ?, ?, ?, ?)",
                stock.getDescription(), stock.getQty(), stock.getPrice(), stock.getBranchId(), stock.getProductId());
    }

    @Override
    public void update(Stock stock) {
        jdbcTemplate.update("UPDATE stock " + " SET description = ?, qty = ?, price = ? , " +
                        "branch_id = ? , product_id = ? " + " WHERE id = ?",
                stock.getDescription(), stock.getQty(), stock.getPrice(),
                stock.getBranchId(), stock.getProductId(), stock.getId());
    }

    @Override
    public void delete(String id) {
        jdbcTemplate.update("UPDATE stock " + " SET qty = ? WHERE id = ?", 0, id);
    }

    @Override
    public List<Stock> findAllByBranch(String branchId) {

        String sql = "SELECT * FROM stock WHERE branch_id = ?";

        return jdbcTemplate.query(sql, new StockRowMapper(), branchId);
    }

    @Override
    public List<Stock> findAllByProduct(String productId) {
        String sql = "SELECT * FROM stock WHERE product_id = ?";

        return jdbcTemplate.query(sql, new StockRowMapper(), productId);
    }

    private static class StockRowMapper implements RowMapper<Stock> {
        @Override
        public Stock mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            return Stock.builder()
                    .id(resultSet.getString("id"))
                    .description(resultSet.getString("description"))
                    .qty(resultSet.getInt("qty"))
                    .price(resultSet.getBigDecimal("price"))
                    .branchId(resultSet.getString("branch_id"))
                    .productId(resultSet.getString("product_id"))
                    .createdAt(resultSet.getTimestamp("created_at").toLocalDateTime())
                    .build();
        }
    }

}
