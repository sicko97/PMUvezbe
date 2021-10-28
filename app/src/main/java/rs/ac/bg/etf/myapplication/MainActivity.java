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


    }
}