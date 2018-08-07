package com.solstice.stocks.repository;

import com.solstice.stocks.data.Symbol;
import org.springframework.data.repository.CrudRepository;

public interface SymbolRepository extends CrudRepository<Symbol, Long>{
  Symbol findBySymbol(String symbol);
}
