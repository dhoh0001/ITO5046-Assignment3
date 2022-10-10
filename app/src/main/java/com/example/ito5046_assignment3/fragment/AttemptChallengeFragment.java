package com.example.ito5046_assignment3.fragment;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.example.ito5046_assignment3.AppState;
import com.example.ito5046_assignment3.R;
import com.example.ito5046_assignment3.databinding.AttemptChallengeFragmentBinding;
import com.example.ito5046_assignment3.databinding.ViewFragmentBinding;
import com.example.ito5046_assignment3.entity.Attempt;
import com.example.ito5046_assignment3.entity.Challenge;
import com.example.ito5046_assignment3.entity.User;
import com.example.ito5046_assignment3.model.AttemptModel;
import com.example.ito5046_assignment3.retrofit.RetrofitClient;
import com.example.ito5046_assignment3.retrofit.RetrofitInterface;
import com.example.ito5046_assignment3.retrofit.response;
import com.example.ito5046_assignment3.viewmodel.AttemptViewModel;
import com.example.ito5046_assignment3.viewmodel.ChallengeViewModel;
import com.example.ito5046_assignment3.viewmodel.SharedViewModel;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.CompletableFuture;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AttemptChallengeFragment extends Fragment {
    private AttemptChallengeFragmentBinding binding;
    private RetrofitInterface retrofitInterface;
    int successCount = 0;

    public AttemptChallengeFragment(){}
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = AttemptChallengeFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        SharedViewModel model = new
                ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        Bundle bundle = getArguments();
        int challenge_id = ((AttemptModel) bundle.getParcelable("attempt")).id;

        ChallengeViewModel challengeModel = new
                ViewModelProvider(getActivity()).get(ChallengeViewModel.class);
        CompletableFuture<Challenge> challengeFuture = challengeModel.findByIDFuture(challenge_id);
        challengeFuture.thenApply(challenge -> {
            binding.titleValue.setText(challenge.challengeName);
            binding.detailValue.setText(challenge.challengeDetails);
            return challenge;
        });

        binding.successButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AttemptViewModel model = new
                        ViewModelProvider(getActivity()).get(AttemptViewModel.class);
                Attempt attempt = new Attempt(
                        AppState.currentUserIdLoggedIn,
                        challenge_id,
                        new Date().getTime(),
                        1);
                model.insert(attempt);

                model.getAllAttempts().observe(getViewLifecycleOwner(), attempts -> {
                    successCount = 0;
                    for(Attempt attempted : attempts) {
                        if(attempted.attemptStatus > 0)
                            successCount++;
                    }
                });

                retrofitInterface = RetrofitClient.getRetrofitService();
                Call<response> callAsync =
                        retrofitInterface.customSearch();
                callAsync.enqueue(new Callback<response>() {
                    @Override
                    public void onResponse(Call<response> call,
                                           Response<response> response) {
                        if (response.isSuccessful()) {
                            int i = 0;
                            int p1 = response.body().pos1val;
                            int p2 = response.body().pos2val;
                            int p3 = response.body().pos3val;
                            if(successCount > p1) {
                                p3 = p2;
                                p2 = p1;
                                p1 = successCount;
                            } else if(successCount > p2) {
                                p3 = p2;
                                p2 = successCount;
                            } else if(successCount > p3) {
                                p3 = successCount;
                            }

                            Call callRes = retrofitInterface.updateData(new response(response.body().pos1,response.body().pos2,response.body().pos3,p1,p2,p3));
                            callRes.enqueue(new Callback<response>() {
                                @Override
                                public void onResponse(Call<response> call,
                                                       Response<response> response) {
                                    if (response.isSuccessful()) {
                                        int j = 0;

//getting snippet from the object in the position 0
                                    }
                                    else {
                                        //Log.i("Error ","Response failed");
                                        int j = 0;
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

                Navigation.findNavController(v).navigate(R.id.nav_challenges_nearby_fragment);
            }
        });
        binding.failureButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AttemptViewModel model = new
                        ViewModelProvider(getActivity()).get(AttemptViewModel.class);
                Attempt attempt = new Attempt(
                        AppState.currentUserIdLoggedIn,
                        challenge_id,
                        new Date().getTime(),
                        -1);
                model.insert(attempt);
                Navigation.findNavController(v).navigate(R.id.nav_challenges_nearby_fragment);
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
