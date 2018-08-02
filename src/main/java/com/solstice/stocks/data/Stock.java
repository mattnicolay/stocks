package com.solstice.stocks.data;

import java.sql.Timestamp;

public class Stock {

//  private int id;
  private String symbol;
  private double price;
  private int volume;
  private Timestamp date;

  public Stock() {
  }

  public Stock(String symbol, double price, int volume, Timestamp date) {
    this.symbol = symbol;
    this.price = price;
    this.volume = volume;
    this.date = date;
  }

//  public int getId() {
//    return id;
//  }
//
//  public void setId(int id) {
//    this.id = id;
//  }

  public String getSymbol() {
    return symbol;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public int getVolume() {
    return volume;
  }

  public void setVolume(int volume) {
    this.volume = volume;
  }

  public Timestamp getDate() {
    return date;
  }

  public void setDate(Timestamp date) {
    this.date = date;
  }
}
