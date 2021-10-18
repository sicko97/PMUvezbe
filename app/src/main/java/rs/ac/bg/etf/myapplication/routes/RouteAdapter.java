package rs.ac.bg.etf.myapplication.routes;

import android.content.Intent;
import android.net.Uri;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import rs.ac.bg.etf.myapplication.databinding.ViewHolderRouteBinding;

public class RouteAdapter extends RecyclerView.Adapter<RouteAdapter.RouteViewHolder> {

  private final List<Route>routes;
  private final RouteBrowseActivity routeBrowseActivity;
    public RouteAdapter(RouteBrowseActivity routeBrowseActivity,List<Route> routes) {
        this.routes=routes;
        this.routeBrowseActivity=routeBrowseActivity;
    }

    @NonNull
    @Override
    public RouteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewHolderRouteBinding viewHolderRouteBinding = ViewHolderRouteBinding.inflate(
                layoutInflater,parent,false);

        return new RouteViewHolder(viewHolderRouteBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RouteViewHolder holder, int position) {
        Route route = routes.get(position);
        ViewHolderRouteBinding binding = holder.binding;
        binding.routeLabel.setText(routes.get(position).getLabel());
        binding.routeImage.setImageDrawable(route.getImage());
        binding.routeName.setText(route.getName());
        binding.routeDifficulty.setText(route.getDifficulty());
        binding.routeLength.setText(route.getLength() + "km" );

    }

    @Override
    public int getItemCount() {
        return routes.size();
    }

    public class RouteViewHolder extends RecyclerView.ViewHolder{

        public ViewHolderRouteBinding binding;

        public RouteViewHolder(@NonNull ViewHolderRouteBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.routeButtonLocation.setOnClickListener(view -> {
                int routeIndex = getAdapterPosition();

                String locationString = routes.get(routeIndex).getLocation();
                locationString = locationString.replace(" ", "%20");
                locationString = locationString.replace("," , "%2C");
                Uri locationUri = Uri.parse("geo:0,0?q=" + locationString);
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(locationUri);

                routeBrowseActivity.startActivity(intent);
            });
        }
    }

}
