package com.solstice.stocks.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;

public class AggregateQuote {

  @JsonProperty("Symbol")
  private String symbol;
  @JsonProperty("Highest Price")
  private double maxPrice;
  @JsonProperty("Lowest Price")
  private double minPrice;
  @JsonProperty("Closing Price")
  private double closingPrice;
  @JsonProperty("Total Volume")
  private int totalVolume;
  @JsonProperty("Date")
  private Date date;

  public AggregateQuote(String symbol, double maxPrice, double minPrice, double closingPrice, int totalVolume,
      Date date) {
    this.symbol = symbol;
    this.maxPrice = maxPrice;
    this.minPrice = minPrice;
    this.closingPrice = closingPrice;
    this.totalVolume = totalVolume;
    this.date = date;
  }

  public String getSymbol() {
    return symbol;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  public double getMaxPrice() {
    return maxPrice;
  }

  public void setMaxPrice(double maxPrice) {
    this.maxPrice = maxPrice;
  }

  public double getMinPrice() {
    return minPrice;
  }

  public void setMinPrice(double minPrice) {
    this.minPrice = minPrice;
  }

  public double getClosingPrice() {
    return closingPrice;
  }

  public void setClosingPrice(double closingPrice) {
    this.closingPrice = closingPrice;
  }

  public int getTotalVolume() {
    return totalVolume;
  }

  public void setTotalVolume(int totalVolume) {
    this.totalVolume = totalVolume;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }
}
