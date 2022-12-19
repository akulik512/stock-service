package com.akulik.stockservice.stock.domain.validator;

import com.akulik.stockservice.stock.domain.model.Stock;
import org.junit.jupiter.api.Test;

import static com.akulik.stockservice.testutil.StockTestData.buildStock;
import static java.lang.String.format;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class StockValidationServiceTest {

    private final StockValidationService stockValidationService = new StockValidationService();

    @Test
    void shouldPassValidation() {
        final String validPricePerShare = "123.45 EUR";
        final Stock stock = buildStock(modifier -> modifier.pricePerShare(validPricePerShare));

        assertDoesNotThrow(() -> stockValidationService.validator(stock));
    }

    @Test
    void throwMismatchCurrencyCodeException() {
        final String invalidPricePerShare = "123.45 USD";
        final Stock stock = buildStock(modifier -> modifier.pricePerShare(invalidPricePerShare));

        assertThatThrownBy(() -> stockValidationService.validator(stock))
                .isInstanceOf(MismatchCurrencyCodeException.class)
                .hasMessage(format("Stock name %s has a price per share currency %s doesn't equal EUR",
                        stock.getShareName(), stock.getPricePerShare()));
    }

}