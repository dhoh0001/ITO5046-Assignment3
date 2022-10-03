package com.example.ito5046_assignment3.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.ito5046_assignment3.R;
import com.example.ito5046_assignment3.databinding.LoginFragmentBinding;
import com.example.ito5046_assignment3.databinding.ViewFragmentBinding;
import com.example.ito5046_assignment3.viewmodel.SharedViewModel;
import com.example.ito5046_assignment3.viewmodel.UserViewModel;

public class LoginFragment extends Fragment {
    private LoginFragmentBinding binding;
    private UserViewModel userViewModel;

    public LoginFragment(){}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = LoginFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        this.userViewModel = new
                ViewModelProvider(requireActivity()).get(UserViewModel.class);

        binding.login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.nav_host_fragment,new RegisterFragment());
                fr.commit();
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
