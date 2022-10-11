package com.example.ito5046_assignment3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.ito5046_assignment3.databinding.ActivityMainBinding;
import com.example.ito5046_assignment3.databinding.ActivityRegisterBinding;
import com.example.ito5046_assignment3.entity.User;
import com.example.ito5046_assignment3.viewmodel.UserViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;
    private UserViewModel userViewModel;
    private FirebaseAuth auth;

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        userViewModel = ViewModelProvider.AndroidViewModelFactory
                .getInstance(getApplication())
                .create(UserViewModel.class);

        /*auth = FirebaseAuth.getInstance();*/

        binding.register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String firstname = binding.firstname.getEditText().getText().toString();
                String lastname = binding.lastname.getEditText().getText().toString();
                String password = binding.password.getEditText().getText().toString();
                String salt = "salt";
                String email = binding.email.getEditText().getText().toString();
                String age = binding.age.getEditText().getText().toString();
                String gender = binding.gender.getEditText().getText().toString();
                String fitness = binding.fitness.getEditText().getText().toString();

                /*auth.createUserWithEmailAndPassword(
                                binding.email.getEditText().getText().toString(),
                                binding.password.getEditText().getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                String msg = "";
                                if(task.isSuccessful()) {
                                    msg = "Registration Successful";
                                }else {
                                    msg = "Registration Unsuccessful";
                                }
                                Toast.makeText(getApplicationContext(),
                                        msg,
                                        Toast.LENGTH_SHORT).show();
                            }
                        });*/
                Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
                if(!matcher.find()) {
                    Toast.makeText(RegisterActivity.this,
                                    "Incorrect email format",
                                    Toast.LENGTH_SHORT)
                            .show();
                } else if(password.length() < 6) {
                    Toast.makeText(RegisterActivity.this,
                                    "Password needs to be more than 6 characters",
                                    Toast.LENGTH_SHORT)
                            .show();
                } else {
                        if ((!firstname.isEmpty() && firstname != null) &&
                                (!lastname.isEmpty() && lastname != null) &&
                                (!password.isEmpty() && password != null) &&
                                (!email.isEmpty() && email != null) &&
                                (!age.isEmpty() && age != null) &&
                                (!gender.isEmpty() && gender != null) &&
                                (!fitness.isEmpty() && fitness != null)) {
                            int age_int = Integer.parseInt(age);
                            int fitness_int = Integer.parseInt(fitness);
                            User user = new User(firstname, lastname
                                    , password, salt, email
                                    , gender, age_int, fitness_int, 0);
                            userViewModel.insert(user);
                            Intent intent = new Intent(RegisterActivity.this,
                                    MainActivity.class);
                            startActivity(intent);
                            Toast.makeText(RegisterActivity.this,
                                            "User registered",
                                            Toast.LENGTH_SHORT)
                                    .show();
                            //binding.textViewAdd.setText("Added Record: " + name + " "
                            //        + surname + " " + salary);
                        }
                    }
                }
        });
    }
}