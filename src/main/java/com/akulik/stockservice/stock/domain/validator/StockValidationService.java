package com.akulik.stockservice.stock.domain.validator;

import com.akulik.stockservice.stock.domain.model.Stock;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StockValidationService {

    private static final String EXPECTED_CURRENCY_CODE = "EUR";

    public void validator(Stock receivedStock) {
        if (!receivedStock.getPricePerShare().contains(EXPECTED_CURRENCY_CODE)) {
            throw new MismatchCurrencyCodeException(receivedStock.getShareName(), receivedStock.getPricePerShare());
        }
    }

}
