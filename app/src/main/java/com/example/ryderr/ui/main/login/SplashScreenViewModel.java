package com.example.ryderr.ui.main.login;

import android.os.Handler;
import android.os.Looper;

import com.google.firebase.firestore.FirebaseFirestore;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SplashScreenViewModel extends ViewModel {
    private static final long SPLASH_DISPLAY_LENGTH = 2500;
    public MutableLiveData<Integer> navigateScreen;

    public MutableLiveData<Integer> getNavigateScreen() {
        if (navigateScreen == null) {
            navigateScreen = new MutableLiveData<Integer>(0);
        }



        return navigateScreen;
    }

    public void loadApp(){
        getNavigateScreen();
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                navigateScreen.setValue(1);
                //Navigation.findNavController(view).navigate(R.id.action_splashScreen_to_loginFragment);
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
    public void addCab(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
     //   LiveCab liveCab = new LiveCab("c0",null,7,0,);
    }
}
