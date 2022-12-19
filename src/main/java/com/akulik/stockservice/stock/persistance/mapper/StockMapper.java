package com.akulik.stockservice.stock.persistance.mapper;

import com.akulik.stockservice.stock.domain.model.Stock;
import com.akulik.stockservice.stock.persistance.entity.StockEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface StockMapper {

    Stock map(StockEntity stockEntity);

    StockEntity map(Stock stock);

}
