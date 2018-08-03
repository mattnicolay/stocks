package com.solstice.stocks.controller;

import com.solstice.stocks.data.AggregateQuote;
import com.solstice.stocks.repository.StockRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuoteController {

  private final Logger log = LoggerFactory.getLogger(this.getClass());
  private final long ONE_DAY_IN_MILLISECONDS = 86400000L;

  private StockRepository stockRepository;

  public QuoteController(StockRepository stockRepository) {
    this.stockRepository = stockRepository;
  }

  @GetMapping("/{symbol}/{dateString}")
  public AggregateQuote getStock(@PathVariable String symbol, @PathVariable String dateString) {
    Date parsedDate = new Date();
    try {
      parsedDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
    } catch (ParseException e) {
      log.error("Failed to parse date in url. Stacktrace:");
      e.printStackTrace();
    }

    return stockRepository.getAggregateData(symbol, parsedDate,
        new Date(parsedDate.getTime() + ONE_DAY_IN_MILLISECONDS));
  }
}
