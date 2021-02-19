package com.icbt.ap.sales.service;

import com.icbt.ap.sales.controller.v1.model.request.StockQtyUpdateRequest;
import com.icbt.ap.sales.entity.Stock;
import com.icbt.ap.sales.entity.query.StockResult;
import com.icbt.ap.sales.service.main.CrudService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Tharindu Eranga
 * @date Wed 17 Feb 2021
 */
@Service
public interface StockService extends CrudService<String, Stock> {

    List<StockResult> getAllByBranch(String branchId);

    List<StockResult> getAllByProduct(String productId);

    void updateStockQty(List<StockQtyUpdateRequest> qtyUpdateRequests);

    List<Stock> validateAndGetStocksByIds(List<String> stockIds);
}
