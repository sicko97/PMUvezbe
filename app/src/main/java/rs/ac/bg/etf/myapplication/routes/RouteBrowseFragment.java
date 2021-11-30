package rs.ac.bg.etf.myapplication.routes;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import rs.ac.bg.etf.myapplication.LifeCycleAwareLogger;
import rs.ac.bg.etf.myapplication.MainActivity;
import rs.ac.bg.etf.myapplication.R;
import rs.ac.bg.etf.myapplication.databinding.FragmentRouteBrowseBinding;


public class RouteBrowseFragment extends Fragment {

    private RouteViewModel routeViewModel;
    private FragmentRouteBrowseBinding binding;
    private NavController navController;

    public RouteBrowseFragment() {
        getLifecycle().addObserver(new LifeCycleAwareLogger(
                MainActivity.LOG_TAG,
                RouteBrowseFragment.class.getSimpleName()
        ));
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentRouteBrowseBinding.inflate(inflater,container,false);

        MainActivity parentActivity = (MainActivity) getActivity();
        routeViewModel = new ViewModelProvider(parentActivity).get(RouteViewModel.class);

        List<Route> routes = new ArrayList<>();
        for(int i = 0; i<9 ; i++){
            routes.add(Route.createFromResources(getResources(),i));
        }
        routeViewModel.setRouteList(routes);

        routeViewModel.getSelectedRoute().observe(getViewLifecycleOwner() , selectedRoute->{
            if(selectedRoute != null ){
                navController.navigate(R.id.route_details);
            }
        });

        routeViewModel = new ViewModelProvider(parentActivity).get(RouteViewModel.class);

        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setAdapter(new RouteAdapter(parentActivity));
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(parentActivity));


        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
    }
}