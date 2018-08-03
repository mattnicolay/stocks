package com.solstice.stocks.data;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "Quote")
@Table(name = "stocks")
@SqlResultSetMapping(name="AggregateQuoteMapping", classes = {
    @ConstructorResult(targetClass = AggregateQuote.class,
        columns = {
            @ColumnResult(name="symbol", type=String.class),
            @ColumnResult(name="maxPrice", type=Double.class),
            @ColumnResult(name="minPrice", type=Double.class),
            @ColumnResult(name="closingPrice", type=Double.class),
            @ColumnResult(name="totalVolume", type=Integer.class),
            @ColumnResult(name="date", type=Date.class)
        })
})
@NamedNativeQuery(
    name = "Quote.getAggregateData",
    query = "SELECT symbol, maxPrice, minPrice, totalVolume, closingPrice, date\n"
        + "FROM\n"
        + "(SELECT symbol, MAX(price) AS maxPrice, MIN(price) AS minPrice, SUM(VOLUME) AS totalVolume, MIN(date) as date\n"
        + "FROM stocks\n"
        + "WHERE symbol = :symbol AND date >= :date AND date < :dayAfterDate\n) s1\n"
        + "JOIN\n"
        + "(SELECT price AS closingPrice, MAX(date)\n"
        + " FROM stocks\n"
        + " WHERE symbol = :symbol AND date < :dayAfterDate\n) s2",
    resultSetMapping = "AggregateQuoteMapping"
)
public class Quote {

  @Id
  @GeneratedValue
  private int id;
  @Column(nullable = false)
  private String symbol;
  @Column(nullable = false)
  private double price;
  @Column(nullable = false)
  private int volume;
  @Temporal(TemporalType.TIMESTAMP)
  @Column(nullable = false)
  private Date date;


  public Quote() {
  }

  public Quote(String symbol, double price, int volume, Date date) {
    this.symbol = symbol;
    this.price = price;
    this.volume = volume;
    this.date = date;
  }

  public int getId() {
    return id;
  }

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

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }
}
