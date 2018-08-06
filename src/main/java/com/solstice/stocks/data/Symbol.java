package com.solstice.stocks.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="symbols")
public class Symbol {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;
  @Column(nullable = false)
  private String symbol;

  public Symbol(String symbol) {
    this.symbol = symbol;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getSymbol() {
    return symbol;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }
}
