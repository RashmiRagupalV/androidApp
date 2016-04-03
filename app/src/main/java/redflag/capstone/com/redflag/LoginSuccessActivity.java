package redflag.capstone.com.redflag;

import android.app.Activity;
import android.content.Intent;
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

        //Setup Action Bar tabs
        mActionBar = this.getActionBar();
        mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

       /* Tab home = mActionBar.newTab();
        home.setText("Home");
        TabListener<Tab1Fragment> tab = new TabListener<Tab1Fragment>(this,
                "Home", Tab1Fragment.class);

        home.setTabListener(tab);

        Tab viewAll = mActionBar.newTab();
        viewAll.setText("View All");
        TabListener<Tab2Fragment> tab2 = new TabListener<Tab2Fragment>(this,
                "View All", Tab2Fragment.class);
        viewAll.setTabListener(tab2);

        Tab filter = mActionBar.newTab();
        filter.setText("Filter");
        TabListener<Tab3Fragment> tab3 = new TabListener<Tab3Fragment>(this,
                "Filter", Tab3Fragment.class);
        filter.setTabListener(tab3);*/

       /* Tab t3 = mActionBar.newTab();
        t3.setText("Deals");
        TabListener<Tab3Fragment> tab3 = new TabListener<Tab3Fragment>(this,
                "Deals", Tab3Fragment.class);
        t3.setTabListener(tab3);*/

       /* mActionBar.addTab(home);
        mActionBar.addTab(viewAll);
        mActionBar.addTab(filter);*/

        //Get the message from Intent
        Intent intent = getIntent();
        String message = intent.getStringExtra(Login.EXTRA_MESSAGE);

        //Create the TextView
       // TextView textView = (TextView) this.findViewById(R.id.greetingUser);
       // textView.setText("Hello " + message);

        //setContentView(textView);

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
        Intent studentListBySchoolIntent = new Intent(this, StudentSearchAndListActivity.class);
        /*SearchView searchText = (SearchView) findViewById(R.id.studentSearch);
        String user_name = searchText.getQuery().toString();
        studentListBySchoolIntent.putExtra(STUDENT_NAME, user_name);*/
        startActivity(studentListBySchoolIntent);
    }

    public void registerStudent(View view) {
        Intent studentRegisterIntent = new Intent(this, RegisterStudentActivity.class);
        /*SearchView searchText = (SearchView) findViewById(R.id.studentSearch);
        String user_name = searchText.getQuery().toString();
        studentListBySchoolIntent.putExtra(STUDENT_NAME, user_name);*/
        startActivity(studentRegisterIntent);
    }

    public void generateReport(View view) {
        Intent generateReportIntent = new Intent(this, StudentDetailsActivity.class);
        startActivity(generateReportIntent);
    }

    public void tutorials(View view) {
        Intent studentRegisterIntent = new Intent(this, RegisterStudentActivity.class);
        startActivity(studentRegisterIntent);
    }

    public void recordSession(View view) {
        Intent recordIntent = new Intent(this, VideoCapture.class);
        startActivity(recordIntent);
    }

    public void recordActivity(View view){


    }

    public void studentListBySchool(View view) {
        Intent filterStudentBySchoolIntent = new Intent(this, FilterStudentBySchoolActivity.class);
        startActivity(filterStudentBySchoolIntent);
    }
}
