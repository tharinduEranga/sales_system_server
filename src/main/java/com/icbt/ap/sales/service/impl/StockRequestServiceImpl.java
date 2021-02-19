package com.icbt.ap.sales.service.impl;

import com.icbt.ap.sales.entity.*;
import com.icbt.ap.sales.entity.query.StockRequestResult;
import com.icbt.ap.sales.exception.CustomServiceException;
import com.icbt.ap.sales.repository.*;
import com.icbt.ap.sales.service.BranchService;
import com.icbt.ap.sales.service.StockRequestService;
import com.icbt.ap.sales.service.StockService;
import com.icbt.ap.sales.service.VehicleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Tharindu Eranga
 * @date Fri 19 Feb 2021
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class StockRequestServiceImpl implements StockRequestService {

    private final StockRequestRepository stockRequestRepository;
    private final StockRequestDetailRepository stockRequestDetailRepository;
    private final VehicleRepository vehicleRepository;
    private final BranchRepository branchRepository;
    private final StockRepository stockRepository;

    private final BranchService branchService;
    private final VehicleService vehicleService;
    private final StockService stockService;

    @Transactional
    @Override
    public void add(StockRequest stockRequest) {
        /*validates the incoming data*/
        validateStockRequest(stockRequest);

        final String savedId = stockRequestRepository.saveAndGetId(stockRequest);
        /*sets the saved stock request id to details*/
        stockRequest.getStockRequestDetails()
                .forEach(stockRequestDetail -> stockRequestDetail.setStockRequestId(savedId));
        stockRequestDetailRepository.saveAll(stockRequest.getStockRequestDetails());
    }

    @Transactional
    @Override
    public void update(StockRequest stockRequest) {
        /*validates the incoming data*/
        final StockRequest stockRequestById = getById(stockRequest.getId());
        validateStockRequest(stockRequest);

        stockRequestRepository.update(stockRequestById);
    }

    @Override
    public void delete(String id) {
        final StockRequest stockRequest = getById(id);
        stockRequestRepository.delete(stockRequest.getId());
    }

    @Override
    public StockRequest getById(String id) {
        return stockRequestRepository.findById(id).orElseThrow(() -> new CustomServiceException(
                "error.validation.common.not.found.code",
                "error.validation.stock.request.not.found.message"
        ));
    }

    @Override
    public List<StockRequest> getAll() {
        return stockRequestRepository.findAll();
    }

    @Override
    public List<StockRequestResult> getAllByRequestedByBranch(String stockRequestId) {
        return stockRequestRepository.findAllByRequestByBranch(stockRequestId);
    }

    @Override
    public List<StockRequestResult> getAllByRequestedForBranch(String stockRequestId) {
        return stockRequestRepository.findAllByRequestToBranch(stockRequestId);
    }

    /*Internal functions below*/

    private void validateStockRequest(StockRequest stockRequest) {
        /*checks whether the requested branches and vehicle exist*/
        final Branch byBranch = branchService.getById(stockRequest.getByBranchId());
        final Branch forBranch = branchService.getById(stockRequest.getForBranchId());
        final Vehicle vehicle = vehicleService.getById(stockRequest.getVehicleId());

        /*explicitly sets IDs*/
        stockRequest.setByBranchId(byBranch.getId());
        stockRequest.setForBranchId(forBranch.getId());
        stockRequest.setVehicleId(vehicle.getId());
        validateStockDetailsRequest(stockRequest.getStockRequestDetails());
    }

    private void validateStockDetailsRequest(List<StockRequestDetail> stockRequestDetails) {
        final List<String> stockIdList = stockRequestDetails
                .stream()
                .map(StockRequestDetail::getStockId)
                .collect(Collectors.toList());
        stockService.validateAndGetStocksByIds(stockIdList);
    }
}
