package com.solstice.stocks.repository;

import com.solstice.stocks.data.Symbol;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface SymbolRepository extends CrudRepository<Symbol, Long>{
  @Query(value = "select id from Symbol where symbol = ?1")
  int findIdBySymbol(String symbol);
}
