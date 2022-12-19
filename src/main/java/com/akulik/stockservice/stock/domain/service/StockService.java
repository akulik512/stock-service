package com.akulik.stockservice.stock.domain.service;

import com.akulik.stockservice.stock.domain.model.Stock;
import com.akulik.stockservice.stock.domain.port.StockRepositoryPort;
import com.akulik.stockservice.stock.domain.validator.StockValidationService;
import com.akulik.stockservice.stock.persistance.mapper.StockMapper;
import com.akulik.stockservice.stock.rest.model.StockRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StockService {

    private final StockMapper stockMapper;
    private final StockRepositoryPort stockRepositoryPort;
    private final StockValidationService stockValidationService;

    public void save(StockRequest requestBody) {
        final Stock mappedStock = stockMapper.map(requestBody);
        stockValidationService.validator(mappedStock);
        stockRepositoryPort.saveStock(mappedStock);
    }

}
