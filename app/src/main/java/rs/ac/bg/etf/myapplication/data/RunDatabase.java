package rs.ac.bg.etf.myapplication.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@TypeConverters(value={DateConverter.class})
@Database(entities = {Workout.class} , version = 1 , exportSchema = false)
public abstract class RunDatabase extends RoomDatabase {
    public abstract WorkoutDao workoutDao();
}
