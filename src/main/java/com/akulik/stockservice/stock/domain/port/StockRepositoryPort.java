package com.akulik.stockservice.stock.domain.port;

import com.akulik.stockservice.stock.domain.model.Stock;

public interface StockRepositoryPort {

    Stock saveStock(Stock stockEntity);

}
