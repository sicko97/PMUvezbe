package rs.ac.bg.etf.myapplication;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import rs.ac.bg.etf.myapplication.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    public static final String LOG_TAG = "running-app-example";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }
}