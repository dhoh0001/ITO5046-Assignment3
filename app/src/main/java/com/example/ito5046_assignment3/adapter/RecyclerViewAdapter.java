package com.example.ito5046_assignment3.adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ito5046_assignment3.R;
import com.example.ito5046_assignment3.databinding.RvLayoutBinding;
import com.example.ito5046_assignment3.entity.Challenge;
import com.example.ito5046_assignment3.fragment.AttemptChallengeFragment;
import com.example.ito5046_assignment3.model.AttemptModel;
import com.example.ito5046_assignment3.model.CoordinatesModel;
import com.example.ito5046_assignment3.model.CourseResult;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private static List<Challenge> courseResults;
    private FragmentManager fragmentManager;

    public RecyclerViewAdapter(List<Challenge> courseResults, FragmentManager fragmentManager) {
        this.courseResults = courseResults;
        this.fragmentManager = fragmentManager;
    }

    //creates a new viewholder that is constructed with a new View, inflated from a layout
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                             int viewType) {
        RvLayoutBinding binding =
                RvLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new ViewHolder(binding);
    }

    // this method binds the view holder created with data that will be displayed
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder viewHolder, int
            position) {
        final Challenge unit = courseResults.get(position);
        viewHolder.binding.tvRvunit.setText(unit.challengeName);
        viewHolder.binding.tvRvmark.setText((unit.challengeDetails));
        viewHolder.binding.attempt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*courseResults.remove(unit);
                notifyDataSetChanged();*/
                Bundle bundle=new Bundle();
                AttemptModel attempt = new AttemptModel();
                attempt.id = unit.challenge_id;
                bundle.putParcelable("attempt", attempt);
                Navigation.findNavController(v).navigate(R.id.nav_attempt_challenge_fragment, bundle);
            }
        });
        viewHolder.binding.ivItemDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                CoordinatesModel coor = new CoordinatesModel();
                coor.longitude = unit.location_longitude;
                coor.lattitude = unit.location_lattitude;
                bundle.putParcelable("coordinates", coor);
                Navigation.findNavController(v).navigate(R.id.nav_map_Fragment, bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return courseResults.size();
    }

    public void addUnits(List<Challenge> results) {
        courseResults = results;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private RvLayoutBinding binding;

        public ViewHolder(RvLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}