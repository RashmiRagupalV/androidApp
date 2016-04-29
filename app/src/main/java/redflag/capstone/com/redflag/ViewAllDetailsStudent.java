package redflag.capstone.com.redflag;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by rashm on 4/24/2016.
 */
public class ViewAllDetailsStudent extends Activity {

    private ArrayList<HashMap<String, String>> list;
    private DatabaseOperations dBHelper;
    Context ctxt = this;
    Cursor cursor;

    TextView txtstudName, txtstudId,txtstudAge,txtstudSchool,txtstudTeacher,txtstudGrade;
    TextView txttracking1, txttracking2, txttracking3;

    TextView horizontal, tracking,need,vertical,eyeoleft,eyeoright,eyecleft,eyecright,balance;
    TextView norecords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_report);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Intent intent = getIntent();
        Bundle b = intent.getExtras();

        String studId = (String) b.get("SelectedstudId");

        try {
            dBHelper = new DatabaseOperations(ctxt);

            cursor = dBHelper.getStudentDetails(dBHelper, studId);
            if (cursor.moveToFirst()) {
                do {
                    txtstudName = (TextView)findViewById(R.id.textStudName);
                    txtstudName.setText(cursor.getString(1));
                    txtstudId = (TextView)findViewById(R.id.textStudId);
                    txtstudId.setText(cursor.getString(0));
                    txtstudAge = (TextView)findViewById(R.id.textStudAge);
                    txtstudAge.setText(cursor.getString(3));
                    txtstudSchool = (TextView)findViewById(R.id.textSchoolName);
                    txtstudSchool.setText(cursor.getString(6));
                    txtstudTeacher = (TextView)findViewById(R.id.textTeachName);
                    txtstudTeacher.setText(cursor.getString(2));
                    txtstudGrade = (TextView)findViewById(R.id.textStudGrade);
                    txtstudGrade.setText(cursor.getString(5));
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error encountered.",
                    Toast.LENGTH_LONG);
        }
        cursor = null;

        try {
            cursor = dBHelper.getStudentTracking(dBHelper, studId);
            if (cursor.moveToFirst()) {
                do {
                    txttracking1 = (TextView)findViewById(R.id.textView10);
                    txttracking1.setText(cursor.getString(1));
                    txttracking2 = (TextView)findViewById(R.id.textView11);
                    txttracking2.setText(cursor.getString(2));
                    txttracking3 = (TextView)findViewById(R.id.textView12);
                    txttracking3.setText(cursor.getString(3));
                } while (cursor.moveToNext());
            }
            else{
                horizontal = (TextView)findViewById(R.id.textView7);
                horizontal.setVisibility(View.INVISIBLE);
                tracking = (TextView)findViewById(R.id.textView);
                tracking.setVisibility(View.INVISIBLE);
                need = (TextView)findViewById(R.id.textView8);
                need.setVisibility(View.INVISIBLE);
                vertical =  (TextView)findViewById(R.id.textView9);
                vertical.setVisibility(View.INVISIBLE);
                eyeoleft = (TextView)findViewById(R.id.textView14);
                eyeoleft.setVisibility(View.INVISIBLE);
                eyeoright = (TextView)findViewById(R.id.textView15);
                eyeoright.setVisibility(View.INVISIBLE);
                eyecleft = (TextView)findViewById(R.id.textView16);
                eyecleft.setVisibility(View.INVISIBLE);
                eyecleft = (TextView)findViewById(R.id.textView17);
                eyecright.setVisibility(View.INVISIBLE);
                balance = (TextView)findViewById(R.id.textView13);
                balance.setVisibility(View.INVISIBLE);
//                norecords = (TextView)findViewById(R.id.textView30);
//                norecords.setVisibility(View.VISIBLE);
//                norecords.setText("NO ACTIVITIES RECORDED ");

            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error encountered.",
                    Toast.LENGTH_LONG);
        }



    }

}
