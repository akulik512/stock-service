package com.akulik.stockservice.stock.domain.port;

import com.akulik.stockservice.stock.domain.model.Stock;

import java.util.List;

public interface StockRepositoryPort {

    Stock saveStock(Stock stockEntity);

    List<Stock> findStocksByEmployeeId(Integer employeeId);

}
