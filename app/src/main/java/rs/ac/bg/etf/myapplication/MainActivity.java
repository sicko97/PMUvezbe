package rs.ac.bg.etf.myapplication;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import rs.ac.bg.etf.myapplication.calories.CaloriesFragment;
import rs.ac.bg.etf.myapplication.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private CaloriesFragment caloriesFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        caloriesFragment = new CaloriesFragment();

        binding.bottomNavigation.setOnItemSelectedListener(item -> {

            switch (item.getItemId()) {

                case R.id.menu_item_routes:
                    Toast.makeText(this, "routes", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.menu_item_calories:
                    Toast.makeText(this, "calories", Toast.LENGTH_SHORT).show();
                    return true;
            }

            return false;

        });

    }
}