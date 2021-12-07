package rs.ac.bg.etf.myapplication.workout;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import rs.ac.bg.etf.myapplication.databinding.ViewHolderWorkoutBinding;

public class WorkoutAdapter extends RecyclerView.Adapter<WorkoutAdapter.WorkoutViewHolder>  {

    public WorkoutAdapter() {

    }

    @NonNull
    @Override
    public WorkoutViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ViewHolderWorkoutBinding binding = ViewHolderWorkoutBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false);

        return new WorkoutAdapter.WorkoutViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutViewHolder holder, int position) {
        ViewHolderWorkoutBinding binding = holder.binding;
    }

    @Override
    public int getItemCount() {
       return 0;
    }



    public static class WorkoutViewHolder extends RecyclerView.ViewHolder{

        public ViewHolderWorkoutBinding binding;

        public WorkoutViewHolder ( @NonNull ViewHolderWorkoutBinding viewHolderWorkoutBinding){
            super(viewHolderWorkoutBinding.getRoot());
            this.binding = viewHolderWorkoutBinding;
        }

    }


}
