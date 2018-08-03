package com.solstice.stocks.repository;

import com.solstice.stocks.data.AggregateQuote;
import com.solstice.stocks.data.Quote;
import java.util.Date;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface StockRepository extends CrudRepository<Quote, Integer> {

  @Query(value ="SELECT new com.solstice.stocks.data.AggregateQuote(symbol, MAX(price), MIN(price), SUM(volume), MIN(date))\n"
      + "FROM Quote\n"
      + "WHERE symbol = :symbol AND date BETWEEN :date AND :dayAfterDate\n"
      + "GROUP BY symbol")
  AggregateQuote getAggregateData(@Param("symbol") String symbol,@Param("date") Date date, @Param("dayAfterDate") Date dayAfterDate);

}
