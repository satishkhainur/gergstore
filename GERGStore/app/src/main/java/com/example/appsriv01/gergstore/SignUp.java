package com.example.appsriv01.gergstore;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class SignUp extends Activity {

    private static String LOG_TAG = "In Signup Class";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Toast.makeText(getApplicationContext(),"IN  signUp Class ",Toast.LENGTH_SHORT).show();

    }

}
