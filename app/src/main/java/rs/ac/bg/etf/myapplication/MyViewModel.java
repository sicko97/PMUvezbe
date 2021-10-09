package rs.ac.bg.etf.myapplication;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {

    private MutableLiveData<Integer> caloriesBurned = new MutableLiveData<>();


    public LiveData<Integer> getCaloriesBurned(){
        return caloriesBurned;
    }

    public void setCaloriesBurned(int value){ caloriesBurned.setValue(value); }

}
