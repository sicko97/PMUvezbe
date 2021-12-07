package rs.ac.bg.etf.myapplication.routes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import rs.ac.bg.etf.myapplication.MainActivity;
import rs.ac.bg.etf.myapplication.databinding.FragmentRouteDetailsBinding;

public class RouteDetailsFragment extends Fragment {

    private FragmentRouteDetailsBinding binding;
    private RouteViewModel routeViewModel;
    private MainActivity mainActivity;
    private NavController navController;

    public RouteDetailsFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivity = (MainActivity) requireActivity();
        routeViewModel = new ViewModelProvider(mainActivity).get(RouteViewModel.class);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {
        binding = FragmentRouteDetailsBinding.inflate(inflater, container, false);

        Route selectedRoute = routeViewModel.getRouteList().get(
                RouteDetailsFragmentArgs.fromBundle(requireArguments()).getRouteIndex());

        binding.toolbar.setTitle(selectedRoute.getLabel());
        binding.toolbar.setNavigationOnClickListener(view -> {
                navController.navigateUp();
        });
        binding.routeImage.setImageDrawable(selectedRoute.getImage());
        binding.routeLabel.setText(selectedRoute.getLabel());
        binding.routeName.setText(selectedRoute.getName());
        binding.routeLength.setText(selectedRoute.getLength() + "km");
        binding.routeDifficulty.setText(selectedRoute.getDifficulty());
        binding.routeDescription.setText(selectedRoute.getDescription());

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
    }
}