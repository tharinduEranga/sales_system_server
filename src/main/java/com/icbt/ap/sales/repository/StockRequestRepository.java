package com.icbt.ap.sales.repository;

import com.icbt.ap.sales.entity.StockRequest;
import com.icbt.ap.sales.entity.query.StockRequestResult;
import com.icbt.ap.sales.enums.StockRequestStatus;
import com.icbt.ap.sales.repository.main.CrudRepository;

import java.util.List;

/**
 * @author Tharindu Eranga
 * @date Fri 19 Feb 2021
 */
public interface StockRequestRepository extends CrudRepository<String, StockRequest> {
    String saveAndGetId(StockRequest stock);

    void updateStatus(String id, StockRequestStatus status);

    List<StockRequestResult> findAllByRequestByBranch(String byBranchId);

    List<StockRequestResult> findAllByRequestToBranch(String toBranchId);
}
