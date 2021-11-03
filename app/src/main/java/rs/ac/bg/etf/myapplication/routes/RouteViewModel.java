package rs.ac.bg.etf.myapplication.routes;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class RouteViewModel extends ViewModel {

    private List<Route> routeList;
    private MutableLiveData<Route> selectedRoute  = new MutableLiveData<>(null);


    public List<Route> getRouteList() {
        return routeList;
    }

    public void setRouteList(List<Route> routeList) {
        this.routeList = routeList;
    }

    public LiveData<Route> getSelectedRoute() {
        return selectedRoute;
    }

    public void setSelectedRoute(Route selectedRoute) {
        this.selectedRoute.setValue(selectedRoute);
    }
}
