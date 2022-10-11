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
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.ito5046_assignment3.adapter.RecyclerViewAdapter;
import com.example.ito5046_assignment3.databinding.AttemptChallengeFragmentBinding;
import com.example.ito5046_assignment3.databinding.CreateChallengeFragmentBinding;
import com.example.ito5046_assignment3.entity.Challenge;
import com.example.ito5046_assignment3.entity.Customer;
import com.example.ito5046_assignment3.viewmodel.ChallengeViewModel;
import com.example.ito5046_assignment3.viewmodel.SharedViewModel;
import com.example.ito5046_assignment3.viewmodel.UserViewModel;

import java.util.ArrayList;
import java.util.List;

public class CreateChallengeFragment extends Fragment {
    private CreateChallengeFragmentBinding binding;
    private final LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(final Location location) {
            binding.lattitude.getEditText().setText("" + location.getLatitude());
            binding.longitude.getEditText().setText("" + location.getLongitude());
        }
    };

    public CreateChallengeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = CreateChallengeFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        binding.editButton.setVisibility(View.GONE);
        ChallengeViewModel model = new
                ViewModelProvider(getActivity()).get(ChallengeViewModel.class);

        LocationManager locationManager = (LocationManager) getContext()
                .getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            //return TODO;
        }
        locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                1000L,
                500.0f,
                mLocationListener
        );

        binding.addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String id =
                        binding.id.getEditText().getText().toString();
                String lattitude =
                        binding.lattitude.getEditText().getText().toString();
                String longitude =
                        binding.longitude.getEditText().getText().toString();

                String title =
                        binding.title.getEditText().getText().toString();
                String details =
                        binding.details.getEditText().getText().toString();

                if(lattitude == null || lattitude.equals("") || longitude == null || longitude.equals("")){
                    Toast.makeText(getContext(),
                                    "Please check inputs are correct",
                                    Toast.LENGTH_SHORT)
                            .show();
                } else {

                    double lattitude_double = Double.valueOf(lattitude);
                    double longitude_double = Double.valueOf(longitude);

                    Challenge challenge = new Challenge(lattitude_double, longitude_double, title, details);
                    //challenge.challenge_id = Integer.parseInt(id);
                    model.insert(challenge);

                    Toast.makeText(getContext(),
                                    "Challenge created",
                                    Toast.LENGTH_SHORT)
                            .show();
/*
                    binding.textViewAdd.setText("Added Record: " + name + " "
                            + surname + " " + salary);
*/
                    binding.id.setEnabled(false);
                    binding.lattitude.setEnabled(false);
                    binding.longitude.setEnabled(false);
                    binding.title.setEnabled(false);
                    binding.details.setEnabled(false);

                    binding.editButton.setVisibility(View.VISIBLE);
                }

            }});

        binding.updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id =
                        binding.id.getEditText().getText().toString();
                String lattitude =
                        binding.lattitude.getEditText().getText().toString();
                String longitude =
                        binding.longitude.getEditText().getText().toString();

                String title =
                        binding.title.getEditText().getText().toString();
                String details =
                        binding.details.getEditText().getText().toString();

                if(id == null || id.equals("") || lattitude == null || lattitude.equals("") || longitude == null || longitude.equals("")){
                    Toast.makeText(getContext(),
                                    "Please check inputs are correct",
                                    Toast.LENGTH_SHORT)
                            .show();
                } else {
                    double lattitude_double = Double.valueOf(lattitude);
                    double longitude_double = Double.valueOf(longitude);

                    Challenge challenge = new Challenge(lattitude_double, longitude_double, title, details);
                    challenge.challenge_id = Integer.parseInt(id);
                    model.update(challenge);

                    Toast.makeText(getContext(),
                                    "Challenge updated",
                                    Toast.LENGTH_SHORT)
                            .show();
/*
                    binding.textViewAdd.setText("Added Record: " + name + " "
                            + surname + " " + salary);
*/
                    binding.id.setEnabled(false);
                    binding.lattitude.setEnabled(false);
                    binding.longitude.setEnabled(false);
                    binding.title.setEnabled(false);
                    binding.details.setEnabled(false);

                    binding.editButton.setVisibility(View.VISIBLE);
                }
            }
        });

        binding.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.id.setEnabled(true);
                binding.lattitude.setEnabled(true);
                binding.longitude.setEnabled(true);
                binding.title.setEnabled(true);
                binding.details.setEnabled(true);
                binding.editButton.setVisibility(View.GONE);
            }
        });

        return view;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
