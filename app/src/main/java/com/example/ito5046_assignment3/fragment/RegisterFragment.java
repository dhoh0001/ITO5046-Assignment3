package com.example.ito5046_assignment3.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import com.example.ito5046_assignment3.databinding.RegisterFragmentBinding;
import com.example.ito5046_assignment3.entity.Customer;
import com.example.ito5046_assignment3.entity.User;
import com.example.ito5046_assignment3.viewmodel.CustomerViewModel;
import com.example.ito5046_assignment3.viewmodel.SharedViewModel;
import com.example.ito5046_assignment3.viewmodel.UserViewModel;

public class RegisterFragment  extends Fragment {
    private RegisterFragmentBinding binding;
    private UserViewModel userViewModel;
    public RegisterFragment(){}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = RegisterFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        this.userViewModel = new
                ViewModelProvider(requireActivity()).get(UserViewModel.class);

        binding.register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String username = binding.username.getEditText().getText().toString();
                String lastname = "lastname";
                String password = binding.password.getEditText().getText().toString();
                String salt = "salt";
                String email = binding.email.getEditText().getText().toString();
                String age = binding.age.getEditText().getText().toString();
                String gender = binding.gender.getEditText().getText().toString();
                String fitness = binding.fitness.getEditText().getText().toString();

                if ((!username.isEmpty() && username!= null) &&
                        (!password.isEmpty() && password!=null) &&
                        (!email.isEmpty() && email!=null) &&
                        (!age.isEmpty() && age!=null) &&
                        (!gender.isEmpty() && gender!=null) &&
                        (!fitness.isEmpty() && fitness!=null)) {
                    int age_int = Integer.parseInt(age);
                    int fitness_int = Integer.parseInt(fitness);
                    User user = new User( username, username
                            , password, salt, email
                            , gender, age_int, fitness_int,0);
                    userViewModel.insert(user);
                    //binding.textViewAdd.setText("Added Record: " + name + " "
                    //        + surname + " " + salary);
                }
            }});
        return view;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
