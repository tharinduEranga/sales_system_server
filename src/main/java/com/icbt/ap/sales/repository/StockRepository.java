package com.icbt.ap.sales.repository;

import com.icbt.ap.sales.entity.Stock;
import com.icbt.ap.sales.entity.query.StockResult;
import com.icbt.ap.sales.repository.main.CrudRepository;

import java.util.List;

/**
 * @author Tharindu Eranga
 * @date Thu 18 Feb 2021
 */
public interface StockRepository extends CrudRepository<String, Stock> {
    List<StockResult> findAllByBranch(String branchId);

    List<StockResult> findAllByProduct(String productId);

    List<Stock> findAllByIdsIn(List<String> stockIds);

    void updateListQty(List<Stock> stocks);
}
