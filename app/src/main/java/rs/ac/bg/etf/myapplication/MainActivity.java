package rs.ac.bg.etf.myapplication;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import rs.ac.bg.etf.myapplication.calories.CaloriesFragment;
import rs.ac.bg.etf.myapplication.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private FragmentManager fragmentManager;
    public static final String LOG_TAG = "fragment-example";
    private static final String CALORIES_TAG = "fragment-calories-tag";
    private CaloriesFragment caloriesFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        fragmentManager = getSupportFragmentManager();
        if(fragmentManager.getFragments().size() == 0 ) {
            caloriesFragment = new CaloriesFragment();
            fragmentManager
                    .beginTransaction()
                    .add(R.id.frame_layout, caloriesFragment, CALORIES_TAG)
                    .commit();
        }else{
            caloriesFragment = (CaloriesFragment) fragmentManager.findFragmentByTag(CALORIES_TAG);
        }
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