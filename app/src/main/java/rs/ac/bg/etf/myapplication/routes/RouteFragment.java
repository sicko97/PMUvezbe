package rs.ac.bg.etf.myapplication.routes;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import rs.ac.bg.etf.myapplication.MainActivity;
import rs.ac.bg.etf.myapplication.R;
import rs.ac.bg.etf.myapplication.databinding.FragmentRouteBinding;
import rs.ac.bg.etf.myapplication.databinding.FragmentRouteBrowseBinding;


public class RouteFragment extends Fragment {

    private FragmentRouteBinding binding;
    private FragmentManager childFragmentManager;
    private RouteViewModel routeViewModel;
    private static final String ROUTE_BROWSE_TAG = "fragment-route-browse-tag";
    private RouteBrowseFragment routeBrowseFragment;
    public RouteFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       binding = FragmentRouteBinding.inflate(inflater,container,false);

        MainActivity parentActivity = (MainActivity) getActivity();
        routeViewModel = new ViewModelProvider(parentActivity).get(RouteViewModel.class);

        List<Route> routes = new ArrayList<>();
        for(int i = 0; i<9 ; i++){
            routes.add(Route.createFromResources(getResources(),i));
        }
        routeViewModel.setRouteList(routes);

       childFragmentManager = getChildFragmentManager();

       if(childFragmentManager.getFragments().size() == 0){
            routeBrowseFragment = new RouteBrowseFragment();
            childFragmentManager
                    .beginTransaction()
                    .add(R.id.frame_layout,routeBrowseFragment , ROUTE_BROWSE_TAG)
                    .commit();
       }else{
           routeBrowseFragment = (RouteBrowseFragment) childFragmentManager.findFragmentByTag(ROUTE_BROWSE_TAG);
       }

       routeViewModel.getSelectedRoute().observe(getViewLifecycleOwner() , selectedRoute->{
                        if(selectedRoute != null && childFragmentManager.getBackStackEntryCount() == 0){
                            childFragmentManager
                                    .beginTransaction()
                                    .replace(R.id.frame_layout, new RouteDetailsFragment())
                                    .addToBackStack(null)
                                    .commit();
                        }
               });

        return binding.getRoot();
    }

}