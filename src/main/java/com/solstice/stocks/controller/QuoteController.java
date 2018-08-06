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

  @GetMapping("/{timePeriod}/{symbol}/{dateString}")
  public AggregateQuote getAggregate(@PathVariable String timePeriod, @PathVariable String symbol, @PathVariable String dateString) {
    if (timePeriod.equalsIgnoreCase("daily")) {
      Date date = dateUtilService.parseDate(dateString, "yyyy-MM-dd");

      return quoteRepository.getDailyAggregateData(symbol, date);
    } else if (timePeriod.equalsIgnoreCase("monthly")) {
      Date date = dateUtilService.parseDate(dateString, "yyyy-MM");

      return quoteRepository.getMonthlyAggregateData(symbol, date);
    }
    return null;
  }
}
