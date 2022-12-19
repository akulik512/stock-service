package com.akulik.stockservice.stock.rest;

import com.akulik.stockservice.stock.domain.service.StockService;
import com.akulik.stockservice.stock.rest.model.StockRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;

    @PostMapping(value = "/stock")
    public ResponseEntity<Void> saveStock(@RequestBody StockRequest requestBody) {
        stockService.save(requestBody);
        return ResponseEntity.ok().build();
    }

}
