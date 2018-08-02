package com.solstice.stocks.controller;

import com.solstice.stocks.data.Stock;
import com.solstice.stocks.service.JsonService;
import java.io.BufferedReader;
import java.io.File;
import java.io.Reader;
import java.net.URL;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
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

  public LoadController(JsonService jsonService) {
    this.jsonService = jsonService;
  }


  @GetMapping
  public ResponseEntity<List<Stock>> load(@RequestParam boolean refresh) {
    if (refresh) {
      File stocksJson = new File("classpath:stocks.json");

    }
    List<Stock> stocks = jsonService.getStocksFromJson();

  }
}
