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
        return binding.getRoot();
    }

}