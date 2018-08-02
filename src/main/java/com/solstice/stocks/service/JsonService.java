package com.solstice.stocks.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solstice.stocks.data.Quote;
import java.io.File;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class JsonService {

  private final Logger log = LoggerFactory.getLogger(this.getClass());
  private final String jsonFilePath = this.getClass().getClassLoader()
      .getResource("stocks.json").getFile();

  public List<Quote> getStocksFromJson() {
    List<Quote> quotes = null;
    try {
      File dataset = new File(jsonFilePath);
      ObjectMapper jsonMapper = new ObjectMapper();
      // read JSON array in and save as a List<Quote>
      quotes = jsonMapper.readValue(dataset, new TypeReference<List<Quote>>(){});
    } catch (Exception e) {
      log.error("Error reading from json file. Stacktrace: ");
      e.printStackTrace();
    }

    return quotes;
  }
}
