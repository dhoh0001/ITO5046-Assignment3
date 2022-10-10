package com.example.ito5046_assignment3.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.ito5046_assignment3.databinding.RegisterFragmentBinding;
import com.example.ito5046_assignment3.entity.Customer;
import com.example.ito5046_assignment3.entity.User;
import com.example.ito5046_assignment3.viewmodel.UserViewModel;

import java.util.List;

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
                ViewModelProvider(getActivity()).get(UserViewModel.class);
        userViewModel.getAllUsers().observe(getViewLifecycleOwner(), users -> {
            String allCustomers = "";
            for (User temp : users) {
                //String customerDetails = (temp.firstName + " " +temp.lastName);
                binding.id.getEditText().setText("" + temp.user_id);
                binding.firstname.getEditText().setText(temp.firstName);
                binding.lastname.getEditText().setText(temp.lastName);
                binding.password.getEditText().setText(temp.password);
                binding.email.getEditText().setText(temp.email);
                binding.age.getEditText().setText("" + temp.age);
                binding.gender.getEditText().setText(temp.gender);
                binding.fitness.getEditText().setText("" + temp.fitnessLevel);
            }
        });


        binding.register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String user_id = binding.id.getEditText().getText().toString();
                String firstname = binding.firstname.getEditText().getText().toString();
                String lastname = binding.lastname.getEditText().getText().toString();
                String password = binding.password.getEditText().getText().toString();
                String salt = "salt";
                String email = binding.email.getEditText().getText().toString();
                String age = binding.age.getEditText().getText().toString();
                String gender = binding.gender.getEditText().getText().toString();
                String fitness = binding.fitness.getEditText().getText().toString();

                if ((!firstname.isEmpty() && firstname!=null) &&
                        (!lastname.isEmpty() && lastname!=null) &&
                        (!password.isEmpty() && password!=null) &&
                        (!email.isEmpty() && email!=null) &&
                        (!age.isEmpty() && age!=null) &&
                        (!gender.isEmpty() && gender!=null) &&
                        (!fitness.isEmpty() && fitness!=null)) {
                    int age_int = Integer.parseInt(age);
                    int fitness_int = Integer.parseInt(fitness);
                    User user = new User( firstname, lastname
                            , password, salt, email
                            , gender, age_int, fitness_int,0);
                    user.user_id = Integer.parseInt(user_id);
                    userViewModel.update(user);
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
