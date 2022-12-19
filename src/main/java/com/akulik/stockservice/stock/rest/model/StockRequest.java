package com.akulik.stockservice.stock.rest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class StockRequest {

    String companyName;
    String shareName;
    String shareIsinCode;
    String country;
    String fieldEconomicActivity;
    String pricePerShare;
    Integer volume;
    String date;
    Integer employeeId;

}
