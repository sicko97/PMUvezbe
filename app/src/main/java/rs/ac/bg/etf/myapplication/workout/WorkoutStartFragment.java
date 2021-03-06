package rs.ac.bg.etf.myapplication.workout;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.os.Looper;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import dagger.hilt.android.AndroidEntryPoint;
import rs.ac.bg.etf.myapplication.MainActivity;
import rs.ac.bg.etf.myapplication.R;
import rs.ac.bg.etf.myapplication.data.Workout;
import rs.ac.bg.etf.myapplication.databinding.FragmentWorkoutStartBinding;

@AndroidEntryPoint
public class WorkoutStartFragment extends Fragment {

    private static final String SHARED_PREFERENCES_NAME = "workout-shared-preferences";
    private FragmentWorkoutStartBinding binding;
    private NavController navController;
    private MainActivity mainActivity;
    private WorkoutViewModel workoutViewModel;
    private static final String START_TIMESTAMP_KEY = "start-timestamp-key";
    private Timer timer;
    private SharedPreferences sharedPreferences;

    public WorkoutStartFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivity = (MainActivity) requireActivity();
        workoutViewModel = new ViewModelProvider(mainActivity).get(WorkoutViewModel.class);

        sharedPreferences = mainActivity
                .getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentWorkoutStartBinding.inflate(inflater, container, false);
        timer = new Timer();
        if (sharedPreferences.contains(START_TIMESTAMP_KEY)) {
            startWorkout(sharedPreferences.getLong(START_TIMESTAMP_KEY, new Date().getTime()));
        }
        binding.start.setOnClickListener(view -> {
            startWorkout(new Date().getTime());
        });
        binding.cancel.setOnClickListener(view ->{
            cancelWorkout();
        });
        binding.finish.setOnClickListener(view ->{
            finishWorkout();
        });
        mainActivity.getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                stopWorkout();
            }
        });
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        timer.cancel();
    }

    private void startWorkout(long startTimeStamp) {
        binding.start.setEnabled(false);
        binding.finish.setEnabled(true);
        binding.cancel.setEnabled(true);
        binding.power.setEnabled(true);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(START_TIMESTAMP_KEY, startTimeStamp);
        editor.commit();
        Handler handler = new Handler(Looper.getMainLooper());
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                long elapsed = new Date().getTime() - startTimeStamp;
                int miliseconds = (int) ((elapsed % 1000) / 10);
                int seconds = (int) ((elapsed / 1000) % 60);
                int minutes = (int) ((elapsed / (1000 * 60)) % 60);
                int hours = (int) ((elapsed / (1000 * 60 * 60)) % 24);
                StringBuilder workoutDuration = new StringBuilder();
                workoutDuration.append(String.format("%02d", hours)).append(":");
                workoutDuration.append(String.format("%02d", minutes)).append(":");
                workoutDuration.append(String.format("%02d", seconds)).append(":");
                workoutDuration.append(String.format("%02d", miliseconds)).append(":");
                handler.post(() -> binding.workoutDuration.setText(workoutDuration));
            }
        }, 0, 10);
    }

    private void cancelWorkout(){
        stopWorkout();
    }

    private void finishWorkout(){
        long startTimestamp = sharedPreferences.getLong(START_TIMESTAMP_KEY,new Date().getTime());
        long elapsed = new Date().getTime() - startTimestamp;
        double minutes = elapsed / (1000.0 *60);
        workoutViewModel.insertWorkout(new Workout(
                0,
                new Date(),
                "Dobar trening",
                0.2 * minutes,
                minutes
        ));
        stopWorkout();
    }

    private void stopWorkout() {
        sharedPreferences.edit().remove(START_TIMESTAMP_KEY).commit();
        navController.navigateUp();
    }

}