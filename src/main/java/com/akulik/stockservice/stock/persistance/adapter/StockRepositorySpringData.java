package com.akulik.stockservice.stock.persistance.adapter;

import com.akulik.stockservice.stock.domain.model.Stock;
import com.akulik.stockservice.stock.domain.port.StockRepositoryPort;
import com.akulik.stockservice.stock.persistance.entity.StockEntity;
import com.akulik.stockservice.stock.persistance.mapper.StockMapper;
import com.akulik.stockservice.stock.persistance.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StockRepositorySpringData implements StockRepositoryPort {

    private final StockRepository stockRepository;
    private final StockMapper stockMapper;

    @Override
    public Stock saveStock(Stock stock) {
        final StockEntity savedStock = stockRepository.save(stockMapper.map(stock));
        return stockMapper.map(savedStock);
    }

}
