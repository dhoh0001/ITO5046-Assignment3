package com.example.ito5046_assignment3;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.ito5046_assignment3.databinding.ActivityMainBinding;
import com.example.ito5046_assignment3.retrofit.RetrofitClient;
import com.example.ito5046_assignment3.retrofit.RetrofitInterface;
import com.example.ito5046_assignment3.entity.User;
import com.example.ito5046_assignment3.viewmodel.UserViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mapbox.maps.MapView;

import java.util.concurrent.CompletableFuture;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    /*private AppBarConfiguration mAppBarConfiguration;
    private CustomerViewModel customerViewModel;*/
    private UserViewModel userViewModel;
    private FirebaseAuth auth;
    private DatabaseReference mDb;
    private DatabaseReference testEndPoint;
    private MapView mapView;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final Pattern VALID_EMAIL_ADDRESS_REGEX =
                Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        userViewModel = ViewModelProvider.AndroidViewModelFactory
                .getInstance(getApplication())
                .create(UserViewModel.class);

        /*auth = FirebaseAuth.getInstance();*/

        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /**/

                String email = binding.username.getEditText().getText().toString();
                String password = binding.password.getEditText().getText().toString();

                Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
                if(!matcher.find()) {
                    Toast.makeText(MainActivity.this,
                                    "Incorrect email format",
                                    Toast.LENGTH_SHORT)
                            .show();
                } else if(email == null || email.equals("") || password == null || password.equals("")) {
                    Toast.makeText(MainActivity.this,
                                    "Empty password or email detected, please enter correct details",
                                    Toast.LENGTH_SHORT)
                            .show();
                } else {
                    CompletableFuture<User> userCompletableFuture = userViewModel.findByUsername(email);
                    userCompletableFuture.thenApply(user -> {
                        if (password != null && password.equals(user.password)) {
                            Intent intent = new Intent(MainActivity.this,
                                    TestActivity.class);
                            AppState.currentUserIdLoggedIn = user.user_id;
                            AppState.currentUserLoggedIn = user.email;
                            startActivity(intent);
                        } else {
                            //TODO add failure toast
                            Toast.makeText(MainActivity.this,
                                            "Login attempt failed, please enter the correct details",
                                            Toast.LENGTH_SHORT)
                                    .show();

                        }
                        return user;
                    });
                }


                /*auth.signInWithEmailAndPassword(email,
                        password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        String msg = "Login Successful";
                        Toast.makeText(getApplicationContext(),
                                msg,
                                Toast.LENGTH_SHORT).show();
                    }
                });*/
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