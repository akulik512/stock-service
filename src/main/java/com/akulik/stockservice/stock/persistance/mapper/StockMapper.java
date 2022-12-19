package com.akulik.stockservice.stock.persistance.mapper;

import com.akulik.stockservice.stock.domain.model.Stock;
import com.akulik.stockservice.stock.persistance.entity.StockEntity;
import com.akulik.stockservice.stock.rest.model.StockRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface StockMapper {

    Stock map(StockEntity stockEntity);

    StockEntity map(Stock stock);

    @Mapping(target = "stockId", ignore = true)
    Stock map(StockRequest requestBody);

}
