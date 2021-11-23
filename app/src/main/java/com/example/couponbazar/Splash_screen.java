package com.example.couponbazar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.example.couponbazar.Authentication.LoginActivity;
import com.example.couponbazar.Authentication.SignupActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Splash_screen extends AppCompatActivity {

    private static int SPLASH_SCREEN_TIME_OUT=2000;
    private FirebaseAuth mAuth;
    FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash_screen);

        mAuth=FirebaseAuth.getInstance();

        if(mAuth!=null){
            currentUser= mAuth.getCurrentUser();
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if(currentUser==null){
                    startActivity(new Intent(Splash_screen.this, SignupActivity.class));
                    finish();
                }else{
                    Intent i=new Intent(Splash_screen.this, LoginActivity.class);
                    startActivity(i);
                    finish();
                }

            }
        },SPLASH_SCREEN_TIME_OUT);

    }
}