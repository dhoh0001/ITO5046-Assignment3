package com.example.ito5046_assignment3;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.ito5046_assignment3.databinding.ActivityMainBinding;
import com.example.ito5046_assignment3.entity.Customer;
import com.example.ito5046_assignment3.entity.User;
import com.example.ito5046_assignment3.viewmodel.CustomerViewModel;
import com.example.ito5046_assignment3.viewmodel.UserViewModel;

import java.util.concurrent.CompletableFuture;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    /*private AppBarConfiguration mAppBarConfiguration;
    private CustomerViewModel customerViewModel;*/
    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        userViewModel = ViewModelProvider.AndroidViewModelFactory
                .getInstance(getApplication())
                .create(UserViewModel.class);

        binding.login.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                String email = binding.username.getEditText().getText().toString();
                String password = binding.password.getEditText().getText().toString();
                CompletableFuture<User> userCompletableFuture = userViewModel.findByUsername(email);
                userCompletableFuture.thenApply(user -> {
                    if(password != null && password.equals(user.password)) {
                        Intent intent = new Intent(MainActivity.this,
                                TestActivity.class);
                        startActivity(intent);
                    } else {
                        //TODO add failure toast
                    }
                    return user;
                });

            }
        });

        binding.register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,
                        RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}