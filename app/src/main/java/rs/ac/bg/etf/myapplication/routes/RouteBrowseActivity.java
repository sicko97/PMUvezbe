package rs.ac.bg.etf.myapplication.routes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import rs.ac.bg.etf.myapplication.databinding.ActivityRouteBrowseBinding;

public class RouteBrowseActivity extends AppCompatActivity {

    private ActivityRouteBrowseBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRouteBrowseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}