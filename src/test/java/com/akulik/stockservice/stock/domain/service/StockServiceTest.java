package com.akulik.stockservice.stock.domain.service;

import com.akulik.stockservice.stock.domain.model.Stock;
import com.akulik.stockservice.stock.domain.port.StockRepositoryPort;
import com.akulik.stockservice.stock.domain.validator.MismatchCurrencyCodeException;
import com.akulik.stockservice.stock.domain.validator.StockValidationService;
import com.akulik.stockservice.stock.persistance.mapper.StockMapper;
import com.akulik.stockservice.stock.rest.model.StockRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.akulik.stockservice.testutil.StockTestData.buildStock;
import static com.akulik.stockservice.testutil.StockTestData.buildStockRequest;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class StockServiceTest {

    @Mock
    private StockMapper stockMapper;
    @Mock
    private StockRepositoryPort stockRepositoryPort;
    @Mock
    private StockValidationService stockValidationService;
    @InjectMocks
    private StockService stockService;

    @Test
    void shouldSaveStock() {
        final StockRequest stockRequest = buildStockRequest();
        final Stock mappedStock = buildStock();
        given(stockMapper.map(stockRequest)).willReturn(mappedStock);

        stockService.save(stockRequest);

        then(stockRepositoryPort).should().saveStock(mappedStock);
        then(stockValidationService).should().validator(mappedStock);
    }

    @Test
    void shouldNotSaveStockDueFailedValidation() {
        final StockRequest stockRequest = buildStockRequest();
        final Stock mappedStock = buildStock();
        given(stockMapper.map(stockRequest)).willReturn(mappedStock);
        willThrow(MismatchCurrencyCodeException.class).given(stockValidationService).validator(mappedStock);

        assertThatThrownBy(() -> stockService.save(stockRequest))
                .isInstanceOf(MismatchCurrencyCodeException.class);

        then(stockValidationService).should().validator(mappedStock);
        then(stockRepositoryPort).should(never()).saveStock(mappedStock);
    }

}