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

import java.util.List;

import static com.akulik.stockservice.testutil.StockTestData.buildStock;
import static com.akulik.stockservice.testutil.StockTestData.buildStockEntity;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class StockRepositorySpringDataTest {

    @Mock
    private StockRepository stockRepository;
    @Mock
    private StockMapper stockMapper;
    @InjectMocks
    private StockRepositorySpringData stockRepositorySpringData;

    @Test
    void shouldSaveStock() {
        final Stock stockModel = buildStock();
        final StockEntity stockEntity = buildStockEntity();

        given(stockMapper.map(stockEntity)).willReturn(stockModel);
        given(stockRepository.save(stockEntity)).willReturn(stockEntity);
        given(stockMapper.map(stockModel)).willReturn(stockEntity);

        stockRepositorySpringData.saveStock(stockModel);

        then(stockMapper).should().map(stockEntity);
        then(stockRepository).should().save(stockEntity);
        then(stockMapper).should().map(stockModel);
    }

    @Test
    void shouldFindStocksByEmployeeId() {
        final Integer employeeId = 12345;
        final StockEntity stockEntity1 = buildStockEntity(modifier -> modifier
                .employeeId(employeeId));
        final StockEntity stockEntity2 = buildStockEntity(modifier -> modifier
                .employeeId(employeeId));
        final List<StockEntity> stockEntities = List.of(stockEntity1, stockEntity2);

        final Stock stock1 = buildStock(modifier -> modifier
                .employeeId(employeeId));
        final Stock stock2 = buildStock(modifier -> modifier
                .employeeId(employeeId));

        given(stockRepository.findByEmployeeId(employeeId)).willReturn(stockEntities);
        given(stockMapper.map(stockEntity1)).willReturn(stock1);
        given(stockMapper.map(stockEntity2)).willReturn(stock2);

        final List<Stock> stocksByEmployeeId = stockRepositorySpringData.findStocksByEmployeeId(employeeId);

        assertThat(stocksByEmployeeId).isEqualTo(List.of(stock1, stock2));
        then(stockRepository).should().findByEmployeeId(employeeId);
        then(stockMapper).should().map(stockEntity2);
        then(stockMapper).should().map(stockEntity2);
    }

}