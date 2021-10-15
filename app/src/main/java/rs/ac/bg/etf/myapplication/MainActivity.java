package rs.ac.bg.etf.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import rs.ac.bg.etf.myapplication.calories.CaloriesActivity;
import rs.ac.bg.etf.myapplication.databinding.ActivityMainBinding;
import rs.ac.bg.etf.myapplication.routes.RouteBrowseActivity;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonCalories.setOnClickListener(view -> {
            Intent intent = new Intent();
//            intent.setComponent(new ComponentName(
//                    "rs.ac.bg.etf.myapplication",
//                    "rs.ac.bg.etf.myapplication.calories.CaloriesActivity"
//            ));

           /* intent.setComponent(new ComponentName(this, CaloriesActivity.class));*/
            intent.setClass(this, CaloriesActivity.class);
            this.startActivity(intent);
        });

        binding.buttonRoutes.setOnClickListener(view -> {
                 Intent intent = new Intent();
                 intent.setClass(this, RouteBrowseActivity.class);
                 this.startActivity(intent);
        });
    }
}