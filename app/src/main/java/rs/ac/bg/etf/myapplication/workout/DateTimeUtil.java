package rs.ac.bg.etf.myapplication.workout;

import java.text.SimpleDateFormat;
import java.util.AbstractMap;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUtil {

    private static final SimpleDateFormat simpleDateFormat =
            new SimpleDateFormat("dd/MM/yyyy");

    public static SimpleDateFormat getSimpleDateFormat(){
        return simpleDateFormat;
    }

    private static final Calendar calendar = Calendar.getInstance();

    public static String realMinutesToString(double realMinutes){
        int minutes = (int) realMinutes;
        int seconds = (int) (60* (realMinutes-minutes));
        return minutes + ":" + String.format("%2d" , seconds);
    }

    public static Date getDate(int year, int month, int day){
            calendar.set(year,month,day);
            return calendar.getTime();
    }
}
