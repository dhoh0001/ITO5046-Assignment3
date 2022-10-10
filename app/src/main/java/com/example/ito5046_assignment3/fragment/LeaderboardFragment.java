package com.example.ito5046_assignment3.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.ito5046_assignment3.databinding.CreateChallengeFragmentBinding;
import com.example.ito5046_assignment3.databinding.LeaderboardFragmentBinding;
import com.example.ito5046_assignment3.retrofit.RetrofitClient;
import com.example.ito5046_assignment3.retrofit.RetrofitInterface;
import com.example.ito5046_assignment3.retrofit.response;
import com.example.ito5046_assignment3.viewmodel.SharedViewModel;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LeaderboardFragment extends Fragment {
    private LeaderboardFragmentBinding binding;
    private RetrofitInterface retrofitInterface;

    public LeaderboardFragment(){}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = LeaderboardFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        SharedViewModel model = new
                ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        retrofitInterface = RetrofitClient.getRetrofitService();
        Call<response> callAsync =
                retrofitInterface.customSearch();
        callAsync.enqueue(new Callback<response>() {
            @Override
            public void onResponse(Call<response> call,
                                   Response<response> response) {
                if (response.isSuccessful()) {
                    binding.position1.setHint("Position 1: " + response.body().pos1 + " - " + response.body().pos1val) ;
                    binding.position2.setHint("Position 2: " + response.body().pos2 + " - " + response.body().pos2val);
                    binding.position3.setHint("Position 3: " + response.body().pos3 + " - " + response.body().pos3val);

//getting snippet from the object in the position 0
                }
                else {
                    //Log.i("Error ","Response failed");
                    int i = 0;
                }
            }
            @Override
            public void onFailure(Call<response> call, Throwable t){
                try {
                    String test = call.execute().toString();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //Toast.makeText(LeaderboardFragment.this, t.getMessage(), Toast.LENGTH_SHORT);
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
