package redflag.capstone.com.redflag;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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


    public static final String MyPREFERENCES = "MyPrefs" ;
    SharedPreferences sharedpreferences;
    String studId;
    private DatabaseOperations dBHelper;
    Context ctxt = this;
    Cursor cursor;

    TextView txtstudName, txtstudId,txtstudAge,txtstudSchool,txtstudTeacher,txtstudGrade;
    TextView txttracking1, txttrackingbean, txttracking3;

    TextView horizontal,need,vertical, tracking, eyeoleft,eyeoright,eyecleft,eyecright,balance;
    TextView rnd1,rnd2;
    TextView skipping,vd,crawl,comments;
    TextView norecords;
    String user_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_report);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Intent intent = getIntent();
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        Bundle bundle = getIntent().getExtras();
        user_name = bundle.getString("TesterName");
        studId = sharedpreferences.getString("StudId", "");

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
                    txttracking1.setText(cursor.getString(2));
                    txttrackingbean = (TextView)findViewById(R.id.textViewbeandata);
                    txttrackingbean.setText(cursor.getString(3));
                    txttracking3 = (TextView)findViewById(R.id.textView12);
                    txttracking3.setText(cursor.getString(4));

                } while (cursor.moveToNext());
            }
            else{
                txttracking1 = (TextView)findViewById(R.id.textView10);
                txttracking1.setText("No activities found");
                txttrackingbean = (TextView)findViewById(R.id.textViewbeandata);
                txttrackingbean.setText("No activities found");
                txttracking3 = (TextView)findViewById(R.id.textView12);
                txttracking3.setText("No activities found");

            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error encountered.",
                    Toast.LENGTH_LONG);
        }

        cursor = null;

        try {
            cursor = dBHelper.getStudentBalancing(dBHelper, studId);
            if (cursor.moveToFirst()) {
                do {
                    eyeoright = (TextView)findViewById(R.id.textView18);
                    eyeoright.setText(cursor.getString(2));
                    eyeoleft = (TextView)findViewById(R.id.textView19);
                    eyeoleft.setText(cursor.getString(3));
                    eyecright = (TextView)findViewById(R.id.textView20);
                    eyecright.setText(cursor.getString(4));
                    eyecleft = (TextView)findViewById(R.id.textView21);
                    eyecleft.setText(cursor.getString(5));

                } while (cursor.moveToNext());
            }
            else{
                eyeoright = (TextView)findViewById(R.id.textView18);
                eyeoright.setText("No activities found");
                eyeoleft = (TextView)findViewById(R.id.textView19);
                eyeoleft.setText("No activities found");
                eyecright = (TextView)findViewById(R.id.textView20);
                eyecright.setText("No activities found");
                eyecleft = (TextView)findViewById(R.id.textView21);
                eyecleft.setText("No activities found");

            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error encountered.",
                    Toast.LENGTH_LONG);
        }

        cursor = null;

        try {
            cursor = dBHelper.getStudentteaming(dBHelper, studId);
            if (cursor.moveToFirst()) {
                do {
                    rnd1 = (TextView)findViewById(R.id.textViewrnd1text);
                    rnd1.setText(cursor.getString(2));
                    rnd2 = (TextView)findViewById(R.id.textView23);
                    rnd2.setText(cursor.getString(3));

                } while (cursor.moveToNext());
            }
            else{
                rnd1 = (TextView)findViewById(R.id.textViewrnd1text);
                rnd1.setText("No activities found");
                rnd2 = (TextView)findViewById(R.id.textView23);
                rnd2.setText("No activities found");

            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error encountered.",
                    Toast.LENGTH_LONG);
        }

        cursor = null;

        try {
            cursor = dBHelper.getStudentSkipping(dBHelper, studId);
            if (cursor.moveToFirst()) {
                do {
                    skipping = (TextView)findViewById(R.id.textView25);
                    skipping.setText(cursor.getString(2));


                } while (cursor.moveToNext());
            }
            else{
                skipping = (TextView)findViewById(R.id.textView25);
                skipping.setText("No activities found");

            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error encountered.",
                    Toast.LENGTH_LONG);
        }

        cursor = null;

        try {
            cursor = dBHelper.getStudentVD(dBHelper, studId);
            if (cursor.moveToFirst()) {
                do {
                    vd = (TextView)findViewById(R.id.textView27);
                    vd.setText(cursor.getString(2));


                } while (cursor.moveToNext());
            }
            else{
                vd = (TextView)findViewById(R.id.textView27);
                vd.setText("No activities found");

            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error encountered.",
                    Toast.LENGTH_LONG);
        }


        cursor = null;

        try {
            cursor = dBHelper.getStudentCrawling(dBHelper, studId);
            if (cursor.moveToFirst()) {
                do {
                    crawl = (TextView)findViewById(R.id.textView29);
                    crawl.setText(cursor.getString(2));


                } while (cursor.moveToNext());
            }
            else{
                crawl = (TextView)findViewById(R.id.textView29);
                crawl.setText("No activities found");

            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error encountered.",
                    Toast.LENGTH_LONG);
        }

        cursor = null;

        try {
            cursor = dBHelper.getStudentComments(dBHelper, studId);
            if (cursor.moveToFirst()) {
                do {
                    comments = (TextView)findViewById(R.id.textViewcmnts);
                    comments.setText(cursor.getString(2));


                } while (cursor.moveToNext());
            }
            else{
                comments = (TextView)findViewById(R.id.textViewcmnts);
                comments.setText("No activities found");

            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error encountered.",
                    Toast.LENGTH_LONG);
        }







    }

    public void backpage(View view) {
        clearall();
        Intent intent = new Intent(this, ReportType.class);
        intent.putExtra("TesterName",user_name);
        startActivity(intent);
    }

    public void cancelRegistration(View view) {
        clearall();
        Intent intent = new Intent(this, LoginSuccessActivity.class);
        intent.putExtra("TesterName",user_name);
        startActivity(intent);
    }

    public void clearall(){
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.clear();
        // editor.clear();
        editor.commit();

    }
}
