package com.solstice.stocks.controller;

import com.solstice.stocks.data.Stock;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/load")
@PropertySource("classpath:application.properties")
public class LoadController {

  private final Logger log = LoggerFactory.getLogger(this.getClass());
  private final String jsonFilePath = this.getClass().getClassLoader()
      .getResource("stocks.json").getFile();

  @Value("${dataset-url}")
  private URL datasetUrl;
  private JsonService jsonService;
  private StockRepository stockRepository;

  public LoadController(JsonService jsonService, StockRepository stockRepository) {
    this.jsonService = jsonService;
    this.stockRepository = stockRepository;
  }


  @PostMapping
  public ResponseEntity<List<Stock>> load(@RequestParam(required = false) boolean refresh) {
    if (refresh) {
      try{
        log.info("Refreshing json dataset from url");
        ReadableByteChannel rbc = Channels.newChannel(datasetUrl.openStream());
        FileOutputStream fos = new FileOutputStream(jsonFilePath);
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
      } catch (IOException e) {
        log.error("Failed to refresh dataset from url. Stacktrace: ");
        e.printStackTrace();
      }
    }
    List<Stock> stocks = jsonService.getStocksFromJson();
    stockRepository.insertStocks(stocks);
    return new ResponseEntity<>(stocks, HttpStatus.CREATED);
  }
}
