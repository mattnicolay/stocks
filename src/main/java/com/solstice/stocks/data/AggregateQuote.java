package com.solstice.stocks.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;


public class AggregateQuote {

  @JsonProperty("symbol")
  private String symbol;
  @JsonProperty("highestPrice")
  private double highestPrice;
  @JsonProperty("lowestPrice")
  private double lowestPrice;
  @JsonProperty("totalVolume")
  private long totalVolume;
  @JsonProperty("date")
  private Date date;

  public AggregateQuote(String symbol, double highestPrice, double lowestPrice, long totalVolume,
      Date date) {
    this.symbol = symbol;
    this.highestPrice = highestPrice;
    this.lowestPrice = lowestPrice;
    this.totalVolume = totalVolume;
    this.date = date;
  }

  public String getSymbol() {
    return symbol;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  public double getHighestPrice() {
    return highestPrice;
  }

  public void setHighestPrice(double highestPrice) {
    this.highestPrice = highestPrice;
  }

  public double getLowestPrice() {
    return lowestPrice;
  }

  public void setLowestPrice(double lowestPrice) {
    this.lowestPrice = lowestPrice;
  }

  public long getTotalVolume() {
    return totalVolume;
  }

  public void setTotalVolume(long totalVolume) {
    this.totalVolume = totalVolume;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }
}
