package com.akulik.stockservice.stock.rest;

import com.akulik.stockservice.stock.domain.model.Stock;
import com.akulik.stockservice.stock.domain.service.StockService;
import com.akulik.stockservice.stock.rest.model.StockRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/stock")
public class StockController {

    private final StockService stockService;

    @PostMapping
    public ResponseEntity<Void> saveStock(@RequestBody StockRequest requestBody) {
        stockService.save(requestBody);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{employeeId}")
    public ResponseEntity<List<Stock>> saveStock(@PathVariable Integer employeeId) {
        final List<Stock> byEmployeeId = stockService.getByEmployeeId(employeeId);
        return ResponseEntity.ok().body(byEmployeeId);
    }

}
