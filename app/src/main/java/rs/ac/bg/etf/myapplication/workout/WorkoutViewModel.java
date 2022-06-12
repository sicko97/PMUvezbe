package rs.ac.bg.etf.myapplication.workout;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import rs.ac.bg.etf.myapplication.data.Workout;
import rs.ac.bg.etf.myapplication.data.WorkoutRepository;

@HiltViewModel
public class WorkoutViewModel extends ViewModel {

    private final WorkoutRepository workoutRepository;

    @Inject
    public WorkoutViewModel(WorkoutRepository workoutRepository) {
        this.workoutRepository = workoutRepository;
    }

    public void insertWorkout(Workout workout){
        workoutRepository.insert(workout);
    }

    public LiveData<List<Workout>> getWorkoutList(){
      return  workoutRepository.getAllLiveData();
    }

}
