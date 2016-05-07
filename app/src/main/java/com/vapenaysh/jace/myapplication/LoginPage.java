package com.vapenaysh.jace.myapplication;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;


public class LoginPage extends FragmentActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener{

    //backend object
    private Firebase firebase;

    //login button
    private Button login;

    //register button
    private Button register;

    SignInButton signInButton;

    private GoogleApiClient mGoogleApiClient;

    private static final int RC_SIGN_IN = 9001;

    private static final int RC_SIGN_OUT = 9002;

    private static final String TAG = "SignInActivity";

    private ImageView imageView;
    private Animation animation;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //get rid of the hiderous action bar on the top
        ActionBar actionBar = getActionBar();
        actionBar.hide();

        //set up the view
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.yinyang);
        animation = AnimationUtils.loadAnimation(getBaseContext(), R.anim.rotate);

        imageView.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                signInButton.setVisibility(View.VISIBLE);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        ObjectAnimator fadeOut = ObjectAnimator.ofFloat(imageView, "alpha", 1f, .3f);
        ObjectAnimator fadeIn = ObjectAnimator.ofFloat(imageView, "alpha", .3f, 1f);
        fadeIn.setDuration(2000);
        fadeOut.setDuration(2000);
        final AnimatorSet mAnimationSet = new AnimatorSet();
        mAnimationSet.play(fadeIn).after(fadeOut);
        mAnimationSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mAnimationSet.start();
            }
        });
        mAnimationSet.start();


        // / Customize sign-in button. The sign-in button can be displayed in
        // multiple sizes and color schemes. It can also be contextually
        // rendered based on the requested scopes. For example. a red button may
        // be displayed when Google+ scopes are requested, but a white button
        // may be displayed when only basic profile is requested. Try adding the
        // Scopes.PLUS_LOGIN scope to the GoogleSignInOptions to see the
        // difference.
        signInButton = (SignInButton) findViewById(R.id.google_signin);



        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()//.requestIdToken("1021736687932-f73c4tc0pdcdhlrt16b3h3pj7qi0aq1b.apps.googleusercontent.com")
                .requestScopes(new Scope(Scopes.PLUS_LOGIN))
                .build();



        // Build a GoogleApiClient with access to the Google Sign-In API and the
        // options specified by gso.
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        // Customize sign-in button. The sign-in button can be displayed in
        // multiple sizes and color schemes. It can also be contextually
        // rendered based on the requested scopes. For example. a red button may
        // be displayed when Google+ scopes are requested, but a white button
        // may be displayed when only basic profile is requested. Try adding the
        // Scopes.PLUS_LOGIN scope to the GoogleSignInOptions to see the
        // difference.
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        signInButton.setScopes(gso.getScopeArray());

        signInButton.setOnClickListener(this);

        //connect the login button
        login = (Button)findViewById(R.id.login);
        login.setOnClickListener(this);


        //connect the register button
        register = (Button)findViewById(R.id.register);
        register.setOnClickListener(this);

        //initialize the firebase object
        Firebase.setAndroidContext(this);


        //connect to the server
        firebase = new Firebase("https://coupletone.firebaseio.com/");
        firebase.addAuthStateListener(new Firebase.AuthStateListener() {
            @Override
            public void onAuthStateChanged(AuthData authData) {
                if (authData != null) {
                    Toast.makeText(getApplication(), "I am in", Toast.LENGTH_LONG).show();
                } else {
                    // user is not logged in
                }
            }
        });

        Firebase usernames = firebase.child("usernames");
        usernames.setValue("Test");

        //load favorite locations into app for current session
        SavedLocations.loadLocations(this);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login:
                EditText uname = (EditText) findViewById(R.id.email);
                EditText passw = (EditText) findViewById(R.id.password);
                //TODO: login logic
                startActivity(new Intent(LoginPage.this, HomePage.class));
                break;
            case R.id.register:
                startActivity(new Intent(LoginPage.this, CreateAccount.class));
                break;
            case R.id.google_signin:
                signIn();
                break;
        }
    }


    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        imageView.clearAnimation();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }

        if(requestCode == RC_SIGN_OUT){
            Toast.makeText(getApplication(), "Signed out", Toast.LENGTH_SHORT).show();
            imageView.startAnimation(animation);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            Toast.makeText(getApplication(),getString(R.string.signed_in_fmt, acct.getDisplayName()), Toast.LENGTH_SHORT).show();

            String idToken = acct.getIdToken();
            //Toast.makeText(getApplication(),idToken, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginPage.this, HomePage.class);
            startActivityForResult(intent, RC_SIGN_OUT);


            /*** Firebase set up for authentication ***/
            /*
            Firebase.AuthResultHandler authResultHandler = new Firebase.AuthResultHandler() {
                @Override
                public void onAuthenticated(AuthData authData) {
                    Toast.makeText(getApplication(),"Authenticated successfully with payload authData", Toast.LENGTH_SHORT).show();
                    // Authenticated successfully with payload authData
                }
                @Override
                public void onAuthenticationError(FirebaseError firebaseError) {
                    // Authenticated failed with error firebaseError
                    if(firebaseError.getCode() == FirebaseError.UNKNOWN_ERROR){
                        Toast.makeText(getApplication(),"Authenticated failed with error firebaseError", Toast.LENGTH_SHORT).show();
                    }
                }
            };


            firebase.authWithOAuthToken("google",idToken, authResultHandler);*/


        } else {

            Toast.makeText(getApplication(),"Authenticated failed!", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Toast.makeText(getApplicationContext(), "Something wrong", Toast.LENGTH_LONG).show();


    }
}
