package com.akulik.stockservice.stock.persistance.repository;

import com.akulik.stockservice.stock.persistance.entity.StockEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface StockRepository extends CrudRepository<StockEntity, UUID> {
}
