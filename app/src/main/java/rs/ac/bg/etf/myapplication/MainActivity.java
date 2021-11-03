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
    public static final String LOG_TAG = "fragment-example";

    private static final String CALORIES_TAG = "fragment-calories-tag";
    private CaloriesFragment caloriesFragment;

    private static final String ROUTES_TAG = "fragment-routes-tag";
    private RouteFragment routeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        routeViewModel = new ViewModelProvider(this).get(RouteViewModel.class);

        fragmentManager = getSupportFragmentManager();
        if (fragmentManager.getFragments().size() == 0) {
            caloriesFragment = new CaloriesFragment();
            routeFragment = new RouteFragment();
            fragmentManager
                    .beginTransaction()
                    .add(R.id.frame_layout, routeFragment, ROUTES_TAG)
                    .add(R.id.frame_layout, caloriesFragment, CALORIES_TAG)
                    .hide(caloriesFragment)
                    .show(routeFragment)
                    .commit();
        } else {
            caloriesFragment = (CaloriesFragment) fragmentManager.findFragmentByTag(CALORIES_TAG);
            routeFragment = (RouteFragment) fragmentManager.findFragmentByTag(ROUTES_TAG);
        }
        binding.bottomNavigation.setOnItemSelectedListener(item -> {

            switch (item.getItemId()) {

                case R.id.menu_item_routes:
                    // Toast.makeText(this, "routes", Toast.LENGTH_SHORT).show();
                    fragmentManager
                            .beginTransaction()
//                        .replace(R.id.frame_layout,routeBrowseFragment,ROUTES_TAG)
                            .hide(caloriesFragment)
                            .show(routeFragment)
                            .commit();
                    return true;
                case R.id.menu_item_calories:
                    //     Toast.makeText(this, "calories", Toast.LENGTH_SHORT).show();
                    fragmentManager
                            .beginTransaction()
//                          .replace(R.id.frame_layout, caloriesFragment, CALORIES_TAG)
                            .hide(routeFragment)
                            .show(caloriesFragment)
                            .commit();
                    return true;
            }

            return false;

        });
    }

    @Override
    public void onBackPressed() {
        if(binding.bottomNavigation.getSelectedItemId() == R.id.menu_item_routes){
            if(routeFragment.getChildFragmentManager().getBackStackEntryCount() > 0){
                routeViewModel.setSelectedRoute(null);
                routeFragment.getChildFragmentManager().popBackStack();
                return;
            }
        }
        super.onBackPressed();
    }
}