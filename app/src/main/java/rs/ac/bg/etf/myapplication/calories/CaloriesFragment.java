package rs.ac.bg.etf.myapplication.calories;

import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.text.NumberFormat;
import java.text.ParseException;

import rs.ac.bg.etf.myapplication.LifeCycleAwareLogger;
import rs.ac.bg.etf.myapplication.MainActivity;
import rs.ac.bg.etf.myapplication.R;
import rs.ac.bg.etf.myapplication.databinding.FragmentCaloriesBinding;
import rs.ac.bg.etf.myapplication.threading.CustomDequeThread;
import rs.ac.bg.etf.myapplication.threading.CustomLooperThread;

public class CaloriesFragment extends Fragment {

    private CaloriesViewModel caloriesViewModel;
    private FragmentCaloriesBinding binding;
    private MainActivity mainActivity;
    private NavController navController;

    private CustomLooperThread customLooperThread;

    public CaloriesFragment() {
        //  getLifecycle().addObserver(new LifeCycleAwareLogger(MainActivity.LOG_TAG , CaloriesFragment.class.getSimpleName()));
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        customLooperThread = new CustomLooperThread();
        customLooperThread.start();

        mainActivity = (MainActivity) requireActivity();
        caloriesViewModel = new ViewModelProvider(this).get(CaloriesViewModel.class);

    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        binding = FragmentCaloriesBinding.inflate(inflater, container, false);


        caloriesViewModel.getCaloriesBurned().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer burned) {
                if (burned != -1) {
                    String prefix = getResources().getString(R.string.calories_burned);
                    binding.burned.setText(prefix + ":" + burned + "kcal");
                }
            }
        });

        caloriesViewModel.getCaloriesNeeded().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer needed) {
                if (needed != -1) {
                    String prefix = getResources().getString(R.string.calories_needed);
                    binding.needed.setText(prefix + ":" + needed + "kcal");
                }
            }
        });
        String[] metStrings = getResources().getStringArray(R.array.met_strings);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                mainActivity,
                android.R.layout.simple_list_item_1,
                metStrings);
        binding.spinner.setAdapter(arrayAdapter);

        binding.calculate.setOnClickListener(view -> {


            double weight = fetchNumber(binding.weight).doubleValue();
            double height = fetchNumber(binding.height).doubleValue();
            int age = fetchNumber(binding.age).intValue();

            if (binding.radioGroup.getCheckedRadioButtonId() == -1) {
                Toast.makeText(
                        mainActivity,
                        R.string.calories_error_message,
                        Toast.LENGTH_SHORT).show();
                return;
            }
            boolean isMale = binding.male.isChecked();

            double duration = fetchNumber(binding.duration).doubleValue();

            TypedArray metValues = getResources().obtainTypedArray(R.array.met_values);
            double met = metValues.getFloat(binding.spinner.getSelectedItemPosition(), 0);
            metValues.recycle();
            caloriesViewModel.updateValues(weight, height, age, isMale, duration, met);


            Handler newThreadHandler = new Handler(customLooperThread.getLooper());

            final int SLEEP_PERIOD = 1000;
            newThreadHandler.post(() -> {
                SystemClock.sleep(SLEEP_PERIOD);
                mainActivity.runOnUiThread(() -> binding.calculate.setBackgroundColor(Color.GREEN));
                SystemClock.sleep(SLEEP_PERIOD);
                mainActivity.runOnUiThread(() -> binding.calculate.setBackgroundColor(Color.BLUE));
                SystemClock.sleep(SLEEP_PERIOD);
                mainActivity.runOnUiThread(() -> binding.calculate.setBackgroundColor(Color.RED));
                SystemClock.sleep(SLEEP_PERIOD);
                mainActivity.runOnUiThread(() -> binding.calculate.setText("okay 1"));
                SystemClock.sleep(SLEEP_PERIOD);
                binding.calculate.post(() -> binding.calculate.setText("okay 2"));
            });

        });
        return binding.getRoot();
    }

    private Number fetchNumber(TextInputLayout textInputLayout) {
        Number result = 0;
        try {
            result = NumberFormat.getInstance().parse(
                    textInputLayout.getEditText().getText().toString());
        } catch (NumberFormatException | ParseException nfe) {
            Toast.makeText(
                    mainActivity,
                    R.string.calories_error_message,
                    Toast.LENGTH_SHORT).show();
            textInputLayout.getEditText().requestFocus();
        }
        return result;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
    }
}