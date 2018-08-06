package com.solstice.stocks.service;

import com.solstice.stocks.data.Quote;
import com.solstice.stocks.data.RawQuote;
import com.solstice.stocks.data.Symbol;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class DatasetUtilService {
  public List<Quote> convertDatasetFromRawToEntities(List<RawQuote> rawQuotes) {
    Symbol symbol = null;
    List<Quote> quotes = new ArrayList<>();
    for (RawQuote rawQuote : rawQuotes) {
      // only create new Symbol entity for this symbol if one does not already exist
      if (symbol == null || !rawQuote.getSymbol().equals(symbol.getSymbol())){
        symbol = new Symbol(rawQuote.getSymbol());
      }
      Quote quote = new Quote(symbol, rawQuote.getPrice(), rawQuote.getVolume(), rawQuote.getDate());

      quotes.add(quote);
    }
    return quotes;
  }
}
