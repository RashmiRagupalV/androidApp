package redflag.capstone.com.redflag;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;


public class StudentListActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list_by_school);

        //Get the message from Intent
       /* Intent intent = getIntent();
        String message = intent.getStringExtra(LoginSuccessActivity.STUDENT_NAME);

        //Create the TextView
        TextView textView = new TextView(this);
        textView.setTextSize(20);
        textView.setText("Hello " + message);*/

        //setContentView(textView);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.student_list_by_school, menu);
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

    public void loadScreeningHistory(View view) {
//        Intent studentListBySchoolIntent = new Intent(this, ScreeningTestListActivity.class);
//        //SearchView searchText = (SearchView) findViewById(R.id.studentSearch);
//        //String user_name = searchText.getQuery().toString();
//        //studentListBySchoolIntent.putExtra(STUDENT_NAME, user_name);
//        startActivity(studentListBySchoolIntent);
    }
}
