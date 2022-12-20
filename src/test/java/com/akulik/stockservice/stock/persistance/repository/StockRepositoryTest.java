package com.akulik.stockservice.stock.persistance.repository;

import com.akulik.stockservice.stock.persistance.entity.StockEntity;
import com.akulik.stockservice.testutil.StockTestData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static com.akulik.stockservice.testutil.StockTestData.buildStockEntity;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
class StockRepositoryTest {

    @Autowired
    private StockRepository stockRepository;

    @Test
    void shouldSaveStock() {
        final StockEntity stockEntity = StockTestData.buildStockEntity();

        final StockEntity savedEntity = stockRepository.save(stockEntity);

        assertThat(savedEntity).isEqualTo(stockEntity);
    }

    @Test
    void shouldFindSavedStocks() {
        final Integer employeeId = 12345;
        final StockEntity stockEntity1 = buildStockEntity(modifier -> modifier
                .employeeId(employeeId));
        final StockEntity stockEntity2 = buildStockEntity(modifier -> modifier
                .employeeId(employeeId));

        stockRepository.save(stockEntity1);
        stockRepository.save(stockEntity2);

        final List<StockEntity> byEmployeeId = stockRepository.findByEmployeeId(employeeId);

        assertThat(byEmployeeId)
                .usingRecursiveComparison()
                .ignoringFields("stockId")
                .isEqualTo(List.of(stockEntity1, stockEntity2));
    }

}