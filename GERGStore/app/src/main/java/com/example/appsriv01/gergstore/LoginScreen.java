package com.example.appsriv01.gergstore;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.content.IntentSender;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;

import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.plus.Plus;
import com.facebook.FacebookSdk;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class LoginScreen extends Activity implements  ConnectionCallbacks, OnConnectionFailedListener{
    private static String LOG_TAG = "Login Screen";
    private SignInButton sign_in_button;

    private static final int RC_SIGN_IN = 0;

    // Google client to interact with Google API
    private GoogleApiClient mGoogleApiClient;

    private boolean mIntentInProgress;

    private boolean mSignInClicked;

    private ConnectionResult mConnectionResult;

    private ImageView fbbutton;

    public  static CallbackManager callbackmanager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        System.out.println("In Login Class ******");


        ImageView btnsignup = (ImageView) findViewById(R.id.btnsignup);

        ImageView btnlogin = (ImageView) findViewById(R.id.btnlogin);

        // App Forgot Password
        TextView forgotpassword = (TextView) findViewById(R.id.forgot);

        final EditText editusername = (EditText) findViewById(R.id.username);
        final EditText editpassword = (EditText) findViewById(R.id.password);

    /* ImageButton   fbbutton = (ImageButton) findViewById(R.id.login_button);
        fbbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFblogin();
            }
        });*/


        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this).addApi(Plus.API, null)
                .addScope(Plus.SCOPE_PLUS_LOGIN).build();


      /*  sign_in_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                signInWithGplus();

            }
        });*/

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



                if(editusername.getText().toString().equalsIgnoreCase("admin") && editpassword.getText().toString().equalsIgnoreCase("admin")){
                    Intent i = new Intent(LoginScreen.this,MainActivity.class);
                            startActivity(i);
                }

                    //correcct password

            else{
                    Toast.makeText(getApplicationContext(),"ENTER admin as BOth",Toast.LENGTH_LONG).show();
                }
            }





        });
/*  if (!isValidEmail(username)) {
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
            }*/


        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "You forgotten Password", Toast.LENGTH_SHORT).show();
            }
        });











    }



    /**
     * FB login button on_click
     * */


    private void onFblogin()
    {
        callbackmanager = CallbackManager.Factory.create();

        // Set permissions
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("email", "user_photos", "public_profile"));

        LoginManager.getInstance().registerCallback(callbackmanager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {

                        System.out.println("Success");
                        Intent i = new Intent(LoginScreen.this, MainActivity.class);
                        startActivity(i);
                        GraphRequest.newMeRequest(
                                loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                                    @Override
                                    public void onCompleted(JSONObject json, GraphResponse response) {
                                        if (response.getError() != null) {
                                            // handle error
                                            System.out.println("ERROR");
                                        } else {
                                            System.out.println("Success");
                                            try {

                                                String jsonresult = String.valueOf(json);
                                                System.out.println("JSON Result" + jsonresult);

                                                String str_email = json.getString("email");
                                                // System.out.println("JSON Email" + str_email);
                                                String str_id = json.getString("id");
                                                // System.out.println("JSON Id" + str_id);
                                                String str_firstname = json.getString("first_name");
                                                // System.out.println("JSON Result" + str_firstname);
                                                String str_lastname = json.getString("last_name");

                                                //System.out.println("JSON Result" + str_lastname);
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }

                                }).executeAsync();

                    }

                    @Override
                    public void onCancel() {
                        //Log.d(TAG_CANCEL,"On cancel");
                    }

                    @Override
                    public void onError(FacebookException error) {
                        //  Log.d(TAG_ERROR,error.toString());
                    }
                });
    }



    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    protected void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    /**
     * Method to resolve any signin errors
     * */
    private void resolveSignInError() {
        if (mConnectionResult.hasResolution()) {
            try {
                mIntentInProgress = true;
                mConnectionResult.startResolutionForResult(this, RC_SIGN_IN);
            } catch (IntentSender.SendIntentException e) {
                mIntentInProgress = false;
                mGoogleApiClient.connect();
            }
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {
        if (!result.hasResolution()) {
            GooglePlayServicesUtil.getErrorDialog(result.getErrorCode(), this,
                    0).show();
            return;
        }

        if (!mIntentInProgress) {
            // Store the ConnectionResult for later usage
            mConnectionResult = result;

            if (mSignInClicked) {
                resolveSignInError();
            }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int responseCode,
                                    Intent intent) {
        if (requestCode == RC_SIGN_IN) {
            if (responseCode != RESULT_OK) {
                mSignInClicked = false;
            }

            mIntentInProgress = false;

            if (!mGoogleApiClient.isConnecting()) {
                mGoogleApiClient.connect();
            }
        }

        else {
            callbackmanager.onActivityResult(requestCode, responseCode, intent);
        }

    }

    @Override
    public void onConnected(Bundle arg0) {
        mSignInClicked = false;
        Toast.makeText(this, "User is connected!", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getApplicationContext(), SignUp.class);
        startActivity(intent);

    }



    @Override
    public void onConnectionSuspended(int arg0) {
        mGoogleApiClient.connect();

    }

    private void signInWithGplus()
    {
        if (!mGoogleApiClient.isConnecting()) {
            mSignInClicked = true;
            resolveSignInError();
        }
    }




    /*@Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.sign_in_button:
                signInWithGplus();
                break;
        }
    }*/



}





