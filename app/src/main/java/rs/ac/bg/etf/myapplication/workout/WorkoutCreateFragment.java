package rs.ac.bg.etf.myapplication.workout;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.textfield.TextInputLayout;

import java.text.Format;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Date;

import dagger.hilt.android.AndroidEntryPoint;
import rs.ac.bg.etf.myapplication.MainActivity;
import rs.ac.bg.etf.myapplication.R;
import rs.ac.bg.etf.myapplication.data.RunDatabase;
import rs.ac.bg.etf.myapplication.data.Workout;
import rs.ac.bg.etf.myapplication.data.WorkoutRepository;
import rs.ac.bg.etf.myapplication.databinding.FragmentWorkoutCreateBinding;

@AndroidEntryPoint
public class WorkoutCreateFragment extends Fragment {

    public static final String REQUEST_KEY = "date-picker-request";
    private FragmentWorkoutCreateBinding binding;
    private NavController navController;
    private WorkoutViewModel workoutViewModel;
    private MainActivity mainActivity;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainActivity = (MainActivity) requireActivity();
        workoutViewModel = new ViewModelProvider(mainActivity).get(WorkoutViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentWorkoutCreateBinding.inflate(inflater, container, false);


        binding.toolbar.setNavigationOnClickListener(
                view -> navController.navigateUp());

        binding.date1.setOnClickListener(
                view -> new DatePickerFragment().show(getChildFragmentManager(), null));

        getChildFragmentManager().setFragmentResultListener(REQUEST_KEY, this,
                ((requestKey, result) -> {
                    Date date = (Date) result.getSerializable(DatePickerFragment.SET_DATE_KEY);
                    String dateForEditText = DateTimeUtil.getSimpleDateFormat().format(date);
                    binding.workoutDate.getEditText().setText(dateForEditText);
                }));

        binding.workoutCreate.setOnClickListener(view -> {

            Date date = (Date) parse(binding.workoutDate, DateTimeUtil.getSimpleDateFormat());
            String label = (String) parse(binding.workoutLabel, null);
            Number distance = (Number) parse(binding.workoutDistance, NumberFormat.getInstance());
            Number duration = (Number) parse(binding.workoutDuration, NumberFormat.getInstance());

            if (!(date == null || distance == null || label == null || duration == null)) {
                workoutViewModel.insertWorkout(new Workout(
                        0,
                        date,
                        label,
                        distance.doubleValue(),
                        duration.doubleValue()
                ));

                navController.navigateUp();
            }

        });


        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
    }

    private Object parse(TextInputLayout textInputLayout, Format format) {
        Object result;
        try {
            String inputString = textInputLayout.getEditText().getText().toString();
            if (!inputString.equals("")) {
                if (format != null) {
                    result = format.parseObject(inputString);
                } else {
                    result = inputString;
                }
                textInputLayout.setError(null);
            } else {
                textInputLayout.setError(
                        mainActivity.getResources().getString(R.string.workout_create_error_empty));
                result = null;
            }
        } catch (ParseException e) {
            textInputLayout.setError("Wrong format");
            return null;
        }
        return result;
    }

}