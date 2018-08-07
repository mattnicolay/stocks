package com.solstice.stocks.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="symbols")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class Symbol {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @JsonProperty("Symbol ID")
  private int id;
  @Column(nullable = false)
  @JsonProperty("Symbol")
  private String symbol;

  public Symbol() {}

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
