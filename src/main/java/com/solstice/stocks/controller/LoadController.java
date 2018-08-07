package com.solstice.stocks.controller;

import com.solstice.stocks.data.Quote;
import com.solstice.stocks.data.RawQuote;
import com.solstice.stocks.repository.QuoteRepository;
import com.solstice.stocks.service.DatasetUtilService;
import com.solstice.stocks.service.JsonUtilService;
import java.io.IOException;
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

  private JsonUtilService jsonUtilService;
  private DatasetUtilService datasetUtilService;
  private QuoteRepository quoteRepository;

  public LoadController(JsonUtilService jsonUtilService, DatasetUtilService datasetUtilService,
      QuoteRepository quoteRepository) {
    this.jsonUtilService = jsonUtilService;
    this.datasetUtilService = datasetUtilService;
    this.quoteRepository = quoteRepository;
  }


  @PostMapping
  public ResponseEntity<List<Quote>> load() throws IOException {
    List<RawQuote> rawQuotes = jsonUtilService.getStocksFromJson();
    List<Quote> quotes = datasetUtilService.convertDatasetFromRawToEntities(rawQuotes);
    quoteRepository.saveAll(quotes);
    return new ResponseEntity<>(quotes, HttpStatus.CREATED);
  }

  @GetMapping
  public List<Quote> getStocks() {
    return (List<Quote>) quoteRepository.findAll();
  }
}
