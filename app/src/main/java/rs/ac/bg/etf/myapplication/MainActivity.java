package rs.ac.bg.etf.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import rs.ac.bg.etf.myapplication.databinding.ActivityMainBinding;



public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.button.setOnClickListener(v -> {
            double duration = Double.parseDouble(binding.editText.getText().toString());
            double met = 9.8;
            double weight = 80;
            int caloriesBurned = (int)(duration * met * 3.5 * weight / 200);
            binding.textView.setText(caloriesBurned + "kcal");

        });

    }
}