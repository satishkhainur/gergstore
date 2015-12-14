/*
package com.example.appsriv01.gergstore;

import android.app.Activity;
import android.content.Intent;
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

public class MainActivity extends Activity implements  ConnectionCallbacks, OnConnectionFailedListener
{
    // Log Cat Tag
    private static String LOG_TAG = "MainActivity or SignUp";

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
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        System.out.println("In MainActivity Class *****");

       // sign_in_button = (SignInButton) findViewById(R.id.sign_in_button);
        fbbutton = (ImageView) findViewById(R.id.login_button);
        fbbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFblogin();
            }
        });


        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this).addApi(Plus.API, null)
                .addScope(Plus.SCOPE_PLUS_LOGIN).build();


        sign_in_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                signInWithGplus();

            }
        });

    }



    */
/**
     * FB login button on_click
     * *//*



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
                                Intent i = new Intent(MainActivity.this,SignUp.class);
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

                */
/**
                 * Method to resolve any signin errors
                 * *//*

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



    */
/*@Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.sign_in_button:
                signInWithGplus();
                break;
        }
    }*//*




}
*/
