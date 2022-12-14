package com.example.ito5046_assignment3.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.ito5046_assignment3.databinding.HomeFragmentBinding;
import com.example.ito5046_assignment3.viewmodel.SharedViewModel;
import com.example.ito5046_assignment3.viewmodel.UserViewModel;

import java.io.File;
import java.util.Date;

public class HomeFragment extends Fragment {
    private SharedViewModel model;
    private HomeFragmentBinding binding;
    public HomeFragment(){}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
// Inflate the View for this fragment
        binding = HomeFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.textMessage.setText("The current date is " + new Date());

        return view;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}