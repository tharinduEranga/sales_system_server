package com.icbt.ap.sales.controller.v1.rest;

import com.icbt.ap.sales.controller.CommonController;
import com.icbt.ap.sales.controller.v1.model.request.StockQtyUpdateRequest;
import com.icbt.ap.sales.controller.v1.model.response.StockResponse;
import com.icbt.ap.sales.controller.v1.model.request.StockSaveRequest;
import com.icbt.ap.sales.controller.v1.model.request.StockUpdateRequest;
import com.icbt.ap.sales.dto.CommonResponseDTO;
import com.icbt.ap.sales.dto.ContentResponseDTO;
import com.icbt.ap.sales.entity.Stock;
import com.icbt.ap.sales.service.StockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import static com.icbt.ap.sales.controller.v1.util.ApiConstant.DATE_TIME_FORMATTER;
import static com.icbt.ap.sales.controller.v1.util.ApiConstant.VERSION;

/**
 * @author Tharindu Eranga
 * @date Thu 18 Feb 2021
 */
@RestController
@RequestMapping(value = VERSION + "/stock")
@Slf4j
@RequiredArgsConstructor
public class StockController implements CommonController {

    private final StockService stockService;

    private final MessageSource messageSource;

    @GetMapping(path = "")
    public ResponseEntity<ContentResponseDTO<List<StockResponse>>> getStocks() {
        log.info("Get all stocks");
        return getAllStocks();
    }

    @GetMapping(path = "/{branchId}")
    public ResponseEntity<ContentResponseDTO<StockResponse>> getStock(
            @PathVariable(name = "branchId") String branchId) {

        log.info("Get stock by id, Stock id: {}", branchId);
        return getStockById(branchId);
    }

    @PostMapping(path = "")
    public ResponseEntity<CommonResponseDTO> saveStock(@Valid @RequestBody StockSaveRequest request) {
        log.info("Add new stock, Stock: {}", request);
        return addNewStock(request);
    }

    @PutMapping(path = "")
    public ResponseEntity<CommonResponseDTO> updateStock(@Valid @RequestBody StockUpdateRequest request) {
        log.info("Update stock, Stock: {}", request);
        return modifyStock(request);
    }

    @DeleteMapping(path = "/{branchId}")
    public ResponseEntity<CommonResponseDTO> deleteStock(@PathVariable(name = "branchId") String branchId) {
        log.info("Delete stock by id, Stock id: {}", branchId);
        return deleteStockTmp(branchId);
    }

    @PutMapping(path = "/qty")
    public ResponseEntity<CommonResponseDTO> updateStockQty(@Valid @RequestBody List<StockQtyUpdateRequest> request) {
        log.info("Update stock qty, Stock details: {}", request);
        return modifyStockQty(request);
    }


    /*Internal functions*/

    private ResponseEntity<ContentResponseDTO<List<StockResponse>>> getAllStocks() {
        return ResponseEntity.ok(new ContentResponseDTO<>(true,
                getStockResponseList(stockService.getAll())));
    }

    private ResponseEntity<ContentResponseDTO<StockResponse>> getStockById(String branchId) {
        return ResponseEntity.ok(new ContentResponseDTO<>(true,
                getStockResponse(stockService.getById(branchId))));
    }

    private ResponseEntity<CommonResponseDTO> addNewStock(StockSaveRequest request) {
        stockService.add(getStockSaveEntity(request));
        return new ResponseEntity<>(new CommonResponseDTO(true,
                getCode("success.confirmation.common.added.code"),
                getMessage("success.confirmation.stock.added.message")),
                HttpStatus.CREATED);
    }

    private ResponseEntity<CommonResponseDTO> modifyStock(StockUpdateRequest request) {
        stockService.update(getStockUpdateEntity(request));
        return new ResponseEntity<>(new CommonResponseDTO(true,
                getCode("success.confirmation.common.updated.code"),
                getMessage("success.confirmation.stock.updated.message")),
                HttpStatus.OK);
    }

    private ResponseEntity<CommonResponseDTO> modifyStockQty(List<StockQtyUpdateRequest> request) {
        stockService.updateStockQty(request);
        return new ResponseEntity<>(new CommonResponseDTO(true,
                getCode("success.confirmation.common.updated.code"),
                getMessage("success.confirmation.stock.qty.updated.message")),
                HttpStatus.OK);
    }

    private ResponseEntity<CommonResponseDTO> deleteStockTmp(String branchId) {
        stockService.delete(branchId);
        return new ResponseEntity<>(new CommonResponseDTO(true,
                getCode("success.confirmation.common.updated.code"),
                getMessage("success.confirmation.stock.deleted.message")),
                HttpStatus.OK);
    }

    private List<StockResponse> getStockResponseList(List<Stock> stocks) {
        return stocks
                .stream()
                .map(this::getStockResponse)
                .collect(Collectors.toList());
    }

    private StockResponse getStockResponse(Stock stock) {
        return StockResponse.builder()
                .id(stock.getId())
                .description(stock.getDescription())
                .qty(String.valueOf(stock.getQty()))
                .price(getFormattedAmount(stock.getPrice()))
                .branchId(stock.getBranchId())
                .productId(stock.getProductId())
                .createdAt(getFormattedDateTime(stock.getCreatedAt()))
                .build();
    }

    private Stock getStockSaveEntity(StockSaveRequest request) {
        return Stock.builder()
                .description(request.getDescription())
                .qty(Integer.valueOf(request.getQty()))
                .price(new BigDecimal(request.getPrice()))
                .productId(request.getProductId())
                .branchId(request.getBranchId())
                .build();
    }

    private Stock getStockUpdateEntity(StockUpdateRequest request) {
        return Stock.builder()
                .id(request.getId())
                .description(request.getDescription())
                .qty(Integer.valueOf(request.getQty()))
                .price(new BigDecimal(request.getPrice()))
                .productId(request.getProductId())
                .branchId(request.getBranchId())
                .build();
    }

    private String getFormattedDateTime(LocalDateTime dateTime) {
        return DATE_TIME_FORMATTER.format(dateTime);
    }

    private String getFormattedAmount(BigDecimal amount) {
        return String.valueOf(amount);
    }

    private String getCode(String key) {
        return messageSource.getMessage(key, new Object[0], Locale.getDefault());
    }

    private String getMessage(String key) {
        return messageSource.getMessage(key, new Object[0], Locale.getDefault());
    }
}
