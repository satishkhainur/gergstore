package com.example.appsriv01.gergstore;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginScreen extends Activity{
    private static String LOG_TAG = "Login Screen";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        System.out.println("In Login Class ******");


        // App SignUp button
        Button btnsignup = (Button) findViewById(R.id.btnsignup);


        Button btnlogin = (Button) findViewById(R.id.btnlogin);

        // App Forgot Password
        TextView forgotpassword = (TextView) findViewById(R.id.forgot);

        final EditText editusername = (EditText) findViewById(R.id.username);
        final EditText editpassword = (EditText) findViewById(R.id.password);


        //App SignUp OnClick
        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), SignUp.class);
                startActivity(intent);

            }
        });



        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final String username = editusername.getText().toString();
                if (!isValidEmail(username)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginScreen.this);
                    builder
                            .setTitle("Please Enter valid Username & Password")
                            .setMessage(" ")
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            })
                            .show();

                }

                final String password = editpassword.getText().toString();
                if (!isValidPassword(password)) {
                    // editpassword.setError("Invalid Password");
                }

            }

            private boolean isValidEmail(String username) {
                String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

                Pattern pattern = Pattern.compile(EMAIL_PATTERN);
                Matcher matcher = pattern.matcher(username);
                return matcher.matches();
            }

            // validating password with retype password
            private boolean isValidPassword(String password) {
                if (password != null && password.length() > 6) {
                    return true;
                }
                return false;
            }

        });


        /**
         * Forgot Password button
         * */
        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "You forgotten Password", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
