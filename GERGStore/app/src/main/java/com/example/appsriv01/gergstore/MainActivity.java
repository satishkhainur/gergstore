package com.example.appsriv01.gergstore;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;

import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.plus.Plus;

public class MainActivity extends Activity implements  ConnectionCallbacks, OnConnectionFailedListener
{

    private static String LOG_TAG = "MainActivity";

     /* sign_in_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    if (mGoogleApiClient.isConnected()) {
                        Plus.AccountApi.clearDefaultAccount(mGoogleApiClient);
                        mGoogleApiClient.disconnect();
                        mGoogleApiClient.connect();
                        signInWithGplus();

                }
            }
        });
*/

    private SignInButton sign_in_button;
    private static final int RC_SIGN_IN = 0;
    // Logcat tag
    private static final String TAG = "MainActivity";

    // Profile pic image size in pixels
    private static final int PROFILE_PIC_SIZE = 400;

    // Google client to interact with Google API
    private GoogleApiClient mGoogleApiClient;

    private boolean mIntentInProgress;

    private boolean mSignInClicked;

    private ConnectionResult mConnectionResult;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button btnsignup = (Button) findViewById(R.id.btnsignup);
        Button btnlogin = (Button) findViewById(R.id.btnlogin);
        TextView forgotpassword = (TextView) findViewById(R.id.forgot);
        final EditText username = (EditText) findViewById(R.id.username);
        final EditText password = (EditText) findViewById(R.id.password);
        sign_in_button  = (SignInButton) findViewById(R.id.sign_in_button);


        //sign_in_button.setOnClickListener(this);

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


                if (username.getText().toString().equalsIgnoreCase("admin") &&
                        password.getText().toString().equalsIgnoreCase("admin")) {
                    Toast.makeText(getApplicationContext(), "Success ", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Not Valid User", Toast.LENGTH_SHORT).show();
                }


            }
        });




        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "You forgotten Password", Toast.LENGTH_SHORT).show();
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
    }

    @Override
    public void onConnected(Bundle arg0) {
        mSignInClicked = false;
        Toast.makeText(this, "User is connected!", Toast.LENGTH_LONG).show();

    }



    @Override
    public void onConnectionSuspended(int arg0) {
        mGoogleApiClient.connect();

    }

    private void signInWithGplus() {
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
