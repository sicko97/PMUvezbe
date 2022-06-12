package rs.ac.bg.etf.myapplication.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;


@Dao
public interface WorkoutDao {

    @Insert
    long insert(Workout workout);

    @Query("SELECT * FROM Workout")
    List<Workout> getAll();

    @Query("SELECT * FROM Workout")
    LiveData<List<Workout>> getAllLiveData();

    @Query("SELECT * FROM Workout ORDER BY distance DESC")
    LiveData<List<Workout>> getAllSortedLiveData();

}
