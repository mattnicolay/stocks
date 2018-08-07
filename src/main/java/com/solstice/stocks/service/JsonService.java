package com.solstice.stocks.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solstice.stocks.data.RawQuote;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Service
@PropertySource("classpath:application.properties")
public class JsonService {

  @Value("${dataset-url}")
  private URL datasetUrl;
  private final Logger log = LoggerFactory.getLogger(this.getClass());

  public List<RawQuote> getStocksFromJson() throws IOException {
    List<RawQuote> quotes = null;
//    try {
      ObjectMapper jsonMapper = new ObjectMapper();
      // read JSON array from url and save as a List<Quote>
      quotes = jsonMapper.readValue(datasetUrl, new TypeReference<List<RawQuote>>(){});
//    } catch (Exception e) {
//      log.error("Error reading from json file. Stacktrace: ");
//      e.printStackTrace();
//    }

    return quotes;
  }
}
