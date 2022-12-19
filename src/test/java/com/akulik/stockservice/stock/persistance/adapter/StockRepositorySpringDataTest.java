package com.akulik.stockservice.stock.persistance.adapter;

import com.akulik.stockservice.stock.domain.model.Stock;
import com.akulik.stockservice.stock.persistance.entity.StockEntity;
import com.akulik.stockservice.stock.persistance.mapper.StockMapper;
import com.akulik.stockservice.stock.persistance.repository.StockRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.akulik.stockservice.testutil.StockTestData.buildStock;
import static com.akulik.stockservice.testutil.StockTestData.buildStockEntity;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class StockRepositorySpringDataTest {

    final Stock stockModel = buildStock();
    final StockEntity stockEntity = buildStockEntity();
    @Mock
    private StockRepository stockRepository;
    @Mock
    private StockMapper stockMapper;
    @InjectMocks
    private StockRepositorySpringData stockRepositorySpringData;

    @Test
    void shouldSaveStock() {
        given(stockMapper.map(stockEntity)).willReturn(stockModel);
        given(stockRepository.save(stockEntity)).willReturn(stockEntity);
        given(stockMapper.map(stockModel)).willReturn(stockEntity);

        stockRepositorySpringData.saveStock(stockModel);

        then(stockMapper).should().map(stockEntity);
        then(stockRepository).should().save(stockEntity);
        then(stockMapper).should().map(stockModel);
    }

}