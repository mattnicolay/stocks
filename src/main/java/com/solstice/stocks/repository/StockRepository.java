package com.solstice.stocks.repository;

import com.solstice.stocks.data.Quote;
import org.springframework.data.repository.CrudRepository;

public interface StockRepository extends CrudRepository<Quote, Integer> {

}
