package redflag.capstone.com.redflag;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.app.ActionBar;
import android.widget.Button;
import android.widget.Toast;

public class LoginSuccessActivity extends Activity {

    public final static String STUDENT_NAME = "com.Capstone.RedFlag2.STUDENT";
    //public final static String STUDENT_FILTER = "com.Capstone.RedFlag2.FILTER";
    private ActionBar mActionBar;
    Button LOGOUT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_login_success);
        setContentView(R.layout.activity_homepage);
        LOGOUT = (Button) findViewById(R.id.button4);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //Setup Action Bar tabs
        mActionBar = this.getActionBar();
        mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        //Get the message from Intent
        Intent intent = getIntent();
        String message = intent.getStringExtra(Login.EXTRA_MESSAGE);
        final AlertDialog.Builder alertDialog=new AlertDialog.Builder(this);

        LOGOUT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.setMessage("Are you sure you want to log off ?");

                alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //do this on logout button click
//                            final String LOG_OUT = "event_logout";
//                            Intent intent = new Intent(LOG_OUT);
//                            //send the broadcast to all activities who are listening
//                            LocalBroadcastManager.getInstance(getBaseContext()).sendBroadcast(intent);
                        Intent loginscreen = new Intent(getBaseContext(), Login.class);
                        startActivity(loginscreen);

                    }
                });
                alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
//                            Intent loginintent = new Intent(getBaseContext(), LoginSuccessActivity.class);
//                            startActivity(loginintent);
                    }
                });
                alertDialog.show();

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
        startActivity(studentRegisterIntent);
    }

    public void generateReport(View view) {
        Intent generateReportIntent = new Intent(this, ReportType.class);
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
        startActivity(record);
    }

    public void display(View view){
        Intent record = new Intent(this, Graph.class);
        startActivity(record);

    }


}
