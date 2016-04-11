package redflag.capstone.com.redflag;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.app.ActionBar;

public class LoginSuccessActivity extends Activity {

    public final static String STUDENT_NAME = "com.Capstone.RedFlag2.STUDENT";
    //public final static String STUDENT_FILTER = "com.Capstone.RedFlag2.FILTER";
    private ActionBar mActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_login_success);
        setContentView(R.layout.activity_homepage);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //Setup Action Bar tabs
        mActionBar = this.getActionBar();
        mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        //Get the message from Intent
        Intent intent = getIntent();
        String message = intent.getStringExtra(Login.EXTRA_MESSAGE);

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

//    public void generateReport(View view) {
//        Intent generateReportIntent = new Intent(this, StudentDetailsActivity.class);
//        startActivity(generateReportIntent);
//    }

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

    public void studentListBySchool(View view) {
//        Intent filterStudentBySchoolIntent = new Intent(this, FilterStudentBySchoolActivity.class);
//        startActivity(filterStudentBySchoolIntent);
    }
}
