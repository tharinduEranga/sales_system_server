package com.icbt.ap.sales.service;

import com.icbt.ap.sales.entity.StockRequest;
import com.icbt.ap.sales.entity.query.StockRequestResult;
import com.icbt.ap.sales.enums.StockRequestStatus;
import com.icbt.ap.sales.service.main.CrudService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Tharindu Eranga
 * @date Fri 19 Feb 2021
 */
@Service
public interface StockRequestService extends CrudService<String, StockRequest> {

    List<StockRequestResult> getAllByRequestedByBranch(String branchId);

    List<StockRequestResult> getAllByRequestedForBranch(String branchId);

    void updateStatus(String stockRequestId, StockRequestStatus status);
}
