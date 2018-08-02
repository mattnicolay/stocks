package com.solstice.stocks.repository;

import com.solstice.stocks.data.Stock;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


@Repository
public class StockRepository {

  private final JdbcTemplate jdbcTemplate;
  private final RowMapper<Stock> rowMapper = (ResultSet rs, int row) -> new Stock(
//      rs.getInt("id"),
      rs.getString("symbol"),
      rs.getDouble("price"),
      rs.getInt("volume"),
      rs.getTimestamp("date")
  );

  public StockRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  private final String SQL_QUERY_INSERT = "INSERT INTO stocks (symbol, price, volume, date) "
      + "VALUES (?, ?, ?, ?)";

  public void insertStocks(List<Stock> stocks) {

    jdbcTemplate.batchUpdate(SQL_QUERY_INSERT, new BatchPreparedStatementSetter() {
      @Override
      public void setValues(PreparedStatement ps, int i) throws SQLException {
        Stock stock = stocks.get(i);
//        ps.setInt(1, stock.getId());
        ps.setString(1, stock.getSymbol());
        ps.setDouble(2, stock.getPrice());
        ps.setInt(3, stock.getVolume());
        ps.setTimestamp(4, stock.getDate());
      }

      @Override
      public int getBatchSize() {
        return stocks.size();
      }
    });
  }

  private final String SQL_QUERY_SELECT_ALL = "SELECT * FROM stocks";

  public List<Stock> getStocks() {
    return jdbcTemplate.query(SQL_QUERY_SELECT_ALL, rowMapper);
  }

}
