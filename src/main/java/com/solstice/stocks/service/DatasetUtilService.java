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
    List<Symbol> symbols = new ArrayList<>();
    List<Quote> quotes = new ArrayList<>();

    for (RawQuote rawQuote : rawQuotes) {
      Symbol symbol = getSymbol(rawQuote.getSymbol(), symbols);
      Quote quote = new Quote(symbol, rawQuote.getPrice(), rawQuote.getVolume(), rawQuote.getDate());
      quotes.add(quote);
    }
    return quotes;
  }

  private Symbol getSymbol(String symbolString, List<Symbol> symbols) {
    for (Symbol symbol : symbols) {
      if (symbol.getSymbol().equals(symbolString)) {
        return symbol;
      }
    }
    Symbol newSymbol = new Symbol(symbolString);
    symbols.add(newSymbol);
    return newSymbol;
  }
}
