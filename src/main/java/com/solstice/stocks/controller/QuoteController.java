package com.solstice.stocks.controller;

import com.solstice.stocks.data.AggregateQuote;
import com.solstice.stocks.repository.StockRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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

  @GetMapping("/daily/{symbol}/{dateString}")
  public AggregateQuote getAggregate(@PathVariable String symbol, @PathVariable String dateString) {
    Date date = parseDate(dateString, "yyyy-MM-dd");

    return stockRepository.getAggregateData(symbol, date,
        new Date(date.getTime() + ONE_DAY_IN_MILLISECONDS));
  }

  @GetMapping("/monthly/{symbol}/{dateString}")
  public AggregateQuote getAggregateMonthly(@PathVariable String symbol, @PathVariable String dateString) {
    Date date = parseDate(dateString, "yyyy-MM");

    return stockRepository.getAggregateData(symbol, date,
        new Date(date.getTime() + getMonthlyOffset(date)));
  }

  private Date parseDate(String dateString, String pattern) {
    Date parsedDate = new Date();
    try {
      parsedDate = new SimpleDateFormat(pattern).parse(dateString);
    } catch (ParseException e) {
      log.error("Failed to parse date in url. Stacktrace:");
      e.printStackTrace();
    }
    return parsedDate;
  }

  private long getMonthlyOffset(Date date) {
    Calendar calendar = new GregorianCalendar();
    calendar.setTime(date);
    return ONE_DAY_IN_MILLISECONDS * calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
  }
}
