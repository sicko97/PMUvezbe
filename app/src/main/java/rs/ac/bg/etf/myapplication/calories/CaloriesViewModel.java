package rs.ac.bg.etf.myapplication.calories;

import android.os.Bundle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

public class CaloriesViewModel extends ViewModel {

    public static final String CALORIES_BURNED_KEY = "caloriesBurned";

    public static final String CALORIES_NEEDED_KEY = "caloriesNeeded";

    private SavedStateHandle savedStateHandle;

    private final LiveData<Integer> caloriesBurned ;
    private final LiveData<Integer> caloriesNeeded ;


    public CaloriesViewModel(SavedStateHandle savedStateHandle) {
        this.savedStateHandle = savedStateHandle;
        LiveData<Integer> caloriesBurnedSaved =
                savedStateHandle.getLiveData(CALORIES_BURNED_KEY, -1);
        caloriesBurned = Transformations.map(caloriesBurnedSaved,caloriesBurnedSavedValue-> {
           return caloriesBurnedSavedValue;

        });
        LiveData<Integer> caloriesNeededSaved =
                savedStateHandle.getLiveData(CALORIES_NEEDED_KEY, -1);
        caloriesNeeded = Transformations.map(caloriesNeededSaved,caloriesNeededSavedValue-> {
            return caloriesNeededSavedValue;

        });
    }


    public LiveData<Integer> getCaloriesBurned() {

        return caloriesBurned;
        // return  savedStateHandle.getLiveData(CALORIES_BURNED_KEY);
    }

    public LiveData<Integer> getCaloriesNeeded() {

        return caloriesNeeded;
        //  return savedStateHandle.getLiveData(CALORIES_NEEDED_KEY)
        //  ;}
    }

    public void updateValues(double weight, double height, int age, boolean isMale,
                             double duration, double met) {

        double caloriesNeededValue = 0;
        if (isMale) {
            caloriesNeededValue = 66 + 13.7 * weight + 5 * height - 6.8 * age;
        } else {
            caloriesNeededValue = 655.1 + 9.6 * weight + 1.9 * height - 4.7 * age;
        }

        double caloriesBurnedValue = duration * met * 3.5 * weight / 200;

        savedStateHandle.set(CALORIES_NEEDED_KEY, (int) caloriesNeededValue);
        savedStateHandle.set(CALORIES_BURNED_KEY, (int) caloriesBurnedValue);


    }


}
