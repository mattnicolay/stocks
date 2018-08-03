package com.solstice.stocks.controller;

import com.solstice.stocks.data.Quote;
import com.solstice.stocks.repository.StockRepository;
import com.solstice.stocks.service.JsonService;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/load")
@PropertySource("classpath:application.properties")
public class LoadController {

  @Value("${dataset-url}")
  private URL datasetUrl;
  private JsonService jsonService;
  private StockRepository stockRepository;

  public LoadController(JsonService jsonService, StockRepository stockRepository) {
    this.jsonService = jsonService;
    this.stockRepository = stockRepository;
  }


  @PostMapping
  public ResponseEntity<List<Quote>> load() {
    List<Quote> quotes = jsonService.getStocksFromJson();
    stockRepository.saveAll(quotes);
    return new ResponseEntity<>(quotes, HttpStatus.CREATED);
  }

  @GetMapping
  public List<Quote> getStocks() {
    return (List<Quote>)stockRepository.findAll();
  }
}
