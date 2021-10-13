package rs.ac.bg.etf.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import rs.ac.bg.etf.myapplication.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private MyViewModel myViewModel;
    private ActivityMainBinding binding;

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(
                MyViewModel.CALORIES_BURNED_KEY , myViewModel.getCaloriesBurned().getValue());
        outState.putInt(
                MyViewModel.CALORIES_NEEDED_KEY, myViewModel.getCaloriesNeeded().getValue());

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    myViewModel =  new ViewModelProvider(this).get(MyViewModel.class);

    myViewModel.initByInstanceStateBundle(savedInstanceState);

    myViewModel.getCaloriesBurned().observe(this, new Observer<Integer>() {
        @Override
        public void onChanged(Integer burned) {
            if(burned != -1){
                String prefix = getResources().getString(R.string.burned);
                binding.burned.setText( prefix + ":" + burned + "kcal");
            }
        }
    });

    myViewModel.getCaloriesNeeded().observe(this, new Observer<Integer>() {
        @Override
        public void onChanged(Integer needed) {
            if(needed != -1){
                String prefix = getResources().getString(R.string.needed);
                binding.bmr.setText(prefix + ":"+needed + "kcal");
            }
        }
    });
    String[] metStrings = getResources().getStringArray(R.array.met_strings);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                metStrings);
        binding.spinner.setAdapter(arrayAdapter);

    binding.calculate.setOnClickListener(view -> {
        double weight = 0;
        try{
            weight  = Double.parseDouble(binding.weight.getEditText().getText().toString());
        }catch (NumberFormatException nfe){
            Toast.makeText(this , R.string.error_message , Toast.LENGTH_SHORT).show();
            binding.weight.getEditText().requestFocus();
            return;
        }
        double height = 0;
        try {
          height = Double.parseDouble(binding.height.getEditText().getText().toString());
        }catch (NumberFormatException nfe){
            Toast.makeText(this , R.string.error_message , Toast.LENGTH_SHORT).show();
            binding.height.getEditText().requestFocus();
            return;
        }
        int age = 0;
        try{
            age = Integer.parseInt(binding.age.getEditText().getText().toString());
          }catch (NumberFormatException nfe) {
            Toast.makeText(this, R.string.error_message, Toast.LENGTH_SHORT).show();
            binding.age.getEditText().requestFocus();
            return;
        }
        if(binding.radioGroup.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, R.string.error_message, Toast.LENGTH_SHORT).show();
            return;
        }
            boolean isMale = binding.male.isChecked();


        double duration = 0;
        try{
            duration  = Double.parseDouble(binding.duration.getEditText().getText().toString());
             }catch (NumberFormatException nfe) {
            Toast.makeText(this, R.string.error_message, Toast.LENGTH_SHORT).show();
            binding.duration.getEditText().requestFocus();
            return;
        }

        TypedArray metValues = getResources().obtainTypedArray(R.array.met_values);
        double met = metValues.getFloat(binding.spinner.getSelectedItemPosition() , 0);
        myViewModel.updateValues(weight,height,age,isMale,duration,met);
    });



    }
}