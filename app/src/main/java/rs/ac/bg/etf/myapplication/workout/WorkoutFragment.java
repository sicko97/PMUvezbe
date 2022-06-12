package rs.ac.bg.etf.myapplication.workout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.Date;

import dagger.hilt.android.AndroidEntryPoint;
import rs.ac.bg.etf.myapplication.MainActivity;
import rs.ac.bg.etf.myapplication.data.RunDatabase;
import rs.ac.bg.etf.myapplication.data.Workout;
import rs.ac.bg.etf.myapplication.data.WorkoutRepository;
import rs.ac.bg.etf.myapplication.databinding.FragmentWorkoutBinding;

@AndroidEntryPoint
public class WorkoutFragment extends Fragment {
    private FragmentWorkoutBinding binding;
    private WorkoutViewModel workoutViewModel;
    private NavController navController;
    private MainActivity mainActivity;

    public WorkoutFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivity = (MainActivity) requireActivity();
        workoutViewModel = new ViewModelProvider(mainActivity).get(WorkoutViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentWorkoutBinding.inflate(inflater, container, false);

        WorkoutAdapter workoutAdapter = new WorkoutAdapter();
        workoutViewModel.getWorkoutList().observe(
                getViewLifecycleOwner(),
                workoutAdapter::setWorkoutList);

        binding.recyclerView.setAdapter(workoutAdapter);

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(mainActivity));

        binding.floatingActionButton.setOnClickListener(view -> {
            NavDirections action = WorkoutFragmentDirections.actionWorkoutCreate();
            navController.navigate(action);
        });


        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
    }
}