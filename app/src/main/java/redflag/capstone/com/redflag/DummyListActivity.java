package redflag.capstone.com.redflag;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class DummyListActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dummy_list);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dummy_list, menu);
        return true;
    }

    public void studentDetail(View view) {
        Intent studentListBySchoolIntent = new Intent(this, StudentDetailsActivity.class);
        //SearchView searchText = (SearchView) findViewById(R.id.studentSearch);
        //String user_name = searchText.getQuery().toString();
        //studentListBySchoolIntent.putExtra(STUDENT_NAME, user_name);
        startActivity(studentListBySchoolIntent);
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
}
