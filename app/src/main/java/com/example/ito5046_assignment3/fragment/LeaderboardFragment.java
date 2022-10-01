package com.example.ito5046_assignment3.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.ito5046_assignment3.databinding.CreateChallengeFragmentBinding;
import com.example.ito5046_assignment3.databinding.LeaderboardFragmentBinding;
import com.example.ito5046_assignment3.viewmodel.SharedViewModel;

public class LeaderboardFragment extends Fragment {
    private LeaderboardFragmentBinding binding;
    public LeaderboardFragment(){}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = LeaderboardFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        SharedViewModel model = new
                ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        return view;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
