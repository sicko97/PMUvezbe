package rs.ac.bg.etf.myapplication.di;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import rs.ac.bg.etf.myapplication.data.RunDatabase;
import rs.ac.bg.etf.myapplication.data.WorkoutDao;

@Module
@InstallIn(SingletonComponent.class)
public interface DatabaseModule {

    @Provides
    static WorkoutDao provideWorkoutDao(@ApplicationContext Context context){
        return RunDatabase.getInstance(context).workoutDao();
    }


}
