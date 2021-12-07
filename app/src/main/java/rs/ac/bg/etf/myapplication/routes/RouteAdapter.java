package rs.ac.bg.etf.myapplication.routes;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import rs.ac.bg.etf.myapplication.MainActivity;
import rs.ac.bg.etf.myapplication.R;
import rs.ac.bg.etf.myapplication.databinding.ViewHolderRouteBinding;


public class RouteAdapter extends RecyclerView.Adapter<RouteAdapter.RouteViewHolder> {

    public interface Callback<T> {
        void invoke(T parameter);
    }

    private final RouteViewModel routeViewModel;
    private final Callback<Integer> callbackDescription;
    private final Callback<Integer> callbackLocation;

    public RouteAdapter(RouteViewModel routeViewModel,
                        Callback<Integer> callbackDescription,
                        Callback<Integer> callbackLocation) {
        this.callbackDescription = callbackDescription;
        this.routeViewModel = routeViewModel;
        this.callbackLocation = callbackLocation;
    }

    @NonNull
    @Override
    public RouteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewHolderRouteBinding viewHolderRouteBinding = ViewHolderRouteBinding.inflate(
                layoutInflater, parent, false);

        return new RouteViewHolder(viewHolderRouteBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RouteViewHolder holder, int position) {
        Route route = routeViewModel.getRouteList().get(position);
        ViewHolderRouteBinding binding = holder.binding;
        binding.routeLabel.setText(route.getLabel());
        binding.routeImage.setImageDrawable(route.getImage());
        binding.routeName.setText(route.getName());
        binding.routeDifficulty.setText(route.getDifficulty());
        binding.routeLength.setText(route.getLength() + "km");

    }

    @Override
    public int getItemCount() {
        return routeViewModel.getRouteList().size();
    }


    public class RouteViewHolder extends RecyclerView.ViewHolder {

        public ViewHolderRouteBinding binding;

        public RouteViewHolder(@NonNull ViewHolderRouteBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.routeButtonLocation.setOnClickListener(view -> {

                int routeIndex = getAdapterPosition();
                callbackLocation.invoke(routeIndex);
            });

            binding.routeButtonDescription.setOnClickListener(view -> {
                int routeIndex = getAdapterPosition();
                callbackDescription.invoke(routeIndex);
            });
        }
    }

}
