package rs.ac.bg.etf.myapplication.routes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import rs.ac.bg.etf.myapplication.MainActivity;
import rs.ac.bg.etf.myapplication.databinding.FragmentRouteDetailsBinding;

public class RouteDetailsFragment extends Fragment {

    private FragmentRouteDetailsBinding binding;
    private RouteViewModel routeViewModel;

    public RouteDetailsFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRouteDetailsBinding.inflate(inflater, container, false);
        MainActivity parentActivity = (MainActivity) getParentFragment().getActivity();
        routeViewModel = new ViewModelProvider(parentActivity).get(RouteViewModel.class);

        routeViewModel.getSelectedRoute().observe(getViewLifecycleOwner(), selectedRoute -> {
            if (selectedRoute != null) {
                binding.routeImage.setImageDrawable(selectedRoute.getImage());
                binding.routeLabel.setText(selectedRoute.getLabel());
                binding.routeName.setText(selectedRoute.getName());
                binding.routeLength.setText(selectedRoute.getLength() + "km");
                binding.routeDifficulty.setText(selectedRoute.getDifficulty());
                binding.routeDescription.setText(selectedRoute.getDescription());
            }
        });

        return binding.getRoot();
    }
}