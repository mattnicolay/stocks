package com.solstice.stocks.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solstice.stocks.data.Stock;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

@Service
public class JsonService {

  private final Logger log = LoggerFactory.getLogger(this.getClass());

  public List<Stock> getStocksFromJson() {
    List<Stock> stocks = null;
    try {
      File dataset = new File("/stocks.json");
      ObjectMapper jsonMapper = new ObjectMapper();
      // read JSON array in and save as a List<Quote>
      stocks = jsonMapper.readValue(dataset, new TypeReference<List<Stock>>(){});
    } catch (Exception e) {
      log.error("Error reading from json file. Stacktrace: ");
      e.printStackTrace();
    }

    return stocks;
  }
}
