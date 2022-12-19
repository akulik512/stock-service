package com.akulik.stockservice.testutil;

import com.akulik.stockservice.stock.domain.model.Stock;
import com.akulik.stockservice.stock.domain.model.Stock.StockBuilder;
import com.akulik.stockservice.stock.persistance.entity.StockEntity;
import com.akulik.stockservice.stock.persistance.entity.StockEntity.StockEntityBuilder;
import com.akulik.stockservice.stock.rest.model.StockRequest;
import com.akulik.stockservice.stock.rest.model.StockRequest.StockRequestBuilder;

import java.util.function.Consumer;

public class StockTestData {

    private static final String COMPANY_NAME = "Stock Service";
    private static final String SHARE_NAME = "NSE:DRREDDY";
    private static final String SHARE_ISIN_CODE = "US-000402625-0";
    private static final String COUNTRY = "Sweden";
    private static final String FIELD_ECONOMIC_ACTIVITY = "crypto";
    private static final String PRICE_PER_SHARE = "12.34 EUR";
    private static final Integer VOLUME = 2;
    private static final String DATE = "20221219";
    private static final Integer EMPLOYEE_ID = 12345;

    public static Stock buildStock() {
        return buildStock(null);
    }

    public static Stock buildStock(Consumer<StockBuilder> modifier) {
        final StockBuilder builder = Stock.builder()
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

    public static StockRequest buildStockRequest() {
        return buildStockRequest(null);
    }

    public static StockRequest buildStockRequest(Consumer<StockRequestBuilder> modifier) {
        final StockRequestBuilder builder = StockRequest.builder()
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
