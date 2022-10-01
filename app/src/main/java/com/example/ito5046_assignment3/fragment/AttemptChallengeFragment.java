package com.example.ito5046_assignment3.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.ito5046_assignment3.databinding.AttemptChallengeFragmentBinding;
import com.example.ito5046_assignment3.databinding.ViewFragmentBinding;
import com.example.ito5046_assignment3.viewmodel.SharedViewModel;

public class AttemptChallengeFragment extends Fragment {
    private AttemptChallengeFragmentBinding binding;
    public AttemptChallengeFragment(){}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = AttemptChallengeFragmentBinding.inflate(inflater, container, false);
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