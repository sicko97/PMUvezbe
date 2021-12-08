package rs.ac.bg.etf.myapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;

import rs.ac.bg.etf.myapplication.calories.CaloriesFragmentDirections;
import rs.ac.bg.etf.myapplication.databinding.ActivityMainBinding;
import rs.ac.bg.etf.myapplication.routes.RouteViewModel;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private FragmentManager fragmentManager;
    private RouteViewModel routeViewModel;
    private NavController navController;
    public static final String LOG_TAG = "fragment-example";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        routeViewModel = new ViewModelProvider(this).get(RouteViewModel.class);

        fragmentManager = getSupportFragmentManager();

        NavHostFragment navHost = (NavHostFragment) fragmentManager
                .findFragmentById(R.id.nav_host_fragment);

        navController = navHost.getNavController();

        binding.bottomNavigation.setOnItemSelectedListener(item -> {

            switch (item.getItemId()) {

                case R.id.menu_item_routes:
                    switch (navController.getCurrentDestination().getId()) {
                        case R.id.calories:
                            NavDirections action =
                                    CaloriesFragmentDirections.actionCaloriesPop();
                            navController.navigate(action);
                            break;
                        default:
                            //nothing
                            break;
                    }
                    return true;
                case R.id.menu_item_calories:
                    switch (navController.getCurrentDestination().getId()) {
                        case R.id.route_browse:
                            NavDirections action = NavGraphDirections.actionGlobalCalories();
                            navController.navigate(action);
                            break;

                        case R.id.route_details:
                            action = NavGraphDirections.actionGlobalCalories();
                            navController.navigate(action);
                            break;

                        default:
                            //nothing
                            break;
                    }
                    return true;

                case R.id.menu_item_workouts:

                    break;
            }

            return false;

        });
    }

    @Override
    public void onBackPressed() {
        switch (navController.getCurrentDestination().getId()) {
            case R.id.route_details:
                routeViewModel.setSelectedRoute(null);
                break;
            case R.id.calories:
                binding.bottomNavigation.getMenu().getItem(0).setChecked(true);
                break;
        }
        super.onBackPressed();
    }
}