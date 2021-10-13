package rs.ac.bg.etf.myapplication;

import android.os.Bundle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {

    public static final String CALORIES_BURNED_KEY = "caloriesBurned";

    public static final String CALORIES_NEEDED_KEY = "caloriesNeeded";

    private boolean dataValid = false;
    private MutableLiveData<Integer> caloriesBurned = new MutableLiveData<>(-1);
    private MutableLiveData<Integer> caloriesNeeded = new MutableLiveData<>(-1);

    public void initByInstanceStateBundle(Bundle bundle){
        if(bundle != null){
            if(!dataValid){
                if(bundle.containsKey(CALORIES_BURNED_KEY)){
                    dataValid = true;
                    caloriesBurned.setValue(bundle.getInt(CALORIES_BURNED_KEY));
                }
                if(bundle.containsKey(CALORIES_NEEDED_KEY)){
                    dataValid=true;
                    caloriesNeeded.setValue(bundle.getInt(CALORIES_NEEDED_KEY));
                }
            }
        }
    }


    public LiveData<Integer> getCaloriesBurned(){
        return caloriesBurned;
    }

    public LiveData<Integer> getCaloriesNeeded() {return caloriesNeeded ;}

    public void updateValues(double weight , double height , int age ,boolean isMale,
                                                      double duration , double met){

        dataValid = true;
        double caloriesNeededValue = 0;
        if(isMale){
            caloriesNeededValue = 66+13.7*weight + 5*height -6.8*age;
        }else{
            caloriesNeededValue = 655.1 + 9.6*weight  + 1.9*height - 4.7* age;
        }

        double caloriesBurnedValue = duration * met * 3.5 * weight / 200;

        caloriesNeeded.setValue((int) caloriesNeededValue);
        caloriesBurned.setValue((int) caloriesBurnedValue);

    }


}
