package com.akulik.stockservice.testutil;

import com.akulik.stockservice.stock.persistance.entity.StockEntity;
import com.akulik.stockservice.stock.persistance.entity.StockEntity.StockEntityBuilder;

import java.math.BigDecimal;
import java.util.function.Consumer;

public class StockEntityTestData {

    private static final String COMPANY_NAME = "Stock Servoce";
    private static final String SHARE_NAME = "NSE:DRREDDY";
    private static final String SHARE_ISIN_CODE = "US-000402625-0";
    private static final String COUNTRY = "Sweden";
    private static final String FIELD_ECONOMIC_ACTIVITY = "crypto";
    private static final BigDecimal PRICE_PER_SHARE = BigDecimal.valueOf(12.34);
    private static final Integer VOLUME = 2;
    private static final String DATE = "20221219";
    private static final Integer EMPLOYEE_ID = 12345;

    public static StockEntity buildStockEntity() {
        return buildStockEntity(null);
    }

    public static StockEntity buildStockEntity(Consumer<StockEntityBuilder> modifier) {
        final StockEntityBuilder builder = StockEntity.builder()
                .companyName(COMPANY_NAME)
                .shareName(SHARE_NAME)
                .shareIsinCode(SHARE_ISIN_CODE)
                .country(COUNTRY)
                .fieldEconomicActivity(FIELD_ECONOMIC_ACTIVITY)
                .pricePerShare(PRICE_PER_SHARE)
                .volume(VOLUME)
                .date(DATE)
                .employeeId(EMPLOYEE_ID);

        if (modifier != null) {
            modifier.accept(builder);
        }

        return builder.build();
    }

}
