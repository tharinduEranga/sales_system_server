package com.icbt.ap.sales.repository;

import com.icbt.ap.sales.entity.StockRequestDetail;
import com.icbt.ap.sales.repository.main.CrudRepository;

import java.util.List;

/**
 * @author Tharindu Eranga
 * @date Fri 19 Feb 2021
 */
public interface StockRequestDetailRepository extends CrudRepository<String, StockRequestDetail> {
    List<StockRequestDetail> findAllByStockRequest(String stockRequestId);

    void saveAll(List<StockRequestDetail> stocks);
}
