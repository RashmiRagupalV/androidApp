package redflag.capstone.com.redflag;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.provider.Contacts;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.app.ActionBar;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

public class LoginSuccessActivity extends FragmentActivity implements
        GoogleApiClient.OnConnectionFailedListener{

    public final static String STUDENT_NAME = "com.Capstone.RedFlag2.STUDENT";
    //public final static String STUDENT_FILTER = "com.Capstone.RedFlag2.FILTER";
    private ActionBar mActionBar;
    Button LOGOUT;

    GoogleApiClient mGoogleApiClient;
    boolean mSignInClicked;
    String user_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_login_success);
        setContentView(R.layout.activity_homepage);
        LOGOUT = (Button) findViewById(R.id.button4);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Bundle bundle = getIntent().getExtras();
        user_name = bundle.getString("TesterName");
        //Setup Action Bar tabs
        mActionBar = this.getActionBar();
        mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(LoginSuccessActivity.this, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        //Get the message from Intent
        Intent intent = getIntent();
        //String message = intent.getStringExtra(Login.EXTRA_MESSAGE);
        final AlertDialog.Builder alertDialog=new AlertDialog.Builder(this);

//        LOGOUT.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                alertDialog.setMessage("Are you sure you want to log off ?");
//
//                alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        Intent loginscreen = new Intent(getBaseContext(), Login.class);
//                        startActivity(loginscreen);
//
//                    }
//                });
//                alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                    }
//                });
//                alertDialog.show();
//
//            }
//
//        });
        //copy this code on "Logout" Onclick
        LOGOUT.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (mGoogleApiClient.isConnected()) {
                    Auth.GoogleSignInApi.signOut(mGoogleApiClient);
                    mGoogleApiClient.disconnect();
                    mGoogleApiClient.connect();
                    // updateUI(false);
                    System.err.println("LOG OUT ^^^^^^^^^^^^^^^^^^^^ SUCCESS");
                    Intent loginscreen = new Intent(getBaseContext(), Login.class);
                        startActivity(loginscreen);
                }

            }
        });




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login_success, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void searchStudent(View view) {
//        Intent studentListBySchoolIntent = new Intent(this, StudentSearchAndListActivity.class);
//        startActivity(studentListBySchoolIntent);
    }

    public void registerStudent(View view) {
        Intent studentRegisterIntent = new Intent(this, RegisterStudentActivity.class);
        studentRegisterIntent.putExtra("TesterName", user_name);
        startActivity(studentRegisterIntent);
    }

    public void generateReport(View view) {
        Intent generateReportIntent = new Intent(this, ReportType.class);
        generateReportIntent.putExtra("TesterName", user_name);
        startActivity(generateReportIntent);
    }

    public void tutorials(View view) {
        Intent studentRegisterIntent = new Intent(this, RegisterStudentActivity.class);
        startActivity(studentRegisterIntent);
    }

    public void recordSession(View view) {
//        Intent recordIntent = new Intent(this, VideoCapture.class);
//        startActivity(recordIntent);
    }

    public void recordActivity(View view){

        Intent record = new Intent(this, StudentDetailsActivity.class);
        record.putExtra("TesterName", user_name);
        startActivity(record);
    }

    public void display(View view){
        Intent record = new Intent(this, Graph.class);
        startActivity(record);

    }

//    @Override
//    public void onConnected(Bundle arg0) {
//        // TODO Auto-generated method stub
//        mSignInClicked = false;
//
//        // updateUI(true);
////        Auth.PeopleApi.loadVisible(mGoogleApiClient, null).setResultCallback(
////                this);
//    }

//    @Override
//    public void onConnectionSuspended(int arg0) {
//        // TODO Auto-generated method stub
//        mGoogleApiClient.connect();
//        // updateUI(false);
//    }

    @Override
    public void onConnectionFailed(ConnectionResult arg0) {
        // TODO Auto-generated method stub

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

//    @Override
//    public void onResult(LoadPeopleResult arg0) {
//        // TODO Auto-generated method stub
//
//    }




}
