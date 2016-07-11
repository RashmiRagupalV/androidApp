//package redflag.capstone.com.redflag;
//
//import android.app.ActionBar;
//import android.app.Activity;
//import android.app.ProgressDialog;
//import android.content.DialoinsergInterface;
//import android.content.pm.ActivityInfo;
//import android.database.Cursor;
//import android.graphics.Color;
//import android.net.Uri;
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.os.Environment;
//import android.text.Editable;
//import android.text.TextWatcher;
//import android.util.Log;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.Button;
//import android.widget.CheckBox;
//import android.widget.EditText;
//import android.content.Intent;
//import android.widget.TextView;
//import android.widget.Toast;
//import android.content.Context;
//import android.content.SharedPreferences;
//
//import com.google.android.gms.auth.api.Auth;
//import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
//import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
//import com.google.android.gms.common.api.GoogleApiClient;
//import com.google.android.gms.auth.api.signin.GoogleSignInResult;
//import com.google.android.gms.common.ConnectionResult;
//import com.google.android.gms.common.SignInButton;
//import com.google.android.gms.common.api.OptionalPendingResult;
//import com.google.android.gms.common.api.ResultCallback;
//import com.google.android.gms.common.api.Status;
//
//
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.Locale;
//
//import au.com.bytecode.opencsv.CSVWriter;
//
//public class Login extends Activity implements GoogleApiClient.OnConnectionFailedListener {
//
//
//    public final static String EXTRA_MESSAGE = "com.Capstone.RedFlag2.MESSAGE";
//    public static final String MyPREFERENCES = "MyPrefs" ;
//
//    private DatabaseOperations dBHelper;
//    Context ctxt = this;
//    EditText edittxt1,edittxt2,edittxtname;
//    Button button1;
//    Cursor cursor;
//    SharedPreferences sharedpreferences;
//
//    private static final int RC_SIGN_IN = 9001;
//
//    private GoogleApiClient mGoogleApiClient;
//    private TextView mStatusTextView;
//    private ProgressDialog mProgressDialog;
//
//
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        dBHelper = new DatabaseOperations(getBaseContext());
//        dBHelper.getWritableDatabase();
//        setContentView(R.layout.activity_login);
//        setUpView();
//        setUpFocus();
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//        edittxt1 = (EditText) findViewById(R.id.login_name);
//        edittxt2 = (EditText) findViewById(R.id.login_password);
//        edittxtname = (EditText)findViewById(R.id.yourname);
//        button1 = (Button) findViewById(R.id.button_login);
//        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
//// Configure sign-in to request the user's ID, email address, and basic
//// profile. ID and basic profile are included in DEFAULT_SIGN_IN.
//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestEmail()
//                .build();
//
//        // Build a GoogleApiClient with access to the Google Sign-In API and the
//// options specified by gso.
//        mGoogleApiClient = new GoogleApiClient.Builder(this)
//                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
//                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
//                .build();
//        ((Button) findViewById(R.id.sign_in_button)).setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
//                        startActivityForResult(signInIntent, RC_SIGN_IN);
//                    }
//        });
//
//
//        edittxt1.addTextChangedListener(new TextWatcher() {
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//                edittxt1.setError(null);
//            }
//        });
//
//        edittxt2.addTextChangedListener(new TextWatcher() {
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//                edittxt2.setError(null);
//            }
//        });
//    }
//
//    private void setUpView(){
//        edittxt1=(EditText)findViewById(R.id.login_name);
//        edittxt2=(EditText)findViewById(R.id.login_password);
//        button1=(Button)findViewById(R.id.button_login);
//    }
//    private void setUpFocus(){
//        edittxt1.setNextFocusForwardId(R.id.login_password);
//        edittxt2.setNextFocusForwardId(R.id.button_login);// you can give focus to any id
//
//    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.login, menu);
//        return true;
//    }
//
//    public void onClick(){
//
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//    public void validateLogin(View view) {
//
//        //EditText editText = (EditText) findViewById(R.id.login_name);
//
//        String yourname = edittxtname.getText().toString();
//        String user_name = edittxt1.getText().toString().toUpperCase();
//        //EditText editText1 = (EditText) findViewById(R.id.login_password);
//        String user_pass = edittxt2.getText().toString();
//        dBHelper = new DatabaseOperations(ctxt);
////        if( edittxt1.getText().toString().length() == 0 )
////            edittxt1.setError( "UserName is needed to Login !" );
////
////        if( edittxt2.getText().toString().length() == 0 )
////            edittxt2.setError("Password cannot be empty !");
//        if(!(yourname.isEmpty())) {
//            if (user_name.isEmpty()) {
//                if (user_pass.isEmpty()) {
//                    //edittxt1.requestFocus();
//                    Toast.makeText(getBaseContext(), "Please fill up all details !", Toast.LENGTH_LONG).show();
//                } else {
//                    //edittxt1.requestFocus();
//                    Toast.makeText(getBaseContext(), "UserName is required to login !", Toast.LENGTH_LONG).show();
//                }
//            } else if (user_pass.isEmpty()) {
//                // edittxt2.requestFocus();
//                Toast.makeText(getBaseContext(), "Please enter password !", Toast.LENGTH_LONG).show();
//            }
//
//            if (!user_name.isEmpty() && !user_pass.isEmpty()) {
//                cursor = dBHelper.isValidUser(dBHelper);
//                // looping through all rows and adding to list
//                if (cursor.moveToFirst()) {
//                    do {
//                        String column1 = cursor.getString(1).toUpperCase();
//                        String column2 = cursor.getString(2);
//                        if (!column1.equals(user_name)) {
//                            Toast.makeText(getBaseContext(), "Username is incorrect !", Toast.LENGTH_LONG).show();
//                            //edittxt1.requestFocus();
//                            // edittxt1.setError("UserName is incorrect !" );
//                            edittxt1.requestFocus();
//                            edittxt1.setText("");
//                            edittxt2.setText("");
//                        } else if (!column2.equals(user_pass)) {
//                            Toast.makeText(getBaseContext(), "Password is incorrect !", Toast.LENGTH_LONG).show();
//                            // edittxt2.requestFocus();
//                            //  edittxt2.setError("Password is incorrect !" );
//                            // edittxt1.setText("");
//                            edittxt2.setText("");
//                        } else if (user_name.equals(column1) && user_pass.equals(column2)) {
//                            SharedPreferences.Editor editor = sharedpreferences.edit();
//                            editor.clear();
//                            editor.commit();
//
//                            editor.putString("TesterName", yourname);
//                            editor.commit();
//
//                            Intent loginIntent = new Intent(this, LoginSuccessActivity.class);
//                            loginIntent.putExtra(EXTRA_MESSAGE, user_name);
//                            startActivity(loginIntent);
//
//                        }
//                    } while (cursor.moveToNext());
//                }
//            }
//        }
//        else{
//            Toast.makeText(getBaseContext(), "Please fill up all details !", Toast.LENGTH_LONG).show();
//        }
//    }
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
//        if (requestCode == RC_SIGN_IN) {
//            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
//            handleSignInResult(result);
//        }
//    }
//
//    private void handleSignInResult(GoogleSignInResult result) {
//       // Log.d(TAG, "handleSignInResult:" + result.isSuccess());
//        if (result.isSuccess()) {
//            // Signed in successfully, show authenticated UI.
//            GoogleSignInAccount acct = result.getSignInAccount();
//            mStatusTextView.setText(getString(R.string.signed_in_fmt, acct.getDisplayName()));
//            updateUI(true);
//        } else {
//            // Signed out, show unauthenticated UI.
//            updateUI(false);
//        }
//    }
//
//    @Override
//    public void onConnectionFailed(ConnectionResult connectionResult) {
//        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
//        // be available.
//       // Log.d(TAG, "onConnectionFailed:" + connectionResult);
//    }
//
//
//
//}

package redflag.capstone.com.redflag;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

/**
 * Activity to demonstrate basic retrieval of the Google user's ID, email address, and basic
 * profile.
 */
public class Login extends FragmentActivity implements
        GoogleApiClient.OnConnectionFailedListener,
        View.OnClickListener {

    private static final String TAG = "SignInActivity";
    private static final int RC_SIGN_IN = 9001;

    private GoogleApiClient mGoogleApiClient;
    private TextView mStatusTextView;
    private ProgressDialog mProgressDialog;

    private DatabaseOperations dBHelper;
    Context ctxt = this;
    EditText edittxt1,edittxt2,edittxtname;
    ImageView imgadmin;
    TextView txtadmin,katie;
    Button button1, proceedbutton;
    Button signInButton;
    Cursor cursor;
    String user_name;
    public final static String EXTRA_MESSAGE = "com.Capstone.RedFlag2.MESSAGE";
        public static final String MyPREFERENCES = "MyPrefs" ;

    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        edittxt1 = (EditText) findViewById(R.id.login_name);

        katie = (TextView)findViewById(R.id.katie);
        katie.setClickable(true);
        katie.setMovementMethod(LinkMovementMethod.getInstance());
        String text = "<a href='https://www.amazon.com/Red-Flags-Elementary-Teachers-Neurodevelopmental-ebook/dp/B00O8BA7BY'> Katie Johnson's book </a>";
        katie.setText(Html.fromHtml(text));

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.clear();
        editor.commit();

        //edittxt1.setVisibility(View.INVISIBLE);

        edittxt2 = (EditText) findViewById(R.id.login_password);
//        edittxtname = (EditText)findViewById(R.id.yourname);
        //edittxt2.setVisibility(View.INVISIBLE);

        imgadmin = (ImageView)findViewById(R.id.admin);
        //imgadmin.setVisibility(View.INVISIBLE);

        txtadmin = (TextView)findViewById(R.id.title_textadmin);
        //txtadmin.setVisibility(View.INVISIBLE);

        button1 = (Button) findViewById(R.id.button_login);
        //button1.setVisibility(View.INVISIBLE);

        proceedbutton = (Button) findViewById(R.id.button_proceed);
        proceedbutton.setVisibility(View.INVISIBLE);


        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        // Views
        mStatusTextView = (TextView) findViewById(R.id.status);

        // Button listeners
        findViewById(R.id.sign_in_button).setOnClickListener(this);
       // findViewById(R.id.sign_out_button).setOnClickListener(this);
       // findViewById(R.id.disconnect_button).setOnClickListener(this);

        // [START configure_signin]
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        // [END configure_signin]

        // [START build_client]
        // Build a GoogleApiClient with access to the Google Sign-In API and the
        // options specified by gso.
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(Login.this, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        // [END build_client]

        // [START customize_button]
        // Customize sign-in button. The sign-in button can be displayed in
        // multiple sizes and color schemes. It can also be contextually
        // rendered based on the requested scopes. For example. a red button may
        // be displayed when Google+ scopes are requested, but a white button
        // may be displayed when only basic profile is requested. Try adding the
        // Scopes.PLUS_LOGIN scope to the GoogleSignInOptions to see the
        // difference.
//        SignInButton signInButton = (SignInButton) findViewById(R.id.sign_in_button);
//        signInButton.setSize(SignInButton.SIZE_STANDARD);
//        signInButton.setScopes(gso.getScopeArray());

        signInButton = (Button) findViewById(R.id.sign_in_button);
//        // [END customize_button]

        edittxt1.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                edittxt1.setError(null);
            }
        });

        edittxt2.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                edittxt2.setError(null);
            }
        });
    }



    @Override
    public void onStart() {
        super.onStart();

        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
        if (opr.isDone()) {
            // If the user's cached credentials are valid, the OptionalPendingResult will be "done"
            // and the GoogleSignInResult will be available instantly.
            Log.d(TAG, "Got cached sign-in");
            GoogleSignInResult result = opr.get();
            handleSignInResult(result);
        } else {
            // If the user has not previously signed in on this device or the sign-in has expired,
            // this asynchronous branch will attempt to sign in the user silently.  Cross-device
            // single sign-on will occur in this branch.
            showProgressDialog();
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(GoogleSignInResult googleSignInResult) {
                    hideProgressDialog();
                    handleSignInResult(googleSignInResult);
                }
            });
        }
    }

    // [START onActivityResult]
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }
    // [END onActivityResult]

    // [START handleSignInResult]
    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            user_name = acct.getEmail();
            if (user_name == null){
                user_name = "rashmira@uw.edu";
            }
            mStatusTextView.setText(getString(R.string.signed_in_fmt, user_name));
            updateUI(true);
        } else {
            // Signed out, show unauthenticated UI.
            updateUI(false);
        }
    }
    // [END handleSignInResult]

    // [START signIn]
    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    // [END signIn]

    // [START signOut]
//    private void signOut() {
//        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
//                new ResultCallback<Status>() {
//                    @Override
//                    public void onResult(Status status) {
//                        // [START_EXCLUDE]
//                        updateUI(false);
//                        // [END_EXCLUDE]
//                    }
//                });
//    }
//    // [END signOut]

    // [START revokeAccess]
    private void revokeAccess() {
        Auth.GoogleSignInApi.revokeAccess(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        // [START_EXCLUDE]
                        updateUI(false);
                        // [END_EXCLUDE]
                    }
                });
    }
    // [END revokeAccess]

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
        // be available.
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
    }

    private void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage(getString(R.string.loading));
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }

    private void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.hide();
        }
    }

    public void showadmin(View view){
        edittxt1.setVisibility(View.VISIBLE);
        edittxt2.setVisibility(View.VISIBLE);
        imgadmin.setVisibility(View.VISIBLE);
        txtadmin.setVisibility(View.VISIBLE);
        findViewById(R.id.button_login).setVisibility(View.VISIBLE);
        findViewById(R.id.button_proceed).setVisibility(View.INVISIBLE);
        findViewById(R.id.button_login).setEnabled(true);
        findViewById(R.id.sign_in_button).setEnabled(false);
        findViewById(R.id.no_account).setVisibility(View.INVISIBLE);


    }

    public void nextscreen(View view){
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("TesterName", user_name);
        Intent loginIntent = new Intent(this, LoginSuccessActivity.class);
        loginIntent.putExtra("TesterName", user_name);
        startActivity(loginIntent);
    }

    private void updateUI(boolean signedIn) {
        if (signedIn) {
            findViewById(R.id.sign_in_button).setVisibility(View.GONE);
            findViewById(R.id.googlesignin).setVisibility(View.GONE);
            findViewById(R.id.google_icon).setVisibility(View.GONE);
            findViewById(R.id.no_account).setVisibility(View.GONE);
          //  findViewById(R.id.sign_out_and_disconnect).setVisibility(View.VISIBLE);
            findViewById(R.id.button_login).setVisibility(View.GONE);
            edittxt1.setVisibility(View.INVISIBLE);
            edittxt2.setVisibility(View.INVISIBLE);
            imgadmin.setVisibility(View.INVISIBLE);
            txtadmin.setVisibility(View.INVISIBLE);
            findViewById(R.id.button_login).setVisibility(View.INVISIBLE);
            (findViewById(R.id.button_proceed)).setVisibility(View.VISIBLE);
            Toast.makeText(getBaseContext(), "Sign in Successful !", Toast.LENGTH_LONG).show();
//            Intent loginIntent = new Intent(this, LoginSuccessActivity.class);
//                            startActivity(loginIntent);

        } else {
           // mStatusTextView.setText(R.string.signed_out);
            mStatusTextView.setText("Please sign in...");
            findViewById(R.id.sign_in_button).setVisibility(View.VISIBLE);
            edittxt1.setVisibility(View.INVISIBLE);
            edittxt2.setVisibility(View.INVISIBLE);
            imgadmin.setVisibility(View.INVISIBLE);
            txtadmin.setVisibility(View.INVISIBLE);
            findViewById(R.id.button_login).setVisibility(View.INVISIBLE);
            // findViewById(R.id.sign_out_and_disconnect).setVisibility(View.GONE);
           // Toast.makeText(getBaseContext(), "Login UnSuccessful! Try again!!!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in_button:
                signIn();
                break;
//            case R.id.sign_out_button:
//                signOut();
//                break;
////            case R.id.disconnect_button:
//                revokeAccess();
//                break;
        }
    }


    public void validateLogin(View view) {

        //EditText editText = (EditText) findViewById(R.id.login_name);

    //    String yourname = edittxtname.getText().toString();
        String user_name = edittxt1.getText().toString().toUpperCase();
        //EditText editText1 = (EditText) findViewById(R.id.login_password);
        String user_pass = edittxt2.getText().toString();
        dBHelper = new DatabaseOperations(ctxt);
//        if( edittxt1.getText().toString().length() == 0 )
//            edittxt1.setError( "UserName is needed to Login !" );
//
//        if( edittxt2.getText().toString().length() == 0 )
//            edittxt2.setError("Password cannot be empty !");
      //  if(!(yourname.isEmpty())) {
            if (user_name.isEmpty()) {
                if (user_pass.isEmpty()) {
                    //edittxt1.requestFocus();
                    Toast.makeText(getBaseContext(), "Please fill up all details !", Toast.LENGTH_LONG).show();
                } else {
                    //edittxt1.requestFocus();
                    Toast.makeText(getBaseContext(), "UserName is required to login !", Toast.LENGTH_LONG).show();
                }
            } else if (user_pass.isEmpty()) {
                // edittxt2.requestFocus();
                Toast.makeText(getBaseContext(), "Please enter password !", Toast.LENGTH_LONG).show();
            }

            if (!user_name.isEmpty() && !user_pass.isEmpty()) {
                cursor = dBHelper.isValidUser(dBHelper);
                // looping through all rows and adding to list
                if (cursor.moveToFirst()) {
                    do {
                        String column1 = cursor.getString(1).toUpperCase();
                        String column2 = cursor.getString(2);
                        if (!column1.equals(user_name)) {
                            Toast.makeText(getBaseContext(), "Username is incorrect !", Toast.LENGTH_LONG).show();
                            //edittxt1.requestFocus();
                            // edittxt1.setError("UserName is incorrect !" );
                            edittxt1.requestFocus();
                            edittxt1.setText("");
                            edittxt2.setText("");
                        } else if (!column2.equals(user_pass)) {
                            Toast.makeText(getBaseContext(), "Password is incorrect !", Toast.LENGTH_LONG).show();
                            // edittxt2.requestFocus();
                            //  edittxt2.setError("Password is incorrect !" );
                            // edittxt1.setText("");
                            edittxt2.setText("");
                        } else if (user_name.equals(column1) && user_pass.equals(column2)) {
                            mStatusTextView.setText("Signed in as: Admin");
                            Toast.makeText(getBaseContext(), "Sign in Successful!", Toast.LENGTH_LONG).show();
                          (findViewById(R.id.button_proceed)).setVisibility(View.VISIBLE);
                            user_name = "Admin";
                            findViewById(R.id.button_login).setEnabled(false);
                            SharedPreferences.Editor editor = sharedpreferences.edit();
                            editor.clear();
                            editor.commit();

//                            editor.putString("TesterName", "admin");
  //                          editor.commit();

//                            Intent loginIntent = new Intent(this, LoginSuccessActivity.class);
//                            loginIntent.putExtra(EXTRA_MESSAGE, user_name);
//                            startActivity(loginIntent);
                            (findViewById(R.id.button_proceed)).setVisibility(View.VISIBLE);
                        }
                    } while (cursor.moveToNext());
                }
            }
//        }
//        else{
//            Toast.makeText(getBaseContext(), "Please fill up all details !", Toast.LENGTH_LONG).show();
//        }
    }

    
}


