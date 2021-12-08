package rs.ac.bg.etf.myapplication.data;

import androidx.room.TypeConverter;

import java.util.Date;

public class DateConverter {

    @TypeConverter
    public long dateToTimestamp(Date date) {
        return date.getTime();
    }
    @TypeConverter
    public Date timeStampToDate(long timestamp){
        return new Date(timestamp);
    }
}
