package com.example.ito5046_assignment3.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.ito5046_assignment3.databinding.AboutFragmentBinding;
import com.example.ito5046_assignment3.databinding.HomeFragmentBinding;
import com.example.ito5046_assignment3.viewmodel.SharedViewModel;

import java.util.Date;

public class AboutFragment extends Fragment {
    private SharedViewModel model;
    private AboutFragmentBinding binding;
    public AboutFragment(){}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
// Inflate the View for this fragment
        binding = AboutFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
