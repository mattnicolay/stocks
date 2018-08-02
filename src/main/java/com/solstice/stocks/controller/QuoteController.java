package com.solstice.stocks.controller;

import com.solstice.stocks.data.Quote;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.Timestamp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuoteController {

  private final Logger log = LoggerFactory.getLogger(this.getClass());

  @GetMapping("/{symbol}/{dateString}")
  public Quote getStock(@PathVariable String symbol, @PathVariable String dateString) {
    Date parsedDate = new Date();
    try {
      parsedDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
    } catch (ParseException e) {
      log.error("Failed to parse date in url. Stacktrace:");
      e.printStackTrace();
    }
    //get highest price of stock

    //get lowest price of stock

    //get total volume
    return new Quote("test", 0, 0, new Timestamp(parsedDate.getTime()));
  }
}
