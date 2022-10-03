package com.example.ito5046_assignment3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.ito5046_assignment3.databinding.ActivityMainBinding;
import com.example.ito5046_assignment3.databinding.ActivityRegisterBinding;
import com.example.ito5046_assignment3.entity.User;
import com.example.ito5046_assignment3.viewmodel.UserViewModel;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;
    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        userViewModel = ViewModelProvider.AndroidViewModelFactory
                .getInstance(getApplication())
                .create(UserViewModel.class);

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
                    Intent intent = new Intent(RegisterActivity.this,
                            MainActivity.class);
                    startActivity(intent);
                    //binding.textViewAdd.setText("Added Record: " + name + " "
                    //        + surname + " " + salary);
                }
            }
        });
    }
}