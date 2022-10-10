package com.example.ito5046_assignment3.fragment;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ito5046_assignment3.adapter.RecyclerViewAdapter;
import com.example.ito5046_assignment3.databinding.ChallengesNearbyFragmentBinding;
import com.example.ito5046_assignment3.databinding.SearchFragmentBinding;
import com.example.ito5046_assignment3.entity.Challenge;
import com.example.ito5046_assignment3.model.CourseResult;
import com.example.ito5046_assignment3.viewmodel.ChallengeViewModel;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ChallengesNearbyFragment extends Fragment {
    private ChallengeViewModel challengeViewModel;
    private RecyclerView.LayoutManager layoutManager;
    private List<Challenge> units;
    private RecyclerViewAdapter adapter;
    private ChallengesNearbyFragmentBinding binding;
    private ChallengeViewModel model;
    private int tolerance = 10;
    private List<Challenge> newChallenges = null;
    private Location location = null;

    private final LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(final Location location) {
            setLocation(location);
            setRecyclers();
        }
    };

    public ChallengesNearbyFragment() {
    }

    public void setRecyclers() {
        if(newChallenges == null || location == null)
            return;
        ArrayList<Challenge> challenges = new ArrayList<>();
        for(Challenge challenge : newChallenges) {
            if(challenge.location_lattitude > location.getLatitude() - tolerance &&
                    challenge.location_lattitude < location.getLatitude() + tolerance &&
                    challenge.location_longitude > location.getLongitude() -tolerance &&
                    challenge.location_longitude < location.getLongitude() + tolerance) {
                challenges.add(challenge);
            }
        }
        adapter = new RecyclerViewAdapter(challenges, getFragmentManager());
        binding.recyclerView.addItemDecoration(new
                DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        binding.recyclerView.setAdapter(adapter);
        layoutManager = new LinearLayoutManager(getContext());
        binding.recyclerView.setLayoutManager(layoutManager);
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
// Inflate the View for this fragment using the binding
        binding = ChallengesNearbyFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        model = new
                ViewModelProvider(getActivity()).get(ChallengeViewModel.class);

        LocationManager locationManager = (LocationManager) getContext()
                .getSystemService(Context.LOCATION_SERVICE);
        int test1 = ActivityCompat.checkSelfPermission(getContext(),
                Manifest.permission.ACCESS_FINE_LOCATION);
        int test2 = ActivityCompat.checkSelfPermission(getContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION);
        if (ActivityCompat.checkSelfPermission(getContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getContext(),
                        Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.
                        PERMISSION_GRANTED)
        {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return view;
        }
        locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                1000L,
                500.0f,
                mLocationListener
        );

        model.getAllChallenges().observe(getViewLifecycleOwner(), challenges -> {
                newChallenges = challenges;
                setRecyclers();
            });
        /*units=new ArrayList<Challenge>();*/

        //units = CourseResult.createContactsList();


        return view;
    }

    private void saveData(String unit, int mark) {
        /*CourseResult courseResult = new CourseResult(unit, mark);
        units.add(courseResult);
        adapter.addUnits(units);*/
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
