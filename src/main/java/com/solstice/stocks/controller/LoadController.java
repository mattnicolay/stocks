package com.solstice.stocks.controller;

import com.solstice.stocks.data.Quote;
import com.solstice.stocks.data.RawQuote;
import com.solstice.stocks.repository.QuoteRepository;
import com.solstice.stocks.service.DatasetUtilService;
import com.solstice.stocks.service.JsonService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/load")
public class LoadController {

  private JsonService jsonService;
  private DatasetUtilService datasetUtilService;
  private QuoteRepository quoteRepository;

  public LoadController(JsonService jsonService, DatasetUtilService datasetUtilService,
      QuoteRepository quoteRepository) {
    this.jsonService = jsonService;
    this.datasetUtilService = datasetUtilService;
    this.quoteRepository = quoteRepository;
  }


  @PostMapping
  public ResponseEntity<List<Quote>> load() {
    List<RawQuote> rawQuotes = jsonService.getStocksFromJson();
    List<Quote> quotes = datasetUtilService.convertDatasetFromRawToEntities(rawQuotes);
    quoteRepository.saveAll(quotes);
    return new ResponseEntity<>(quotes, HttpStatus.CREATED);
  }

  @GetMapping
  public List<Quote> getStocks() {
    return (List<Quote>) quoteRepository.findAll();
  }
}
