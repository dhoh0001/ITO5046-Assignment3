package com.example.ito5046_assignment3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.View;

import com.example.ito5046_assignment3.databinding.ActivityHomeBinding;
import com.example.ito5046_assignment3.databinding.ActivityMainBinding;
import com.example.ito5046_assignment3.databinding.ActivityTestBinding;
import com.example.ito5046_assignment3.viewmodel.CustomerViewModel;

public class TestActivity extends AppCompatActivity {
    private ActivityTestBinding binding;

    private AppBarConfiguration mAppBarConfiguration;
    private CustomerViewModel customerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTestBinding.inflate(getLayoutInflater());
        //setContentView(R.layout.activity_test);
        View view = binding.getRoot();
        setContentView(view);

        setSupportActionBar(binding.appBar.toolbar);
        mAppBarConfiguration = new AppBarConfiguration.Builder(
        R.id.nav_home_fragment,
        R.id.nav_register_fragment,
        R.id.nav_history_fragment,
        R.id.nav_challenges_nearby_fragment,
        R.id.nav_create_challenge_fragment,
        R.id.nav_view_challenge_fragment,
        R.id.nav_leaderboard_fragment)
//to display the Navigation button as a drawer symbol,not being shown as an Up button
        .setOpenableLayout(binding.drawerLayout)
        .build();
        FragmentManager fragmentManager= getSupportFragmentManager();
        NavHostFragment navHostFragment = (NavHostFragment)
                fragmentManager.findFragmentById(R.id.nav_host_fragment);
        NavController navController = navHostFragment.getNavController();
        //Sets up a NavigationView for use with a NavController.
        NavigationUI.setupWithNavController(binding.navView, navController);
        //Sets up a Toolbar for use with a NavController.
        NavigationUI.setupWithNavController(binding.appBar.toolbar,navController,
                mAppBarConfiguration);
    }
}