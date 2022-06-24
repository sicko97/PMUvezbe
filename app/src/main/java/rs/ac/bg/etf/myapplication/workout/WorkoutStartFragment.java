package rs.ac.bg.etf.myapplication.workout;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import dagger.hilt.android.AndroidEntryPoint;
import rs.ac.bg.etf.myapplication.MainActivity;
import rs.ac.bg.etf.myapplication.R;
import rs.ac.bg.etf.myapplication.databinding.FragmentWorkoutStartBinding;

@AndroidEntryPoint
public class WorkoutStartFragment extends Fragment {

    private FragmentWorkoutStartBinding binding;
    private NavController navController;
    private MainActivity mainActivity;
    private WorkoutViewModel workoutViewModel;

    private Timer timer;
    public WorkoutStartFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivity = (MainActivity) requireActivity();
        workoutViewModel = new ViewModelProvider(mainActivity).get(WorkoutViewModel.class);

        timer  = new Timer();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentWorkoutStartBinding.inflate(inflater, container, false);

        binding.start.setOnClickListener(view->{
            startWorkout(new Date().getTime());
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
            Handler handler = new Handler(Looper.getMainLooper());
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    long elapsed = new Date().getTime() - startTimeStamp;
                    int miliseconds = (int)((elapsed%1000)/10);
                    int seconds = (int) ((elapsed/1000)%60);
                    int minutes = (int) ((elapsed/(1000*60))%60);
                    int hours = (int)((elapsed/(1000*60*60))%24);
                    StringBuilder workoutDuration = new StringBuilder();
                    workoutDuration.append(String.format("%02d",hours)).append(":");
                    workoutDuration.append(String.format("%02d",minutes)).append(":");
                    workoutDuration.append(String.format("%02d",seconds)).append(":");
                    workoutDuration.append(String.format("%02d",miliseconds)).append(":");
                    handler.post(()->binding.workoutDuration.setText(workoutDuration));
                }
            },0,10);
    }

}