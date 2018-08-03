package com.solstice.stocks.controller;

import com.solstice.stocks.data.AggregateQuote;
import com.solstice.stocks.repository.QuoteRepository;
import com.solstice.stocks.service.DateUtilService;
import java.util.Date;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuoteController {


  private QuoteRepository quoteRepository;
  private DateUtilService dateUtilService;

  public QuoteController(QuoteRepository quoteRepository, DateUtilService dateUtilService) {
    this.quoteRepository = quoteRepository;
    this.dateUtilService = dateUtilService;
  }

  @GetMapping("/daily/{symbol}/{dateString}")
  public AggregateQuote getAggregate(@PathVariable String symbol, @PathVariable String dateString) {
    Date date = dateUtilService.parseDate(dateString, "yyyy-MM-dd");

    return quoteRepository.getAggregateData(symbol, date, dateUtilService.getNextDay(date));
  }

  @GetMapping("/monthly/{symbol}/{dateString}")
  public AggregateQuote getAggregateMonthly(@PathVariable String symbol, @PathVariable String dateString) {
    Date fromDate = dateUtilService.parseDate(dateString, "yyyy-MM");
    Date toDate = dateUtilService.getNextMonth(fromDate);

    return quoteRepository.getAggregateData(symbol, fromDate, toDate);
  }
}
