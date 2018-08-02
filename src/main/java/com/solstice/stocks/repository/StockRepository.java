package com.solstice.stocks.repository;

import com.solstice.stocks.data.Stock;
import org.springframework.data.repository.CrudRepository;

public interface StockRepository extends CrudRepository<Stock, Integer> {

}
