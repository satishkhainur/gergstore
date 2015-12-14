package com.example.appsriv01.gergstore;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class SplashScreen extends AppCompatActivity {
    private static String LOG_TAG = "SplashScreen";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        System.out.println("IN Flash Screen Class *****");


        Thread background =new Thread(){

            @Override
            public void run() {
                try{
                    sleep( 5 * 1000 );

                    Intent i =new Intent(getBaseContext(),LoginScreen.class);
                    startActivity(i);
                    finish();


                }
                catch (Exception e){

                }
            }
        };background.start();

    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }

}
