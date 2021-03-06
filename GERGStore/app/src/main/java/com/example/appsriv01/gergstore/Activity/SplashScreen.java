package com.example.appsriv01.gergstore.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.appsriv01.gergstore.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.plus.Plus;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class SplashScreen extends Activity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    private static String LOG_TAG = "SplashScreen";
    private static final int RC_SIGN_IN = 0;

    // Google client to interact with Google API
    private GoogleApiClient mGoogleApiClient;

    private boolean mIntentInProgress;

    private boolean mSignInClicked;

    private ConnectionResult mConnectionResult;

    private ImageView fbbutton;

    private ImageView gplusbutton;

    private Context context;

    public  static CallbackManager callbackmanager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_splash_screen);
        context = getApplicationContext();
        System.out.println("IN Flash Screen Class *****");

        final boolean checkConnection=new ApplicationUtility().checkConnection(this);
        if(checkConnection) {

            Button btnsignin = (Button) findViewById(R.id.btnsignin);
            Button btnsignup = (Button) findViewById(R.id.btnsignup);


            btnsignin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                        Intent i = new Intent(getBaseContext(), LoginScreen.class);
                        startActivity(i);

                }
            });

            btnsignup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Intent i = new Intent(getBaseContext(), SignUp.class);
                    startActivity(i);}


            });


            fbbutton = (ImageView) findViewById(R.id.login_button);

            gplusbutton = (ImageView) findViewById(R.id.gplusbutton);

            fbbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onFblogin();
                }
            });


            gplusbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    signInWithGplus();

                }
            });


            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this).addApi(Plus.API, null)
                    .addScope(Plus.SCOPE_PLUS_LOGIN).build();

        }
        else

        {
            Toast.makeText(SplashScreen.this ,"Please check internet connection",Toast.LENGTH_LONG).show();
        }

    }
      /* End of the onCreate()   */


    boolean checkConnection=new ApplicationUtility().checkConnection(this);
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
                        Intent i = new Intent(SplashScreen.this, MainActivity.class);
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

                                                //System.out.println("JSON Result" + str_lastname);₹
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
        if(checkConnection) {
            mGoogleApiClient.connect();
        }
        else {
           // Toast.makeText(SplashScreen.this,"Please Check Connection ",Toast.LENGTH_LONG).show();
        }


    }

    protected void onStop() {
        super.onStop();
            if(checkConnection) {
                if (mGoogleApiClient.isConnected()) {
                    mGoogleApiClient.disconnect();
                }
            }
        else {}

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
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
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



/*
     //2nd method For finding image size or button in pixels //
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        // TODO Auto-generated method stub
        super.onWindowFocusChanged(hasFocus);
        updateSizeInfo();
    }


    private void updateSizeInfo(){

        System.out.println(String.valueOf("Gergstore Landing's Width(pixels): " + btnsignup1.getWidth()));
        System.out.println(String.valueOf("Button's Height(pixels): " + btnsignup.getHeight()));
    }
*/



}
