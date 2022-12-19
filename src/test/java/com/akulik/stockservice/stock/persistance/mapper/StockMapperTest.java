package com.akulik.stockservice.stock.persistance.mapper;

import com.akulik.stockservice.stock.domain.model.Stock;
import com.akulik.stockservice.stock.persistance.entity.StockEntity;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static com.akulik.stockservice.testutil.StockTestData.buildStock;
import static com.akulik.stockservice.testutil.StockTestData.buildStockEntity;
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

}