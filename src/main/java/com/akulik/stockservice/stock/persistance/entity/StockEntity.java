package com.akulik.stockservice.stock.persistance.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "stock")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
public class StockEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID stockId;
    private String companyName;
    private String shareName;
    private String shareIsinCode;
    private String country;
    private String fieldEconomicActivity;
    private BigDecimal pricePerShare;
    private Integer volume;
    private String date;
    private Integer employeeId;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        StockEntity that = (StockEntity) o;
        return stockId != null && Objects.equals(stockId, that.stockId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
