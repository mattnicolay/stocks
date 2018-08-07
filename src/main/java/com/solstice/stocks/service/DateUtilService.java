package com.solstice.stocks.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DateUtilService {


  private final Logger log = LoggerFactory.getLogger(this.getClass());
  private final long ONE_DAY_IN_MILLISECONDS = 86400000L;

  public Date parseDate(String dateString, String pattern) throws ParseException {
    return new SimpleDateFormat(pattern).parse(dateString);
  }

  public Date getNextDay(Date date) {
    return new Date(date.getTime() + ONE_DAY_IN_MILLISECONDS);
  }

  public Date getNextMonth(Date date) {
    Calendar calendar = new GregorianCalendar();
    calendar.setTime(date);
    long monthOffset = ONE_DAY_IN_MILLISECONDS * calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    return new Date(date.getTime() + monthOffset);
  }
}
