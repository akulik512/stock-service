package com.akulik.stockservice.stock.persistance.mapper;

import com.akulik.stockservice.stock.domain.model.Stock;
import com.akulik.stockservice.stock.persistance.entity.StockEntity;
import com.akulik.stockservice.stock.rest.model.StockRequest;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static com.akulik.stockservice.testutil.StockTestData.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class StockMapperTest {

    private final StockMapper stockMapper = Mappers.getMapper(StockMapper.class);

    @Test
    void shouldMapEntityToModel() {
        final StockEntity stockEntity = buildStockEntity();

        final Stock mappedResult = stockMapper.map(stockEntity);

        final Stock expectedResult = buildStock();
        assertThat(mappedResult).isEqualTo(expectedResult);
    }

    @Test
    void shouldMapModelToEntity() {
        final StockEntity stockEntity = buildStockEntity();

        final Stock mappedResult = stockMapper.map(stockEntity);

        final Stock expectedResult = buildStock();
        assertThat(mappedResult).isEqualTo(expectedResult);
    }

    @Test
    void shouldMapRequestBodyToModel() {
        final StockRequest stockRequest = buildStockRequest();

        final Stock mappedResult = stockMapper.map(stockRequest);

        final Stock expectedResult = buildStock();
        assertThat(mappedResult).isEqualTo(expectedResult);
    }

}