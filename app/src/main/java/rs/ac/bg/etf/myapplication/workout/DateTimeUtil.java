package rs.ac.bg.etf.myapplication.workout;

import java.text.SimpleDateFormat;
import java.util.AbstractMap;

public class DateTimeUtil {

    private static final SimpleDateFormat simpleDateFormat =
            new SimpleDateFormat("dd/MM/yyyy");

    public static SimpleDateFormat getSimpleDateFormat(){
        return simpleDateFormat;
    }

    public static String realMinutesToString(double realMinutes){
        int minutes = (int) realMinutes;
        int seconds = (int) (60* (realMinutes-minutes));
        return minutes + ":" + String.format("%2d" , seconds);
    }
}
