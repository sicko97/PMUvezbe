package rs.ac.bg.etf.myapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import rs.ac.bg.etf.myapplication.calories.CaloriesFragment;
import rs.ac.bg.etf.myapplication.databinding.ActivityMainBinding;
import rs.ac.bg.etf.myapplication.routes.RouteBrowseFragment;
import rs.ac.bg.etf.myapplication.routes.RouteFragment;
import rs.ac.bg.etf.myapplication.routes.RouteViewModel;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private FragmentManager fragmentManager;
    private RouteViewModel routeViewModel;
    public static final String LOG_TAG="fragment-example";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        routeViewModel = new ViewModelProvider(this).get(RouteViewModel.class);

        fragmentManager = getSupportFragmentManager();

        binding.bottomNavigation.setOnItemSelectedListener(item -> {

            switch (item.getItemId()) {

                case R.id.menu_item_routes:
                    // Toast.makeText(this, "routes", Toast.LENGTH_SHORT).show();


                    return true;
                case R.id.menu_item_calories:
                    //     Toast.makeText(this, "calories", Toast.LENGTH_SHORT).show();

                    return true;
            }

            return false;

        });
    }

    @Override
    public void onBackPressed() {
        if(binding.bottomNavigation.getSelectedItemId() == R.id.menu_item_routes){
                routeViewModel.setSelectedRoute(null);
            }
        super.onBackPressed();
        }
}