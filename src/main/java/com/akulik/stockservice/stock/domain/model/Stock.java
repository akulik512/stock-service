package com.akulik.stockservice.stock.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.util.UUID;

@Value
@Builder
@AllArgsConstructor
public class Stock {

    UUID stockId;
    String companyName;
    String shareName;
    String shareIsinCode;
    String country;
    String fieldEconomicActivity;
    BigDecimal pricePerShare;
    Integer volume;
    String date;
    Integer employeeId;

}
