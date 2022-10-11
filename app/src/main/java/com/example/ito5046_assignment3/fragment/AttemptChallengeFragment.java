package com.example.ito5046_assignment3.fragment;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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

        ChallengeViewModel model = new
                ViewModelProvider(getActivity()).get(ChallengeViewModel.class);

        Bundle bundle = getArguments();
        int challenge_id = ((AttemptModel) bundle.getParcelable("attempt")).id;

        ChallengeViewModel challengeModel = new
                ViewModelProvider(getActivity()).get(ChallengeViewModel.class);

        model.getAllChallenges().observe(getViewLifecycleOwner(), challenges -> {
            for(Challenge challenge : challenges) {
                if(challenge.challenge_id == challenge_id) {
                    binding.titleValue.setText(challenge.challengeName);
                    binding.detailValue.setText(challenge.challengeDetails);
                }
            }
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
                            String p1u = response.body().pos1;
                            String p2u = response.body().pos2;
                            String p3u = response.body().pos3;

                            if(successCount > p1) {
                                p3 = p2;
                                p3u = p2u;
                                p2 = p1;
                                p2u = p1u;
                                p1 = successCount;
                                p1u = AppState.currentUserLoggedIn;
                            } else if(successCount > p2) {
                                p3 = p2;
                                p3u = p2u;
                                p2 = successCount;
                                p2u = AppState.currentUserLoggedIn;
                            } else if(successCount > p3) {
                                p3 = successCount;
                                p3u = AppState.currentUserLoggedIn;
                            }

                            Call callRes = retrofitInterface.updateData(new response(p1u,p2u,p3u,p1,p2,p3));
                            callRes.enqueue(new Callback<response>() {
                                @Override
                                public void onResponse(Call<response> call,
                                                       Response<response> response) {
                                    if (response.isSuccessful()) {
                                        Toast.makeText(getContext(),
                                                        "Data updated",
                                                        Toast.LENGTH_SHORT)
                                                .show();
                                    }
                                    else {
                                        Toast.makeText(getContext(),
                                                        "Error updating attempt",
                                                        Toast.LENGTH_SHORT)
                                                .show();
                                    }
                                }
                                @Override
                                public void onFailure(Call<response> call, Throwable t){
                                    Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT);
                                }
                            });

//getting snippet from the object in the position 0
                        }
                        else {
                            Toast.makeText(getContext(),
                                            "Unable to retrieve data",
                                            Toast.LENGTH_SHORT)
                                    .show();
                        }
                    }
                    @Override
                    public void onFailure(Call<response> call, Throwable t){
                        try {
                            String test = call.execute().toString();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
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
                Toast.makeText(getContext(),
                                "Attempt recorded",
                                Toast.LENGTH_SHORT)
                        .show();
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
