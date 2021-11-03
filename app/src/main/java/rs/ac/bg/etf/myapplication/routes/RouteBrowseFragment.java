package rs.ac.bg.etf.myapplication.routes;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
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


    private FragmentRouteBrowseBinding binding;

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

        List<Route> routes = new ArrayList<>();
        for(int i = 0; i<9 ; i++){
            routes.add(Route.createFromResources(getResources(),i));
        }

        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setAdapter(new RouteAdapter(parentActivity,routes));
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(parentActivity));


        return binding.getRoot();
    }
}