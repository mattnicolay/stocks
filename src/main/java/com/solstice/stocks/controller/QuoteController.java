package com.solstice.stocks.controller;

import com.solstice.stocks.data.AggregateQuote;
import com.solstice.stocks.data.TimePeriod;
import com.solstice.stocks.repository.QuoteRepository;
import com.solstice.stocks.repository.SymbolRepository;
import com.solstice.stocks.service.DateUtilService;
import java.text.ParseException;
import java.util.Date;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class QuoteController {


  private QuoteRepository quoteRepository;
  private SymbolRepository symbolRepository;
  private DateUtilService dateUtilService;

  public QuoteController(QuoteRepository quoteRepository, SymbolRepository symbolRepository,
      DateUtilService dateUtilService) {
    this.quoteRepository = quoteRepository;
    this.symbolRepository = symbolRepository;
    this.dateUtilService = dateUtilService;
  }

  @GetMapping("/daily/{symbol}/{dateString}")
  public AggregateQuote getAggregateDaily(
      @PathVariable String symbol,
      @PathVariable String dateString) throws ParseException {

    return getAggregate(symbol, dateString, TimePeriod.DAY, "yyyy-MM-dd");
  }

  @GetMapping("/monthly/{symbol}/{dateString}")
  public AggregateQuote getAggregateMonthly(
      @PathVariable String symbol,
      @PathVariable String dateString) throws ParseException {

    return getAggregate(symbol, dateString, TimePeriod.MONTH, "yyyy-MM");
  }

  private AggregateQuote getAggregate(
      String symbol,
      String dateString,
      TimePeriod timePeriod,
      String pattern)
      throws ParseException {
    int symbolId = symbolRepository.findBySymbol(symbol).getId();
    Date fromDate = dateUtilService.parseDate(dateString, pattern);
    Date toDate = dateUtilService.getNext(timePeriod, fromDate);

    return quoteRepository.getAggregateData(symbolId, fromDate, toDate);
  }
}
