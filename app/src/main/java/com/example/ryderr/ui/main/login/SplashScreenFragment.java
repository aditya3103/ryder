package com.example.ryderr.ui.main.login;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ryderr.R;
import com.google.firebase.auth.FirebaseAuth;


public class SplashScreenFragment extends Fragment {


    private static final long SPLASH_DISPLAY_LENGTH = 2500;
    SplashScreenViewModel model;
    public SplashScreenFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        model  = new ViewModelProvider(this).get(SplashScreenViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_splash_screen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        model.loadApp();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser()!=null)
            Navigation.findNavController(view).navigate(R.id.action_splashScreen_to_cabsFragment);


        model.navigateScreen.observe(getViewLifecycleOwner(), navigateScreenOption -> {
            if(navigateScreenOption==1)
                Navigation.findNavController(view).navigate(R.id.action_splashScreen_to_loginFragment);
        });



    }
}