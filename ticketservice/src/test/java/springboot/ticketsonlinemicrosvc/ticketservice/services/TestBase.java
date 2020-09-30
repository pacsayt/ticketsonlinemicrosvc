package springboot.ticketsonline.services;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * pt++ : the database returned (*.find*()) Timestamp instead of Date
 * java.sql.Timestamp extends java.util.Date
 */

public class TestBase
{
  // pt++ : as there's no reasonable constructor for Date
  protected static Timestamp stringToDate(String dateInStringFormat) throws ParseException
  {
    Date newdate = null;

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    newdate = dateFormat.parse( dateInStringFormat);

    return new Timestamp( newdate.getTime());
  }
}