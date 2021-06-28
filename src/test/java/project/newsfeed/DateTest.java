package project.newsfeed;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateTest {
    public static void main(String[] args) throws ParseException {

        //"2021-04-15T20:38:11+0000"
        String test = "2021-04-15T20:38:11+0000";

        test = test.substring(0, test.length() - 5);
        System.out.println(test);
        LocalDateTime localDate = LocalDateTime.parse(test);
        long timeInSeconds = localDate.toEpochSecond(ZoneOffset.UTC);

        System.out.println(timeInSeconds);
        //1618519091

        long epoch = 1618519091L * 1000;
        Date date = new Date(epoch);
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        String formatted = formatter.format(date);
        System.out.println(formatted);
    }
}
