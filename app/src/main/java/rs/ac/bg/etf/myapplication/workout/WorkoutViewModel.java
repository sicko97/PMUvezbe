package rs.ac.bg.etf.myapplication.workout;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import dagger.assisted.Assisted;
import dagger.assisted.AssistedInject;
import dagger.hilt.android.lifecycle.HiltViewModel;
import rs.ac.bg.etf.myapplication.data.Workout;
import rs.ac.bg.etf.myapplication.data.WorkoutRepository;

@HiltViewModel
public class WorkoutViewModel extends ViewModel {

    private final WorkoutRepository workoutRepository;
    private final SavedStateHandle savedStateHandle;
    private static final String SORTED_KEY = "sorted-key";
    private boolean sorted = false;
    private final LiveData<List<Workout>> workouts ;

    @Inject
    public WorkoutViewModel(WorkoutRepository workoutRepository,
                           SavedStateHandle savedStateHandle)  {
        this.workoutRepository = workoutRepository;
        this.savedStateHandle = savedStateHandle;

      workouts =  Transformations.switchMap(
          savedStateHandle.getLiveData(SORTED_KEY , false),
          sorted -> {
              if (!sorted) {
                  return workoutRepository.getAllLiveData();
              } else {
                  return workoutRepository.getAllSortedLiveData();
              }
          }
        );
    }

    public void invertSorted() {
        savedStateHandle.set(SORTED_KEY,sorted = !sorted);
    }

    public void insertWorkout(Workout workout) {
        workoutRepository.insert(workout);
    }

    public LiveData<List<Workout>> getWorkoutList() {
       return workouts;
    }

}
