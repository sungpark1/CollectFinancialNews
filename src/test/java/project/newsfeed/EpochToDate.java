package project.newsfeed;

import java.sql.SQLOutput;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class EpochToDate {

    public static void main(String[] args) throws ParseException {

//      "dateLastPublished": "2021-04-15T20:10:32+0000",
//      Result should be 04/15/2021 8:10:32sec PM

        //Yahoo
//        long epoch = Long.parseLong("1618605130") * 1000;
        long epoch = 1618605130L * 1000;
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T' HH:mm:ss");
//        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date testOne = new Date(epoch);

        //CNBC
        String CNBC = "2021-04-15T20:10:32+0000";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);
        formatter.setTimeZone(TimeZone.getTimeZone("America/New_York"));
        Date testTwo = formatter.parse(CNBC);

        String testing = "2021-04-15T20:10:32+0000";
        testing = testing.substring(0, testing.length() - 5);
        System.out.println(testing);

        List<Date> list = new ArrayList<>();
        list.add(testOne);
        list.add(testTwo);

        PriorityQueue<Date> heap = new PriorityQueue<>((a, b) -> b.compareTo(a));
        heap.addAll(list);

        while (!heap.isEmpty()) {
            System.out.println(heap.poll());
        }

        /////////////////////////////////////////////////////////////////////
        //ABOVE SORTING WORKS//
        System.out.println(" ");

        long difference_In_Time = testOne.getTime() - testTwo.getTime();
        System.out.println(difference_In_Time);
        long difference_In_Minutes = (difference_In_Time / (1000 * 60)) % 60;
        long difference_In_Hours = (difference_In_Time / (1000 * 60 * 60)) % 24;
        long difference_In_Days = (difference_In_Time / (1000 * 60 * 60 * 24)) % 365;

//        System.out.println(
//                difference_In_Days + " days, "
//                        + difference_In_Hours + " hours, "
//                        + difference_In_Minutes + " minutes."
//        );

        if(difference_In_Days == 0 && difference_In_Hours == 0){
            System.out.println(difference_In_Minutes + "m");
        } else if(difference_In_Days == 0){
            System.out.println(difference_In_Hours + "h");
        } else {
            System.out.println(difference_In_Days + "d");
        }


    }
}
